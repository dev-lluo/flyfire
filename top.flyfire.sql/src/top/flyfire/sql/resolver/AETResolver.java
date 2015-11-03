package top.flyfire.sql.resolver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import top.flyfire.base.ClassUtil;
import top.flyfire.base.FFContext;
import top.flyfire.base.LoopUtil;
import top.flyfire.base.ObjectUtil;
import top.flyfire.base.LoopUtil.Task;
import top.flyfire.base.PackageUtil;
import top.flyfire.base.StringUtil;
import top.flyfire.base.bytecode.FClass;
import top.flyfire.base.bytecode.FPackage;
import top.flyfire.base.kval.StringKVal;
import top.flyfire.sql.ann.Default;
import top.flyfire.sql.ann.Id;
import top.flyfire.sql.ann.Length;
import top.flyfire.sql.ann.Name;
import top.flyfire.sql.ann.NotNull;
import top.flyfire.sql.ann.Numeric;
import top.flyfire.sql.ann.Table;
import top.flyfire.sql.ann.Type;
import top.flyfire.sql.ann.Unique;

public class AETResolver {

	private static String buildName(Name name,Method method){
		if(ObjectUtil.notNull(name)&&StringUtil.notEmpty(name.value())){
			return name.value();
		}else{
			String defVal = (String) method.getDefaultValue();
			return FFContext.toGo(StringUtil.notEmpty(defVal), defVal, method.getName());
		}
	}

	private static String buildType(Type type, Method method) {
		if (ObjectUtil.notNull(type)) {
			return type.value().getSqlType();
		} else {

			throw new RuntimeException("SqlTypeNotPresentException@" + System.currentTimeMillis() + ":"
					+ method.getDeclaringClass().getSimpleName() + " property[" + method.getName()
					+ "] Type Not Present.");
		}
	}

	private static String buildLength(Length length, Method method) {
		if (ObjectUtil.notNull(length)) {
			return "(" + length.value() + ") ";
		} else {
			return "";
		}
	}

	private static String buildNumeric(Numeric numeric, Method method) {
		if (ObjectUtil.notNull(numeric)) {
			return "(" + numeric.precision() + "," + numeric.scale() + ") ";
		} else {
			return "";
		}
	}

	private static String buildConstraint(NotNull notNull, Unique unique) {
		StringBuffer buffer = new StringBuffer();
		if (ObjectUtil.notNull(notNull) && notNull.value()) {
			buffer.append("NOT NULL ");
		}
		if (ObjectUtil.notNull(unique) && unique.value()) {
			buffer.append("UNIQUE ");
		}
		return buffer.toString();
	}

	private static boolean buildId(Id id) {
		if (ObjectUtil.notNull(id) && id.value())
			return true;
		else
			return false;
	}

	private static String buildId(Set<String> idSet, Class<?> clzz) {
		if (idSet == null || idSet.isEmpty())
			throw new RuntimeException("IdNotPresentException@" + System.currentTimeMillis() + ":"
					+ clzz.getSimpleName() + " Id Not Present.");
		StringBuilder builder = new StringBuilder("PRIMARY KEY( ");
		for (Iterator<String> i = idSet.iterator(); i.hasNext();) {
			builder.append(i.next());
			builder.append(" ,");
		}
		return builder.replace(builder.length() - 1, builder.length(), ")").toString();
	}
	
	private static String buildDefVal(Default defVal,Method method){
		if(ObjectUtil.notNull(defVal)&&StringUtil.notEmpty(defVal.value())){
			return "this."+method.getName()+" = " +defVal.value()+";";
		}else{
			return "";
		}
	}

	public static void resolve(String annPckg, String targetPckg) {
		try {
			List<String> annList = PackageUtil.getClassName(annPckg);
			FPackage pckg = new FPackage(targetPckg);
			for (Iterator<String> i = annList.iterator(); i.hasNext();) {
				Class<?> clzz = Class.forName(i.next());
				Table table = clzz.getAnnotation(Table.class);
				if (table == null || !Annotation.class.isAssignableFrom(clzz))
					continue;

				Method[] methods = clzz.getDeclaredMethods();
				
				final FClass fClass = pckg.fclass(table.face());
				final Set<String> idSet = new HashSet<String>();
				final StringBuilder constructorBuilder = new StringBuilder("public "+table.face()+"() { super();");
				final StringBuilder sqlBuilder = new StringBuilder("CREATE TABLE " + table.name() + " (");
				final StringKVal[] mv = new StringKVal[methods.length];
				
				

				LoopUtil.run(methods, new Task<Method>() {

					@Override
					public void exec(Method t, int i) {
						// TODO Auto-generated method stub
						String baseName = t.getName();
						
						StringBuilder builder = new StringBuilder();
						Name name = t.getAnnotation(Name.class);
						String sqlName = buildName(name,t);
						builder.append(sqlName);
						builder.append(" ");
						
						mv[i] = new StringKVal(baseName, sqlName);

						Type type = t.getAnnotation(Type.class);
						builder.append(buildType(type, t));
						builder.append(" ");

						Length length = t.getAnnotation(Length.class);
						builder.append(buildLength(length, t));

						Numeric numeric = t.getAnnotation(Numeric.class);
						builder.append(buildNumeric(numeric, t));

						NotNull notNull = t.getAnnotation(NotNull.class);
						Unique unique = t.getAnnotation(Unique.class);
						builder.append(buildConstraint(notNull, unique));

						Default defaultVal = t.getAnnotation(Default.class);
						constructorBuilder.append(buildDefVal(defaultVal, t));
						
						Id id = t.getAnnotation(Id.class);
						if (buildId(id))
							idSet.add(sqlName);

						fClass.field(ClassUtil.get(type.value().getType()), baseName).$();

						sqlBuilder.append((builder));
						sqlBuilder.append(',');
					}

				});
				sqlBuilder.append(buildId(idSet, clzz));
				sqlBuilder.append(')');
				constructorBuilder.append("}");
				System.out.println(constructorBuilder);
				fClass.insertConstructor(constructorBuilder.toString());
				fClass.annotation(clzz.getName(),mv);
				fClass.insertField("public static final " + clzz.getName() + " TABLE = " + targetPckg + "."
						+ table.face() + ".class.getAnnotation(" + clzz.getName() + ".class);");
				System.out.println(sqlBuilder);
				i.remove();
			}

			pckg.toJar("entity");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

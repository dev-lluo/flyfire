package top.flyfire.sql.resolver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import top.flyfire.base.LoopUtil;
import top.flyfire.base.ObjectUtil;
import top.flyfire.base.LoopUtil.Task;
import top.flyfire.base.PackageUtil;
import top.flyfire.base.StringUtil;
import top.flyfire.base.bytecode.FPackage;
import top.flyfire.sql.ann.Default;
import top.flyfire.sql.ann.Id;
import top.flyfire.sql.ann.Length;
import top.flyfire.sql.ann.Name;
import top.flyfire.sql.ann.NotNull;
import top.flyfire.sql.ann.Table;
import top.flyfire.sql.ann.Type;
import top.flyfire.sql.ann.Unique;
public class AETResolver {
	
	private static String buildName(Name name,Method method){
		if(ObjectUtil.notNull(name)&&StringUtil.notEmpty(name.value())){
			return name.value();
		}else{
			return method.getName();
		}
	}
	
	public static void resolve(String annPckg,String targetPckg){
		try{
			List<String> annList = PackageUtil.getClassName(annPckg);
			FPackage pckg = new FPackage(targetPckg);
			for(Iterator<String> i = annList.iterator();i.hasNext();){
				Class<?> clzz = Class.forName(i.next());
				Table table = clzz.getAnnotation(Table.class);
				if(table==null)continue;
				pckg.fclass(table.face());
				
				System.out.println(table.name()+"-->");
				
				if(Annotation.class.isAssignableFrom(clzz)){
					Method[] methods = clzz.getDeclaredMethods();
					LoopUtil.run(methods, new Task<Method>(){

						@Override
						public void exec(Method t, int i) {
							// TODO Auto-generated method stub
							
							Name name = t.getAnnotation(Name.class);
							Type type = t.getAnnotation(Type.class);
							Length length = t.getAnnotation(Length.class);
							NotNull notNull = t.getAnnotation(NotNull.class);
							Unique unique = t.getAnnotation(Unique.class);
							Default defaultVal = t.getAnnotation(Default.class);
							Id id = t.getAnnotation(Id.class);
						}
						
					});
				}
				i.remove();
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}

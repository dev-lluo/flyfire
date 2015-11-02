package top.flyfire.base;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.Descriptor;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.BooleanMemberValue;
import javassist.bytecode.annotation.ByteMemberValue;
import javassist.bytecode.annotation.CharMemberValue;
import javassist.bytecode.annotation.ClassMemberValue;
import javassist.bytecode.annotation.DoubleMemberValue;
import javassist.bytecode.annotation.EnumMemberValue;
import javassist.bytecode.annotation.FloatMemberValue;
import javassist.bytecode.annotation.IntegerMemberValue;
import javassist.bytecode.annotation.LongMemberValue;
import javassist.bytecode.annotation.MemberValue;
import javassist.bytecode.annotation.ShortMemberValue;
import javassist.bytecode.annotation.StringMemberValue;
import top.flyfire.base.kval.StringKVal;

public class ClassUtil {

	public final static String PATH = FFContext.PATH+"class.path"+FFContext.URL_SEPARATOR;
	
	public final static String JARPATH = FFContext.PATH+"jar.path"+FFContext.URL_SEPARATOR;
	
	static{
		File file = new File(ClassUtil.PATH);
		if(!file.exists()||file.isFile()){
			file.mkdirs();
		}
		File file2 = new File(ClassUtil.JARPATH);
		if(!file2.exists()||file2.isFile()){
			file2.mkdirs();
		}
	}

	private static ClassPool pool = ClassPool.getDefault();

	public static CtClass get(String name) {
		try {
			return pool.get(name);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public static CtClass create(String name) {
		CtClass clzz = ClassUtil.pool.makeClass(name);
		return clzz;

	}

	public static AnnotationsAttribute buildAnnotation(ConstPool cp, String annotationPath,
			StringKVal...memberValues) {
		AnnotationsAttribute attr = new AnnotationsAttribute(cp, AnnotationsAttribute.visibleTag);
		try {
			Annotation annotation = new Annotation(cp, ClassUtil.get(annotationPath));
			for (int i = 0; i<memberValues.length;i++) {
				StringKVal entry = memberValues[i];
				annotation.addMemberValue(entry.key(), ClassUtil.createMemberValue(cp, entry.val()));
			}
			attr.addAnnotation(annotation);
			return attr;
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	private static MemberValue createMemberValue(ConstPool cp, Object val) {
		if (val instanceof Integer) {
			return new IntegerMemberValue(cp, (int) val);
		} else if (val instanceof Double) {
			return new DoubleMemberValue((double) val, cp);
		} else if (val instanceof Character) {
			return new CharMemberValue((char) val, cp);
		} else if (val instanceof Float) {
			return new FloatMemberValue((float) val, cp);
		} else if (val instanceof Byte) {
			return new ByteMemberValue((byte) val, cp);
		} else if (val instanceof Short) {
			return new ShortMemberValue((short) val, cp);
		} else if (val instanceof Long) {
			return new LongMemberValue((long) val, cp);
		} else if (val instanceof Boolean) {
			return new BooleanMemberValue((boolean) val, cp);
		} else if (val instanceof Class) {
			return new ClassMemberValue(((Class) val).getName(), cp);
		} else if(val instanceof Enum){
			return new EnumMemberValue(cp.addUtf8Info(Descriptor.of(val.getClass().getName())), cp.addUtf8Info(((Enum) val).name()), cp);
		} else {
			return new StringMemberValue(val.toString(), cp);
		}
	}

	public static void addMethod(CtClass clzz, String src) {
		try {
			CtMethod method = CtNewMethod.make(src, clzz);
			clzz.addMethod(method);
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public static CtMethod buildMethod(CtClass clzz, CtClass retType, String name, CtClass[] params, String content,
			boolean isPrivate, boolean isStatic) {

		CtMethod method = new CtMethod(retType, name, params, clzz);
		try {
			method.setBody(content);
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		if (isPrivate) {
			method.setModifiers(Modifier.PRIVATE);
		} else {
			method.setModifiers(Modifier.PUBLIC);
		}
		if (isStatic) {
			method.setModifiers(Modifier.STATIC);
		}
		return method;
	}
	
	

	public static void addFieldWithGetterAndSetter(CtClass clzz, CtClass type, String name) {
		CtField field;
		try {
			field = new CtField(type, name, clzz);
			field.setModifiers(Modifier.PRIVATE);
			name = StringUtil.upperFirst(name);
			CtMethod getter = CtNewMethod.getter("get" + name, field);
			CtMethod setter = CtNewMethod.setter("set" + name, field);
			clzz.addField(field);
			clzz.addMethod(getter);
			clzz.addMethod(setter);
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public static CtField buildPrivateField(CtClass clzz, CtClass type, String name) {
		CtField field;
		try {
			field = new CtField(type, name, clzz);
			field.setModifiers(Modifier.PRIVATE);
			return field;
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public static CtField buildField(CtClass clzz, CtClass type, String name, boolean isPublic, boolean isStatic) {
		CtField field;
		try {
			field = new CtField(type, name, clzz);
			if (isPublic) {
				field.setModifiers(Modifier.PUBLIC);
			} else {
				field.setModifiers(Modifier.PRIVATE);
			}
			if (isStatic) {
				field.setModifiers(Modifier.STATIC);
			}
			return field;
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public static void addSetter(CtClass clzz, CtField field, String name) {
		name = StringUtil.upperFirst(name);
		try {
			CtMethod setter = CtNewMethod.setter("set" + name, field);
			clzz.addMethod(setter);
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public static void addGetter(CtClass clzz, CtField field, String name) {
		name = StringUtil.upperFirst(name);
		try {
			CtMethod getter = CtNewMethod.getter("get" + name, field);
			clzz.addMethod(getter);
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public static Class<?> flush(CtClass clzz) {
		try {

			return clzz.toClass();
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} finally {
			clzz.detach();
		}
	}

	public static Class<?> flush(CtClass clzz, boolean writeFile) {
		if (writeFile) {
			try {
				clzz.writeFile(ClassUtil.PATH);
				return clzz.toClass();
			} catch (CannotCompileException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			} finally {
				clzz.detach();
			}
		} else {
			return ClassUtil.flush(clzz);
		}
	}

}

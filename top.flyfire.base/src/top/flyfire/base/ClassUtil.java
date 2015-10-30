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
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.BooleanMemberValue;
import javassist.bytecode.annotation.ByteMemberValue;
import javassist.bytecode.annotation.CharMemberValue;
import javassist.bytecode.annotation.ClassMemberValue;
import javassist.bytecode.annotation.DoubleMemberValue;
import javassist.bytecode.annotation.FloatMemberValue;
import javassist.bytecode.annotation.IntegerMemberValue;
import javassist.bytecode.annotation.LongMemberValue;
import javassist.bytecode.annotation.MemberValue;
import javassist.bytecode.annotation.ShortMemberValue;
import javassist.bytecode.annotation.StringMemberValue;

public class ClassUtil {

	public final static String PATH = FFContext.PATH+"class.path"+File.separator;

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
			Map<String, Object> memberValue) {
		AnnotationsAttribute attr = new AnnotationsAttribute(cp, AnnotationsAttribute.visibleTag);
		try {
			Annotation annotation = new Annotation(cp, ClassUtil.get(annotationPath));
			Set<Entry<String, Object>> set = memberValue.entrySet();
			for (Iterator<Entry<String, Object>> i = set.iterator(); i.hasNext();) {
				Entry<String, Object> entry = i.next();
				annotation.addMemberValue(entry.getKey(), ClassUtil.createMemberValue(cp, entry.getValue()));
				i.remove();
			}
			attr.addAnnotation(annotation);
			return attr;
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

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

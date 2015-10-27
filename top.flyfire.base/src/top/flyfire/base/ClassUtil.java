package top.flyfire.base;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.CtPrimitiveType;
import javassist.Modifier;

public class ClassUtil {
	private static ClassPool pool = ClassPool.getDefault();
	
	public CtClass create(String name){
		return ClassUtil.pool.makeClass(name);
	}
	
	public void addPublicMethod(CtClass clzz,String src){
		try {
			CtMethod method = CtNewMethod.make(src, clzz);
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	public void addField(CtClass clzz,CtClass type,String name){
		CtField field;
		try {
			field = new CtField(type, name, clzz);
			field.setModifiers(Modifier.PRIVATE);
			name = StringUtil.upperFirst(name);
			CtMethod getter = CtNewMethod.getter("get"+name, field);
			CtMethod setter = CtNewMethod.setter("set"+name, field);
			clzz.addField(field);
			clzz.addMethod(getter);
			clzz.addMethod(setter);
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	public void flush(CtClass clzz){
		try {
			clzz.toClass();
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	public void flush(CtClass clzz,boolean writeFile){
		
	}
}

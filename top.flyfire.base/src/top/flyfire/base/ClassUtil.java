package top.flyfire.base;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import top.flyfire.base.ffclass.FPackage;

public class ClassUtil {
	
	private static String path = FFContext.PATH;
	
	private static ClassPool pool = ClassPool.getDefault();
	
	
	public static String getPath(){
		return ClassUtil.path;
	}
	
	public static CtClass get(String name){
		try {
			return pool.get(name);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	public static CtClass create(String name){
		return ClassUtil.pool.makeClass(name);
	}
	
	public static void addMethod(CtClass clzz,String src){
		try {
			CtMethod method = CtNewMethod.make(src, clzz);
			clzz.addMethod(method);
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	public static void addField(CtClass clzz,CtClass type,String name){
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
	
	public static Class<?> flush(CtClass clzz){
		try {
			
			return clzz.toClass();
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	public static Class<?> flush(CtClass clzz,boolean writeFile){
		if(writeFile){
			try {
				clzz.writeFile(ClassUtil.path);
				return clzz.toClass();
			} catch (CannotCompileException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}
		}else{
			return ClassUtil.flush(clzz);
		}
	}
	
	public static void main(String[] args) throws IOException  {
		FPackage fPackage = new FPackage("top.flyfire");
		fPackage.fclass("Class$A")
		.field(ClassUtil.get("java.lang.String"), "name")
		.method("public void show(){System.out.println(this.name);}");
		
		fPackage.toJar("entity");
	}
}

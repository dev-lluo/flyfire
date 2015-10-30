package top.flyfire.base.bytecode;

import java.util.Map;

import javassist.CtClass;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import top.flyfire.base.ClassUtil;

public class FClass implements FBehavior<FClass> {
	private final CtClass clCtClass;
	private final ClassFile classFile;
	private final ConstPool constPool;
	public  FClass(String name) {
		this.clCtClass = ClassUtil.create(name);
		this.classFile = this.clCtClass.getClassFile();
		this.constPool = this.classFile.getConstPool();
	}
	
	public FMethod method(String name){
		return new FMethod(this,this.clCtClass,name);
	}
	
	public FField field(CtClass type,String name){
		return new FField(this, this.clCtClass, type, name);
	}
	
	@Override
	public FClass flush(){
		ClassUtil.flush(this.clCtClass);
		return this;
	}
	
	public FClass flush(boolean writeFile){
		Class<?> clzz = ClassUtil.flush(this.clCtClass, writeFile);
		return this;
	}



	@Override
	public FClass annotation(String annotationPath, Map<String, Object> memberValue) {
		// TODO Auto-generated method stub
		this.classFile.addAttribute(ClassUtil.buildAnnotation(constPool, annotationPath, memberValue));
		return this;
	}
}

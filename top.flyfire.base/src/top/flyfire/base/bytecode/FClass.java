package top.flyfire.base.bytecode;


import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import top.flyfire.base.ClassUtil;
import top.flyfire.base.kval.StringKVal;

public class FClass implements FBehavior<FClass> {
	
	
	private final CtClass clCtClass;
	private final ClassFile classFile;
	private final ConstPool constPool;
	public  FClass(String name) {
		this.clCtClass = ClassUtil.create(name);
		this.classFile = this.clCtClass.getClassFile();
		this.constPool = this.classFile.getConstPool();
	}
	
	public  FClass(String name,String superName) {
		this.clCtClass = ClassUtil.create(name);
		try {
			this.clCtClass.setSuperclass(ClassUtil.get(superName));
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		this.classFile = this.clCtClass.getClassFile();
		this.constPool = this.classFile.getConstPool();
	}
	
	public FMethod method(String name){
		return new FMethod(this,this.clCtClass,name);
	}
	
	public FField field(CtClass type,String name){
		return new FField(this, this.clCtClass, type, name);
	}
	
	public void insertField(String src){
		ClassUtil.addFiled(this.clCtClass, src);
	}
	
	public void insertConstructor(String src){
		ClassUtil.addConstructor(this.clCtClass, src);
	}
	
	public void insertMethod(String src){
		ClassUtil.addMethod(this.clCtClass, src);
	}
	
	public void logicalMethod(String name,String src){
		ClassUtil.logicalMethod(this.clCtClass, name, src);
	}
	
	@Override
	public FClass flush(){
		ClassUtil.flush(this.clCtClass);
		return this;
	}
	
	public FClass flush(boolean writeFile){
		ClassUtil.flush(this.clCtClass, writeFile);
		return this;
	}



	@Override
	public FClass annotation(String annotationPath, StringKVal...memberValues) {
		// TODO Auto-generated method stub
		this.classFile.addAttribute(ClassUtil.buildAnnotation(constPool, annotationPath, memberValues));
		return this;
	}
}

package top.flyfire.base.ffclass;

import javassist.CtClass;
import top.flyfire.base.ClassUtil;

public class FClass {
	private final CtClass clCtClass;
	public  FClass(String name) {
		this.clCtClass = ClassUtil.create(name);
	}
	
	public FClass method(String src){
		ClassUtil.addMethod(this.clCtClass, src);
		return this;
	}
	
	public FClass field(CtClass type,String name){
		ClassUtil.addField(this.clCtClass, type, name);
		return this;
	}
	
	public FClass flush(){
		ClassUtil.flush(this.clCtClass);
		return this;
	}
	
	public FClass flush(boolean writeFile){
		Class<?> clzz = ClassUtil.flush(this.clCtClass, writeFile);
		return this;
	}
}

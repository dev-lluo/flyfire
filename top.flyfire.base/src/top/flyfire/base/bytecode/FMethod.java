package top.flyfire.base.bytecode;


import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.ConstPool;
import javassist.bytecode.MethodInfo;
import top.flyfire.base.ClassUtil;
import top.flyfire.base.kval.StringKVal;

public class FMethod implements FBehavior<FMethod> {
	
	private boolean isPrivate = false;
	
	private boolean isStatic = false;
	
	private boolean flushed = false;
	
	private CtMethod method;
	
	private MethodInfo methodInfo;
	
	private ConstPool cp;
	
	private String name;
	
	private CtClass source;
	
	private CtClass[] params;
	
	private CtClass retType = CtClass.voidType;
	
	private StringBuffer content = new StringBuffer("");
	
	public FMethod params(CtClass...params){
		this.params = params;
		return this;
	}
	
	public FMethod isPrivate(boolean isPrivate){
		this.isPrivate = isPrivate;
		return this;
	}
	
	public FMethod isStatic(boolean isStatic){
		this.isStatic = isStatic;
		return this;
	}
	
	
	public FMethod(FClass $,CtClass clzz,String name){
		this.$ = $;
		this.source = clzz;
		this.name = name;
	}
	
	public FMethod bodyAppend(String line){
		content.append(line);
		content.append("\r\n");
		return this;
	}
	
	private FClass $;
	

	@Override
	public FMethod flush() {
		// TODO Auto-generated method stub
		this.content = new StringBuffer("{").append(this.content).append("}");
		this.method = ClassUtil.buildMethod(this.source, this.retType, this.name, this.params, this.content.toString(), this.isPrivate, this.isStatic);
		try {
			this.source.addMethod(method);
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		this.methodInfo = this.method.getMethodInfo();
		this.cp = this.methodInfo.getConstPool();
		this.flushed = true;
		return this;
	}

	@Override
	public FMethod annotation(String annotationPath,StringKVal...memberValues) {
		// TODO Auto-generated method stub
		if(!this.flushed){
			this.flush();
		}
		this.methodInfo.addAttribute(ClassUtil.buildAnnotation(this.cp, annotationPath, memberValues));
		return this;
	}
	
	public FClass $(){
		if(!this.flushed){
			this.flush();
		}
		return this.$;
	}

}

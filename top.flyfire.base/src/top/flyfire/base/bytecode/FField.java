package top.flyfire.base.bytecode;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtField;
import javassist.bytecode.ConstPool;
import javassist.bytecode.FieldInfo;
import top.flyfire.base.ClassUtil;
import top.flyfire.base.kval.StringKVal;

public class FField implements FBehavior<FField> {
	
	private boolean isPublic = false;
	
	private boolean isStatic = false;
	
	private boolean withGetter = true;
	
	private boolean withSetter = true;
	
	private boolean flushed = false;
	
	private FClass $;
	
	private CtClass source;
	
	private CtClass type;
	
	private String name;
	
	private CtField field;
	
	private FieldInfo fieldInfo;
	
	private ConstPool cp;
	
	public FField isPublic(boolean isPublic){
		this.isPublic = isPublic;
		return this;
	}
	
	public FField isStatic(boolean isStatic){
		this.isStatic = isStatic;
		return this;
	}
	
	public FField withGetter(boolean withGetter){
		this.withGetter = withGetter;
		return this;
	}
	
	public FField widthSetter(boolean withSetter){
		this.withSetter = withSetter;
		return this;
	}
	
	public FField(FClass $,CtClass clzz,CtClass type,String name) {
		// TODO Auto-generated constructor stub
		this.$ = $;
		this.source = clzz;
		this.type = type;
		this.name= name;
	}

	@Override
	public FField flush() {
		// TODO Auto-generated method stub
		this.field = ClassUtil.buildField(this.source, this.type, this.name ,this.isPublic,this.isStatic);
		if(withGetter){
			ClassUtil.addGetter(this.source, this.field, this.name);
		}
		if(withSetter){
			ClassUtil.addSetter(this.source, this.field, this.name);
		}
		try {
			this.source.addField(this.field);
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		this.fieldInfo = this.field.getFieldInfo();
		this.cp = this.fieldInfo.getConstPool();
		this.flushed = true;
		return this;
	}

	@Override
	public FField annotation(String annotationPath,StringKVal...memberValues) {
		// TODO Auto-generated method stub
		if(!this.flushed){
			this.flush();
		}
		this.fieldInfo.addAttribute(ClassUtil.buildAnnotation(this.cp, annotationPath, memberValues));
		return this;
	}
	
	public FClass $(){
		if(!this.flushed){
			this.flush();
		}
		return this.$;
	}
	
	

}

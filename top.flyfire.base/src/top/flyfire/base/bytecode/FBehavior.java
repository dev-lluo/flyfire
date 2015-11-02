package top.flyfire.base.bytecode;


import top.flyfire.base.kval.StringKVal;

public interface FBehavior <R>{
	R flush();
	R annotation(String annotationPath,StringKVal...memberValues);
}

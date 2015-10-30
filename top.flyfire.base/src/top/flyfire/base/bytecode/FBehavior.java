package top.flyfire.base.bytecode;

import java.util.Map;

public interface FBehavior <R>{
	R flush();
	R annotation(String annotationPath,Map<String,Object> memberValue);
}

package top.flyfire.base;

import top.flyfire.base.LoopUtil.Task;

public class ObjectUtil {
	public static boolean notNull(Object obj){
		return !isNull(obj);
	}
	
	public static boolean isNull(Object obj){
		return obj==null;
	}
	
}

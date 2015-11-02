package top.flyfire.base;

public class StringUtil {
	
	public static final String EMPTY = "";
	
	public static String upperFirst(String str){
		StringBuffer buffer = new StringBuffer(str);
		return buffer.replace(0, 1, buffer.substring(0, 1).toUpperCase()).toString();
	}
	
	public static boolean isEmpty(String str){
		return ObjectUtil.isNull(str)||str.length()==0;
	}
	
	public static boolean notEmpty(String str){
		return !isEmpty(str);
	}
}

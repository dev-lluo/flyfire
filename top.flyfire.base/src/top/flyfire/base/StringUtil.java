package top.flyfire.base;

public class StringUtil {
	public static String upperFirst(String str){
		StringBuffer buffer = new StringBuffer(str);
		return buffer.replace(0, 1, buffer.substring(0, 1).toUpperCase()).toString();
	}
}

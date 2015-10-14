package flyfire.root.util;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@SuppressWarnings("all")
public class JsonC {
	public static String convert(Object obj){
			if(obj==null){
				return "null";
			}
			
			Class<?> type = obj.getClass();
			if(isVType(type)){
				return VJSON(obj);
			}else if(isRBaseType(type)){
				return RBseJSON(obj);
			}else if(obj.getClass().isArray()){
				return ArrayJSON(obj);
			}else if(obj instanceof java.util.Collection){
				return ListJSON(obj);
			}else if(obj instanceof java.util.Map){
				return MapJSON(obj);
			}else{
				try {
					return ObjectJSON(obj);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					throw new RuntimeException(e);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					throw new RuntimeException(e);
				}
			}

		
	}
	
	private static boolean isVType(Class<?> type){
		return type.isPrimitive()
				|Boolean.class.isAssignableFrom(type)
				|Number.class.isAssignableFrom(type);
	}
	
	private static boolean isRBaseType(Class<?> type){
		return Date.class.isAssignableFrom(type)
		|String.class.isAssignableFrom(type);
	}
	
	private static String VJSON(Object obj){
		return obj.toString();
	}
	
	private static String RBseJSON(Object obj){
		return "\""+obj.toString()+"\"";
	}
	
	private static String ObjectJSON(Object obj) throws IllegalArgumentException, IllegalAccessException {
		Class<?> clss =obj.getClass();
		StringBuffer sb = new StringBuffer("{ ");
		Field[] fieldArr = clss.getDeclaredFields();
		for(int i = 0;i<fieldArr.length;i++){
			Field field = fieldArr[i];
			field.setAccessible(true);
			sb.append("\"");
			sb.append(field.getName());
			sb.append("\"");
			sb.append(":");
			sb.append(JsonC.convert(field.get(obj)));
			sb.append(",");
		}
		sb.replace(sb.length()-1, sb.length(), "}");
		return sb.toString();
	}
	private static String ArrayJSON(Object obj){
		StringBuffer sb = new StringBuffer("[");
		Object[] objs = (Object[])obj;
		for(Object o : objs){
			sb.append(JsonC.convert(o)+" ,");
		}
		sb.replace(sb.length()-1, sb.length(), "]");
		return sb.toString();
	}
	private static String MapJSON(Object obj){
		StringBuffer sb = new StringBuffer("{");
		Map objs = (Map)obj;
		Set<Entry> entrySet = objs.entrySet();
		for(Map.Entry entry : entrySet){
			sb.append(entry.getKey());
			sb.append(":");
			sb.append(JsonC.convert(entry.getValue()));
			sb.append(",");
		}
		sb.replace(sb.length()-1, sb.length(), "}");
		return sb.toString();
	}
	private static String ListJSON(Object obj){
		StringBuffer sb = new StringBuffer("[");
		List objs = (List)obj;
		for(Object o : objs){
			sb.append(JsonC.convert(o)+" ,");
		}
		sb.replace(sb.length()-1, sb.length(), "]");
		return sb.toString();
	}

}

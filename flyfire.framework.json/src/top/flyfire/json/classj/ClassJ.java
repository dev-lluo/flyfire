package top.flyfire.json.classj;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import top.flyfire.json.jsonc.JsonC;

@SuppressWarnings("all")
public class ClassJ {
	
	private static final int defaultDeep = 3;
	
	private static final String defaultSkip = "$[skip]$";
	
	public static String convert(Object obj){
			return convert(obj,ClassJ.defaultDeep);
	}
	
	public static String convert(Object obj,int deep){
		if(obj==null){
			return "null";
		}else if(deep==0){
			return ClassJ.defaultSkip;
		}
		
		Class<?> type = obj.getClass();
		if(isVType(type)){
			return VJSON(obj,deep);
		}else if(isRBaseType(type)){
			return RBseJSON(obj,deep);
		}else if(obj.getClass().isArray()){
			return ArrayJSON(obj,deep);
		}else if(obj instanceof java.util.Collection){
			return ListJSON(obj,deep);
		}else if(obj instanceof java.util.Map){
			return MapJSON(obj,deep);
		}else{
			try {
				return ObjectJSON(obj,deep);
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
	
	private static boolean isVArrType(Object obj){
		return obj instanceof int[]
				|obj instanceof double[]
				|obj instanceof float[]
				|obj instanceof byte[]
				|obj instanceof long[]
				|obj instanceof short[]
				|obj instanceof boolean[]
				|obj instanceof char[];
	}
	
	private static String VJSON(Object obj,int deep){
		deep--;
		return obj.toString();
	}
	
	private static String RBseJSON(Object obj,int deep){
		deep--;
		return "\""+obj.toString()+"\"";
	}
	
	private static String ObjectJSON(Object obj,int deep) throws IllegalArgumentException, IllegalAccessException {
		deep--;
		Class<?> clss =obj.getClass();
		StringBuffer sb = new StringBuffer("{ ");
		Field[] fieldArr = clss.getDeclaredFields();
		for(int i = 0;i<fieldArr.length;i++){
			Field field = fieldArr[i];
			field.setAccessible(true);
			String temp = ClassJ.convert(field.get(obj),deep);
			if(temp==ClassJ.defaultSkip)continue;
			sb.append("\"");
			sb.append(field.getName());
			sb.append("\"");
			sb.append(":");
			sb.append(temp);
			sb.append(",");
		}
		while(clss.getSuperclass()!=Object.class&&clss!=Object.class){
			clss = clss.getSuperclass();
			fieldArr = clss.getDeclaredFields();
			for(int i = 0;i<fieldArr.length;i++){
				Field field = fieldArr[i];
				field.setAccessible(true);
				String temp = ClassJ.convert(field.get(obj),deep);
				if(temp==ClassJ.defaultSkip)continue;
				sb.append("\"");
				sb.append(field.getName());
				sb.append("\"");
				sb.append(":");
				sb.append(temp);
				sb.append(",");
			}
		}
		sb.replace(sb.length()-1, sb.length(), "}");
		return sb.toString();
	}
	private static String ArrayJSON(Object obj,int deep){
		deep--;
		if(ClassJ.convert("",deep)==ClassJ.defaultSkip)return ClassJ.defaultSkip;
		StringBuffer sb = new StringBuffer("[ ");
		for(int i = 0,len = Array.getLength(obj);i<len;i++){
			sb.append(ClassJ.convert(Array.get(obj, i),deep)+" ,");
		}
		sb.replace(sb.length()-1, sb.length(), "]");
		return sb.toString();
	}
	private static String MapJSON(Object obj,int deep){
		deep--;
		StringBuffer sb = new StringBuffer("{ ");
		Map objs = (Map)obj;
		Set<Entry> entrySet = objs.entrySet();
		for(Map.Entry entry : entrySet){
			String temp = ClassJ.convert(ClassJ.convert(entry.getValue(),deep),deep);
			if(temp==ClassJ.defaultSkip)continue;
			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\"");
			sb.append(":");
			sb.append(temp);
			sb.append(",");
		}
		sb.replace(sb.length()-1, sb.length(), "}");
		return sb.toString();
	}
	private static String ListJSON(Object obj,int deep){
		deep--;
		if(ClassJ.convert("",deep)==ClassJ.defaultSkip)return ClassJ.defaultSkip;
		StringBuffer sb = new StringBuffer("[ ");
		Collection objs = (Collection)obj;
		for(Object o : objs){
			sb.append(ClassJ.convert(o,deep)+" ,");
		}
		sb.replace(sb.length()-1, sb.length(), "]");
		return sb.toString();
	}

}

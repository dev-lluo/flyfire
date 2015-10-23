package top.flyfire.json.jsonc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonC {

	private static  Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
	
	public enum JType {
		ARR, KVL, STR, NUM
	}

	@SuppressWarnings("unchecked")
	public static <T> T convert(String json) {
		JType type = JsonC.dist(json);
		if(type==JType.KVL){
			Map<String,Object> result = JsonC.kvlReadAdapter(json);
			Set<Entry<String,Object>> set = result.entrySet();
			for(Iterator<Entry<String,Object>> i = set.iterator();i.hasNext();){
				Entry<String,Object> entry = i.next();
				entry.setValue(JsonC.convert(entry.getValue().toString()));
			}
			return (T) result;
		}else if(type==JType.ARR){
			List<Object> result = JsonC.arrReadAdapter(json);
			for(int i = 0,len = result.size();i<len;i++){
				Object obj = result.get(i);
				result.set(i, JsonC.convert(obj.toString()));
			}
			return (T) result;
		}else if(type==JType.STR){
			return (T) json;
		}else if(type==JType.NUM){
			return (T) new BigDecimal(json);
		}
		return null;
	}

	private static JType dist(String json) {
		if (json.startsWith("{") && json.endsWith("}")) {
			return JType.KVL;
		} else if (json.startsWith("[") && json.endsWith("]")) {
			return JType.ARR;
		} else if (json.startsWith("\"")&&json.endsWith("\"")) {
			return JType.STR;
		} else {
			Matcher isNum = pattern.matcher(json);
			if (isNum.matches()){
			    return JType.NUM; 
			}else{
				throw new UnknowJTypeException(json);
			}
		}
	}
	//[1,2,3,4,[1,2,3,4,5,[1,2,3,4,5]]]
	private static List<Object> arrReadAdapter(String json){
		List<Object> arrTemp = new ArrayList<Object>();
		StringBuffer temp = new StringBuffer();
		List<Character> fruitage = JsonC.husking(json);
		
		Character idf = null;
		for(int deep = 0;!fruitage.isEmpty();){
			
			char ch = fruitage.remove(0);
			
			if(ch=='"'&&idf==null){
				deep++;
				idf = ch;
			}else if((ch=='{'||ch=='[')&&idf==null){
				deep++;
			}else if(ch=='"'&&idf=='"'){
				deep--;
				idf = null;
			}else if((ch=='}'||ch==']')&&idf==null){
				deep--;
			}
			
			if(temp.length()==0&&ch==','){
				throw new UnknowJTypeException(json);
			}else if(ch!=','||deep!=0){
				temp.append(ch);
			}else{
				arrTemp.add(temp.toString());
				temp.delete(0, temp.length());
			}
			
			if(fruitage.size()==0){
				arrTemp.add(temp.toString());
				temp.delete(0, temp.length());
			}
		}
		
		return arrTemp;
	}
	
	private static Map<String,Object> kvlReadAdapter(String json){
		Map<String,Object> kvlTemp = new HashMap<String, Object>();
		StringBuffer temp = new StringBuffer();
		List<Character> fruitage = JsonC.husking(json);
		Character idf = null;
		Character prev = null;
		for(int deep = 0;!fruitage.isEmpty();){
			
			char ch = fruitage.remove(0);
			if(ch=='"'&&idf==null&&prev!='\\'){
				deep++;
				idf = ch;
			}else if((ch=='{'||ch=='[')&&idf==null){
				deep++;
			}else if(ch=='"'&&idf=='"'&&prev!='\\'){
				deep--;
				idf = null;
			}else if((ch=='}'||ch==']')&&idf==null){
				deep--;
			}
			
			if(temp.length()==0&&ch==','){
				throw new UnknowJTypeException(json);
			}else if(ch!=','||deep!=0){
				temp.append(ch);
			}else{
				String key = temp.substring(0,temp.indexOf(":"));
				String value = temp.substring(temp.indexOf(":")+1);
				kvlTemp.put(key,value);
				temp.delete(0, temp.length());
			}
			
			if(fruitage.size()==0){
				String key = temp.substring(0,temp.indexOf(":"));
				String value = temp.substring(temp.indexOf(":")+1);
				kvlTemp.put(key,value);
				temp.delete(0, temp.length());
			}
			prev = ch;
		}
		return kvlTemp;
	}
	
	private static List<Character> husking(String json){
		json = json.substring(1,json.length()-1);
		List<Character> rs = new ArrayList<Character>();
		for(int i = 0,len = json.length();i<len;i++){
			rs.add(json.charAt(i));
		}
		return rs;
	}
	
	
	
}

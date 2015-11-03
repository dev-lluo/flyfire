package top.flyfire.base;

import java.net.URL;
import java.sql.Date;

public class FFContext {
	
	public final static int BUFFER_SIZE = 1024;
	
	public final static char URL_SEPARATOR= '/';
	
	public final static ClassLoader LOADER = Thread.currentThread().getContextClassLoader();
	
	public final static URL URL = FFContext.LOADER.getResource("//");
	
	public final static String PATH = FFContext.URL.getPath();
	
	public final static String CST_NOW = "NOW";
	
	@SuppressWarnings("unchecked")
	public final static <T> T CSTResolve(String cst){
		if(CST_NOW==cst){
			return (T) new Date(System.currentTimeMillis());
		}
		return null;
	}
	
	public final static <T> T toGo(boolean expr,T t1,T t2){
		return expr?t1:t2;
	}
	
	public final static <T> T toGo(boolean expr,T t1,Throwable e) throws Throwable{
		if(expr)return t1;
		else throw e;
	}
	
	public final static <T> T toGo(boolean expr,T t1,RuntimeException e) {
		if(expr)return t1;
		else throw e;
	}
	
	@SuppressWarnings("unchecked")
	public final static <T1,T2,R> R toGo2(boolean expr,T1 t1,T2 t2){
		return (R) (expr?t1:t2);
	}
	
	
	
	
}

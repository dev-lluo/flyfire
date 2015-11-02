package top.flyfire.base;

import java.net.URL;

public class FFContext {
	
	public final static int BUFFER_SIZE = 1024;
	
	public final static char URL_SEPARATOR= '/';
	
	public final static ClassLoader LOADER = Thread.currentThread().getContextClassLoader();
	
	public final static URL URL = FFContext.LOADER.getResource("//");
	
	public final static String PATH = FFContext.URL.getPath();
	
}

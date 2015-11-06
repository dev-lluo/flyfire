package top.flyfire.base.bytecode;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName DeployJar
 * @Description 热加载Jar包
 * @Author weizhi2018
 * @Date 2013-8-30 上午08:59:27
 * 
 */

public class HotDeploy {
	// 后缀
	private final static String CLASS_SUFFIX = ".class";
	
	private final static String JAR_SUFFIX = ".jar";

	// 类加载器
	private ClassLoader classLoader;


	public void loadPath(String jarPath) {
		try {
			File jarFiles = new File(jarPath);
			if(jarFiles.exists()){
				List<URL> urlList = new ArrayList<URL>();
				this.loadPath(jarFiles, urlList);
				URL[] jarFilePathArr = new URL[urlList.size()];
				urlList.toArray(jarFilePathArr);
				classLoader = new URLClassLoader(jarFilePathArr,Thread.currentThread().getContextClassLoader());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private void loadPath(File jarPath,List<URL> urlList) throws MalformedURLException{
		URL url = jarPath.toURI().toURL();
		String jarName = jarPath.getName();
		if(jarPath.isFile()&&(jarName.endsWith(JAR_SUFFIX)||jarName.endsWith(CLASS_SUFFIX))){
			urlList.add(url);
		}else if(jarPath.isDirectory()){
			File[] chF = jarPath.listFiles();
			for(int i = 0 ;i<chF.length;i++){
				this.loadPath(chF[i],urlList);
			}
		}
	}



	@SuppressWarnings("unchecked")
	public <T> T newInstance(String clazzName) {
		if (this.classLoader == null) {
			return null;
		}
		Class<?> clazz = null;
		try {
			clazz = this.classLoader.loadClass(clazzName);
			Object obj = clazz.newInstance();
			return (T) obj;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (NoClassDefFoundError e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	public <T> Class<T> loadClass(String clazzName){
		if (this.classLoader == null) {
			return null;
		}
		Class<?> clazz = null;
		try {
			return (Class<T>) classLoader.loadClass(clazzName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
}


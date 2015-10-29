package top.flyfire.base;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 
 * @author Orange
 * package工具类
 *
 */
@SuppressWarnings("all")
public final class PackageUtil {
	/**
	 * 禁止外界实例化以及反射
	 */
	private PackageUtil(){
		throw new Error();
	}
	
	//注入包标识
    private static String idtt;
	
    /** 
     * 获取某包下（包括该包的所有子包）所有类 
     * @param packageName 包名 
     * @return 一组类的完整名称 
     */ 
    public static List<String> getClassName(String packageName) {  
        return getClassName(packageName, true);  
    }  
    
    /** 
     * 获取某包下所有类 
     * @param packageName 包名 
     * @param childPackage 是否遍历子包 
     * @return 一组类的完整名称 
     */  
    public static List<String> getClassName(String packageName, boolean childPackage) {  
        List<String> fileNames = null;  
        ClassLoader loader = FFContext.LOADER;  
        String packagePath = packageName.replace(".", "/");  
        idtt = packagePath.replace("/", "\\");
        URL url = loader.getResource(packagePath);  
        if (url != null) {  
            String type = url.getProtocol(); 
            String tempPath = url.getPath();
            tempPath = tempPath.replace("%20", " ");
            if (type.equals("file")) { 
                fileNames = getClassNameByFile(tempPath,  childPackage);  
            } else if (type.equals("jar")) {  
                fileNames = getClassNameByJar(tempPath, childPackage);  
            }  
        }   
        return fileNames;  
    }  
  
    
    
    
	 /** 
     * 从项目文件获取某包下所有类 
     * @param filePath 文件路径 
     * @param childPackage 是否遍历子包 
     * @return 一组类的完整名称 
     */  
    private static List<String> getClassNameByFile(String filePath,  boolean childPackage) {  
        List<String> myClassName = new ArrayList<String>();  
        File file = new File(filePath);  
        File[] childFiles = file.listFiles();  
        for (File childFile : childFiles) {  
            if (childFile.isDirectory()) {  
                if (childPackage) {  
                    myClassName.addAll(getClassNameByFile(childFile.getPath(),  childPackage));  
                }  
            } else {  
                String childFilePath = childFile.getPath();  
                if (childFilePath.endsWith(".class")) {
                    childFilePath = childFilePath.substring(childFilePath.lastIndexOf(idtt), childFilePath.lastIndexOf("."));  
                    childFilePath = childFilePath.replace("\\", ".");  
                    myClassName.add(childFilePath);  
                }  
            }  
        }  
  
        return myClassName;  
    }  
    
    /** 
     * 从jar获取某包下所有类 
     * @param jarPath jar文件路径 
     * @param childPackage 是否遍历子包 
     * @return 一组类的完整名称 
     */  
    private static List<String> getClassNameByJar(String jarPath, boolean childPackage) {  
        List<String> myClassName = new ArrayList<String>();  
        String[] jarInfo = jarPath.split("!");  
        String jarFilePath = jarInfo[0].substring(jarInfo[0].indexOf("/"));  
        String packagePath = jarInfo[1].substring(1);  
        try {  
            JarFile jarFile = new JarFile(jarFilePath);  
            Enumeration<JarEntry> entrys = jarFile.entries();  
            while (entrys.hasMoreElements()) {  
                JarEntry jarEntry = entrys.nextElement();  
                String entryName = jarEntry.getName();  
                if (entryName.endsWith(".class")) {  
                    if (childPackage) {  
                        if (entryName.startsWith(packagePath)) {  
                            entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));  
                            myClassName.add(entryName);  
                        }  
                    } else {  
                        int index = entryName.lastIndexOf("/");  
                        String myPackagePath;  
                        if (index != -1) {  
                            myPackagePath = entryName.substring(0, index);  
                        } else {  
                            myPackagePath = entryName;  
                        }  
                        if (myPackagePath.equals(packagePath)) {  
                            entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));  
                            myClassName.add(entryName);  
                        }  
                    }  
                }  
            }  
        } catch (Exception e) {  
           throw new RuntimeException(e);
        }  
        return myClassName;  
    }  
}

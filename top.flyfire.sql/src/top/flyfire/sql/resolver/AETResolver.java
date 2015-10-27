package top.flyfire.sql.resolver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import top.flyfire.base.PackageUtil;
public class AETResolver {
	public static void resolve(String annPckg,String targetPckg){
		try{
			List<String> annList = PackageUtil.getClassName(annPckg);
			for(Iterator<String> i = annList.iterator();i.hasNext();){
				Class<?> clzz = Class.forName(i.next());
				if(Annotation.class.isAssignableFrom(clzz)){
					Method[] methods = clzz.getDeclaredMethods();
					
				}
				i.remove();
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}

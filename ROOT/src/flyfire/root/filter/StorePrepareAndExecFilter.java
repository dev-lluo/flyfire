package flyfire.root.filter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import flyfire.root.context.FlyFire;
import flyfire.root.context.Store;
import flyfire.root.util.PackageUtil;

public class StorePrepareAndExecFilter implements Filter {

	private final Map<Class<? extends Store>,List<PropertyM>> storeMap = new HashMap<Class<? extends Store>,List<PropertyM>>(); 
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {
		// TODO Auto-generated method stub
		try{
			String storePckg = cfg.getInitParameter("store.pckg");
			if(storePckg!=null){
				List<String> classPathList = PackageUtil.getClassName(storePckg);
				for(Iterator<String> i = classPathList.iterator();i.hasNext();){
					Class<? extends Store> clzz = (Class<? extends Store>) Class.forName(i.next());
					if(Store.class.isAssignableFrom(clzz)){
						Method[] methods = clzz.getMethods();
						List<PropertyM> prList = new ArrayList<PropertyM>();
						for(int j = 0;j<methods.length;j++){
							String methodName = methods[j].getName();
							if(methodName.indexOf("set")==0){
								prList.add(new PropertyM(methodName.substring(methodName.indexOf("set")+3).toLowerCase(),methods[j]));
							}
						}
						storeMap.put(clzz,prList);
					}
				}
				FlyFire.$.print(storeMap);
			}else{
				throw new RuntimeException("store.pckg is null...");
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	
	class PropertyM{
		private String property;
		private Method method;
		public PropertyM(String property,Method method){
			this.property = property;
			this.method = method;
		}
		public void setter(Object obj,String val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
			this.method.invoke(obj, this.valueOf(val));
		}
		public boolean match(String property){
			return this.property.equals(property);
		}
		public Object[] valueOf(String val){
			return null;
		}
		
		private Integer iValOf(String val){
			return Integer.valueOf(val);
		}
		
		private Double dValOf(String val){
			return Double.valueOf(val);
		}
	}

}

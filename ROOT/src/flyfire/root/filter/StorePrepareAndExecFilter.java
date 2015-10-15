package flyfire.root.filter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
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
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
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
						FlyFire.$.setStoreMap(clzz, prList);
					}
				}
			}else{
				throw new RuntimeException("store.pckg is null...");
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	
	public class PropertyM{
		private String property;
		private Method method;
		private Class<?>[] pTypes;
		public PropertyM(String property,Method method){
			this.property = property;
			this.method = method;
			this.pTypes = method.getParameterTypes();
		}
		public void setter(Object obj,String val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
			this.method.invoke(obj, this.valueOf(val));
		}
		public boolean match(String property){
			return this.property.equals(property);
		}
		private Object[] valueOf(String val){
			Object[] vals = new Object[pTypes.length];
			if(pTypes.length==1){
				Class<?> pType = pTypes[0];
				if(Integer.class.isAssignableFrom(pType)){
					vals[0] = this.iValOf(val);
				}else if(Double.class.isAssignableFrom(pType)){
					vals[0] = this.dValOf(val);
				}else if(Float.class.isAssignableFrom(pType)){
					vals[0] = this.fValOf(val);
				}else if(Long.class.isAssignableFrom(pType)){
					vals[0] = this.lValOf(val);
				}else if(Short.class.isAssignableFrom(pType)){
					vals[0] = this.sValOf(val);
				}else if(BigDecimal.class.isAssignableFrom(pType)){
					vals[0] = this.bgValOf(val);
				}else if(Boolean.class.isAssignableFrom(pType)){
					vals[0] = this.bValOf(val);
				}else{
					vals[0] = pType.cast(val);
				}
			}
			return vals;
		}
		
		private Integer iValOf(String val){
			return Integer.valueOf(val);
		}
		
		private Double dValOf(String val){
			return Double.valueOf(val);
		}
		
		private Float fValOf(String val){
			return Float.valueOf(val);
		}
		
		private Boolean bValOf(String val){
			return Boolean.valueOf(val);
		}
		
		private Long lValOf(String val){
			return Long.valueOf(val);
		}
		private Short sValOf(String val){
			return sValOf(val);
		}
		private BigDecimal bgValOf(String val){
			return new BigDecimal(val);
		}
	}

}

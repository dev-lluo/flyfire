package flyfire.root.filter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import flyfire.root.context.FlyFire;
import flyfire.root.context.Store;
import flyfire.root.listener.SystemInitialListener.ExecCtrl;
import flyfire.root.util.JsonC;
import flyfire.root.util.PackageUtil;

public class StorePrepareAndExecFilter implements Filter {
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		try{
			HttpServletRequest htRequest = (HttpServletRequest) request;
			FlyFire.$.print(htRequest.getRequestURI());
			ExecCtrl ctrl = FlyFire.$.getExecStore(htRequest.getRequestURI());
			if(ctrl!=null){
				Class<?>[] clzzArr = ctrl.clzzArr();
				if(clzzArr!=null&&clzzArr.length>0){
					if (Store.class.isAssignableFrom(clzzArr[0])) {
						Map<String, PropertyM> pm = FlyFire.$.getStoreMap((Class<? extends Store>) clzzArr[0]);
						Object obj = clzzArr[0].getConstructor(new Class<?>[]{HttpServletRequest.class,HttpServletResponse.class}).newInstance(request,response);
						if(pm!=null){
							Set<Entry<String, PropertyM>> set = pm.entrySet();
							for(Iterator<Entry<String, PropertyM>> i = set.iterator();i.hasNext();){
								Entry<String, PropertyM> entry = i.next();
								entry.getValue().setter(obj, htRequest.getParameter(entry.getKey()));
							}
						}
						if(ctrl.isPost()&&!"POST".equals(htRequest.getMethod())){
							Map map = new HashMap<>();
							map.put("msg", "request method is get;but need post...");
							response.getWriter().write(JsonC.convert(map));
						}else{
							ctrl.exec(obj);
						}
						
					}else{
						if(ctrl.isPost()&&!"POST".equals(htRequest.getMethod())){
							Map map = new HashMap<>();
							map.put("msg", "request method is get;but need post...");
							response.getWriter().write(JsonC.convert(map));
						}else{
							ctrl.exec();
						}
					}
				}else{
					if(ctrl.isPost()&&!"POST".equals(htRequest.getMethod())){
						Map map = new HashMap<>();
						map.put("msg", "request method is get;but need post...");
						response.getWriter().write(JsonC.convert(map));
					}else{
						ctrl.exec();
					}
				}
			}else{
				chain.doFilter(request, response);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
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
						Map<String,PropertyM> prMap = new HashMap<String,PropertyM>();
						for(int j = 0;j<methods.length;j++){
							String methodName = methods[j].getName();
							if(methodName.indexOf("set")==0){
								String mName =  methodName.substring(methodName.indexOf("set")+3).toLowerCase();
								prMap.put(mName,new PropertyM(mName,methods[j]));
							}
						}
						FlyFire.$.setStoreMap(clzz, prMap);
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

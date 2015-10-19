package flyfire.root.listener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import flyfire.root.context.Exec;
import flyfire.root.context.FlyFire;
import flyfire.root.util.PackageUtil;

public class SystemInitialListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		String execPckg = event.getServletContext().getInitParameter("exec.pckg");
		if(execPckg!=null){
			initExec(PackageUtil.getClassName(execPckg));
		}else{
			throw new RuntimeException("exec.pckg is null...");
		}
	}
	
	private void initExec(List<String> pckgPath){
		try{
			for(Iterator<String> i = pckgPath.iterator();i.hasNext();){
				Class<?> clzz = Class.forName(i.next());
				Exec exec = clzz.getAnnotation(Exec.class);
				if(exec!=null){
					String bPath = "/"+exec.url();
					Method[] methods = clzz.getMethods();
					for(int j = 0;j<methods.length;j++){
						Method method = methods[j];
						Exec execM = method.getAnnotation(Exec.class);
						if(execM!=null){
							String cPath = bPath+"/"+execM.url();
							Class<?>[] clzzArr = method.getParameterTypes();
							if(exec.isPost()||execM.isPost()){
								FlyFire.$.setExecStore(cPath,new ExecCtrl(clzz.newInstance(), method,true ,clzzArr));
							}else{
								FlyFire.$.setExecStore(cPath,new ExecCtrl(clzz.newInstance(), method,false ,clzzArr));
							}
						}
					}
				}
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public class ExecCtrl{
		private final Object deal;
		private final Method method;
		private final boolean isPost;
		private final Class<?>[] clzzArr;
		public ExecCtrl(Object deal,Method method,boolean isPost,Class<?>[] clzzArr) {
			// TODO Auto-generated constructor stub
			this.deal = deal;
			this.method = method;
			this.isPost = isPost;
			this.clzzArr = clzzArr;
		}
		public void exec(Object...args){
			try {
				this.method.invoke(this.deal, args);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}
		}
		
		public Class<?>[] clzzArr() {
			return this.clzzArr;
		}
		
		public boolean isPost(){
			return this.isPost;
		}
	}

}

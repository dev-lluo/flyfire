package flyfire.root.listener;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import flyfire.root.context.Exec;
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
					String bPath = exec.url();
					Method[] methods = clzz.getMethods();
					for(int j = 0;j<methods.length;j++){
						Method method = methods[j];
						Exec execM = method.getAnnotation(Exec.class);
						if(execM!=null){
							String cPath = bPath+"/"+execM.url();
							method.getParameterTypes();
						}
					}
				}
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

}

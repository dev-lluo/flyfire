package flyfire.root.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

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
			PackageUtil.getClassName(execPckg);
		}else{
			throw new RuntimeException("exec.pckg is null...");
		}
	}

}

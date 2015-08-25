package app.common.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import app.common.SpringHelper;
import app.service.logpool.LogPool;

public class InitListener implements ServletContextListener {

	

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//System.out.println("开始执行初始化工作");
		
		WebApplicationContext webApplicationContext = 
				WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		SpringHelper.setWebApplicationContext(webApplicationContext);
		
		//启动日志缓存队列
		LogPool .getInstance();
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

}

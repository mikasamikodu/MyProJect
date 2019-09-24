package com.atguigu.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SystemStartListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		String path = context.getContextPath();
		context.setAttribute("APP_PATH", path);
		System.out.println("SystemStartListener�������������ķ���application�����ˡ�����");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		

	}

}

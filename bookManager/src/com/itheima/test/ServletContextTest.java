package com.itheima.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;

public class ServletContextTest implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//�õ�servletcontext����
				ServletContext context = arg0.getServletContext();
				//�������ڴ洢session���������list
				final List<HttpSession> list = Collections.synchronizedList(new ArrayList<HttpSession>());
				//��list����servletcontext
				context.setAttribute("list", list);
				//��Ӷ�ʱ����
				Timer time = new Timer();
				time.schedule(new TimerTask() {
					
					@Override
					public void run() {
						System.out.println("��ʼɨ���ˣ�����");
						for (Iterator<HttpSession> iterator = list.iterator(); iterator.hasNext();) {
							HttpSession session = (HttpSession) iterator.next();
							long t = System.currentTimeMillis()-session.getLastAccessedTime();
							if(t>5000) {
								System.out.println("Session"+session.getId()+"���Ƴ��������У�");
								session.invalidate();
								iterator.remove();
							}
						}
					}
				}, 2000, 5000);
	}

}

package com.itheima.test;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class HttpSessionTest implements HttpSessionListener {

	@SuppressWarnings("unchecked")
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		//�õ�servletcontext����
		HttpSession session = arg0.getSession();
		ServletContext context = session.getServletContext();
		//��list�����session����
		List<HttpSession> list = (List<HttpSession>) context.getAttribute("list");
		list.add(session);
		System.out.println("Session"+session.getId()+"������������У�");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {

	}

}

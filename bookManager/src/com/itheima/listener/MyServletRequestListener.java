package com.itheima.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyServletRequestListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		System.out.println("ServletRequest �����ˣ�");
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		System.out.println("ServletRequest �����ˣ�");
	}

}

package com.redis.service;

import java.util.Map;

public interface UserService {

	/*
	 �ж��û���¼�Ƿ�����
	 */
	public Map<String, Object> loginLock(String username);
	
	public boolean login(String username,String password);

	public String loginFail(String username);

	public void clearCount(String username);
}

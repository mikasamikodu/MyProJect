package com.redis.serviceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.redis.bean.User;
import com.redis.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	/*
	 * �ж��û��Ƿ��ѱ����Ƶ�½
	 * */
	@Override
	public Map<String, Object> loginLock(String username) {
		String lockKey = User.getLoginLockKey(username);
		Map<String, Object> map = new HashMap<String, Object>();
		if(redisTemplate.hasKey(lockKey)) {
			map.put("flag", true);
			map.put("time", redisTemplate.getExpire(lockKey, TimeUnit.MINUTES));
		}else {
			map.put("flag", false);
		}
		return map;
	}

	/*
	 * �û���¼
	 * */
	@Override
	public boolean login(String username, String password) {
		return false;
	}

	/*
		3.��½ʧ��,���д������ۼ�,ͬʱ���е�¼ʧ�ܵ���ʾ
		����Ƿ�����ʧ�ܵ�key��û��������һ��key��Ȼ������2���ӹ���ʱ�䣻
		����оͼ��key��ֵ�Ƿ�С��4.�ǵĻ���ֵ��1����Ļ��ͶԵ�¼�������ƣ�Ȼ�����ù���ʱ��
	 * */
	@Override
	public String loginFail(String username) {
		String failKey = User.getLoginFailKey(username);
		String lockKey = User.getLoginLockKey(username);
		int expireTime = 2;
		int expireTime2 = 1;
		String result = "";
		int num = 4;
		if(redisTemplate.hasKey(failKey)) {//����ʧ�ܵ�key
			//����оͼ��key��ֵ�Ƿ�С��4.�ǵĻ���ֵ��1����Ļ��ͶԵ�¼�������ƣ�Ȼ�����ù���ʱ��
			//���key��ֵ
			long count = Long.parseLong((String)redisTemplate.opsForValue().get(failKey));
			if(count<num) {//С��4.�ǵĻ���keyֵ��1
				redisTemplate.opsForValue().increment(failKey, 1);
				num--;
				redisTemplate.getExpire(failKey, TimeUnit.SECONDS);
				result = "��½ʧ�ܣ���"+expireTime+"���ڣ�����ٵ�¼"+num+"��";
			}else {//��Ļ��ͶԵ�¼�������ƣ�Ȼ�����ù���ʱ��
				redisTemplate.opsForValue().set(lockKey, "1");
				redisTemplate.expire(lockKey, expireTime2, TimeUnit.HOURS);
				result = "��¼�����������ƣ�"+username+"�ѱ���ֹ��½����ֹʱ�仹��"+expireTime2+"Сʱ";
			}
		}else {//����һ��key��Ȼ�����ù���ʱ�䣻
			redisTemplate.opsForValue().set(failKey, "1");
			redisTemplate.expire(failKey, expireTime, TimeUnit.MINUTES);
			result = "��½ʧ�ܣ���"+expireTime+"���ڣ�����ٵ�¼"+num+"��";
		}
		return result;
	}
	
	/*
	 * ��յ�½ʧ�ܵĴ���
	 * */
	@Override
	public void clearCount(String username) {
		String failKey = User.getLoginFailKey(username);
		if(redisTemplate.hasKey(failKey)) {
			redisTemplate.delete(failKey);
		}
	}

}

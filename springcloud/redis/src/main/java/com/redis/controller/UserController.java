package com.redis.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redis.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/login")
	public String login(@RequestParam(name="username") final String username,
						@RequestParam(name="password") final String password,
						@RequestParam(name="valcode") final String valcode) {
		//1.�ж��û��Ƿ��ѱ����Ƶ�½���ǵĻ��������ʾ����Ļ����е�¼
		Map<String, Object> map = userService.loginLock(username);
		if((boolean) map.get("flag")) {//�ѱ����Ƶ�½
			//������ʾ
			return "���û�"+username+"��¼�����������ƣ��ѱ���ֹ��½����ֹʱ�仹��"+map.get("time")+"����";
		}else {
			//��¼
			boolean login = userService.login(username, password);
			//2.��¼�ɹ�����յ�½ʧ�ܴ�������½ʧ������д������ۼƣ��ﵽ5�ξ��������¼
			if(login) {//��¼�ɹ�
				//��յ�½ʧ�ܴ���
				userService.clearCount(username);
				return "success.jsp";
			}else {//��½ʧ��,���д������ۼ�,ͬʱ���е�¼ʧ�ܵ���ʾ
				//3.����Ƿ�����ʧ�ܵ�key��û��������һ��key��Ȼ�����ù���ʱ�䣻
				//����оͼ��key��ֵ�Ƿ�С��4.�ǵĻ���ֵ��1����Ļ��ͶԵ�¼�������ƣ�Ȼ�����ù���ʱ��
				String result = userService.loginFail(username);
				return result;
			}
		}
	}
}

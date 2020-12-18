package com.atguigu.singleton.type7;

public class SingletonTest7 {
	public static void main(String[]args) {
		System.out.println("����ʽ���̰߳�ȫ����̬�ڲ��ࣩ");
		Singleton singleton = Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();
		System.out.println(singleton == singleton2);
		System.out.println(singleton.hashCode() == singleton2.hashCode());
	}
}

//����ʽ���̰߳�ȫ����̬�ڲ��ࣩ,�Ƽ�ʹ��
class Singleton{
//	1.������˽�л����ⲿ����new
	private Singleton(){}
	
//	2.���쾲̬�ڲ��࣬������ڲ����������ʵ������Ϊ�ڲ��ౣ֤����Singleton�౻����ʱ���Լ����ᱻ���أ�
	private static  class SingletonInstance{
		private static final Singleton  SINGLETON = new Singleton();
	}
	
//	3.����һ����̬����������SingletonInstance�ڲ��ľ�̬ʵ������
	public static  Singleton getInstance() {
		return SingletonInstance.SINGLETON;
		//��ʹ��SingletonInstance�ڲ��ľ�̬ʵ������ʱ����֤����SingletonInstance�࣬�����Ǿ�̬�ڲ�������ֻ�����һ�Σ������̰߳�ȫ��
	}
}
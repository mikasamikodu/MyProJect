package com.atguigu.singleton.type2;

public class SingletonTest2 {
	public static void main(String[]args) {
		System.out.println("����ʽ����̬����飩");
		Singleton singleton = Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();
		System.out.println(singleton == singleton2);
		System.out.println(singleton.hashCode() == singleton2.hashCode());
	}
}

//����ʽ����̬����飩
class Singleton{
//	1.������˽�л����ⲿ����new
	private Singleton(){}
	
//	2.������ڲ����������ʵ��
	private static Singleton SINGLETON;
	
	static {//3.�ھ�̬������г�ʼ����ĳ���
		SINGLETON = new Singleton();
	}
//	4.ӵ��һ�����еľ�̬����������ʵ������
	public static Singleton getInstance() {
		return SINGLETON;
	}
}
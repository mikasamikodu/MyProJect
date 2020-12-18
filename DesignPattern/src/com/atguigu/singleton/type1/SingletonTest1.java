package com.atguigu.singleton.type1;

public class SingletonTest1 {
	public static void main(String[]args) {
		System.out.println("����ʽ����̬������");
		Singleton singleton = Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();
		System.out.println(singleton == singleton2);
		System.out.println(singleton.hashCode() == singleton2.hashCode());
	}
}

//����ʽ����̬������
class Singleton{
//	1.������˽�л����ⲿ����new
	private Singleton(){}
	
//	2.������ڲ����������ʵ��
	private static final Singleton SINGLETON = new Singleton();
//	3.ӵ��һ�����еľ�̬����������ʵ������
	public static Singleton getInstance() {
		return SINGLETON;
	}
}
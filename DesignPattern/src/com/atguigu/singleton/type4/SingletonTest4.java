package com.atguigu.singleton.type4;

public class SingletonTest4 {
	public static void main(String[]args) {
		System.out.println("����ʽ���̰߳�ȫ��");
		Singleton singleton = Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();
		System.out.println(singleton == singleton2);
		System.out.println(singleton.hashCode() == singleton2.hashCode());
	}
}

//����ʽ���̰߳�ȫ���߳�ͬ����
class Singleton{
//	1.������˽�л����ⲿ����new
	private Singleton(){}
	
//	2.������ڲ����������ʵ��
	private static Singleton SINGLETON;
//	3.ӵ��һ�����еľ�̬����������ʵ������(ͨ������synchronized�ؼ��֣��������������ͬ�������ƣ����ǽ�����Ч��)
	public static synchronized Singleton getInstance() {
		if(SINGLETON == null) {
			SINGLETON = new Singleton();
		}
		return SINGLETON;
	}
}
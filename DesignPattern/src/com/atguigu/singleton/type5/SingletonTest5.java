package com.atguigu.singleton.type5;

public class SingletonTest5 {
	public static void main(String[]args) {
		System.out.println("����ʽ���̰߳�ȫ��");
		Singleton singleton = Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();
		System.out.println(singleton == singleton2);
		System.out.println(singleton.hashCode() == singleton2.hashCode());
	}
}

//����ʽ���̰߳�ȫ��ͬ������飩
class Singleton{
//	1.������˽�л����ⲿ����new
	private Singleton(){}
	
//	2.������ڲ����������ʵ��
	private static Singleton SINGLETON;
//	3.ӵ��һ�����еľ�̬����������ʵ������(ͨ������synchronized�ؼ��֣��������������ͬ��������)
//	��Ȼ����synchronized�ؼ��֣����ǻ��ǻ����SingletonTest3�е�����
	public static  Singleton getInstance() {
		if(SINGLETON == null) {
			synchronized(Singleton.class) {
				SINGLETON = new Singleton();
			}
		}
		return SINGLETON;
	}
}
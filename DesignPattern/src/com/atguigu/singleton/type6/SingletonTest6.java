package com.atguigu.singleton.type6;

public class SingletonTest6 {
	public static void main(String[]args) {
		System.out.println("����ʽ���̰߳�ȫ��˫�ؼ�飩");
		Singleton singleton = Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();
		System.out.println(singleton == singleton2);
		System.out.println(singleton.hashCode() == singleton2.hashCode());
	}
}

//����ʽ���̰߳�ȫ��˫�ؼ�飩,�Ƽ�ʹ��
class Singleton{
//	1.������˽�л����ⲿ����new
	private Singleton(){}
	
//	2.������ڲ����������ʵ��
	private static volatile Singleton SINGLETON;
//	volatile����Ϊ����������synchronized,�����Ƿ�ָֹ�����š�
//	һ�㴴������ᾭ�������������̣�1.����ռ䣻2.��ʼ������3.ָ������ڴ��ַ��
//	���еڶ��͵��������п���˳��ߵ��ģ�����volatile����һ��Ŀ�ľ����ǹ���ǿ�ư�˳��ִ�У��м䲻������ߵ����¹�
//	����volatile����һ��Ŀ����������ͬ��SINGLETON���ڴ棬֪ͨ�����߳�SINGLETON�����
//	volatileֻ�����������α���
	
//	3.ӵ��һ�����еľ�̬����������ʵ������
	public static  Singleton getInstance() {
//		����˫�ؼ�飬����̰߳�ȫ�����⣬ͬʱ��������ص����⣬�����Ч��
		if(SINGLETON == null)	{
			synchronized (Singleton.class) {
				if(SINGLETON == null)	
					SINGLETON = new Singleton();
			}
		}
		return SINGLETON;
	}
}
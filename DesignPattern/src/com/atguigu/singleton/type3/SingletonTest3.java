package com.atguigu.singleton.type3;

public class SingletonTest3 {
	public static void main(String[]args) {
		System.out.println("����ʽ���̲߳���ȫ��");
		Singleton singleton = Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();
		System.out.println(singleton == singleton2);
		System.out.println(singleton.hashCode() == singleton2.hashCode());
	}
}

//����ʽ���̲߳���ȫ��
class Singleton{
//	1.������˽�л����ⲿ����new
	private Singleton(){}
	
//	2.������ڲ����������ʵ��
	private static Singleton SINGLETON;
//	3.ӵ��һ�����еľ�̬����������ʵ������
//	����һ���߳̽���if�жϿ�ʼ����ʵ������ͬʱ��һ���߳�Ҳ����if�жϿ�ʼ����ʵ����
//	�����ͻᴴ�����ʵ�����ﲻ��ֻ����һ��ʵ����Ŀ�ģ�������̲߳���ȫ��
	public static Singleton getInstance() {
		if(SINGLETON == null) {
			SINGLETON = new Singleton();
		}
		return SINGLETON;
	}
}
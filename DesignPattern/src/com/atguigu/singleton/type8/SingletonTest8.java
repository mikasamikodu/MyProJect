package com.atguigu.singleton.type8;

public class SingletonTest8 {
	public static void main(String[]args) {
		System.out.println("ö����");
		Singleton singleton = Singleton.SINGLETON;
		Singleton singleton2 = Singleton.SINGLETON;
		System.out.println(singleton == singleton2);
		System.out.println(singleton.hashCode() == singleton2.hashCode());
		singleton.getInstance();
	}
}

//ö����,�Ƽ�ʹ��
enum Singleton{
	SINGLETON;

	public void getInstance() {
		System.out.println("ok~");
	}
}
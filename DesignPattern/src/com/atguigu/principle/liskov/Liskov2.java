package com.atguigu.principle.liskov;

public class Liskov2 {

	public static void main(String[] args) {
		A1 a = new A1();
		B1 b = new B1();
		System.out.println("4-1=" + a.func1(4, 1));
		System.out.println("4-1=" + b.func1(4, 1));
		System.out.println("4+1=" + b.func2(4, 1));
		
	}

}

class Base{
	//���������Ĳ�
	public int func1(int num1, int num2) {
		return num1 - num2;
	}
}
class A1 extends Base{

}

//b�̳�a�����������֮�͵ķ���
class B1 extends Base{
//	b��ʹ��a�ķ���ʱ����ʹ����ϵķ�ʽ 
	private A1 a = new A1();
//	���������֮�͵ķ���ʱ����С����д��func1�ķ���
	public int func1(int num1, int num2) {
		return num1 + num2;
	}
	
	public int func2(int num1, int num2) {
		return func1(num1, num2) + 9;
	}
	
	public int func3(int num1, int num2) {
		return a.func1(num1, num2);
	}
}
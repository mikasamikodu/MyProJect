package com.atguigu.principle.inversion;

public class DependencyInversion2 {

	public static void main(String[] args) {
		Person2 person2 = new Person2();
		person2.receive(new Email2());
		person2.receive(new WeiXin());

	}

}

interface IReceiver{
	String getInfo();
}

class Email2 implements IReceiver{
	public String getInfo() {
		return "�����ʼ���Ϣ��hello world!";
	}
}
class WeiXin implements IReceiver{
	public String getInfo() {
		return "΢����Ϣ��hello world!";
	}
}
/**
 * ���Person�Ľ�����Ϣ�Ĺ���
 * ����1����
 * 1.�򵥣��Ƚ������뵽��
 * 2.������ǻ�õĶ�����������΢�ţ����ţ��������࣬ͬʱPerson��ҲҪ������Ӧ�Ľ��շ���
 * 3.���˼·������һ��IReceiver�࣬��ʾ�����ߣ�����Person����IReceiver�ӿڷ���������
 * 		��Ϊemail��WeiXin�ȶ����ڽ��շ�Χ�����Ǹ���ʵ��IReceiver�ӿھͿ����ˣ��������Ǿͷ���������תԭ��
 * @author miku
 *
 */
class Person2{
	public void receive(IReceiver iReceiver) {
		System.out.println(iReceiver.getInfo());
	}
}
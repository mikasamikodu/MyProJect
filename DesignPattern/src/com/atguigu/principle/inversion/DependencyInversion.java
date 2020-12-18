package com.atguigu.principle.inversion;

public class DependencyInversion {

	public static void main(String[] args) {
		Person person = new Person();
		person.receive(new Email());

	}

}

class Email{
	public String getInfo() {
		return "hello world!";
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
class Person{
	public void receive(Email email) {
		System.out.println(email.getInfo());
	}
}
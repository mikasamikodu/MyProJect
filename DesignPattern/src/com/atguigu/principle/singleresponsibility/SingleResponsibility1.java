package com.atguigu.principle.singleresponsibility;

public class SingleResponsibility1 {

	public static void main(String[] args) {
		Vehicle vehicle = new Vehicle();
		vehicle.run("Ħ��");
		vehicle.run("�ִ�");
		vehicle.run("�ɻ�");
		/**
		 * 	Ħ���ڹ�·�����С�����
			�ִ��ڹ�·�����С�����
			�ɻ��ڹ�·�����С�����
		 */
	}
}


/**
 * ��ͨ������
 * ��ʽ1
 * 1.�ڷ�ʽ1�з����У�Υ���˵�һְ���ԭ��
 * 2.�������Ҳ�ǳ��򵥣����ݽ�ͨ�������з����Ĳ�ͬ�ֽ�ɲ�ͬ���༴��
 * @author miku
 *
 */
class Vehicle{
	public void run(String vehicle) {
		System.out.println(vehicle + "�ڹ�·�����С�����");
	}
}
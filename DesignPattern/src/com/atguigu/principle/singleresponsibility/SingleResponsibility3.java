package com.atguigu.principle.singleresponsibility;

public class SingleResponsibility3 {

	public static void main(String[] args) {
		Vehicle2 vehicle = new Vehicle2();
		vehicle.run("Ħ��");
		vehicle.runWater("�ִ�");
		vehicle.runAir("�ɻ�");
		/**
		 * 	Ħ���ڹ�·�����С�����
			�ִ��ڴ������С�����
			�ɻ�����������С�����

		 */
	}
}


/**
 * ����32�ķ���
 * 1.�����޸ķ���û�ж�ԭ�������д�ĸĶ���ֻ�������˷���
 * 2.������Ȼû����������ѭ��һְ��ԭ�򣬵����ڷ��������ϣ���Ȼ��ѭ��һְ��ԭ��
 * 
 * @author miku
 *
 */
class Vehicle2{
	public void run(String vehicle) {
		System.out.println(vehicle + "�ڹ�·�����С�����");
	}
	
	public void runAir(String vehicle) {
		System.out.println(vehicle + "����������С�����");
	}
	
	public void runWater(String vehicle) {
		System.out.println(vehicle + "�ڴ������С�����");
	}
}
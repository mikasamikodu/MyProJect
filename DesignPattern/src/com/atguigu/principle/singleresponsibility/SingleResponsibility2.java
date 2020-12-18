package com.atguigu.principle.singleresponsibility;

public class SingleResponsibility2 {

	public static void main(String[] args) {
		RoadVehicle vehicle = new RoadVehicle();
		vehicle.run("Ħ��");
		WaterVehicle vehicle2 = new WaterVehicle();
		vehicle2.run("�ִ�");
		AirVehicle vehicle3 = new AirVehicle();
		vehicle3.run("�ɻ�");
		/**
		 * 	Ħ���ڹ�·�����С�����
			�ִ��ڴ������С�����
			�ɻ�����������С�����

		 */
	}
}


/**
 * ����2�ķ���
 * 1.�����˵�һְ���ԭ��
 * 2.���������ĸĶ��ܴ󣬼�Ҫ�޸��࣬��Ҫ�޸Ŀͻ���
 * 3.�Ľ����޸�Vehicle�࣬�Ķ��Ĵ���Ƚ���
 * @author miku
 *
 */
class RoadVehicle{
	public void run(String vehicle) {
		System.out.println(vehicle + "�ڹ�·�����С�����");
	}
}
class WaterVehicle{
	public void run(String vehicle) {
		System.out.println(vehicle + "�ڴ������С�����");
	}
}
class AirVehicle{
	public void run(String vehicle) {
		System.out.println(vehicle + "����������С�����");
	}
}
package com.atguigu.factory.simplefactory.pizzastore.pizza;

public class GreekPizza extends Pizza {

	@Override
	public void prepare() {
		System.out.println("׼��ϣ��������ԭ���ϡ�����");
	}
	
	public GreekPizza() {
		super.setName("ϣ������");
	}
}

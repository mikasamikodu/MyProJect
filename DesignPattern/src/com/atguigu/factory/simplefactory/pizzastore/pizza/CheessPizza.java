package com.atguigu.factory.simplefactory.pizzastore.pizza;

public class CheessPizza extends Pizza {

	@Override
	public void prepare() {
		System.out.println("׼������������ԭ���ϡ�����");
	}
	
	public CheessPizza() {
		super.setName("��������");
	}
}

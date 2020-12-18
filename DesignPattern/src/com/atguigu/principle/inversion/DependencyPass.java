package com.atguigu.principle.inversion;

public class DependencyPass {

	public static void main(String[] args) {
		ChangHong changHong = new ChangHong();
		
//		setter������������
		OpenAndClose openAndClose = new OpenAndClose();
		openAndClose.setterItv(changHong);
		openAndClose.open();
		
//		���췽����������
//		IOpenAndClose iOpenAndClose = new OpenAndClose(changHong);
//		iOpenAndClose.open();
		
//		�ӿ���������
//		IOpenAndClose iOpenAndClose = new OpenAndClose();
//		iOpenAndClose.open(changHong);

	}

}

/**
 * ��ʽһ���ӿڴ�������
 * @author miku
 *
 */
//interface IOpenAndClose{
//	void open(ITV itv);//���սӿ�
//}
//
//interface ITV{
//	void play();
//}
//class OpenAndClose implements IOpenAndClose{
//	public void open(ITV itv) {
//		itv.play();
//	}
//}
class ChangHong implements ITV{
	@Override
	public void play() {
		System.out.println("�򿪳������");
	}
}

/**
 * ��ʽ�������췽����������
 * @author miku
 *
 */
//interface IOpenAndClose{
//	void open();//���սӿ�
//}
//
//interface ITV{
//	void play();
//}
//class OpenAndClose implements IOpenAndClose{
//	
//	private  ITV itv;
//	
//	public OpenAndClose(ITV itv) {//���սӿ�
//		this.itv = itv;
//	}
//	public void open() {
//		itv.play();
//	}
//}

/**
 * ��������setter������������
 * @author miku
 *
 */
interface IOpenAndClose{
	void open();//���սӿ�
}

interface ITV{
	void play();
}
class OpenAndClose implements IOpenAndClose{
	
	private  ITV itv;
	
	public void setterItv(ITV itv) {
		this.itv = itv;
	}
	
	public void open() {
		itv.play();
	}
}
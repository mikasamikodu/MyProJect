package com.nantian.test;

//import java.util.Iterator;
import java.util.List;

import com.nantian.client2.*;

//import com.service.Student;

public class Test {

	public static void main(String[] args) {
		Data data = new DataImplService().getDataImplPort();
//		String select2 = data.select2("������");
//		System.out.println(select2);
//		StringBuilder soap=new StringBuilder(); //����������
//		soap.append(" <soapenv:Envelope  xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" ");
//		soap.append(" xmlns:wor=\"http://www.horizon.com/workflow/webservice/client/workflowCommon\">");
//		soap.append(" <soapenv:Header>");
//		soap.append(" <HZWFService  xmlns=\"http://www.huizhengtech.com/webservice/workflow\"");
//		soap.append(" xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"");
//		soap.append(" SOAP-ENV:actor=\"http://www.w3.org/2003/05/soap-envelope/role/next\">admin&admin</HZWFService>");
//		soap.append(" </soapenv:Header>");
//		soap.append(" <soapenv:Body>");
//		soap.append(" <wor:sysLogin>");
//		soap.append(" <loginName>loginname</loginName >");
//		soap.append(" <password>password</password>");
//		soap.append(" <dbidentifier>system</dbidentifier>");
//		soap.append(" </wor:sysLogin>");
//		soap.append(" </soapenv:Body>");
//		soap.append(" </soapenv:Envelope>");
//		String requestSoap=soap.toString();
//		List<String> result = data.getErpStr(requestSoap);
		System.out.println("��ʼִ�в���");
		List<String> select2 = data.getErpStr("2008-02-04");
		if(select2==null) {
			System.out.println("�鲻������");
		}else {
//			System.out.println("�Ҳ鵽������,�����ǣ�\n"+select2);
			System.out.println("�Ҳ鵽�����ˡ�");
			for(String a1:select2) {
				System.out.println(a1);
			}
		}
		System.out.println("ִ�����");
	}

}

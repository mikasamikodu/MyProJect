package com.text;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//import com.service.impl.ImportVendorServiceImpl;
import com.service.ws.ImportVendor;
import com.service.ws.importCorp;
import com.service.ws.importName;
import com.service.ws.impl.importCorpImpl;

public class Test2 {
	public static void main(String[] args) {
		importcorpInfo();//������֯����
//		importNameInfo();//������Ա��Ϣ
		//importVendorInfo();//���Թ�Ӧ��
	}

	private static void importcorpInfo() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-beans.xml");
		importCorp service = new importCorpImpl();
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<sscinterface systemname='zsj' billdefine='zsj' organization ='zsj' userid='zsj' operatertype='add' dataid='����Ψһ��ʶ'>");
		sb.append("<bill>");//��Ʊ
		sb.append("<head>");//ͷ��
		sb.append("<ORGANIZATIONID></ORGANIZATIONID>");//organization id��֯ID
		sb.append("<COMPANYID>20000002</COMPANYID>");//��˾ID
		sb.append("<DEPARTMENTID>100006</DEPARTMENTID>");//����ID
		sb.append("<SORGKINDID>dpt</SORGKINDID>");//s org kind id ��֯����ID
		sb.append("</head>");
		sb.append("<body>");//����
		sb.append("<data>");// ��Ա��Ϣ
		
		sb.append("<ORGANIZATION></ORGANIZATION>");//Organization ��֯
		sb.append("<COMPANY>���ҿ���Ͷ�ʹ�˾</COMPANY>");//��˾
		sb.append("<DEPARTMENT>����һ��</DEPARTMENT>");//����
//		sb.append("<SVALIDSTATE>������ǲ</SVALIDSTATE>"); //s valid state ��Ա״̬
		sb.append("<SVALIDSTATE>����</SVALIDSTATE>"); //s valid state ��Ա״̬
		sb.append("<SUPERIORDEPARTMENT></SUPERIORDEPARTMENT>");//s uperiordepartment
		sb.append("<SUPERIORDEPARTMENTID></SUPERIORDEPARTMENTID>");//s uperiordepartment id
		sb.append("</data>");
		sb.append("</body>");
		sb.append("</bill>");
		sb.append("</sscinterface>");
		System.out.println(service.importCorpInfo(sb.toString()));
	}

	private static void importNameInfo() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-beans.xml");
		importName service = (importName) ctx.getBean("clientName");
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append(
				"<sscinterface systemname='zsj'  billdefine='zsj' organization ='zsj' userid='zsj' operatertype='add' dataid='����Ψһ��ʶ'>");
		sb.append("<bill>");
		sb.append("<head>");
		// ��֯����
		sb.append("<NAME>������</NAME>");
		sb.append("<ORGANIZATIONID>20000365</ORGANIZATIONID>");
		sb.append("<DEPARTMENTID>20002066</DEPARTMENTID>");
		sb.append("<IDCARD>110105198209269473</IDCARD>");
		sb.append("<SSTATE>1</SSTATE>");
		sb.append("<COMPANYID></COMPANYID>");

		sb.append("</head>");
		sb.append("<body>");
		sb.append("<data>");
		// ��Ա��Ϣ
		sb.append("<SID>10020502</SID>");
		sb.append("<SNAMEID>xingwenxin9473</SNAMEID>");
		sb.append("<ORGANIZATION>��Ͷ��ҵ����һ�ֹ�˾</ORGANIZATION>");
		sb.append("<DEPARTMENT>����</DEPARTMENT>");
		sb.append("<SORGKINDID>psm</SORGKINDID>");
		sb.append("<SSEQUENCE>psm</SSEQUENCE>");
		sb.append("<SEX>��</SEX>");
		sb.append("<BIRTHDAY></BIRTHDAY>");// ��������
		sb.append("<JOINDATE>2017-05-31</JOINDATE>");// ��ְ����
		sb.append("<SOFFICEPHONE>010-63288355</SOFFICEPHONE>");// �ֻ���
		sb.append("<SMOBILEPHONE>13910068633</SMOBILEPHONE>");// �칫��
		sb.append("<SVALIDSTATE>�ڸ��ڱ�</SVALIDSTATE>"); // ��Ա״̬
		sb.append("<Attr1>N</Attr1><Attr2></Attr2><Attr3></Attr3><Attr4></Attr4><Attr5></Attr5><Attr6></Attr6></data>");
		sb.append("</body>");
		sb.append("</bill>");
		sb.append("</sscinterface>");
		System.out.println(service.importNameInfo(sb.toString()));
	}

	@SuppressWarnings("unused")
	private static void importVendorInfo() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-beans.xml");
		ImportVendor service = (ImportVendor) ctx.getBean("clientVendor");
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append(
				"<sscinterface systemname='zsj'  billdefine='zsj' organization ='zsj' userid='zsj' operatertype='add' dataid='����Ψһ��ʶ'>");
		sb.append("<bill>");
		sb.append("<head>");
		// ��֯����
		sb.append("<VENDOR_ID>13254412</VENDOR_ID>");
		sb.append("<VENDOR_SITE_ID>400120</VENDOR_SITE_ID>");
		sb.append("<ORG_ID>20000002</ORG_ID>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<data>");
		// ��Ա��Ϣ
		sb.append("<VENDOR_NAME>112321</VENDOR_NAME>");
		sb.append("<VENDOR_SITE_CODE>����</VENDOR_SITE_CODE>");
		sb.append("<ORG_NAME>���ҿ���Ͷ�ʼ��Źɷݹ�˾</ORG_NAME>");
		sb.append("<Attr1></Attr1><Attr2></Attr2><Attr3></Attr3><Attr4></Attr4><Attr5></Attr5><Attr6></Attr6></data>");
		sb.append("</body>");
		sb.append("</bill>");
		sb.append("</sscinterface>");
		System.out.println(service.importVendorInfo(sb.toString()));
	}

}

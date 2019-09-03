package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;

//import com.dao.ImportCorpDao;
//import com.dao.impl.ImportCorpDaoImpl;
import com.dao.impl.ImportVendorDaoImpl;
import com.entity.HeadManage;
//import com.entity.Sa_Opperson_Oporg;
import com.entity.Vendor;
import com.entity.resphoneManage;
import com.service.ImportVendorService;
//import com.service.ws.ImportVendor;
import com.util.DateResultNotNull;
//import com.util.ImportPersonInfo;
import com.util.ImportPoVendorInfo;
//import com.util.ToXmlUtilOgnDept;
import com.util.ToXmlUtilVendor;

public class ImportVendorServiceImpl implements ImportVendorService {
	Logger log = ImportPoVendorInfo.log;
	public String importVendorInfo(String requestXml) {
		DateResultNotNull drnn = new DateResultNotNull();
		Vendor vendor=new Vendor();
		ImportVendorDaoImpl vendorDao=new ImportVendorDaoImpl();
		resphoneManage rpm = new resphoneManage();
		HeadManage hm = new HeadManage();
		ToXmlUtilVendor txu = new ToXmlUtilVendor();
		List<Object> objlist=new ArrayList<Object>();
		String VendorIsNotNull="";
		try {
			// ����xml,������Ӧ����֤��Ϣ
			objlist = txu.toXml(requestXml);
			log.info("---���ձ��ģ�"+requestXml);		
			hm = (HeadManage) objlist.get(1);
			vendor=(Vendor)objlist.get(0);
			log.info("---��Ӧ�̣�"+vendor.getVendor_name());
			log.info("---������˾:"+vendor.getOrg_name());
			
			VendorIsNotNull=vendorDao.findVendor(vendor);
			if("true"==VendorIsNotNull){
				rpm.setRtnMsg("�Ϸ��Դ��󣨡�" +vendor.getOrg_name() + "���Ĺ�Ӧ���롰"+vendor.getVendor_name()+"���Ѵ��ڣ�");
				rpm.setRtnCode("40");
				rpm.setDataId(hm.getDataId());
				return txu.resphoneXml(rpm);
			}
			VendorIsNotNull=drnn.vendorNull(vendor);
			if(!"".equals(VendorIsNotNull)){
				rpm.setRtnMsg("�Ϸ��Դ��󣨡�"+VendorIsNotNull+"������Ϊ�գ�");
				rpm.setRtnCode("40");
				rpm.setDataId(hm.getDataId());			
				return txu.resphoneXml(rpm);
			}
			VendorIsNotNull=drnn.validationVendor(vendor);
			if("false".equals(VendorIsNotNull)){
				rpm.setRtnMsg("�Ϸ��Դ��󣨡�" +vendor.getOrg_name() + "���ù�˾δ���ʵ���ʲ�¼����Ȩ��");
				rpm.setRtnCode("40");
				rpm.setDataId(hm.getDataId());
				return txu.resphoneXml(rpm);
			}
		} catch (Exception e) {
			log.error("Exception",e);
			e.printStackTrace();
			rpm.setRtnMsg("xml��ʽ����");
			rpm.setRtnCode("20");
			rpm.setDataId(hm.getDataId());
			return txu.resphoneXml(rpm);
		}		
		return vendorDao.importVendorInfo(objlist);
	}
	public static void main(String[] args) {
		ImportVendorServiceImpl ivs=new ImportVendorServiceImpl();
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<sscinterface systemname='zsj'  billdefine='zsj' organization ='zsj' userid='zsj' operatertype='add' dataid='����Ψһ��ʶ'>");
		sb.append("<bill>");
		sb.append("<head>");
		// ��֯����
		sb.append("<VENDOR_ID>12312312</VENDOR_ID>");
		sb.append("<VENDOR_SITE_ID>1231231</VENDOR_SITE_ID>");
		sb.append("<ORG_ID>20000002</ORG_ID>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<data>");
		// ��Ա��Ϣ
		sb.append("<VENDOR_NAME>����</VENDOR_NAME>");
		sb.append("<VENDOR_SITE_NAME>����</VENDOR_SITE_NAME>");		
		sb.append("<ORG_NAME>���ҿ���Ͷ�ʼ��Źɷݹ�˾</ORG_NAME>");
		sb.append("<Attr1></Attr1><Attr2></Attr2><Attr3></Attr3><Attr4></Attr4><Attr5></Attr5><Attr6></Attr6></data>");
		sb.append("</body>");
		sb.append("</bill>");
		sb.append("</sscinterface>");
		System.out.println(ivs.importVendorInfo(sb.toString()));
	}
}

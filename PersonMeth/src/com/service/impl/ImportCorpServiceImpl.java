package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.dao.ImportCorpDao;
import com.dao.impl.ImportCorpDaoImpl;
import com.entity.HeadManage;
import com.entity.Sa_Opperson_Oporg;
import com.entity.resphoneManage;
import com.service.ImportCorpService;
import com.util.DateResultNotNull;
import com.util.ImportPersonInfo;
import com.util.ToXmlUtilOgnDept;

public class ImportCorpServiceImpl implements ImportCorpService {

	Logger log = ImportPersonInfo.log;//Ϊ����Ĳ�����¼��־
	
	
	public String importCorpInfo(String requestXml) {
		DateResultNotNull drnn = new DateResultNotNull();
		Sa_Opperson_Oporg soo=new Sa_Opperson_Oporg();//ʵ���ཨ��һ������
		ImportCorpDao importCorp = new ImportCorpDaoImpl();
		resphoneManage rpm = new resphoneManage();//ʵ���ཨ��һ������
		HeadManage hm = new HeadManage();//ʵ���ཨ��һ������
		ToXmlUtilOgnDept txu = new ToXmlUtilOgnDept();
		List<Object> objlist=new ArrayList<Object>();
		String OporgIsNotNull="";
		try {
			// ����xml,������Ӧ����֤��Ϣ
			objlist = txu.toXml(requestXml);
			log.info("���ձ��ģ�"+requestXml);		
			hm = (HeadManage) objlist.get(1);
			soo=(Sa_Opperson_Oporg) objlist.get(0);//�����֯��������Ч��
			OporgIsNotNull=drnn.OporgnotNull(soo);
			if (OporgIsNotNull!= "") {//Ч�鷵�ر���
				rpm.setRtnMsg("�Ϸ��Դ���" + OporgIsNotNull + "��");
				rpm.setRtnCode("40");
				rpm.setDataId(hm.getDataId());
				log.info("====������֯������Ϣʧ��");
				return txu.resphoneXml(rpm);
			}
			/*
			//��֤�ù�˾�Ƿ���ʵ���ʲ�ϵͳά���Ĺ�˾
			String svalidationOgn=drnn.validationOgn(soo);
			if("false".equals(svalidationOgn)){
				rpm.setRtnMsg("�Ϸ��Դ��󣨡�" + soo.getCompanyname() + "���ù�˾δ���ʵ���ʲ�¼����Ȩ��");
				rpm.setRtnCode("40");
				rpm.setDataId(hm.getDataId());
				log.info("====������֯������Ϣʧ��");
				return txu.resphoneXml(rpm);
			}*/
		} catch (Exception e) {
			log.error("Exception",e);
			e.printStackTrace();
			rpm.setRtnMsg("xml��ʽ����");
			rpm.setRtnCode("20");
			rpm.setDataId(hm.getDataId());
			log.info("");
			return txu.resphoneXml(rpm);
		}		
		return importCorp.importCorpInfo(objlist);
	}
}

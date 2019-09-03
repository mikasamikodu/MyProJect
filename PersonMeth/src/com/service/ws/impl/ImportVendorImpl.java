package com.service.ws.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

//import com.service.impl.ImportCorpServiceImpl;
import com.service.impl.ImportVendorServiceImpl;
import com.service.ws.ImportVendor;
//import com.util.ImportPersonInfo;
import com.util.ImportPoVendorInfo;
@Service("/importVendor")
public class ImportVendorImpl implements ImportVendor {
	protected Logger log = ImportPoVendorInfo.log;
	ImportVendorServiceImpl ivsi=new ImportVendorServiceImpl();
	public String importVendorInfo(String requestXml) {
		log.info("---���������ݹ�Ӧ����Ϣ");
		String str=ivsi.importVendorInfo(requestXml);
		log.info("---���ر���xml��"+str);
		log.info("---���������ݹ�Ӧ����Ϣ����");
		return str;
	}
}

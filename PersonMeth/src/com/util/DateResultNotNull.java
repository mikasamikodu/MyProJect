package com.util;

import com.entity.Sa_Opperson_Oporg;
import com.entity.Saoporg;
import com.entity.Saopperson;
import com.entity.Vendor;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateResultNotNull
{
  public String notNull(Saopperson emp, Saoporg dept)
  {
    String str = "";
    String SorgkindID = "psm";
    if (SorgkindID.equals(dept.getSorgKindId()))
    {
      if ((emp.getSid() == null) || (emp.getSid().trim().equals("")) || ("null".equals(emp.getSid()))) {
        str = str + "sid����Ϊ��;";
      }
      if ((emp.getSnameid() == null) || (emp.getSnameid().trim().equals("")) || ("null".equals(emp.getSnameid())))
      {
        str = str + "sNameID����Ϊ��";
      }
      else
      {
        String scode = emp.getSnameid();
        if (scode.length() < 4) {
          scode = scode + "ceshi";
        }
        String StrScode = scode.substring(0, 4);
        if (StrScode.equals("null")) {
          str = str + "snameID:" + emp.getSnameid() + ",��ʽ����ȷ";
        }
      }
      if ((emp.getJoinDate() == null) || (emp.getJoinDate().trim().equals("")) || ("null".equals(emp.getJoinDate()))) {
        emp.setJoinDate(new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(new Date()));
      }
      if ((emp.getSvalIdState() == null) || ("null".equals(emp.getSvalIdState())) || 
        ("".equals(emp.getSvalIdState().trim()))) {
        str = str + "SvalIdState����Ϊ��;";
      }
      if ((emp.getSidCard() == null) || (emp.getSidCard().trim().equals("")) || 
        ("null".equals(emp.getSidCard().trim()))) {
        str = str + "SidCard����Ϊ��;";
      }
      if ((emp.getSname() == null) || (emp.getSname().trim().equals("")) || ("null".equals(emp.getSname().trim()))) {
        str = str + "Sname����Ϊ��;";
      }
      if ((dept.getSorgKindId() == null) || (dept.getSorgKindId().trim().equals("")) || 
        ("null".equals(dept.getSorgKindId().trim()))) {
        str = str + "SorgKindId����Ϊ��;";
      }
      if ((dept.getDepartment() == null) || (dept.getDepartment().trim().equals("")) || 
        ("null".equals(dept.getDepartment()))) {
        str = str + "Department����Ϊ��;";
      }
      if ((dept.getDepartmentID() == null) || (dept.getDepartmentID().trim().equals("")) || 
        ("null".equals(dept.getDepartmentID().trim()))) {
        str = str + "DepartMentID����Ϊ��;";
      }
      if ((dept.getOrganization() == null) || (dept.getOrganization().trim().equals("")) || 
        ("null".equals(dept.getOrganization().trim()))) {
        str = str + "Organization����Ϊ��;";
      }
      if ((dept.getOrganizationID() == null) || (dept.getOrganizationID().trim().equals("")) || 
        ("null".equals(dept.getOrganizationID().trim()))) {
        str = str + "OrganizationID����Ϊ��;";
      }
    }
    else
    {
      str = str + "��ȷ����Ա��ʶSorgkindID�Ƿ���д��ȷ��psm����" + dept.getSorgKindId();
    }
    return str;
  }
  
  public String OporgnotNull(Sa_Opperson_Oporg soo)
  {
    String strOporg = "";
    if ("ogn".equals(soo.getSorgkindid()))
    {
      if ((soo.getSfname() == null) || (soo.getSfname().trim().equals("")) || ("null".trim().equals(soo.getSfname()))) {
        strOporg = strOporg + "ORGANIZATION:����Ϊ��";
      }
      if ((soo.getSfid() == null) || (soo.getSfid().trim().equals("")) || ("null".trim().equals(soo.getSfid()))) {
        strOporg = strOporg + "ORGANIZATIONID:����Ϊ��";
      }
      if ((soo.getCompanyname() == null) || (soo.getCompanyname().trim().equals("")) || 
        ("null".trim().equals(soo.getCompanyname()))) {
        strOporg = strOporg + "CompanyName:����Ϊ��";
      }
      if ((soo.getCompanyid() == null) || (soo.getCompanyid().trim().equals("")) || 
        ("null".trim().equals(soo.getCompanyid()))) {
        strOporg = strOporg + "CompanyID:����Ϊ��";
      }
      if ((soo.getSvalidstate() == null) || (soo.getSvalidstate().trim().equals("")) || 
        ("null".trim().equals(soo.getSvalidstate()))) {
        strOporg = strOporg + "SvalidState:����Ϊ��";
      }
    }
    if ("dpt".equals(soo.getSorgkindid()))
    {
      if ((soo.getCompanyname() == null) || (soo.getCompanyname().trim().equals("")) || 
        ("null".equals(soo.getCompanyname()))) {
        strOporg = strOporg + "CompanyName:����Ϊ��;";
      }
      if ((soo.getCompanyid() == null) || (soo.getCompanyid().trim().equals("")) || 
        ("null".equals(soo.getCompanyid()))) {
        strOporg = strOporg + "CompanyID:����Ϊ��;";
      }
      if ((soo.getDepartmentid() == null) || ("".trim().equals(soo.getDepartmentid())) || 
        ("null".equals(soo.getDepartmentid()))) {
        strOporg = strOporg + "DepartmentID:����Ϊ��;";
      }
      if ((soo.getDepartmentname() == null) || (soo.getDepartmentname().trim().equals("")) || 
        ("null".equals(soo.getDepartmentname()))) {
        strOporg = strOporg + "DepartmentName:����Ϊ��;";
      }
      if ((soo.getSvalidstate() == null) || (soo.getSvalidstate().trim().equals("")) || 
        ("null".trim().equals(soo.getSvalidstate()))) {
        strOporg = strOporg + "SvalidState:����Ϊ��;";
      }
    }
    if ((soo.getSorgkindid() == null) || ("".trim().equals(soo.getSorgkindid())) || 
      ("null".trim().equals(soo.getSorgkindid()))) {
      strOporg = "��ȷ�Ϲ�˾/���ű�ʶSorgkindID�Ƿ���д��ȷ��ogn/dpt����" + soo.getSorgkindid();
    }
    return strOporg;
  }
  
  public String validationOgn(Sa_Opperson_Oporg soo)
  {
    String validationBool = "true";
    
    String companyid = soo.getCompanyid();
    String companyName = soo.getCompanyname();
    if ("20000002".equals(companyid)) {
      companyName = "���ҿ���Ͷ�ʹ�˾";
    } else if ("20000052".equals(companyid)) {
      companyName = "��Ͷ�����عɹɷ����޹�˾";
    } else if ("20000654".equals(companyid)) {
      companyName = "��Ͷ��ҵͶ�����޹�˾";
    } else if ("20000208".equals(companyid)) {
      companyName = "��Ͷ��ͨ�ع����޹�˾";
    } else if ("20002041".equals(companyid)) {
      companyName = "��Ͷ����Ƽ�Ͷ�����޹�˾";
    } else if ("20000320".equals(companyid)) {
      companyName = "��Ͷ�ʱ��ع����޹�˾";
    } else if ("20000325".equals(companyid)) {
      companyName = "��Ͷ̩���������޹�˾";
    } else if ("20000414".equals(companyid)) {
      companyName = "��Ͷ���Źɷ����޹�˾";
    } else if ("20000361".equals(companyid)) {
      companyName = "��Ͷ�������޹�˾";
    } else if ("20000631".equals(companyid)) {
      companyName = "��ʵ���ʿع����޹�˾";
    } else if ("20000456".equals(companyid)) {
      companyName = "�й���Ͷ���²�ҵͶ�ʹ�˾";
    } else if ("20000649".equals(companyid)) {
      companyName = "��Ͷ�����ҵ����������޹�˾";
    } else if ("20001672".equals(companyid)) {
      companyName = "��Ͷ������ҵͶ�����޹�˾";
    } else if ("20000646".equals(companyid)) {
      companyName = "��Ͷ�ʲ�����˾";
    } else if ("20000493".equals(companyid)) {
      companyName = "�й����ӹ������Ժ";
    } else if ("20000645".equals(companyid)) {
      companyName = "��Ͷ��ѯ���޹�˾";
    } else if ("20000364".equals(companyid)) {
      companyName = "��Ͷ��ҵ�������ι�˾";
    } else if ("20000365".equals(companyid)) {
      companyName = "��Ͷ��ҵ����һ�ֹ�˾";
    } else if ("20000366".equals(companyid)) {
      companyName = "��Ͷ��ҵ�������ֹ�˾";
    } else if ("20000367".equals(companyid)) {
      companyName = "��Ͷ��ҵ�������ֹ�˾";
    } else if ("20000368".equals(companyid)) {
      companyName = "��Ͷ��ҵ�Ϻ��ֹ�˾";
    } else if ("20000369".equals(companyid)) {
      companyName = "��ֹ�˾";
    } else if ("20000622".equals(companyid)) {
      companyName = "�й������豸�����ڣ����ţ��ܹ�˾";
    } else if ("20000590".equals(companyid)) {
      companyName = "�гɹ�����ҵ�ɷ����޹�˾";
    } else if ("20000568".equals(companyid)) {
      companyName = "�гɽ����ڹɷ����޹�˾";
    } else if ("20001669".equals(companyid)) {
      companyName = "��Ͷ���ܿƼ����޹�˾";
    } else if ("20001673".equals(companyid)) {
      companyName = "���ݹ�Ͷ�ÿ���ۡ���Ϸ������޹�˾";
    } else if ("20000266".equals(companyid)) {
      companyName = "��Ͷ�߿Ƽ�Ͷ�����޹�˾";
    } else if ("20000829".equals(companyid)) {
      companyName = "��Ͷ����Ͷ�����޹�˾";
    } else if ("20000856".equals(companyid)) {
      companyName = "��Ͷ�ÿ�������������Ͷ�����޹�˾";
    } else if ("20000855".equals(companyid)) {
      companyName = "��Ͷ�ÿ����Ϸ������޹�˾";
    } else if ("20000632".equals(companyid)) {
      companyName = "��Ͷ�����������޹�˾";
    } else if ("20001864".equals(companyid)) {
      companyName = "��Ͷ΢������Ƽ�����";
    } else if ("20002575".equals(companyid)) {
      companyName = "��Ͷ������Դ�����룩���޹�˾";
    } else if ("20005406".equals(companyid)) {
      companyName = "��Ͷ������Դ�����ף����޹�˾";
    } else if ("20000650".equals(companyid)) {
      companyName = "��Ͷ������Դ�������޹�˾";
    } else if ("20005392".equals(companyid)) {
        companyName = "��Ͷ���������ݣ����Ϸ������޹�˾";
    } else if ("20004555".equals(companyid)) {
        companyName = "��Ͷ��������֤���޹�˾";
    } else {
      validationBool = "false";
    }
    soo.setCompanyname(companyName);
    return validationBool;
  }
  
  public String validationVendor(Vendor vendor)
  {
    String validationBool = "true";
    
    String companyid = vendor.getOrg_id();
    String companyName = vendor.getOrg_name();
    if ("20000002".equals(companyid)) {
      companyName = "���ҿ���Ͷ�ʹ�˾";
    } else if ("20000052".equals(companyid)) {
      companyName = "��Ͷ�����عɹɷ����޹�˾";
    } else if ("20000654".equals(companyid)) {
      companyName = "��Ͷ��ҵͶ�����޹�˾";
    } else if ("20000208".equals(companyid)) {
      companyName = "��Ͷ��ͨ�ع����޹�˾";
    } else if ("20002041".equals(companyid)) {
      companyName = "��Ͷ����Ƽ�Ͷ�����޹�˾";
    } else if ("20000320".equals(companyid)) {
      companyName = "��Ͷ�ʱ��ع����޹�˾";
    } else if ("20000325".equals(companyid)) {
      companyName = "��Ͷ̩���������޹�˾";
    } else if ("20000414".equals(companyid)) {
      companyName = "��Ͷ���Źɷ����޹�˾";
    } else if ("20000361".equals(companyid)) {
      companyName = "��Ͷ�������޹�˾";
    } else if ("20000631".equals(companyid)) {
      companyName = "��ʵ���ʿع����޹�˾";
    } else if ("20000456".equals(companyid)) {
      companyName = "�й���Ͷ���²�ҵͶ�ʹ�˾";
    } else if ("20000649".equals(companyid)) {
      companyName = "��Ͷ�����ҵ����������޹�˾";
    } else if ("20001672".equals(companyid)) {
      companyName = "��Ͷ������ҵͶ�����޹�˾";
    } else if ("20000646".equals(companyid)) {
      companyName = "��Ͷ�ʲ�����˾";
    } else if ("20000493".equals(companyid)) {
      companyName = "�й����ӹ������Ժ";
    } else if ("20000645".equals(companyid)) {
      companyName = "��Ͷ��ѯ���޹�˾";
    } else if ("20000364".equals(companyid)) {
      companyName = "��Ͷ��ҵ�������ι�˾";
    } else if ("20000365".equals(companyid)) {
      companyName = "��Ͷ��ҵ����һ�ֹ�˾";
    } else if ("20000366".equals(companyid)) {
      companyName = "��Ͷ��ҵ�������ֹ�˾";
    } else if ("20000367".equals(companyid)) {
      companyName = "��Ͷ��ҵ�������ֹ�˾";
    } else if ("20000368".equals(companyid)) {
      companyName = "��Ͷ��ҵ�Ϻ��ֹ�˾";
    } else if ("20000369".equals(companyid)) {
      companyName = "��ֹ�˾";
    } else if ("20000622".equals(companyid)) {
      companyName = "�й������豸�����ڣ����ţ��ܹ�˾";
    } else if ("20000590".equals(companyid)) {
      companyName = "�гɹ�����ҵ�ɷ����޹�˾";
    } else if ("20000568".equals(companyid)) {
      companyName = "�гɽ����ڹɷ����޹�˾";
    } else if ("20001669".equals(companyid)) {
      companyName = "��Ͷ���ܿƼ����޹�˾";
    } else if ("20001673".equals(companyid)) {
      companyName = "���ݹ�Ͷ�ÿ���ۡ���Ϸ������޹�˾";
    } else if ("20000266".equals(companyid)) {
      companyName = "��Ͷ�߿Ƽ�Ͷ�����޹�˾";
    } else if ("20000829".equals(companyid)) {
      companyName = "��Ͷ����Ͷ�����޹�˾";
    } else if ("20000856".equals(companyid)) {
      companyName = "��Ͷ�ÿ�������������Ͷ�����޹�˾";
    } else if ("20000855".equals(companyid)) {
      companyName = "��Ͷ�ÿ����Ϸ������޹�˾";
    } else if ("20000632".equals(companyid)) {
      companyName = "��Ͷ�����������޹�˾";
    } else if ("20001864".equals(companyid)) {
      companyName = "��Ͷ΢������Ƽ�����";
    } else if ("20002575".equals(companyid)) {
      companyName = "��Ͷ������Դ�����룩���޹�˾";
    } else if ("20005406".equals(companyid)) {
      companyName = "��Ͷ������Դ�����ף����޹�˾";
    } else if ("20000650".equals(companyid)) {
      companyName = "��Ͷ������Դ�������޹�˾";
    } else if ("20005392".equals(companyid)) {
        companyName = "��Ͷ���������ݣ����Ϸ������޹�˾";
    } else if ("20004555".equals(companyid)) {
        companyName = "��Ͷ��������֤���޹�˾";
    } else {
      validationBool = "false";
    }
    vendor.setOrg_name(companyName);
    return validationBool;
  }
  
  public String vendorNull(Vendor vendor)
  {
    String vendorinfo = "";
    if (("".equals(vendor.getVendor_id())) || (vendor.getVendor_id() == null)) {
      vendorinfo = "Vendor_id;";
    }
    if (("".equals(vendor.getVendor_name())) || (vendor.getVendor_name() == null)) {
      vendorinfo = "Vendor_name;";
    }
    if (("".equals(vendor.getVendor_site_id())) || (vendor.getVendor_site_id() == null)) {
      vendorinfo = "vendor_site_id;";
    }
    if (("".equals(vendor.getVendor_site_name())) || (vendor.getVendor_site_name() == null)) {
      vendorinfo = "vendor_site_name;";
    }
    if (("".equals(vendor.getOrg_id())) || (vendor.getOrg_id() == null)) {
      vendorinfo = "org_id;";
    }
    if (("".equals(vendor.getOrg_name())) || (vendor.getOrg_name() == null)) {
      vendorinfo = "org_name;";
    }
    return vendorinfo;
  }
}

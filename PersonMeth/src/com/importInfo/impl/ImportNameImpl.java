package com.importInfo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import com.dao.EmpDeptDao;
import com.dao.impl.EmpDeptDaoImpl;
import com.entity.Sa_Opperson_Oporg;
import com.importInfo.ImportName;
import com.util.BaseDao;
import com.util.ImportPersonInfo;

public class ImportNameImpl implements ImportName {
	BaseDao bd = null;
	Logger log = ImportPersonInfo.log;

	public void importNameInfo() {
		bd = new BaseDao();
		log.info("++++++++++++++");
		log.info("++++++++++++++");
		log.info("++++++++++++++");
		log.info("++++++++++++++");
		Sa_Opperson_Oporg soo = new Sa_Opperson_Oporg();
		EmpDeptDao empdeptdao = new EmpDeptDaoImpl();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstm = null;
		// �����Ա֮ǰ���ж��Ƿ��в���Ϊ�������
		ImportOgnDepImpl iod = new ImportOgnDepImpl();
		// ��Ӳ�����Ϣ
		iod.importOgnDptInfo();
		try {
			con = bd.getConnection();
			// ��ѯ�������ݽ�����Ա��ϢΪδ����swzcϵͳ��״̬���е���
			String selectSql = "select * from sa_opperson_saOporg where zsjstate=0 and sorgkindid='psm' and sfid<>'20000493' order by personmechid asc";
			pstm = con.prepareStatement(selectSql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				
				soo.setSid(rs.getString("sid"));
				soo.setSname(rs.getString("sname"));
				soo.setScode(rs.getString("snameid"));
				soo.setSfname(rs.getString("sfname"));
				soo.setSfid(rs.getString("sfid"));
				soo.setDepartmentid(rs.getString("departmentid"));
				soo.setDepartmentname(rs.getString("departmentname"));
				soo.setSorgkindid(rs.getString("sorgkindid"));
				soo.setSparent(rs.getString("sparent"));
				soo.setSidcode(rs.getString("sidcard"));
				soo.setSvalidstate(rs.getString("svalidstate"));
				soo.setSsex(rs.getString("ssex"));
				soo.setSbirthday(rs.getString("sbirthday"));
				soo.setSjoindate(rs.getString("sjoindate"));
				soo.setSmobilephone(rs.getString("smobilephone"));
				soo.setSofficephone(rs.getString("sofficephone"));
				soo.setPersonMechId(rs.getString("personmechid"));
				soo.setArrt1(rs.getString("arrt1"));
				log.info("===============ͬ����Ա��Ϣ============");
				log.info("��ʼִ����Աͬ������,ͬ����Ա��Ϣ��");
				log.info("==Sname��" + soo.getSname());
				log.info("==Sid:" + soo.getSid());
				log.info("==SidCard:" + soo.getSidcode());
				log.info("==sfname:" + soo.getSfname());
				log.info("==deparentName:" + soo.getDepartmentname());
				log.info("==Arrt2:"+soo.getArrt1());
				// ��֤��Ա��˾�Ƿ��Ǳ�ϵͳά����˾
				String validationOgn = empdeptdao.validationOgnImport(soo);
				if (validationOgn == "false") {
					updatePsmSvaliState(soo, "-4");
					log.info("�Ϸ��Դ��󣨡�" + soo.getSname() + "����Ա���ڵġ�" + soo.getSfname() + "��δ¼�뵽ʵ���ʲ�ϵͳ�У�");

				} else {
					// insertName(soo);
					insertNameEnd(soo);
				}
			}
			if (soo.getSid() == null || soo.getSid().equals("")) {
				log.info("����Ա��Ϣ���");
				log.info("=============ͬ����Ա��Ϣ����=============");
			}
		} catch (SQLException e) {
			log.error("SQLException", e);
		} finally {
			try {
				bd.getConnectionClose(con, pstm, rs);
			} catch (Exception e2) {
				log.error("Exception", e2);
			}
		}

	}

	public void insertNameEnd(Sa_Opperson_Oporg soo) {
		bd = new BaseDao();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ResultSet rs1=null;
		// �ж�״̬
		if (soo.getSvalidstate().equals("�ڸ�") || soo.getSvalidstate().equals("�ڸ��ڱ�")) {
			soo.setSvalidstate("1");
		} else {
			soo.setSvalidstate("0");
		}
		String birthday = "";
		String sidCode = soo.getSidcode();
		// ��ȡ��Ա���֤��
		if (sidCode.length() == 18) {
			String year = sidCode.substring(6, 10);// ��ȡ��
			String month = sidCode.substring(10, 12);// ��ȡ�·�
			String day = sidCode.substring(12, 14);// ��ȡ��
			birthday = year + "/" + month + "/" + day;
			log.info("�������֤��" + sidCode + "��ȡ��������:" + birthday);
		} else {
			log.info("���֤����18λ��ͬ����Ա��Ϣʧ��:" + sidCode);
			updatePsmSvaliState(soo, "-2");
			return;
		}
		// ��ȡ��ǰ����
		Date dd = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String nowTime = sdf.format(dd);
		String deptSid = "";
		String sfname = "";
		String sfcode = "";
		String sfid = "";
		String oporgSid = "";
		String sid = "";
		String scode="";
		try {
			con = bd.getConnection();
			con.setAutoCommit(false);
			String zsjState = "";
			// ��ѯ��ǰ��Ա�Ƿ����
			zsjState = findOpperson(soo);

			if ("true".equals(zsjState)) {
				// ���ݼ�ְ��Ϣ�жϴ����Ƿ�����ҵ
				String sfnameArrt1 = "";
				String arrt1 = "";
				boolean bool = false;
				boolean bool1=false;
				String selectArrte1 = "select sfname,arrt1 from sa_opperson_saoporg where sid='" + soo.getSid() + "' order by arrt1";
				pstm = con.prepareStatement(selectArrte1);
				rs = pstm.executeQuery();
				String arrtRevoke = "";
				while (rs.next()) {
					sfnameArrt1 = rs.getString("sfname");
					arrt1 = rs.getString("arrt1");
					log.info(sfnameArrt1 + "---" + arrt1);
					if ("Y".equals(soo.getArrt1())) {
						if (sfnameArrt1.contains("��ҵ") && "N".equals(arrt1)) {
							arrtRevoke = "����ϵ����ҵ";
							bool = true;
							break;
						}
					}
					if ("N".equals(soo.getArrt1())) {
						if (!sfnameArrt1.contains("��ҵ") && "N".equals(arrt1)&&bool1==false) {
							arrtRevoke = "����ϵ������ҵ";
							bool = true;
							break;
						}else {
							String selectArrte2 = "select count(0) as num from sa_opperson_saoporg where sid='" + soo.getSid() + "' and arrt1='Y' ";
							
							pstm = con.prepareStatement(selectArrte2);
							rs1 = pstm.executeQuery();
							if(rs1.next()){
								String num=rs1.getString("num");

								if("0".equals(num)){
									bool=true;
								}else {
									bool=false;
								}
							}
						}
					}
					if ("Y".equals(soo.getArrt1())) {
						if (!sfnameArrt1.contains("��ҵ")) {
							arrtRevoke = "����ϵ������ҵ";
						}
					}
				}
				if (bool) {
					log.info("---"+arrtRevoke+",������Ա��Ϣ�޸ġ�");
				} else {
					log.info(soo.getSfname() + "---" + soo.getArrt1());
					log.info("��Ա��ϢΪ" + soo.getArrt1() + ":" + arrtRevoke + "��������Աͬ��");
					updatePsmSvaliState(soo, "10");
					return;
				}
				log.info("-------��Ա��Ϣ�Ѵ��ڣ��޸���Ա��Ϣ");
				// �ж���Ա,��˾,�����Ƿ�һ��
				zsjState = findOgnName(soo);
				
				if ("2".equals(zsjState)) {
					log.info("��Ա��˾��һ��");

				} else if ("3".equals(zsjState)) {
					log.info("��Ա ���Ų�һ��");

				} else if ("1".equals(zsjState)) {
					log.info("��Ա��Ϣһ��");
					updatePsmSvaliState(soo, "2");
					return;
				}
				String selectPersonIDSql = "select SID,SCODE from sa_opperson where sname='" + soo.getSname()
						+ "' and sidcard='" + soo.getSidcode() + "'";
				pstm = con.prepareStatement(selectPersonIDSql);
				rs = pstm.executeQuery();
				while (rs.next()) {
					sid = rs.getString("SID");
					scode=rs.getString("scode");
					
					if (sid.equals(soo.getSid())) {
						sid = soo.getSid();
					}
					if(scode.equals(soo.getScode())){
						scode=soo.getScode();
					}
				}
				if ("".equals(sid) || null == sid) {
					return;
				}
				// ��ѯ��˾��Ϣ
				String ongInfo[] = findOngInfo(soo);
				sfname = ongInfo[0];
				sfcode = ongInfo[1];
				sfid = ongInfo[2];
				oporgSid = ongInfo[3];
				if (ongInfo[4] == "false") {
					log.info(soo.getSfname() + "���ظ��Ĺ�˾����");
					return;
				}
				if (oporgSid == null || oporgSid == "") {
					log.info("δ��ѯ��[" + soo.getSname() + "]���ڵ�[" + soo.getSfname() + "]��˾");
					zsjState = "7";
					updatePsmSvaliState(soo, zsjState);
					return;
				}
				if (soo.getDepartmentname() != null && soo.getSorgkindid().equals("psm")) {
					// ��ѯ������Ϣ
					deptSid = findDeptInfo(soo, oporgSid);
					
					StringBuffer[] result = new StringBuffer[5];
					if (deptSid == null || deptSid == "") {
						// �����ӵ��Ƕ���������Ա��ִ�д˷���
						result = insertInfo2(soo);

						if (result[1] == null) {
							log.info("δ��ѯ��[" + soo.getSname() + "]���ڹ�˾[" + soo.getSfname() + "]��["
									+ soo.getDepartmentname() + "]����");
							zsjState = "8";
							updatePsmSvaliState(soo, zsjState);
							log.info("�޸���Աʧ��");
							return;
						}
					}
					if (deptSid == "") {
						// ƴ����֯������Ϣresult[1]/result[0]:����Name��result[3]/result[4]:����ID
						deptSid = result[4].toString();
						sfname = sfname + "/" + result[1] + "/" + result[0] + "/" + soo.getSname();
						sfcode = sfcode + "/" + result[3] + "/" + result[4] + "/" + scode;
						sfid = sfid + "/" + result[3] + ".dpt/" + result[4] + ".dpt/" + sid + "@" + deptSid + ".psm";
					} else {
						// ƴ����֯������Ϣ
						sfname = sfname + "/" + soo.getDepartmentname() + "/" + soo.getSname();
						sfcode = sfcode + "/" + deptSid + "/" + scode;
						sfid = sfid + "/" + deptSid + ".dpt/" + sid + "@" + deptSid + ".psm";
					}
				} else if (soo.getDepartmentname() == null
						|| soo.getDepartmentname().equals("") && soo.getSorgkindid().equals("psm")) {
					deptSid = oporgSid;
					sfname = sfname + "/" + soo.getSname();
					sfcode = sfcode + "/" + scode;
					sfid = sfid + "/" + sid + "@" + deptSid + ".psm";
				}
				
				if(null==deptSid||"".equals(deptSid)){
					zsjState = "8";
					updatePsmSvaliState(soo, zsjState);
					log.info("deptSidΪnull�޷�������Ա�����ֶ�SMAINORGID�ĸ���");
					return;
				}
				log.info("��ʼ�޸Ĳ��ű���Ϣ");
				log.info("==Dept:" + soo.getDepartmentname());
				log.info("==Sfname:" + soo.getSfname());
				String updateSaOporg = "update sa_oporg set svalidstate='0' where spersonid='" + sid
						+ "' and svalidstate='1'";
				pstm = con.prepareStatement(updateSaOporg);
				int y = pstm.executeUpdate();
				if (y == 0) {
					// �����ϵͳ��Ա��֯����״̬Ϊ0�����������ƹ������˵���Ϣ���Ƿ����������Ա��Ϣ��
				}
				String updateParentSql = "update sa_opperson set smainorgid='" + deptSid
						+ "',svalidstate='1',sjoindate= to_date(substr('"+soo.getSjoindate()+"',1,10),'yyyy-mm-dd') where sid='" + sid + "'";
				
				pstm = con.prepareStatement(updateParentSql);
				int k = pstm.executeUpdate();
				if (k == 0) {
					// �����ϵͳ��Ա������
				}
				String updateZsjSaSql = "update sa_opperson_saoporg set arrt2='" + sfname + "' where personmechid='"
						+ soo.getPersonMechId() + "'";
				pstm = con.prepareStatement(updateZsjSaSql);
//				int j = pstm.executeUpdate();
				String addSql = "insert into sa_oporg(sid,sname,scode,sfname,sfcode,sfid,sorgkindid,ssequence,"
						+ "svalidstate,sparent,spersonid,snodekind,version,sdescription)"
						+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstm = con.prepareStatement(addSql);

				Object obj3[] = { sid + "@" + deptSid, soo.getSname(), scode, sfname, sfcode, sfid,
						soo.getSorgkindid(), 0, soo.getSvalidstate(), deptSid, sid, "nkLeaf", 0, "ZSJ" };
				for (int i = 0; i < obj3.length; i++) {
					pstm.setObject(i + 1, obj3[i]);
				}
				pstm.executeUpdate();
				String updateSql = "update SA_OPPERSON_SAOPORG set zsjstate='6',modifytime=to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') where sid='"
						+ soo.getSid() + "' and personmechid='" + soo.getPersonMechId() + "'";
				pstm = con.prepareStatement(updateSql);
				pstm.executeUpdate();

				log.info("��Ա��Ϣ�Ѵ��ڸ��ĳɹ�");
			} else {
				log.info("-------��Ա��Ϣ�����ڣ�ͬ����Ա��Ϣ");
				/*String selectArrte1 = "select sfname,arrt1 from sa_opperson_saoporg where sid='" + soo.getSid() + "'";
				pstm = con.prepareStatement(selectArrte1);
				rs = pstm.executeQuery();
				System.out.println(selectArrte1);
				String sfnameArrt1 = "";
				String arrt1 = "";
				String arrtRevoke = "";
				boolean bool = false;
				
				while (rs.next()) {
					sfnameArrt1 = rs.getString("sfname");
					arrt1 = rs.getString("arrt1");
					System.out.println(sfnameArrt1 + "---" + arrt1);
					if ("Y".equals(soo.getArrt1())) {
						if (sfnameArrt1.contains("��ҵ") && "N".equals(arrt1)) {
							arrtRevoke = "����ϵ����ҵ";
							
							bool = true;
						}
					}
					if ("N".equals(soo.getArrt1())) {
						if (sfnameArrt1.contains("��ҵ") && "N".equals(arrt1)) {
							arrtRevoke = "����ϵ����ҵ,��û�м�ְ��Ϣ";
							bool = true;
						}
						
					}
					if ("N".equals(soo.getArrt1())) {
						if (!sfnameArrt1.contains("��ҵ") && "N".equals(arrt1)) {
							arrtRevoke = "����ϵ������ҵ";
							bool = true;
						}
					}
					
					if ("Y".equals(soo.getArrt1())) {
						if (!sfnameArrt1.contains("��ҵ")) {
							arrtRevoke = "����ϵ������ҵ";
						}
					}
				}
				if (bool) {
					System.out.println(soo.getSfname() + "��ҵ��Ա״̬Ϊ" + soo.getArrt1());
				} else {
					System.out.println(soo.getSfname() + "---" + soo.getArrt1());
					log.info("��Ա��ϢΪ" + soo.getArrt1() + ":" + arrtRevoke + "��������Աͬ��");
					updatePsmSvaliState(soo, "10");
					return;
				}*/
				// ��ѯ��˾��Ϣ
				String ongInfo[] = findOngInfo(soo);
				sfname = ongInfo[0];
				sfcode = ongInfo[1];
				sfid = ongInfo[2];
				oporgSid = ongInfo[3];
				if (ongInfo[4] == "false") {
					log.info(soo.getSfname() + "���ظ��Ĺ�˾����");
					return;
				}
				if (oporgSid == null || oporgSid == "") {
					log.info("δ��ѯ��[" + soo.getSname() + "]���ڵ�[" + soo.getSfname() + "]��˾");
					zsjState = "7";
					updatePsmSvaliState(soo, zsjState);
					return;
				}
				// �ж���Ա�Ƿ�����֯�����µ���Ա
				if (soo.getDepartmentname() != null && soo.getSorgkindid().equals("psm")) {
					// ��ѯ������Ϣ
					deptSid = findDeptInfo(soo, oporgSid);
					StringBuffer[] result = new StringBuffer[5];
					if (deptSid == null || deptSid == "") {
						// �����ӵ��Ƕ���������Ա��ִ�д˷���
						result = insertInfo2(soo);

						if (result[1] == null) {
							log.info("δ��ѯ��[" + soo.getSname() + "]���ڹ�˾[" + soo.getSfname() + "]��"
									+ soo.getDepartmentname() + "����");
							zsjState = "8";
							updatePsmSvaliState(soo, zsjState);
							return;
						}
					}
					if (deptSid == "") {
						// ƴ����֯������Ϣresult[1]/result[0]:����Name��result[3]/result[4]:����ID
						deptSid = result[4].toString();
						sfname = sfname + "/" + result[1] + "/" + result[0] + "/" + soo.getSname();
						sfcode = sfcode + "/" + result[3] + "/" + result[4] + "/" + soo.getScode();
						sfid = sfid + "/" + result[3] + ".dpt/" + result[4] + ".dpt/" + soo.getSid() + "@" + deptSid
								+ ".psm";
					} else {
						// ƴ����֯������Ϣ
						sfname = sfname + "/" + soo.getDepartmentname() + "/" + soo.getSname();
						sfcode = sfcode + "/" + deptSid + "/" + soo.getScode();
						sfid = sfid + "/" + deptSid + ".dpt/" + soo.getSid() + "@" + deptSid + ".psm";
					}
				} else if (soo.getDepartmentname() == null
						|| soo.getDepartmentname().equals("") && soo.getSorgkindid().equals("psm")) {
					deptSid = oporgSid;
					sfname = sfname + "/" + soo.getSname();
					sfcode = sfcode + "/" + soo.getScode();
					sfid = sfid + "/" + soo.getSid() + "@" + deptSid + ".psm";
				}
				log.info("��ʼ�������ű���Ϣ");
				log.info("==Dept:" + soo.getDepartmentname());
				log.info("==Sfname:" + soo.getSfname());
				String insertSql2 = "insert into sa_oporg(sid,sname,scode,sfname,sfcode,sfid,sorgkindid,ssequence,"
						+ "svalidstate,sparent,spersonid,snodekind,version,sdescription)"
						+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstm = con.prepareStatement(insertSql2);

				Object obj3[] = { soo.getSid() + "@" + deptSid, soo.getSname(), soo.getScode(), sfname, sfcode, sfid,
						soo.getSorgkindid(), 0, soo.getSvalidstate(), deptSid, soo.getSid(), "nkLeaf", 0, "ZSJ" };
				for (int i = 0; i < obj3.length; i++) {
					pstm.setObject(i + 1, obj3[i]);
				}
				pstm.executeUpdate();
				log.info("��Ա���ű���Ϣ��ӳɹ�");
				log.info("��ʼ������Ա����Ϣ" + soo.getSname());
				String insertSql = "insert into sa_opperson(sid,sname,scode,sidcard,snumb,sloginname,spassword,spasswordmodifytime"
						+ ",smainorgid,ssequence,svalidstate,ssex,sbirthday,sjoindate,smobilephone,sofficephone,scity,version)"
						+ " values(?,?,?,?,?,?,?,to_date(?,'yyyy/MM/dd'),?,?,?,?,to_date(?,'yyyy/MM/dd'),to_date(substr(?,1,10),'yyyy-mm-dd'),?,?,?,?)";
				pstm = con.prepareStatement(insertSql);
				Object obj1[] = { soo.getSid(), soo.getSname(), soo.getScode(), soo.getSidcode(), 6000, soo.getScode(),
						"9A84EE41AA72DE59C63006AAD670BCCE", nowTime, deptSid, 0, soo.getSvalidstate(), soo.getSsex(),
						birthday, soo.getSjoindate(), soo.getSmobilephone(), soo.getSofficephone(), "ZSJ", "0" };
				for (int i = 0; i < obj1.length; i++) {
					pstm.setObject(i + 1, obj1[i]);
				}
				pstm.executeUpdate();
				// �޸�״̬Ϊ1�Ѿ�����ʵ���ʲ�
				String updateSql = "update SA_OPPERSON_SAOPORG set zsjstate='1',modifytime=to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') where sid='"
						+ soo.getSid() + "' and personmechid='" + soo.getPersonMechId() + "'";
				pstm = con.prepareStatement(updateSql);
				pstm.executeUpdate();
				log.info("������Ա����Ϣ�ɹ�");
			}

			con.commit();
		} catch (Exception e) {
			try {
				con.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {

		}
	}

	public void insertName(Sa_Opperson_Oporg soo) {
		bd = new BaseDao();
		Connection con = null;
		PreparedStatement pstm = null;
		// �ж�״̬
		if (soo.getSvalidstate().equals("�ڸ�") || soo.getSvalidstate().equals("�ڸ��ڱ�")) {
			soo.setSvalidstate("1");
		} else {
			soo.setSvalidstate("0");
		}
		String birthday = "";
		String sidCode = soo.getSidcode();
		// ��ȡ��Ա���֤��
		if (sidCode.length() == 18) {
			String year = sidCode.substring(6, 10);// ��ȡ��
			String month = sidCode.substring(10, 12);// ��ȡ�·�
			String day = sidCode.substring(12, 14);// ��ȡ��
			birthday = year + "/" + month + "/" + day;
			log.info("�������֤��" + sidCode + "��ȡ��������:" + birthday);
		} else {
			log.info("���֤����18λ��ͬ����Ա��Ϣʧ��:" + sidCode);
			updatePsmSvaliState(soo, "-2");
			return;
		}
		// ��ȡ��ǰ����
		Date dd = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String nowTime = sdf.format(dd);
		try {
			con = bd.getConnection();
			con.setAutoCommit(false);
			String zsjState = "";

			// ��ѯ��ǰ��Ա�Ƿ����
			zsjState = findOpperson(soo);
			// �ж���Ա��Ϣ�Ƿ���� ����Ѿ�����������޸� �粻�����������Ա���
			if (zsjState.equals("true")) {
				zsjState = findOgnName(soo);
				String stateName = soo.getSvalidstate();
				if ("��ְ".equals(stateName) || "��������ͬ".equals(soo.getSvalidstate())) {
					zsjState = "5";
				} else if ("����".equals(soo.getSvalidstate()) || "����".equals(soo.getSvalidstate())) {
					zsjState = "4";
				}
				updatePsmSvaliState(soo, zsjState);
			} else {
				String deptSid = "";
				String sfname = "";
				String sfcode = "";
				String sfid = "";
				String oporgSid = "";
				// �ж���Ա��Ϣ��½���ڱ�ϵͳ�Ƿ����
				if (!"".equals(loginNameValiState(soo.getScode()))) {
					updatePsmSvaliState(soo, "9");
					log.info(soo.getSname() + "��Ա��½��(" + soo.getScode() + ")�ڱ�ϵͳ�Ѵ���");
					return;
				}
				// ��ѯ��˾��Ϣ
				String ongInfo[] = findOngInfo(soo);
				sfname = ongInfo[0];
				sfcode = ongInfo[1];
				sfid = ongInfo[2];
				oporgSid = ongInfo[3];
				if (ongInfo[4] == "false") {
					log.info(soo.getSfname() + "���ظ��Ĺ�˾����");
					return;
				}
				if (oporgSid == null || oporgSid == "") {
					log.info("δ��ѯ��[" + soo.getSname() + "]���ڵ�[" + soo.getSfname() + "]��˾");
					zsjState = "7";
					updatePsmSvaliState(soo, zsjState);
					return;
				}
				// �ж���Ա�Ƿ�����֯�����µ���Ա
				if (soo.getDepartmentname() != null && soo.getSorgkindid().equals("psm")) {
					// ��ѯ������Ϣ
					deptSid = findDeptInfo(soo, oporgSid);
					StringBuffer[] result = new StringBuffer[5];
					if (deptSid == null || deptSid == "") {
						// �����ӵ��Ƕ���������Ա��ִ�д˷���
						result = insertInfo2(soo);

						if (result[1] == null) {
							log.info("δ��ѯ��[" + soo.getSname() + "]���ڹ�˾[" + soo.getSfname() + "]��"
									+ soo.getDepartmentname() + "����");
							zsjState = "8";
							updatePsmSvaliState(soo, zsjState);
							return;
						}
					}
					if (deptSid == "") {
						// ƴ����֯������Ϣresult[1]/result[0]:����Name��result[3]/result[4]:����ID
						deptSid = result[4].toString();
						sfname = sfname + "/" + result[1] + "/" + result[0] + "/" + soo.getSname();
						sfcode = sfcode + "/" + result[3] + "/" + result[4] + "/" + soo.getScode();
						sfid = sfid + "/" + result[3] + ".dpt/" + result[4] + ".dpt/" + soo.getSid() + "@" + deptSid
								+ ".psm";
					} else {
						// ƴ����֯������Ϣ
						sfname = sfname + "/" + soo.getDepartmentname() + "/" + soo.getSname();
						sfcode = sfcode + "/" + deptSid + "/" + soo.getScode();
						sfid = sfid + "/" + deptSid + ".dpt/" + soo.getSid() + "@" + deptSid + ".psm";
					}
				} else if (soo.getDepartmentname() == null
						|| soo.getDepartmentname().equals("") && soo.getSorgkindid().equals("psm")) {
					deptSid = oporgSid;
					sfname = sfname + "/" + soo.getSname();
					sfcode = sfcode + "/" + soo.getScode();
					sfid = sfid + "/" + soo.getSid() + "@" + deptSid + ".psm";
				}
				log.info("��ʼ�������ű���Ϣ");
				log.info("==Dept:" + soo.getDepartmentname());
				log.info("==Sfname:" + soo.getSfname());
				String insertSql2 = "insert into sa_oporg(sid,sname,scode,sfname,sfcode,sfid,sorgkindid,ssequence,"
						+ "svalidstate,sparent,spersonid,snodekind,version,sdescription)"
						+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstm = con.prepareStatement(insertSql2);

				Object obj3[] = { soo.getSid() + "@" + deptSid, soo.getSname(), soo.getScode(), sfname, sfcode, sfid,
						soo.getSorgkindid(), 0, soo.getSvalidstate(), deptSid, soo.getSid(), "nkLeaf", 0, "ZSJ" };
				for (int i = 0; i < obj3.length; i++) {
					pstm.setObject(i + 1, obj3[i]);
				}
				pstm.executeUpdate();
				log.info("��Ա���ű���Ϣ��ӳɹ�");
				log.info("��ʼ������Ա����Ϣ" + soo.getSname());
				String insertSql = "insert into sa_opperson(sid,sname,scode,sidcard,snumb,sloginname,spassword,spasswordmodifytime"
						+ ",smainorgid,ssequence,svalidstate,ssex,sbirthday,sjoindate,smobilephone,sofficephone,scity,version)"
						+ " values(?,?,?,?,?,?,?,to_date(?,'yyyy/MM/dd'),?,?,?,?,to_date(?,'yyyy/MM/dd'),to_date(substr(?,1,10),'yyyy-mm-dd'),?,?,?,?)";
				pstm = con.prepareStatement(insertSql);
				Object obj1[] = { soo.getSid(), soo.getSname(), soo.getScode(), soo.getSidcode(), 6000, soo.getScode(),
						"9A84EE41AA72DE59C63006AAD670BCCE", nowTime, deptSid, 0, soo.getSvalidstate(), soo.getSsex(),
						birthday, soo.getSjoindate(), soo.getSmobilephone(), soo.getSofficephone(), "ZSJ", "0" };
				for (int i = 0; i < obj1.length; i++) {
					pstm.setObject(i + 1, obj1[i]);
				}
				pstm.executeUpdate();
				// �޸�״̬Ϊ1�Ѿ�����ʵ���ʲ�
				String updateSql = "update SA_OPPERSON_SAOPORG set zsjstate='1',modifytime=to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') where sid='"
						+ soo.getSid() + "' and personmechid='" + soo.getPersonMechId() + "'";
				pstm = con.prepareStatement(updateSql);
				pstm.executeUpdate();
				log.info("������Ա����Ϣ�ɹ�");
			}
			con.commit();
		} catch (SQLException e) {
			try {
				log.info(soo.getSname() + "����ʧ��");
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			log.error("SQLException", e);
		} catch (Exception e) {
			try {
				log.info(soo.getSname() + "����ʧ��");
				con.rollback();
			} catch (SQLException e1) {
				log.error("exception", e1);
			}
			log.error("exception", e);
		} finally {
			try {
				bd.getConnectionClose(con, pstm, null);

			} catch (Exception e) {
				log.error("SQLException", e);
			}
		}
	}

	// �����ӵĲ����Ƕ���������Ա
	public StringBuffer[] insertInfo2(Sa_Opperson_Oporg soo) {
		bd = new BaseDao();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer departmentName = new StringBuffer();
		StringBuffer deptSid = new StringBuffer();
		String sparent = soo.getSparent();
		StringBuffer deptInfo[] = new StringBuffer[5];
		StringBuffer deptInfo1[] = new StringBuffer[5];
		boolean bool = false;
		try {
			con = bd.getConnection();

			int i = 0;
			do {
				bool = false;
				String sql = "select * from SA_OPPERSON_SAOPORG where departmentid='" + sparent
						+ "' and sorgkindid='dpt'";
				
				pstm = con.prepareStatement(sql);
				rs = pstm.executeQuery();
				while (rs.next()) {
					String departmentName1 = rs.getString("departmentname");
					deptInfo[0] = departmentName.insert(0, departmentName1 + "/");
					deptInfo1[i] = new StringBuffer(departmentName1);
					sparent = rs.getString("sparent");
					bool = true;
				}
				i++;
			} while (bool);
			deptInfo1[2] = deptSid.append(soo.getSfname());
			deptInfo1 = findDeptSid(deptInfo1);
			deptInfo[1] = deptInfo1[3];
			deptInfo[2] = deptInfo1[4];

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				bd.getConnectionClose(con, pstm, rs);

			} catch (Exception e2) {
				log.error("SQLException", e2);
			}
		}
		return deptInfo1;
	}

	public StringBuffer[] findDeptSid(StringBuffer deptInfo[]) throws SQLException {
		bd = new BaseDao();
		Connection con = bd.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;

		String orgSid = "";
		String deptSid = "";
		String deptSid2 = "";
		String selectOrgSid2 = "select * from sa_oporg where sname='" + deptInfo[2] + "' and sorgkindid='ogn'";
		pstm = con.prepareStatement(selectOrgSid2);
		rs2 = pstm.executeQuery();
		while (rs2.next()) {
			orgSid = rs2.getString("sid");
		}

		String selectDeptSid1 = "select * from sa_oporg where sname='" + deptInfo[1] + "' and sparent='" + orgSid
				+ "' and sorgkindid='dpt'";
		pstm = null;
		pstm = con.prepareStatement(selectDeptSid1);

		rs1 = pstm.executeQuery();
		while (rs1.next()) {
			deptSid = rs1.getString("sid");
		}

		deptInfo[3] = new StringBuffer(deptSid);
		String selectDeptSid = "select * from sa_oporg where sname='" + deptInfo[0] + "' and sparent='" + deptSid
				+ "' and sorgkindid='dpt'";
		pstm = null;
		pstm = con.prepareStatement(selectDeptSid);
		rs = pstm.executeQuery();
		while (rs.next()) {
			deptSid2 = rs.getString("sid");
		}
		if (deptSid2 == "") {
			log.info("δ�ҵ�����" + deptInfo[0]);
		}
		deptInfo[4] = new StringBuffer(deptSid2);
		try {
			if (rs != null) {
				rs.close();
			}
			if (rs1 != null) {
				rs1.close();
			}
			if (rs2 != null) {
				rs2.close();
			}
			bd.getConnectionClose(con, pstm, null);

		} catch (Exception e) {
			log.error("Exception", e);
		}
		return deptInfo;
	}

	/**
	 * ��ѯ��Ա��Ϣ
	 * 
	 * @param soo
	 * @return
	 */
	public String findOpperson(Sa_Opperson_Oporg soo) {
		bd = new BaseDao();
		String result = "";
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = bd.getConnection();
			String selectSql = "select * from sa_opperson where sidcard='"+ soo.getSidcode() + "'";
			pstm = con.prepareStatement(selectSql);
			rs = pstm.executeQuery();
			if (rs.next()) {
				result = "true";
			}
		} catch (SQLException e) {
			log.info(soo.getSname() + "sa_oporg����Ա��Ϣ��ѯʧ��");
			e.printStackTrace();
		} finally {
			bd.getConnectionClose(con, pstm, rs);
		}
		return result;
	}

	// �ж������������뱾ϵͳ���ݣ���Ա���������֤����˾���Ƿ���ȫ����ͬ
	public String findOgnName(Sa_Opperson_Oporg soo) {
		bd = new BaseDao();
		String result = "";
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs2 = null;
		ResultSet rs = null;
		ResultSet rs3 = null;
		String sorgkindid = "";
		String sparent = "";
		String sparentDept = "";
		String ognName = "";
//		String deptName = "";
		int i = 0;
		try {
			con = bd.getConnection();
			// ��ѯ��˾��Ϣ
			String selectSql2 = "select sparent from sa_oporg where spersonid=(select sid from sa_opperson where sname='"
					+ soo.getSname() + "' and sidcard='" + soo.getSidcode() + "') and svalidstate='1'";
			
			pstm = con.prepareStatement(selectSql2);
			rs2 = pstm.executeQuery();
			while (rs2.next()) {
				sparent = rs2.getString("sparent");
				i++;
			}
			String selectSql3 = "select sname from sa_oporg where sid='" + sparent + "'";
			pstm = con.prepareStatement(selectSql3);
			rs3 = pstm.executeQuery();
			while (rs3.next()) {
				sparentDept = rs3.getString("sname");
			}
			pstm.close();
			if (i > 1) {
				log.info(soo.getSname() + "sa_opperson������Ա��Ϣ��������");
				result = "3";
			} else if (sparent != "") {
				while (!"ogn".equals(sorgkindid)) {
					rs = null;
					pstm = null;
					selectSql2 = "select * from sa_oporg where sid='" + sparent + "' and svalidstate='1' ";
					pstm = con.prepareStatement(selectSql2);
					rs = pstm.executeQuery();
					while (rs.next()) {
						sorgkindid = rs.getString("sorgkindid");
						ognName = rs.getString("sname");
						sparent = rs.getString("sparent");
						// deptName = rs.getString("sname");
					}
					if ("".equals(sorgkindid) || null == sorgkindid) {
						log.info("/\n/\n/��" + soo.getSname() + "�����ڵĲ���IDΪ:" + sparent + "�Ĳ����Ѳ����ڡ�/\n/\n/");
						break;
					}
				}
			}
			log.info(sparentDept + "---" + soo.getDepartmentname());
			log.info(ognName + "---" + soo.getSfname());

			if (!sparentDept.equals(soo.getDepartmentname())) {
				result = "3";// ���������֤�뱾ϵͳһ�£��������뱾ϵͳ��һ��

			}
			if (!ognName.equals(soo.getSfname())) {
				result = "2";// ���������֤,�����뱾ϵͳһ�£���˾���뱾ϵͳ��һ��

			}
			if (ognName.equals(soo.getSfname()) && sparentDept.equals(soo.getDepartmentname())) {
				result = "1";// ���������֤����˾,���ţ����뱾ϵͳһ��

			}
			if ("".equals(sorgkindid) || null == sorgkindid) {
				result = "-1";// ������Ա����idδ�ҵ����ڲ�����Ϣ

			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				if (rs2 != null) {
					rs2.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				bd.getConnectionClose(con, null, rs);

			} catch (SQLException e) {
				log.error("SQLException", e);
			}

		}
		return result;
	}

	public String[] findOngInfo(Sa_Opperson_Oporg soo) {
		bd = new BaseDao();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs2 = null;
		String ongInfo[] = new String[5];
		int i = 0;
		try {
			con = bd.getConnection();
			// ��ѯ��˾��Ϣ
			String selectSql2 = "select * from sa_oporg where sname='" + soo.getSfname() + "' and sorgkindid='ogn'";
			pstm = con.prepareStatement(selectSql2);
			rs2 = pstm.executeQuery();
			while (rs2.next()) {
				ongInfo[0] = rs2.getString("sfname");// ��ȡ�ϼ���֯����
				ongInfo[1] = rs2.getString("sfcode");// ��ȡ�ϼ���֯����
				ongInfo[2] = rs2.getString("sfid");// ��ȡ�ϼ���֯id
				ongInfo[3] = rs2.getString("sid");//// ��ȡ��˾id
				i++;
			}
			ongInfo[4] = "";
			if (i > 1) {
				ongInfo[4] = "false";
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			bd.getConnectionClose(con, pstm, rs2);

		}
		return ongInfo;
	}

	// ��ѯ������Ϣ
	public String findDeptInfo(Sa_Opperson_Oporg soo, String oporgSid) {
		bd = new BaseDao();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs1 = null;
		String deptSid = "";
		try {
			con = bd.getConnection();
			String selectSql1 = "select * from sa_oporg where sname='" + soo.getDepartmentname() + "' and sparent='"
					+ oporgSid + "'";
			pstm = con.prepareStatement(selectSql1);
			rs1 = pstm.executeQuery();
			while (rs1.next()) {
				deptSid = rs1.getString("sid");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			bd.getConnectionClose(con, pstm, rs1);

		}
		return deptSid;
	}

	// �޸���Ա״̬
	public void updatePsmSvaliState(Sa_Opperson_Oporg soo, String zsjState) {
		bd = new BaseDao();
		log.info(soo.getSname() + "��ʼ�޸���Ա״̬��Ա��Ϣ");
		try {
			String updateSql = "update SA_OPPERSON_SAOPORG set zsjstate='" + zsjState
					+ "',modifytime=to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') where sid='" + soo.getSid()
					+ "' and personmechid='" + soo.getPersonMechId() + "'";
			int i = bd.executeSQL(updateSql, null);
			if (i > 0) {
				log.info(soo.getSname() + "�޸�״̬�ɹ�");
			} else {
				log.info(soo.getSname() + "�޸�״̬ʧ��");
			}
		} catch (Exception e) {
			log.error("Exception", e);
		}
	}

	// ��֤�Ƿ�����ͬ�ĵ�½��
	public String loginNameValiState(String snameid) {
		bd = new BaseDao();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String valiState = "";
		try {
			con = bd.getConnection();
			String selectSql1 = "select * from sa_opperson where sloginname='" + snameid + "'";
			pstm = con.prepareStatement(selectSql1);
			rs = pstm.executeQuery();
			while (rs.next()) {
				valiState = rs.getString("sid");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			bd.getConnectionClose(con, pstm, rs);

		}
		return valiState;
	}
}

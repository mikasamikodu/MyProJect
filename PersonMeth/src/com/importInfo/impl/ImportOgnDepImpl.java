package com.importInfo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.entity.Sa_Opperson_Oporg;
import com.importInfo.ImportOgnDpt;
import com.util.BaseDao;
import com.util.DateResultNotNull;
import com.util.ImportPersonInfo;

public class ImportOgnDepImpl implements ImportOgnDpt {
	Logger log = ImportPersonInfo.log;
	BaseDao bd=null;
	public void importOgnDptInfo() {
		log.info("++++++++++++++");
		log.info("++++++++++++++");
		log.info("++++++++++++++");
		log.info("++++++++++++++");
		bd=new BaseDao();
		Sa_Opperson_Oporg soo = new Sa_Opperson_Oporg();
		DateResultNotNull drnn = new DateResultNotNull();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		try {
			con=bd.getConnection();
			// ��ѯ�������ݽ�����֯������ϢΪδ����swzcϵͳ��״̬���е���
			// ͬ����˾
			String selectSql = "select * from sa_opperson_saOporg where zsjstate=0 and sorgkindid='ogn' and sfid<>'20000493'";
			pstm=con.prepareStatement(selectSql);		
			rs =pstm.executeQuery();
			while (rs.next()) {
				soo.setSfname(rs.getString("sfname"));
				soo.setSfid(rs.getString("sfid"));
				soo.setCompanyid(rs.getString("companyid"));
				soo.setCompanyname(rs.getString("company"));
				soo.setSorgkindid(rs.getString("sorgkindid"));
				soo.setSparent(rs.getString("sparent"));
				soo.setSvalidstate(rs.getString("svalidstate"));
				String svalidationOgn=drnn.validationOgn(soo);
				if("false".equals(svalidationOgn)){
					updateDptOgnSorgkindID(soo,"-4");
					log.info("�Ϸ��Դ��󣨡�" + soo.getCompanyname() + "���ù�˾δ���ʵ���ʲ�¼����Ȩ��");
					
					log.info("====ͬ����֯������Ϣʧ��");
					
				}else{
				log.info("��ʼ�����ݹ�˾ͬ��");
				log.info("sfname:" + soo.getSfname());
				log.info("comPanyName:" + soo.getCompanyname());
				log.info("svalidState:" + soo.getSvalidstate());
				insertOng(soo);
				}
			}
			// ͬ������
			String selectdpt = "select * from sa_opperson_saOporg where zsjstate=0 and sorgkindid='dpt' and companyid <> '20000493' order by superiordepartment desc";
			pstm=con.prepareStatement(selectdpt);
			rs = pstm.executeQuery();
			while (rs.next()) {
				soo = new Sa_Opperson_Oporg();
				soo.setSfname(rs.getString("sfname"));
				soo.setSfid(rs.getString("sfid"));
				soo.setCompanyid(rs.getString("companyid"));
				soo.setCompanyname(rs.getString("company"));
				soo.setDepartmentid(rs.getString("departmentid"));
				soo.setDepartmentname(rs.getString("departmentname"));
				soo.setSuperiorDepartment(rs.getString("superiorDepartment"));
				soo.setSuperiorDepartmentID(rs.getString("superiorDepartmentID"));
				soo.setSorgkindid(rs.getString("sorgkindid"));
				soo.setSparent(rs.getString("sparent"));
				soo.setSvalidstate(rs.getString("svalidstate"));
				log.info("��ʼִ�в���ͬ��");
				
				String svalidationOgn=drnn.validationOgn(soo);
				if("false".equals(svalidationOgn)){
					log.info("�Ϸ��Դ��󣨡�" + soo.getCompanyname() + "���ù�˾δ���ʵ���ʲ�¼����Ȩ��");
					
					log.info("====ͬ����֯������Ϣʧ��");
					
				}else{
					insertDpt(soo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			bd.getConnectionClose(con, pstm, rs);
		}
	}

	/**
	 * ͬ��������Ϣ
	 * 
	 * @param soo
	 * @throws Exception
	 */
	public void insertDpt(Sa_Opperson_Oporg soo) {
		bd=new BaseDao();
		String str[] = new String[5];
		String sfname = "";
		String sfcode = "";
		String sfid = "";
		String sparent = "";
		String ssequence = "";
		try {
			// ��ӹ�˾һ������
			if (soo.getSuperiorDepartment() == null || soo.getSuperiorDepartment().equals("")) {
				// Ч����ӵĲ����Ƿ��Ѿ�����
				String result = selectDpt1(soo);
				if (result == "Y") {
					log.info(soo.getCompanyname() + "�µ�" + soo.getDepartmentname() + "�Ѿ�����,�����ظ����");
					updateDptOgnSorgkindID(soo,"2");
					return;
				}
				if (sparent == "false") {
					log.info("δ��ѯ��" + soo.getDepartmentname() + "���ϼ�����" + soo.getSuperiorDepartment());
					updateDptOgnSorgkindID(soo,"3");
					return;
				}
				soo.setSfname(soo.getCompanyname());
				str = selecOgnCurr(soo);
				sfname = str[0] + "/" + soo.getDepartmentname();
				sfcode = str[2] + "/" + soo.getDepartmentid();
				sparent = str[3];
				if (sparent == "false") {
					log.info("δ��ѯ��" + soo.getDepartmentname() + "���ϼ���˾" + soo.getCompanyname());
					updateDptOgnSorgkindID(soo,"4");
					return;
				}
				sfid = str[1] + "/" + soo.getDepartmentid() + ".dpt";
				ssequence = str[4];	
			}
			// ��ӹ�˾��������
			if (soo.getSuperiorDepartment() != null) {
				String result = selectDpt1(soo);
				if (result == "Y") {
					log.info(soo.getCompanyname() + "/" + soo.getSuperiorDepartment() + "/" + soo.getDepartmentname()
							+ "�Ѿ�����,�����ظ����");
					updateDptOgnSorgkindID(soo,"2");
					return;
				}
				str = selecOgnCurr(soo);
				sfname = str[0] + "/" + soo.getDepartmentname();
				sfcode = str[2] + "/" + soo.getDepartmentid();
				sparent = str[3];
				sfid = str[1] + "/" + soo.getDepartmentid() + ".dpt";
				if (sparent == "false") {
					log.info("δ��ѯ��" + soo.getDepartmentname() + "���ϼ�����" + soo.getSuperiorDepartment());
					updateDptOgnSorgkindID(soo,"");
					return;
				}
				ssequence=str[4];		
			}
			if (soo.getSvalidstate().equals("����")) {
				soo.setSvalidstate("1");
			} else {
				soo.setSvalidstate("0");
			}
			log.info("����ͬ��" + soo.getCompanyname() + "�µ�" + soo.getDepartmentname());

			String insertOporg = "insert into sa_oporg(sid,sname,scode,sfname,sfcode,sfid,sorgkindid,svalidstate,sparent,sdescription,version,ssequence) "
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?)";
			Object[] obj = { soo.getDepartmentid(), soo.getDepartmentname(), soo.getDepartmentid(), sfname, sfcode,
					sfid, soo.getSorgkindid(), soo.getSvalidstate(), sparent, "ZSJ", "0",ssequence };
			int i = bd.executeSQL(insertOporg, obj);
			if (i > 0) {
				log.info(sfname + "ͬ���ɹ�");
				//�޸�״̬
				updateDptOgnSorgkindID(soo,"1");
			} else {
				log.info(sfname + "ͬ��ʧ��");
				updateDptOgnSorgkindID(soo,"-1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateDptOgnSorgkindID(Sa_Opperson_Oporg soo,String zsjState) throws Exception {
		bd=new BaseDao();
		try {
			String updateSql = "update sa_opperson_saOporg set zsjstate='"+zsjState+"',modifytime=to_char(sysdate,'YYYY-MM-DD HH24:MI:SS')";
			if("dpt".equals(soo.getSorgkindid())){
				 updateSql+=" where departmentid='" + soo.getDepartmentid()+ "' and sorgkindid='dpt'";
			}else {
				updateSql+=" where companyid='"+soo.getCompanyid()+"' and sorgkindid='ogn'";
			}
			System.out.println(updateSql);
			int j = bd.executeSQL(updateSql, null);
			if (j < 1) {
				
				if("dpt".equals(soo.getSorgkindid())){
					log.info("��"+soo.getCompanyname() + "���¡�"+soo.getDepartmentname()+"���޸�ͬ��״̬[ZSJSTATE]ʧ��");
				}else {
					log.info("��"+soo.getSfname() + "���¡�"+soo.getCompanyname()+"���޸�ͬ��״̬[ZSJSTATE]ʧ��");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("�޸�ͬ��״̬�쳣",e);
		}
	}
	/***
	 * ͬ����˾
	 * @param soo
	 * @throws Exception
	 */
	public void insertOng(Sa_Opperson_Oporg soo) throws Exception {
		bd=new BaseDao();
		/*
		 * DateResultNotNull drnn=new DateResultNotNull(); String
		 * validation=drnn.validationOgn(soo); if("true"!=validation){ String
		 * sql="update sa_opperson_saOporg set zsjstate='9' where companyid='"
		 * +soo.getCompanyid()+"' and sorgkindid='ogn'"; int i =
		 * BaseDao.executeSQL(sql, null); if(i<1){
		 * log.info("�޸�sa_opperson_saOporg���С�"+soo.getCompanyname()+
		 * "����˾״̬��ZSJSTATE=9��ʧ��"); }else {
		 * log.info(soo.getCompanyname()+"δ�õ���Ӹù�˾����Ȩ"); } return; }
		 */
		String str[] = new String[4];
		str = selecOgnCurr(soo);
		String sfname = str[0] + "/" + soo.getCompanyname();
		String sfcode = str[2] + "/" + soo.getCompanyid();
		String sparent = str[3];
		String sfid = str[1] + "/" + soo.getCompanyid() + ".ogn";
		// �������ʱfalse�ַ���˵����ѯ�Ĺ�˾��ֹһ������
		if (sparent == "false") {
			log.info("��֯�������ʧ��,δ��ѯ��" + soo.getCompanyname() + "�ϼ���֯����" + soo.getSfname() + "");
			updateDptOgnSorgkindID(soo,"4");
			return;
		}
		/*
		 * sfid += "/" + soo.getCompanyid() + ".ogn";
		 */
		if (soo.getSvalidstate().equals("����")) {
			soo.setSvalidstate("1");
		} else {
			soo.setSvalidstate("0");
		}
		// �ж���ӵĹ�˾�Ƿ����
		String result = selectOgn(soo);
		if (result != null) {
			log.info(soo.getCompanyname() + "�Ѵ��ڣ������ظ����");
			updateDptOgnSorgkindID(soo,"2");
		} else {
			try {
				log.info("����ͬ����˾" + soo.getCompanyname());
				String insertOporg = "insert into sa_oporg(sid,sname,scode,sfname,sfcode,sfid,sorgkindid,svalidstate,sparent,sdescription,version) "
						+ " values(?,?,?,?,?,?,?,?,?,?,?)";
				Object[] obj = { soo.getCompanyid(), soo.getCompanyname(), soo.getCompanyid(), sfname, sfcode, sfid,
						soo.getSorgkindid(), soo.getSvalidstate(), sparent, "ZSJ", "0" };
				int i = bd.executeSQL(insertOporg, obj);
				if (i > 0) {
					log.info(soo.getCompanyname() + "ͬ���ɹ�");
					updateDptOgnSorgkindID(soo,"1");
				} else {
					log.info(soo.getCompanyname() + "ͬ��ʧ��");

				}

			} catch (Exception e) {
				log.info(soo.getCompanyname() + "ͬ��ʧ��");
				e.printStackTrace();
			}
		}
	}

	/**
	 * ��ѯͬ����˾���ϼ���˾/����
	 * 
	 * @param soo
	 * @return
	 */
	public String[] selecOgnCurr(Sa_Opperson_Oporg soo) throws Exception {
		bd=new BaseDao();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		String[] str = new String[5];
		int i = 0;
		try {
			con=bd.getConnection();
			
			String selectOgn = "";
			if (soo.getSuperiorDepartment() == null) {
				selectOgn = "select * from sa_oporg where sname='" + soo.getSfname() + "' and sorgkindid='ogn'";
			} else {
				selectOgn = "select * from sa_oporg where sname='" + soo.getSuperiorDepartment()+ "' and sorgkindid='dpt' and sparent=(select sid from sa_oporg where sname='"+ soo.getCompanyname() + "')";
			}
			pstm=con.prepareStatement(selectOgn);
			rs = pstm.executeQuery();
			while (rs.next()) {
				str[0] = rs.getString("sfname");
				str[1] = rs.getString("sfid");
				str[2] = rs.getString("sfcode");
				str[3] = rs.getString("sid");
				str[4] = rs.getString("ssequence");

				i++;
			}
			if (i != 1) {
				str[3] = "false";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			bd.getConnectionClose(con, pstm, rs);
		}
		return str;
	}

	/**
	 * ��ѯ��ӹ�˾�Ƿ����
	 * 
	 * @param soo
	 * @return
	 */
	public String selectOgn(Sa_Opperson_Oporg soo) throws Exception {
		bd=new BaseDao();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		String result = null;
		try {
			con=bd.getConnection();
			String selectOgn = "select * from sa_oporg where sname='" + soo.getCompanyname() + "' and sorgkindid='ogn'";
			pstm=con.prepareStatement(selectOgn);
			rs =pstm.executeQuery();
			if (rs.next()) {
				result = "Y";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			bd.getConnectionClose(con, pstm, rs);
		}
		return result;
	}

	/**
	 * ��֤ͬ���Ĳ����Ƿ����
	 * 
	 * @param soo
	 * @return
	 */
	public String selectDpt1(Sa_Opperson_Oporg soo) throws Exception {
		bd=new BaseDao();
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		String result = "";
		String sql = "";
		try {
			con=bd.getConnection();
			if (soo.getSuperiorDepartment() == null) {
				sql = "select * from sa_oporg where sname='" + soo.getDepartmentname()
						+ "' and sparent=(select sid from sa_oporg where sname='" + soo.getCompanyname()
						+ "' and sorgkindid='ogn') and sorgkindid='dpt'";
			} else {
				sql = "select * from sa_oporg where sname='" + soo.getDepartmentname()
						+ "' and sparent = (select sid  from sa_oporg where sname='" + soo.getSuperiorDepartment()
						+ "' and sorgkindid='dpt' and sparent = (select sid from sa_oporg where sname='"
						+ soo.getCompanyname() + "') and sorgkindid='dpt')";
			}
			pstm=con.prepareStatement(sql);
			
			rs =pstm.executeQuery();
			if (rs.next()) {
				result = "Y";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			bd.getConnectionClose(con, pstm, rs);
		}
		return result;
	}
}

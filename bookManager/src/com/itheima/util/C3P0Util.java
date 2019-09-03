package com.itheima.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/*
 * ���ȼ���jar��
 * Ȼ����c3p0�࣬ѡ������ʽ(�����ķ�ʽ��.properties��.xml),
 * ���ѡ��.properties��.xml����Ҫ�������ļ���Ȼ���дgetConnection()��release()����
 * ����д������
 */
public class C3P0Util {

	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	
	
	public static ComboPooledDataSource getDs() {
		return ds;
	}

	public static Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("����������");
		}
	}
	
	public static void release(ResultSet rs,Statement stm,Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs=null;
		}
		if(stm!=null){
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stm=null;
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn=null;
		}
	}
}

package com.shao.DAO.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.shao.DAO.AccountDao;
import com.shao.DAO.ClientDao;
import com.shao.model.Bankinfo;
import com.shao.model.Bankuser;
import com.shao.model.Bankcard;
import com.shao.model.Creditcard;
import com.shao.model.Payee;
import com.shao.model.T_productchosen;
import com.shao.model.User;
import com.shao.util.C3P0Util;
import com.shao.util.ManagerThreadLocal;
import com.shao.model.Clientinfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.DriverManager;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.Statement;

import org.apache.commons.dbutils.QueryRunner;


/**
 * 
 * @author hgx
 *���ÿ���
 *���ݲ�ӿ�ʵ��
 */
public class ClientDaoImpl implements ClientDao {
	
	/**
	 * @param client_id...uid
	 * @throws SQLException 
	 * ���¿ͻ���Ϣ
	 */
	public void updateClientinfo(String client_id,String uid, String client_name, String client_idcard,
			String client_addr, String client_phone) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		qr.update("update CLIENT_INFO set uid = ?,client_name = ?,client_idcard = ?,client_phone = ?,addr = ? where client_id = ?",
				uid,client_name,client_idcard,client_phone,client_addr,client_id);
	}

	/*
	 * @hgx
	 * ����ͻ�ID���ҿͻ���Ϣ
	 */
		//����ͬ���ͻ�ID �޷����
	public Clientinfo findClientID(String client_id) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from client_info where client_id = ?",new BeanHandler<Clientinfo>(Clientinfo.class), client_id);
	}
	
	/**
	 * @param username...
	 * @throws SQLException 
	 * @return
	 * �ͻ���Ϣ��ѯ
	 */
	public Clientinfo cfo_query(String username) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select client_id from client_info where username = ? ",new BeanHandler<Clientinfo>(Clientinfo.class), username);
	}
	
	/**
	 * @param username...
	 * @throws SQLException 
	 * @return
	 * ��ӿͻ���Ϣ
	 */
	public void addClientinfo2(String username) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		qr.update("insert into client_info(username) values(?)",username);
	}
	
	
	/**
	 * �ͻ���Ϣ��ѯ
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	public Clientinfo cinfo_query(String uid) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from client_info where uid = ?",new BeanHandler<Clientinfo>(Clientinfo.class), uid);
	}
	
	
	
}

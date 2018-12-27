package com.shao.DAO.impl;

import com.shao.DAO.BankcardDao;



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
 *储蓄卡表
 *数据层接口实例
 */
public class BankcardDaoImpl implements BankcardDao {
	
	/*
	 * 绑定银行卡信息
	 */
	public void addBankCard(String card_serial,String uid, String bclient_id, String card_id,
			String int_serial, String card_ps,String current, String death) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		qr.update("insert into bankcard (card_serial,uid,client_id,card_id,int_serial,card_ps,current,death) values (?,?,?,?,?,?,?,?)",
				card_serial,uid,bclient_id,card_id,int_serial,card_ps,current,death);
		
	}
	
	/**
	 * 查询银行卡信息
	 * @param card_serial
	 * @return
	 * @throws SQLException
	 */
	public Bankcard bkd_FindCard_id(String card_serial) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from bankcard where card_serial = ?",new BeanHandler<Bankcard>(Bankcard.class), card_serial);
	}
	
	/*
	 * 更新转账后的银行卡余额信息
	 */
	public void upd_bankcard_balance(String outcard,String descard, double tra_money) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"update bankcard set card_balance =card_balance - ? where card_id = ?",tra_money,outcard);
		qr.update(ManagerThreadLocal.getConnection(),"update bankcard set card_balance =card_balance + ? where card_id = ?",tra_money,descard);
	
	}
	
	/*
	 * 更新转账后的银行卡余额信息
	 */
	public void upd_bankcard_balance(String card,double tra_money) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"update bankcard set current = ? where card_id = ? ",tra_money,card);
	}
	
	/**
	 * 查询银行卡信息
	 * @param card_id
	 * @return
	 * @throws SQLException
	 */
	public Bankcard findbkd_card_serial(String card_id) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from bankcard where card_id=?",new BeanHandler<Bankcard>(Bankcard.class), card_id);
	}
	
	/*
	 * 查询付款卡号的序列号
	 */
		public Bankcard finpay_card_serial(String card_id) throws SQLException {
			QueryRunner qr = new QueryRunner();
			return qr.query(ManagerThreadLocal.getConnection(), "select * from bankcard bkd where card_id=?",new BeanHandler<Bankcard>(Bankcard.class), card_id);
		}
	/*
	 * 更新/储蓄卡缴费后的银行卡余额
	 */
		public void upd_paycard_balance(String card_id, double pay_money) throws SQLException {
			QueryRunner qr = new QueryRunner();
			qr.update(ManagerThreadLocal.getConnection(),"update bankcard set current = ? where card_id = ? ",pay_money,card_id);
		}
		/**
		 * 储蓄卡信息查询
		 * @param card_id
		 * @return
		 * @throws SQLException
		 */
		public Bankcard findcard_serial(String card_id) throws SQLException {
			QueryRunner qr = new QueryRunner();
			return qr.query(ManagerThreadLocal.getConnection(), "select * from bankcard  where card_id = ?",new BeanHandler<Bankcard>(Bankcard.class), card_id);
			
		}
	/*
	 * 取款 卡号的死期余额 扣除SQL语句
	 */
		public void updatebankcard_death(String card_serial, double temp) throws SQLException {
			QueryRunner qr = new QueryRunner();
			qr.update(ManagerThreadLocal.getConnection(),"update bankcard set death = ? where card_serial = ? ",temp,card_serial);
		
		}
		
		/**
		 * 储蓄卡信息查询
		 * @param card_id
		 * @return
		 * @throws SQLException
		 */
		public Bankcard query_bankcard(String card_id) throws SQLException {
			QueryRunner qr = new QueryRunner();
			return qr.query(ManagerThreadLocal.getConnection(), "select * from bankcard where card_id=?",new BeanHandler<Bankcard>(Bankcard.class), card_id);
		}
		
		/**
		 * 更新储蓄卡余额
		 * @param card_id
		 * @param temp
		 * @throws SQLException
		 */
		public void upbankcard_balance(String card_id, double temp) throws SQLException {
			QueryRunner qr = new QueryRunner();
			qr.update(ManagerThreadLocal.getConnection(),"update bankcard set card_balance = card_balance - ? where card_id = ? ",temp,card_id);
		
		}
		
		/**
		 * 储蓄卡信息查询
		 * @param tocard_id
		 * @return
		 * @throws SQLException
		 */
		public Bankcard bkd_cif_query(String tocard_id) throws SQLException {
			QueryRunner qr = new QueryRunner();
			return qr.query(ManagerThreadLocal.getConnection(), "select * from client_info ci join bankcard bc on ci.client_id =bc.client_id where bc.card_id = ?",new BeanHandler<Bankcard>(Bankcard.class), tocard_id);
		}
		
		/**
		 * 更新储蓄卡活期余额
		 * @param card_id
		 * @param tra_money
		 * @throws SQLException
		 */
		public void upd_bankcard_current(String card_id,  double tra_money) throws SQLException {
			QueryRunner qr = new QueryRunner();
			qr.update(ManagerThreadLocal.getConnection(),"update bankcard set current = ? where card_id = ?",tra_money,card_id);
		
		
		}
		
		/**
		 * 更新储蓄卡活期余额
		 * @param card_id
		 * @param bcurrent
		 * @throws SQLException
		 */
		public void upcurrent(String card_id, double bcurrent) throws SQLException {
			QueryRunner qr = new QueryRunner();
			qr.update(ManagerThreadLocal.getConnection(),"update bankcard set current = ? where card_id = ? ",bcurrent,card_id);
		
		}
		
		/**
		 * 更新储蓄卡定期余额
		 * @param card_id
		 * @param bdeath
		 * @throws SQLException
		 */
		public void updeath(String card_id, double bdeath) throws SQLException {
			QueryRunner qr = new QueryRunner();
			qr.update(ManagerThreadLocal.getConnection(),"update bankcard set death = ? where card_id = ? ",bdeath,card_id);
		
		}
		
		/**
		 * 解绑储蓄卡
		 * @param card_id
		 * @throws SQLException
		 */
		public void del_card(String card_id) throws SQLException {
			QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
			qr.update("delete from bankcard where card_id = ?",card_id);
		}
		
		/**
		 *添加储蓄卡信息 
		 */
		public void addBankCard(String card_serial, String uid, String bclient_id,
				String card_id, String int_serial, String card_ps,
				String card_balance, String current, String death)
				throws SQLException {
			// TODO Auto-generated method stub
			
		}
		
		
}

package com.shao.DAO;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
/**
 * @author HGX
 *数据访问层 接口
 *转账表 
 *
 */
public interface TransferDao {


	/*
	 * 添加转账交易记录
	 */
		public void addtra_record(String card_serial, String client_id,
				String des_card, String tra_money) throws SQLException, ClassNotFoundException;
		
		/**
		 * 添加转账记录表
		 * @param card_serial
		 * @param client_id
		 * @param tocard
		 * @param formatmoney
		 * @param fee
		 * @throws ClassNotFoundException
		 * @throws SQLException
		 */
		public void addtra_out_record(String card_serial, String client_id,
				String tocard, String formatmoney,double fee) throws ClassNotFoundException, SQLException;
}

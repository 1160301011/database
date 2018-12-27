package com.shao.DAO;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
/**
 * @author HGX
 *���ݷ��ʲ� �ӿ�
 *ת�˱� 
 *
 */
public interface TransferDao {


	/*
	 * ���ת�˽��׼�¼
	 */
		public void addtra_record(String card_serial, String client_id,
				String des_card, String tra_money) throws SQLException, ClassNotFoundException;
		
		/**
		 * ���ת�˼�¼��
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

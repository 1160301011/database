package com.shao.DAO.impl;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.shao.DAO.ConsumptionDao;


/**
 * 
 * @author hgx
 *������ѱ�
 *���ݲ�ӿ�ʵ��
 */
public class ConsumptionDaoImpl implements ConsumptionDao {
	
	/*
	 * ������п����Ѽ�¼��(�ӿ�ʵ����)
	 */
		public void addConsumtion_phoenrecord(String card_serial, double trade_money) throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/mobilebank3?useUnicode=true&characterEncoding=utf8";
			String username = "root";
			String password = "ty199785";
			Connection con = (Connection) DriverManager.getConnection(url,username,password);
			
			Statement stat = (Statement) con.createStatement();
			String insert_Com = "insert into CONSUMPTION (con_serial,card_serial,con_money,con_sort) values(CONCAT('001',ceiling(rand()*10000+10000)),"+card_serial+","+trade_money+",'1')";
			stat.executeUpdate(insert_Com);

			System.out.println("���Ѽ�¼�����ɹ�");
			
			stat.close();
			con.close();
		}
		/*
		 * ���뽻ͨ�ɷ����Ѽ�¼
		 */
		public void addConsumtion_trarecord(String card_serial, double com_money) throws ClassNotFoundException, SQLException {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/mobilebank3?useUnicode=true&characterEncoding=utf8";
			String username = "root";
			String password = "ty199785";
			Connection con = (Connection) DriverManager.getConnection(url,username,password);
			
			Statement stat = (Statement) con.createStatement();
			String insert_Com = "insert into CONSUMPTION (con_serial,card_serial,con_money,con_sort) values(CONCAT('001',ceiling(rand()*10000+10000)),"+card_serial+","+com_money+",'2')";
			stat.executeUpdate(insert_Com);

			System.out.println("���Ѽ�¼�����ɹ�");
			
			stat.close();
			con.close();
		}
		/*
		 * ��������ɷ� ���Ѽ�¼
		 */
		public void addConsumtion_liferecord(String card_serial, double com_money) throws ClassNotFoundException, SQLException {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/mobilebank3?useUnicode=true&characterEncoding=utf8";
			String username = "root";
			String password = "ty199785";
			Connection con = (Connection) DriverManager.getConnection(url,username,password);
			
			Statement stat = (Statement) con.createStatement();
			String insert_Com = "insert into CONSUMPTION (con_serial,card_serial,con_money,con_sort) values(CONCAT('001',ceiling(rand()*10000+10000)),"+card_serial+","+com_money+",'3')";
			stat.executeUpdate(insert_Com);

			System.out.println("���Ѽ�¼�����ɹ�");
			
			stat.close();
			con.close();
		}
	/*
	 * ����ҽ�����Ѽ�¼
	 */
		public void addConsumtion_medirecord(String card_serial, double com_money) throws ClassNotFoundException, SQLException {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/mobilebank3?useUnicode=true&characterEncoding=utf8";
			String username = "root";
			String password = "ty199785";
			Connection con = (Connection) DriverManager.getConnection(url,username,password);
			
			Statement stat = (Statement) con.createStatement();
			String insert_Com = "insert into CONSUMPTION (con_serial,card_serial,con_money,con_sort) values(CONCAT('001',ceiling(rand()*10000+10000)),"+card_serial+","+com_money+",'4')";
			stat.executeUpdate(insert_Com);

			System.out.println("���Ѽ�¼�����ɹ�");
			
			stat.close();
			con.close();
		}
}

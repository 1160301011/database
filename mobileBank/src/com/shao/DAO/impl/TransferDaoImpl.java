package com.shao.DAO.impl;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.shao.DAO.TransferDao;
import com.shao.model.Bankuser;
import com.shao.util.ManagerThreadLocal;


/**
 * 
 * @author hgx
 *转账表
 *数据层接口实例
 */
public class TransferDaoImpl implements TransferDao {

	/*
	 * 添加转账交易记录
	 */
		public void addtra_record(String card_serial, String client_id,
				String des_card, String tra_money) throws SQLException, ClassNotFoundException {
//			QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
//			qr.update("insert into bank_user (username,userpwd,userbalance) values (?,?,?)",new BeanHandler<bank_user>(bank_user.class),username,userpwd,0);
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/mobilebank3?useUnicode=true&characterEncoding=utf8";
			String username = "root";
			String password = "ty199785";
			Connection con = (Connection) DriverManager.getConnection(url,username,password);
			
			
//			SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
//			Date dadate = new Date();
			//获得系统时间
//			String nowDaDate = sdf.format(dadate);
//			Timestamp daTs = Timestamp.valueOf(nowDaDate);
			
			Statement stat = (Statement) con.createStatement();
			System.out.println("转账测试2");
			String insert_tra = "insert into TRANSFER (trade_id,card_serial,client_id,to_cardid,trade_money,int_out) values(CONCAT('T911',ceiling(rand()*10000+10000)),"+card_serial+","+client_id+","+des_card+","+tra_money+",'1')";
			System.out.println("转账测试3");
//			stat.executeUpdate("insert into TRANSFER (trade_id) values(CONCAT('999',ceiling(rand()*10000+10000)))");
			stat.executeUpdate(insert_tra);
//			String str11 = "CONCAT('666',ceiling(rand()*10000+10000))";
//			String sql = "insert into TRANSFER (trade_id,client_id,to_cardid,trade_money,trade_time) values(?,?,?,?,?)";
////			String sql2 ="update TRANSFER set trade_time = ? where trade_id = ?";
//			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
//			pst.setObject(1, "+str11+");
//			pst.setObject(2, client_id);
//			pst.setObject(3, des_card);
//			pst.setObject(4, tra_money);
//			pst.setObject(5, daTs);
//			pst.executeUpdate();
			System.out.println("转账记录表插入成功");
//			System.out.println(daTs);
//			String sql = "Select * from TRANSFER";
//			ResultSet rs = stat.executeQuery(sql);
//			while (rs.next()) {
//			System.out.println(rs.getString("trade_id"));
//			}
//			rs.close();
			stat.close();
//			pst.close();
			con.close();
	}
		
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
				String tocard, String formatmoney,double fee) throws ClassNotFoundException, SQLException {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/mobilebank3?useUnicode=true&characterEncoding=utf8";
			String username = "root";
			String password = "ty199785";
			Connection con = (Connection) DriverManager.getConnection(url,username,password);
//			SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
//			Date dadate = new Date();
			//获得系统时间
//			String nowDaDate = sdf.format(dadate);
//			Timestamp daTs = Timestamp.valueOf(nowDaDate);
			
			Statement stat = (Statement) con.createStatement();
			String insert_tra = "insert into TRANSFER (trade_id,card_serial,client_id,to_cardid,trade_money,trade_remark,int_out,trade_fee) values(CONCAT('911',ceiling(rand()*10000+10000)),"+card_serial+","+client_id+","+tocard+","+formatmoney+",'跨行','2',"+fee+")";
//			stat.executeUpdate("insert into TRANSFER (trade_id) values(CONCAT('999',ceiling(rand()*10000+10000)))");
			stat.executeUpdate(insert_tra);
//			String str11 = "CONCAT('666',ceiling(rand()*10000+10000))";
//			String sql = "insert into TRANSFER (trade_id,client_id,to_cardid,trade_money,trade_time) values(?,?,?,?,?)";
////			String sql2 ="update TRANSFER set trade_time = ? where trade_id = ?";
//			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
//			pst.setObject(1, "+str11+");
//			pst.setObject(2, client_id);
//			pst.setObject(3, des_card);
//			pst.setObject(4, tra_money);
//			pst.setObject(5, daTs);
//			pst.executeUpdate();
			System.out.println("转账记录表插入成功");
//			System.out.println(daTs);
//			String sql = "Select * from TRANSFER";
//			ResultSet rs = stat.executeQuery(sql);
//			while (rs.next()) {
//			System.out.println(rs.getString("trade_id"));
//			}
			stat.close();
			con.close();
		}
}

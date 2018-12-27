package com.shao.DAO;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.shao.model.Bankcard;
import com.shao.model.User;
import com.shao.model.Bankuser;
import com.shao.model.Clientinfo;
import com.shao.util.C3P0Util;
import com.shao.util.ManagerThreadLocal;
/**
 * @author HGX
 *数据访问层 接口
 *储蓄卡表 
 *
 */

public interface BankcardDao {

	// public Bankcard bkd_FindCard_id(String card_serial) throws SQLException;
	//
	// public void addBankCard(String card_serial,String uid, String bclient_id,
	// String card_id,
	// String int_serial, String card_ps,String current, String death) throws
	// SQLException;
	//
	// public Bankcard findbkd_Descard(String card_id) throws SQLException;
	//
	// public Bankcard findbkd_Descard1(String card_id) throws SQLException;
	//
	// public void upd_bankcard_balance(String card,double tra_money) throws
	// SQLException;
	//
	//
	// public Bankcard findbkd_card_serial(String card_id) throws SQLException;
	//
	// public Bankcard finpay_card_serial(String card_id) throws SQLException;
	//
	//
	// public Bankcard query_bankcard(String card_id) throws SQLException;
	//
	//
	// public void upbankcard_balance(String card_id, double temp) throws
	// SQLException;
	//
	// public Bankcard bkd_cif_query(String tocard_id) throws SQLException;

	public void addBankCard(String card_serial, String uid, String bclient_id,
			String card_id, String int_serial, String card_ps, String current,
			String death) throws SQLException;
	
	public Bankcard bkd_FindCard_id(String card_serial) throws SQLException;
	
	public void upd_bankcard_balance(String outcard,String descard, double tra_money) throws SQLException;
	
	public void upd_bankcard_balance(String card,double tra_money) throws SQLException;
	
	public Bankcard findbkd_card_serial(String card_id) throws SQLException;
	
	public Bankcard finpay_card_serial(String card_id) throws SQLException;
	
	
	public void upd_paycard_balance(String card_id, double pay_money) throws SQLException;
	
	public Bankcard findcard_serial(String card_id) throws SQLException;
	
	public void updatebankcard_death(String card_serial, double temp) throws SQLException;
	
	public Bankcard query_bankcard(String card_id) throws SQLException;
	
	public void upbankcard_balance(String card_id, double temp) throws SQLException;
	
	
	public Bankcard bkd_cif_query(String tocard_id) throws SQLException;
	
	public void upd_bankcard_current(String card_id,  double tra_money) throws SQLException;
	
	public void upcurrent(String card_id, double bcurrent) throws SQLException;
	
	public void updeath(String card_id, double bdeath) throws SQLException;
	
	public void del_card(String card_id) throws SQLException;
	
	public void addBankCard(String card_serial, String uid, String bclient_id,
			String card_id, String int_serial, String card_ps,
			String card_balance, String current, String death)
			throws SQLException;
	
	
	
	
	
	
	
	
	

}

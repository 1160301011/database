package com.shao.Service.impl;

import java.sql.SQLException;
import javax.swing.JTextField;
import com.shao.DAO.impl.AccountDaoImpl;
import com.shao.Service.AccountService;
import com.shao.model.User;
import com.shao.util.ManagerThreadLocal;
import com.shao.model.Bankinfo;
import com.shao.model.Bankcard;
import com.shao.model.Clientinfo;
import com.shao.model.Bankuser;
import com.shao.model.Creditcard;
import com.shao.model.Payee;
import com.shao.model.T_productchosen;
/**
 * @author HGX
 *业务逻辑接口实现类
 *账户操作服务 
 *
 */

public class AccountServiceImpl implements AccountService {
	/**
	 * @author Administrato 添加用户
	 * 
	 */
	AccountDaoImpl account = new AccountDaoImpl();
	
	/**
	 * 添加用户信息
	 */
	public void addUser(String name, String password) {
		try {
			ManagerThreadLocal.startTransaction();
			account.addUser(name, password);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}

	/**
	 * @author Administrator 添加用户个人信息
	 */

	public void updateClientinfo(String client_id,String uid, String client_name, String client_idcard,
			 String client_phone,String client_addr) {
		try {
			ManagerThreadLocal.startTransaction();
			account.updateClientinfo(client_id,uid, client_name, client_idcard,
					client_phone,client_addr);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}

	/**
	 * 查询用户账号密码
	 */
	public Bankuser bankuser_check(String username, String userpwd) {
		Bankuser bu = new Bankuser();
		try {
			ManagerThreadLocal.startTransaction();
			bu = account.findbank_userquery(username, userpwd);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		return bu;
	}
	/**
	 * 用户查询
	 */
	public User query(String name) {
		User u = new User();
		try {
			ManagerThreadLocal.startTransaction();
			u = account.findUserByName(name);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		return u;
	}

	/**
	 * 用户信息查询
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public Bankuser bu_query(String username) throws SQLException {
		Bankuser bu = new Bankuser();
		try {
			ManagerThreadLocal.startTransaction();
			bu = account.findbank_user_Name(username);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		return bu;
	}

	/**
	 * 用户信息查询
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	public Bankuser bu_queryuid(String uid) throws SQLException {
		Bankuser bu = new Bankuser();
		try {
			ManagerThreadLocal.startTransaction();
			bu = account.findbank_user_uid(uid);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}

		return bu;
	}

	/**
	 * @author hgx 查询用户个人信息 以免重复添加
	 */
	public Clientinfo cquery(String client_id) {
		Clientinfo c = new Clientinfo();
		try {
			ManagerThreadLocal.startTransaction();
			c = account.findClientID(client_id);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		return c;
	}
	/**
	 * 更新账户余额
	 */
	public void update(String username, double userbalance) {
		// user u = new user();
		Bankuser bu = new Bankuser();
		try {
			ManagerThreadLocal.startTransaction();
			account.updateAccount(username, userbalance);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	
	
	
	
	
/*
 * 初始转账方法  只涉及到 bank_user 表
 * (non-Javadoc)
 * @see com.shao.Service.AccountService#transfer(java.lang.String, java.lang.String, double)
 */
	public void transfer(String FromName, String ToName, double userbalance) {
		try {
			ManagerThreadLocal.startTransaction();
			Bankuser bankuer_from = account.findbank_user_Name(FromName);
			Bankuser bankuer_to = account.findbank_user_Name(ToName);

			bankuer_from.setUserbalance(bankuer_from.getUserbalance()
					- userbalance);
			bankuer_to.setUserbalance(bankuer_to.getUserbalance() + userbalance);

			account.updateAccount(FromName, bankuer_from.getUserbalance());
			account.updateAccount(ToName, bankuer_to.getUserbalance());
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	/**
	 * 用户信息查询
	 */
	public User check(String name, String password) {
		User u = new User();
		try {
			ManagerThreadLocal.startTransaction();
			u = account.query(name, password);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		return u;
	}

	//
	public Clientinfo cfo_check(String username) throws SQLException {
		Clientinfo c = new Clientinfo();
		try {
			ManagerThreadLocal.startTransaction();
			c = account.cfo_query(username);
			ManagerThreadLocal.commit();
		} finally {
			ManagerThreadLocal.close();
		}
		return c;
	}

	public Payee pye_query(String payee_card) {
		Payee pye = new Payee();
		try {
			ManagerThreadLocal.startTransaction();
			pye = account.pye_findByPayee_info(payee_card);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ManagerThreadLocal.close();
		}
		return pye;
	}

	public void addpayee(String payee_uid, String payee_name,
			String payee_card, String belong_bank) {
		try {
			ManagerThreadLocal.startTransaction();
			account.addpayinfo(payee_uid, payee_name, payee_card,
					belong_bank);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	/**
	 * 注册账户
	 */
	public void addbank_User(String username, String userpwd) {
		try {
			ManagerThreadLocal.startTransaction();
			account.addbank_User(username, userpwd);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	/**
	 * 更新账户信息
	 * @param bank_uname
	 * @param newpwdField
	 * @throws SQLException
	 */
	public void updateUser(String bank_uname, String newpwdField)
			throws SQLException {
		Bankuser bu = new Bankuser();
		User user = new User();
		try {
			ManagerThreadLocal.startTransaction();
			account.updatebank_User(bank_uname, newpwdField);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	/**
	 * 
	 * @param card_serial
	 * @return
	 * @throws SQLException
	 * 银行卡信息查询
	 */
	public Bankcard bcd_query(String card_serial) throws SQLException {
		Bankcard bkd = new Bankcard();
		try {
			ManagerThreadLocal.startTransaction();
			bkd = account.bkd_FindCard_id(card_serial);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		return bkd;
	}
	
	/**
	 * 
	 * @param card_serial
	 * @return
	 * @throws SQLException
	 * 添加银行卡信息
	 */

	public void addBankCard(String card_serial,String uid, String bclient_id, String card_id,
			String int_serial, String card_ps,String current, String death) throws SQLException {
		try {
			ManagerThreadLocal.startTransaction();
			account.addBankCard(card_serial,uid, bclient_id, card_id, int_serial, card_ps,current, death);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}


	public Bankinfo bif_query(String strbank_id) {
		Bankinfo bif = new Bankinfo();
		try {
			ManagerThreadLocal.startTransaction();
			bif = account.bif_Findbank_id(strbank_id);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		return bif;
		
	}
/*
 * 缴费交易储蓄卡查询
 */
	public Bankcard tra_bkd_query(String descard) {
		Bankcard bkd = new Bankcard();
		try {
			ManagerThreadLocal.startTransaction();
			bkd = account.findbkd_Descard(descard);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		return bkd;
	}
	/*
	 * 缴费交易信用卡卡号查询
	 */
	public Creditcard cre_card_query(String descard) {
		Creditcard crd = new Creditcard();
		try {
			ManagerThreadLocal.startTransaction();
			crd = account.findcrd_Descard(descard);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		return crd;
	}
	
	/**
	 * 
	 * @param outcard
	 * @param descard
	 * @param tra_money
	 * 储蓄卡转账交易
	 */
	
	public void bkdtransfer(String outcard, String descard, double tra_money) {
		try {
			ManagerThreadLocal.startTransaction();
			Bankcard bkd_from = account.findbkd_Descard(outcard);
			Bankcard bkd_to = account.findbkd_Descard(descard);

			bkd_from.setCurrent(bkd_from.getCurrent()
					- tra_money);
			bkd_to.setCurrent(bkd_to.getCurrent() + tra_money);
			account.upd_bankcard_balance(outcard, bkd_from.getCurrent());
			account.upd_bankcard_balance(descard, bkd_to.getCurrent());
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		
	}
/*
 * 
 * 查询转账卡号的序列号
 */
	public Bankcard query_card_serial(String card_id) {
		Bankcard bkd = new Bankcard();
		try {
			ManagerThreadLocal.startTransaction();
			bkd = account.findbkd_card_serial(card_id);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		return bkd;
		}
	/**
	 * 添加转账交易表信息
	 * @param card_serial
	 * @param client_id
	 * @param des_card
	 * @param tra_money
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void addtra_record(String card_serial, String client_id,
			String des_card, String tra_money) throws SQLException, ClassNotFoundException{
		try {
			ManagerThreadLocal.startTransaction();
			account.addtra_record(card_serial,client_id, des_card, tra_money);
			System.out.println("转账测试1");
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		
	}
/*
 * 查询付款卡号的序列号
 */
	public Bankcard query_paycard_serial(String card_id) {
		Bankcard bkd = new Bankcard();
		try {
			ManagerThreadLocal.startTransaction();
			bkd = account.finpay_card_serial(card_id);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		return bkd;
		}
	
	/*
	 * 取款中 查询卡号的序列号
	 */
	public Bankcard card_query(String card_id) {
		Bankcard bkd = new Bankcard();
		try {
			ManagerThreadLocal.startTransaction();
			bkd = account.findcard_serial(card_id);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		return bkd;
		}
	
	/**
	 * 信用卡信息查询
	 * @param paycard
	 * @return
	 */
	public Creditcard query_crd_serial(String paycard) {
		Creditcard crd = new Creditcard();
		try {
			ManagerThreadLocal.startTransaction();
			crd = account.findCredit_card_serial(paycard);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		return crd;
		}
/*
 * 储蓄卡消费交易扣款计算方法
 */
	public void paybill_trade(String card_id, double paymoney) {
		try {
			ManagerThreadLocal.startTransaction();
			Bankcard paycard = account.findbkd_Descard(card_id);
			paycard.setCurrent(paycard.getCurrent()
					- paymoney);
			account.upd_paycard_balance(card_id, paycard.getCurrent());
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		
	}
	
	
	/*
	 * 信用卡消费交易扣款计算方法
	 */
	
	
/*
 * 添加银行卡缴费记录表
 */
	public void addConsumtion_phone(String card_serial, double com_money)throws Exception {
		try {
			ManagerThreadLocal.startTransaction();
			account.addConsumtion_phoenrecord(card_serial,com_money);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		
	}

	public void addConsumtion_traffic(String card_serial, double com_money)throws Exception {
		try {
			ManagerThreadLocal.startTransaction();
			account.addConsumtion_trarecord(card_serial,com_money);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		
	}

	public void addConsumtion_life(String card_serial, double com_money)throws Exception {
		try {
			ManagerThreadLocal.startTransaction();
			account.addConsumtion_liferecord(card_serial,com_money);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}

	public void addConsumtion_medical(String card_serial, double com_money) throws Exception
	{
		try {
			ManagerThreadLocal.startTransaction();
			account.addConsumtion_medirecord(card_serial,com_money);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		}


/*
 * 添加信用卡
 */
	public void addcredit_card(String uid,String client_id,String cre_pwd, String return_card, String cre_closepay,
			String cre_auto,String cre_balance) throws ClassNotFoundException{
		try {
			ManagerThreadLocal.startTransaction();
			account.addCredit_Card(uid,client_id,cre_pwd,return_card, cre_closepay, cre_auto, cre_balance);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	/*
	 * 更新死期余额
	 */

	public void update_card_death(String card_serial, double temp) {
		Bankcard bck = new Bankcard();
		try {
			ManagerThreadLocal.startTransaction();
			account.updatebankcard_death(card_serial, temp);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		
	}

	/*
	 * 取款后 账户余额更新 
	 */
	public void update_bankuser_balance(String username, double temp2) {
		try {
			ManagerThreadLocal.startTransaction();
			account.updatebankuser_balance(username, temp2);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
/*
 * 添加取款单
 */
	public void insert_WITHDRAWAL(String card_serial, String withmoney,
			String interest, String totalmoney, double temp) throws ClassNotFoundException {
		try {
			ManagerThreadLocal.startTransaction();
			account.addinsert_WITHDRAWAL(card_serial,withmoney,interest,totalmoney, temp);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
			
	}
	/**
	 * 超市商品表信息
	 * @param goods_id
	 * @return
	 */
	public T_productchosen query_product(String goods_id) {
		T_productchosen tp = new T_productchosen();
		try {
			ManagerThreadLocal.startTransaction();
			tp = account.find_product(goods_id);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}

		return tp;
		}

	/**
	 * 信用卡信息查询
	 * @param cre_id
	 * @return
	 */
	public Creditcard query_creditcard_info(String cre_id) {
		Creditcard crd = new Creditcard();
		try {
			ManagerThreadLocal.startTransaction();
			crd = account.find_crecard_info(cre_id);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}

		return crd;
		
	}
	
	public void paybill_cretrade(String cre_serial, double paymoney1) {
		try {
			ManagerThreadLocal.startTransaction();
			Creditcard paycard1 = account.findcrd_Descard(cre_serial);
			paycard1.setCre_available(paycard1.getCre_available()
					- paymoney1);
			account.upd_paycard_cre_available(cre_serial, paycard1.getCre_available());
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}

	public void update_cre_available(String cre_id, double temp) {
		try {
			ManagerThreadLocal.startTransaction();
			account.update_cre_available(cre_id, temp);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}

	public void update_cre_bill(String cre_id, double totalmoney) {
		try {
			ManagerThreadLocal.startTransaction();
			account.update_cre_bill(cre_id, totalmoney);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	
	/**
	 * 信用卡消费记录
	 * @param cre_id
	 * @param summoeny
	 * @param cre_serial
	 * @throws ClassNotFoundException
	 */
	public void add_cre_billrecord(String cre_id,double summoeny,String cre_serial) throws ClassNotFoundException {
		try {
			ManagerThreadLocal.startTransaction();
			account.add_cre_billrecord(cre_id,summoeny,cre_serial);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	/**
	 * 添加客户信息
	 * @param username
	 */
	public void addClientinfo2(String username) {
		try {
			ManagerThreadLocal.startTransaction();
			account.addClientinfo2(username);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	/**
	 * 信用卡信息查询
	 * @param cre_id
	 * @return
	 */
	public Creditcard query_cre_card(String cre_id) {
		Creditcard crd = new Creditcard();
		try {
			ManagerThreadLocal.startTransaction();
			crd = account.query_cre_card(cre_id);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}

		return crd;
		}

	/**
	 * 
	 * @param card_id
	 * @return
	 * 银行卡信息查询
	 */
	public Bankcard query_bankcard(String card_id) {
		Bankcard bkd = new Bankcard();
		try {
			ManagerThreadLocal.startTransaction();
			bkd = account.query_bankcard(card_id);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}

		return bkd;}

	
	//更新储蓄卡 还款信用卡额度后的 余额
	public void upbankcard_balance(String card_id, double temp) {
		try {
			ManagerThreadLocal.startTransaction();
			account.upbankcard_balance(card_id, temp);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	//还款后信用卡可用额度更新
	public void upcredit_card_available(double cre_balance, String cre_id) {
		try {
			ManagerThreadLocal.startTransaction();
			account.upcredit_card_available(cre_balance, cre_id);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	/**
	 * 
	 * @param tocard_id
	 * @return
	 * 银行卡和客户信息查询
	 */
	public Bankcard bkd_cif_query(String tocard_id) {
		Bankcard bkd = new Bankcard();
		try {
			ManagerThreadLocal.startTransaction();
			bkd = account.bkd_cif_query(tocard_id);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		return bkd;
		
	}
	/**
	 * 
	 * @param fromcard
	 * @param tocard
	 * @param firmoney
	 * @param scdmoney
	 * 储蓄卡转账交易
	 */
	public void bkdtransfer_out(String fromcard, String tocard, double firmoney,
			double scdmoney) {
		try {
			ManagerThreadLocal.startTransaction();
			Bankcard bkd_from = account.findbkd_Descard(fromcard);
			Bankcard bkd_to = account.findbkd_Descard(tocard);

			bkd_from.setCurrent(bkd_from.getCurrent()
					- scdmoney);
			bkd_to.setCurrent(bkd_to.getCurrent() + firmoney);
			
			account.upd_bankcard_current(fromcard, bkd_from.getCurrent());
			account.upd_bankcard_current(tocard, bkd_to.getCurrent());
			
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	/**
	 * 添加转账信息表
	 * @param card_serial
	 * @param client_id
	 * @param tocard
	 * @param formatmoney
	 * @param fee
	 * @throws ClassNotFoundException
	 */
	public void addtra_out_record(String card_serial, String client_id,
			String tocard, String formatmoney,double fee) throws ClassNotFoundException {
		try {
			ManagerThreadLocal.startTransaction();
			account.addtra_out_record(card_serial,client_id, tocard, formatmoney,fee);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}

	/*
	 * 账号挂失设置
	 */
	public void upuser_status(String username) {
		try {
			ManagerThreadLocal.startTransaction();
			account.upuser_status(username);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	/**
	 * 客户信息查询
	 * @param uid
	 * @return
	 */
	public Clientinfo cinfo_query(String uid) {
		Clientinfo cif = new Clientinfo();
		try {
			ManagerThreadLocal.startTransaction();
			cif = account.cinfo_query(uid);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
		return cif;
		}
	/**
	 * 找回密码 状态设置
	 * @param username
	 */
	public void Foun_upuser_sta(String username) {
		try {
			ManagerThreadLocal.startTransaction();
			account.Foun_upuser_sta(username);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	
	/**
	 * 更新储蓄卡活期余额
	 * @param card_id
	 * @param bcurrent
	 */
	public void upcurrent(String card_id, double bcurrent) {
		Bankcard bkd = new Bankcard();
		try {
			ManagerThreadLocal.startTransaction();
			account.upcurrent(card_id, bcurrent);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	/**
	 * 更新储蓄卡定期金额
	 * @param card_id
	 * @param bdeath
	 */
	public void updeath(String card_id, double bdeath) {
		Bankcard bkd = new Bankcard();
		try {
			ManagerThreadLocal.startTransaction();
			account.updeath(card_id, bdeath);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	
/*
 * 解绑储蓄卡
 */
	public void del_card(String card_id ) {
		try {
			ManagerThreadLocal.startTransaction();
			account.del_card(card_id);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}

	public void addBankCard(String card_serial, String uid, String bclient_id,
			String card_id, String int_serial, String card_ps,
			String card_balance, String current, String death)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @author hgx
	 * 限制账户交易行为
	 */
	public void upuser_status2(String username) {
		try {
			ManagerThreadLocal.startTransaction();
			account.upuser_status2(username);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}
	/**
	 * @author hgx
	 * 解除限制账户交易行为
	 */
	public void upuser_status3(String username) {
		try {
			ManagerThreadLocal.startTransaction();
			account.upuser_status3(username);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}

	/**
	 * @author hgx
	 *查找收款人信息
	 */
//	public payee pye_query_QPY(String uid) {
//		payee pye = new payee();
//		try {
//			ManagerThreadLocal.startTransaction();
//			pye = account.pye_query_QPY(uid);
//			ManagerThreadLocal.commit();
//		} catch (SQLException e) {
//			ManagerThreadLocal.rollback();
//		} finally {
//			ManagerThreadLocal.close();
//		}
//		return pye;
//		}
	
	}



	


package com.shao.Service.impl;

import com.shao.Service.BankcardService;

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
 *银行卡操作服务 
 *
 */

public class BankcardServiceImpl implements BankcardService {
	AccountDaoImpl account = new AccountDaoImpl();

	/**
	 * 
	 * @param card_serial
	 * @return
	 * @throws SQLException
	 *             银行卡信息查询
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
	 *             添加银行卡信息
	 */
	public void addBankCard(String card_serial, String uid, String bclient_id,
			String card_id, String int_serial, String card_ps, String current,
			String death) throws SQLException {
		try {
			ManagerThreadLocal.startTransaction();
			account.addBankCard(card_serial, uid, bclient_id, card_id,
					int_serial, card_ps, current, death);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
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

	/**
	 * 
	 * @param outcard
	 * @param descard
	 * @param tra_money
	 *            储蓄卡转账交易
	 */

	public void bkdtransfer(String outcard, String descard, double tra_money) {
		try {
			ManagerThreadLocal.startTransaction();
			Bankcard bkd_from = account.findbkd_Descard(outcard);
			Bankcard bkd_to = account.findbkd_Descard(descard);

			bkd_from.setCurrent(bkd_from.getCurrent() - tra_money);
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
	/**
	 * 更新取款后的账户余额
	 */
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
}

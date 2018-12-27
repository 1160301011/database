package com.shao.Service.impl;

import java.sql.SQLException;

import com.shao.DAO.CreditcardDao;
import com.shao.DAO.impl.AccountDaoImpl;
import com.shao.Service.CreditcardService;
import com.shao.model.Creditcard;
import com.shao.util.ManagerThreadLocal;
/**
 * @author HGX
 *ҵ���߼��ӿ�ʵ����
 *���ÿ��������� 
 *
 */
public class CreditcardServiceImpl implements CreditcardService {
	AccountDaoImpl account = new AccountDaoImpl();

	/*
	 * �ɷѽ������ÿ����Ų�ѯ
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
	 * ���ÿ���Ϣ��ѯ
	 * 
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
	 * ������ÿ�
	 */
	public void addcredit_card(String uid, String client_id, String cre_pwd,
			String return_card, String cre_closepay, String cre_auto,
			String cre_balance) throws ClassNotFoundException {
		try {
			ManagerThreadLocal.startTransaction();
			account.addCredit_Card(uid, client_id, cre_pwd, return_card,
					cre_closepay, cre_auto, cre_balance);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}

	/**
	 * ���ÿ���Ϣ��ѯ
	 * 
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

	/**
	 * ���ÿ����Ѽ�¼
	 * 
	 * @param cre_id
	 * @param summoeny
	 * @param cre_serial
	 * @throws ClassNotFoundException
	 */
	public void add_cre_billrecord(String cre_id, double summoeny,
			String cre_serial) throws ClassNotFoundException {
		try {
			ManagerThreadLocal.startTransaction();
			account.add_cre_billrecord(cre_id, summoeny, cre_serial);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			ManagerThreadLocal.rollback();
		} finally {
			ManagerThreadLocal.close();
		}
	}

	/**
	 * ���ÿ���Ϣ��ѯ
	 * 
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

	// ��������ÿ����ö�ȸ���
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

}

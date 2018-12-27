package com.shao.Service;

import com.shao.model.Creditcard;
/**
 * @author HGX
 *业务逻辑接口
 *信用卡操作服务 
 *
 */
public interface CreditcardService {

	public Creditcard cre_card_query(String descard);

	public Creditcard query_crd_serial(String paycard);

	public void addcredit_card(String uid, String client_id, String cre_pwd,
			String return_card, String cre_closepay, String cre_auto,
			String cre_balance) throws ClassNotFoundException;

	public Creditcard query_creditcard_info(String cre_id);

	public void add_cre_billrecord(String cre_id, double summoeny,
			String cre_serial) throws ClassNotFoundException;

	public Creditcard query_cre_card(String cre_id);

	public void upcredit_card_available(double cre_balance, String cre_id);
}

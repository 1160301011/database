package com.shao.model;
/**
 * @author HGX
 *实体类
 *收款人表 
 *
 */
public class Payee {
	private String payee_id;
    private String pay_uid;
    private String payee_name;
    private String payee_card;
    private String belong_bank;
	public String getPayee_id() {
		return payee_id;
	}
	public void setPayee_id(String payee_id) {
		this.payee_id = payee_id;
	}
	public String getPay_uid() {
		return pay_uid;
	}
	public void setPay_uid(String pay_uid) {
		this.pay_uid = pay_uid;
	}
	public String getPayee_name() {
		return payee_name;
	}
	public void setPayee_name(String payee_name) {
		this.payee_name = payee_name;
	}
	public String getPayee_card() {
		return payee_card;
	}
	public void setPayee_card(String payee_card) {
		this.payee_card = payee_card;
	}
	public String getBelong_bank() {
		return belong_bank;
	}
	public void setBelong_bank(String belong_bank) {
		this.belong_bank = belong_bank;
	}
    
    
}

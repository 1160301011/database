package com.shao.model;

/**
 * @author HGX
 *实体类
 *银行信息表
 *
 */
public class Bankinfo {

	private String bank_id;
    private String bank_name;
    private String bank_addr;
	public String getBank_id() {
		return bank_id;
	}
	public void setBank_id(String bank_id) {
		this.bank_id = bank_id;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getBank_addr() {
		return bank_addr;
	}
	public void setBank_addr(String bank_addr) {
		this.bank_addr = bank_addr;
	}
	
}

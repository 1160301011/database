package com.shao.model;
/**
 * @author HGX
 *ʵ����
 *�û��� 
 *
 */
public class User {
    private String name;
    private String password;
    private double balance;	
	public User() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getbalance() {
		return balance;
	}
	public void setbalance(double balance) {
		this.balance = balance;
	}
    
}

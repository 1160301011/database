package com.shao.model;
/**
 * @author HGX
 *实体类
 *用户表 
 *
 */
public class Bankuser {

	private String uid;
    private String username;
    private String userpwd;
    private String userstatus;
    private double userbalance;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getUserstatus() {
		return userstatus;
	}
	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}
	public double getUserbalance() {
		return userbalance;
	}
	public void setUserbalance(double userbalance) {
		this.userbalance = userbalance;
	}
		
}

package com.pku.xinfeng.pojo;

import java.util.Date;

public class UserDetail {
	private Integer userId;
	private String userName;
	private String phone;
	private String equipId;
	private String equipName;
	private Date repairDate;
	private Date filterDate;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getEquipName() {
		return equipName;
	}
	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}
	public Date getRepairDate() {
		return repairDate;
	}
	public void setRepairDate(Date repairDate) {
		this.repairDate = repairDate;
	}
	public Date getFilterDate() {
		return filterDate;
	}
	public void setFilterDate(Date filterDate) {
		this.filterDate = filterDate;
	}
	
}
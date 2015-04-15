package com.pku.xinfeng.pojo;

public class CommandPojo {
	private String equipId;
	private String onOrOff;//设备开关
	private String sleep;//睡眠
	private String heat;//加热
	private String disinfection;//杀菌
	private String autoOrManual;//自动手动
	private String airloop;//内外循环
	private String airflow;//风量
	private String staticElectricity;//景点
	private String airflowPercent;////风量百分比
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getOnOrOff() {
		return onOrOff;
	}
	public void setOnOrOff(String onOrOff) {
		this.onOrOff = onOrOff;
	}
	public String getSleep() {
		return sleep;
	}
	public void setSleep(String sleep) {
		this.sleep = sleep;
	}
	public String getHeat() {
		return heat;
	}
	public void setHeat(String heat) {
		this.heat = heat;
	}
	public String getDisinfection() {
		return disinfection;
	}
	public void setDisinfection(String disinfection) {
		this.disinfection = disinfection;
	}
	public String getAutoOrManual() {
		return autoOrManual;
	}
	public void setAutoOrManual(String autoOrManual) {
		this.autoOrManual = autoOrManual;
	}
	public String getAirloop() {
		return airloop;
	}
	public void setAirloop(String airloop) {
		this.airloop = airloop;
	}
	public String getAirflow() {
		return airflow;
	}
	public void setAirflow(String airflow) {
		this.airflow = airflow;
	}
	public String getStaticElectricity() {
		return staticElectricity;
	}
	public void setStaticElectricity(String staticElectricity) {
		this.staticElectricity = staticElectricity;
	}
	public String getAirflowPercent() {
		return airflowPercent;
	}
	public void setAirflowPercent(String airflowPercent) {
		this.airflowPercent = airflowPercent;
	}
	
}
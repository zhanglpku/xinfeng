package com.pku.xinfeng.model;

import java.util.Date;

public class Equipment {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column equipment.id
	 * @mbggenerated
	 */
	private String id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column equipment.max_airflow
	 * @mbggenerated
	 */
	private Integer max_airflow;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column equipment.factory_date
	 * @mbggenerated
	 */
	private Date factory_date;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column equipment.install_date
	 * @mbggenerated
	 */
	private Date install_date;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column equipment.repair_date
	 * @mbggenerated
	 */
	private Date repair_date;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column equipment.filter_date
	 * @mbggenerated
	 */
	private Date filter_date;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column equipment.version
	 * @mbggenerated
	 */
	private String version;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column equipment.code
	 * @mbggenerated
	 */
	private String code;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column equipment.note
	 * @mbggenerated
	 */
	private String note;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column equipment.id
	 * @return  the value of equipment.id
	 * @mbggenerated
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column equipment.id
	 * @param id  the value for equipment.id
	 * @mbggenerated
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column equipment.max_airflow
	 * @return  the value of equipment.max_airflow
	 * @mbggenerated
	 */
	public Integer getMax_airflow() {
		return max_airflow;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column equipment.max_airflow
	 * @param max_airflow  the value for equipment.max_airflow
	 * @mbggenerated
	 */
	public void setMax_airflow(Integer max_airflow) {
		this.max_airflow = max_airflow;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column equipment.factory_date
	 * @return  the value of equipment.factory_date
	 * @mbggenerated
	 */
	public Date getFactory_date() {
		return factory_date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column equipment.factory_date
	 * @param factory_date  the value for equipment.factory_date
	 * @mbggenerated
	 */
	public void setFactory_date(Date factory_date) {
		this.factory_date = factory_date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column equipment.install_date
	 * @return  the value of equipment.install_date
	 * @mbggenerated
	 */
	public Date getInstall_date() {
		return install_date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column equipment.install_date
	 * @param install_date  the value for equipment.install_date
	 * @mbggenerated
	 */
	public void setInstall_date(Date install_date) {
		this.install_date = install_date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column equipment.repair_date
	 * @return  the value of equipment.repair_date
	 * @mbggenerated
	 */
	public Date getRepair_date() {
		return repair_date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column equipment.repair_date
	 * @param repair_date  the value for equipment.repair_date
	 * @mbggenerated
	 */
	public void setRepair_date(Date repair_date) {
		this.repair_date = repair_date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column equipment.filter_date
	 * @return  the value of equipment.filter_date
	 * @mbggenerated
	 */
	public Date getFilter_date() {
		return filter_date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column equipment.filter_date
	 * @param filter_date  the value for equipment.filter_date
	 * @mbggenerated
	 */
	public void setFilter_date(Date filter_date) {
		this.filter_date = filter_date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column equipment.version
	 * @return  the value of equipment.version
	 * @mbggenerated
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column equipment.version
	 * @param version  the value for equipment.version
	 * @mbggenerated
	 */
	public void setVersion(String version) {
		this.version = version == null ? null : version.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column equipment.code
	 * @return  the value of equipment.code
	 * @mbggenerated
	 */
	public String getCode() {
		return code;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column equipment.code
	 * @param code  the value for equipment.code
	 * @mbggenerated
	 */
	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column equipment.note
	 * @return  the value of equipment.note
	 * @mbggenerated
	 */
	public String getNote() {
		return note;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column equipment.note
	 * @param note  the value for equipment.note
	 * @mbggenerated
	 */
	public void setNote(String note) {
		this.note = note == null ? null : note.trim();
	}

	private String equip_name;

	public String getEquip_name() {
		return equip_name;
	}

	public void setEquip_name(String equip_name) {
		this.equip_name = equip_name;
	}
	
}
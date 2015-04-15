package com.pku.xinfeng.model;

import java.util.Date;

public class TimeSet {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column time_set.id
	 * @mbggenerated
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column time_set.update_date
	 * @mbggenerated
	 */
	private Date update_date;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column time_set.equip_id
	 * @mbggenerated
	 */
	private String equip_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column time_set.action
	 * @mbggenerated
	 */
	private Boolean action;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column time_set.cycle
	 * @mbggenerated
	 */
	private Integer cycle;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column time_set.time
	 * @mbggenerated
	 */
	private Date time;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column time_set.status
	 * @mbggenerated
	 */
	private Boolean status;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column time_set.note
	 * @mbggenerated
	 */
	private String note;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column time_set.id
	 * @return  the value of time_set.id
	 * @mbggenerated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column time_set.id
	 * @param id  the value for time_set.id
	 * @mbggenerated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column time_set.update_date
	 * @return  the value of time_set.update_date
	 * @mbggenerated
	 */
	public Date getUpdate_date() {
		return update_date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column time_set.update_date
	 * @param update_date  the value for time_set.update_date
	 * @mbggenerated
	 */
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column time_set.equip_id
	 * @return  the value of time_set.equip_id
	 * @mbggenerated
	 */
	public String getEquip_id() {
		return equip_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column time_set.equip_id
	 * @param equip_id  the value for time_set.equip_id
	 * @mbggenerated
	 */
	public void setEquip_id(String equip_id) {
		this.equip_id = equip_id == null ? null : equip_id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column time_set.action
	 * @return  the value of time_set.action
	 * @mbggenerated
	 */
	public Boolean getAction() {
		return action;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column time_set.action
	 * @param action  the value for time_set.action
	 * @mbggenerated
	 */
	public void setAction(Boolean action) {
		this.action = action;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column time_set.cycle
	 * @return  the value of time_set.cycle
	 * @mbggenerated
	 */
	public Integer getCycle() {
		return cycle;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column time_set.cycle
	 * @param cycle  the value for time_set.cycle
	 * @mbggenerated
	 */
	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column time_set.time
	 * @return  the value of time_set.time
	 * @mbggenerated
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column time_set.time
	 * @param time  the value for time_set.time
	 * @mbggenerated
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column time_set.status
	 * @return  the value of time_set.status
	 * @mbggenerated
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column time_set.status
	 * @param status  the value for time_set.status
	 * @mbggenerated
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column time_set.note
	 * @return  the value of time_set.note
	 * @mbggenerated
	 */
	public String getNote() {
		return note;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column time_set.note
	 * @param note  the value for time_set.note
	 * @mbggenerated
	 */
	public void setNote(String note) {
		this.note = note == null ? null : note.trim();
	}
}
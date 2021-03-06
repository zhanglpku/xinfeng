package com.pku.xinfeng.model;

import java.util.Date;

public class SensorData {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sensor_data.id
	 * @mbggenerated
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sensor_data.equip_id
	 * @mbggenerated
	 */
	private String equip_id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sensor_data.update_date
	 * @mbggenerated
	 */
	private Date update_date;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sensor_data.pm25
	 * @mbggenerated
	 */
	private String pm25;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sensor_data.pm10
	 * @mbggenerated
	 */
	private String pm10;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sensor_data.co2
	 * @mbggenerated
	 */
	private String co2;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sensor_data.voc
	 * @mbggenerated
	 */
	private String voc;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sensor_data.methanol
	 * @mbggenerated
	 */
	private String methanol;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sensor_data.temperature
	 * @mbggenerated
	 */
	private String temperature;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sensor_data.humidity
	 * @mbggenerated
	 */
	private String humidity;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sensor_data.strainer
	 * @mbggenerated
	 */
	private String strainer;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sensor_data.note
	 * @mbggenerated
	 */
	private String note;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sensor_data.id
	 * @return  the value of sensor_data.id
	 * @mbggenerated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sensor_data.id
	 * @param id  the value for sensor_data.id
	 * @mbggenerated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sensor_data.equip_id
	 * @return  the value of sensor_data.equip_id
	 * @mbggenerated
	 */
	public String getEquip_id() {
		return equip_id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sensor_data.equip_id
	 * @param equip_id  the value for sensor_data.equip_id
	 * @mbggenerated
	 */
	public void setEquip_id(String equip_id) {
		this.equip_id = equip_id == null ? null : equip_id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sensor_data.update_date
	 * @return  the value of sensor_data.update_date
	 * @mbggenerated
	 */
	public Date getUpdate_date() {
		return update_date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sensor_data.update_date
	 * @param update_date  the value for sensor_data.update_date
	 * @mbggenerated
	 */
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sensor_data.pm25
	 * @return  the value of sensor_data.pm25
	 * @mbggenerated
	 */
	public String getPm25() {
		return pm25;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sensor_data.pm25
	 * @param pm25  the value for sensor_data.pm25
	 * @mbggenerated
	 */
	public void setPm25(String pm25) {
		this.pm25 = pm25 == null ? null : pm25.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sensor_data.pm10
	 * @return  the value of sensor_data.pm10
	 * @mbggenerated
	 */
	public String getPm10() {
		return pm10;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sensor_data.pm10
	 * @param pm10  the value for sensor_data.pm10
	 * @mbggenerated
	 */
	public void setPm10(String pm10) {
		this.pm10 = pm10 == null ? null : pm10.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sensor_data.co2
	 * @return  the value of sensor_data.co2
	 * @mbggenerated
	 */
	public String getCo2() {
		return co2;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sensor_data.co2
	 * @param co2  the value for sensor_data.co2
	 * @mbggenerated
	 */
	public void setCo2(String co2) {
		this.co2 = co2 == null ? null : co2.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sensor_data.voc
	 * @return  the value of sensor_data.voc
	 * @mbggenerated
	 */
	public String getVoc() {
		return voc;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sensor_data.voc
	 * @param voc  the value for sensor_data.voc
	 * @mbggenerated
	 */
	public void setVoc(String voc) {
		this.voc = voc == null ? null : voc.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sensor_data.methanol
	 * @return  the value of sensor_data.methanol
	 * @mbggenerated
	 */
	public String getMethanol() {
		return methanol;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sensor_data.methanol
	 * @param methanol  the value for sensor_data.methanol
	 * @mbggenerated
	 */
	public void setMethanol(String methanol) {
		this.methanol = methanol == null ? null : methanol.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sensor_data.temperature
	 * @return  the value of sensor_data.temperature
	 * @mbggenerated
	 */
	public String getTemperature() {
		return temperature;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sensor_data.temperature
	 * @param temperature  the value for sensor_data.temperature
	 * @mbggenerated
	 */
	public void setTemperature(String temperature) {
		this.temperature = temperature == null ? null : temperature.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sensor_data.humidity
	 * @return  the value of sensor_data.humidity
	 * @mbggenerated
	 */
	public String getHumidity() {
		return humidity;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sensor_data.humidity
	 * @param humidity  the value for sensor_data.humidity
	 * @mbggenerated
	 */
	public void setHumidity(String humidity) {
		this.humidity = humidity == null ? null : humidity.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sensor_data.strainer
	 * @return  the value of sensor_data.strainer
	 * @mbggenerated
	 */
	public String getStrainer() {
		return strainer;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sensor_data.strainer
	 * @param strainer  the value for sensor_data.strainer
	 * @mbggenerated
	 */
	public void setStrainer(String strainer) {
		this.strainer = strainer == null ? null : strainer.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sensor_data.note
	 * @return  the value of sensor_data.note
	 * @mbggenerated
	 */
	public String getNote() {
		return note;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sensor_data.note
	 * @param note  the value for sensor_data.note
	 * @mbggenerated
	 */
	public void setNote(String note) {
		this.note = note == null ? null : note.trim();
	}

	private String equip_name;
	private Integer max_airflow;
	private Date factory_date;
	private Date install_date;
	private Date repair_date;
	private Date filter_date;
	private Date ymDate;//年月�?
	private String code;
	private String version;
	private String commands;

	public Integer getMax_airflow() {
		return max_airflow;
	}

	public void setMax_airflow(Integer max_airflow) {
		this.max_airflow = max_airflow;
	}

	public String getEquip_name() {
		return equip_name;
	}

	public void setEquip_name(String equip_name) {
		this.equip_name = equip_name;
	}
	
	public Date getYmDate() {
		return ymDate;
	}

	public void setYmDate(Date ymDate) {
		this.ymDate = ymDate;
	}

	public Date getFactory_date() {
		return factory_date;
	}

	public void setFactory_date(Date factory_date) {
		this.factory_date = factory_date;
	}

	public Date getInstall_date() {
		return install_date;
	}

	public void setInstall_date(Date install_date) {
		this.install_date = install_date;
	}

	public Date getRepair_date() {
		return repair_date;
	}

	public void setRepair_date(Date repair_date) {
		this.repair_date = repair_date;
	}

	public Date getFilter_date() {
		return filter_date;
	}

	public void setFilter_date(Date filter_date) {
		this.filter_date = filter_date;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCommands() {
		return commands;
	}

	public void setCommands(String commands) {
		this.commands = commands;
	}
}
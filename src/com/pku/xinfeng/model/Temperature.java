package com.pku.xinfeng.model;

import java.util.Date;

public class Temperature {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column temperature.id
	 * @mbggenerated
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column temperature.cityId
	 * @mbggenerated
	 */
	private String cityId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column temperature.cityName
	 * @mbggenerated
	 */
	private String cityName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column temperature.wind_direction
	 * @mbggenerated
	 */
	private String wind_direction;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column temperature.wind_force
	 * @mbggenerated
	 */
	private String wind_force;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column temperature.temperature_day
	 * @mbggenerated
	 */
	private String temperature_day;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column temperature.temperature_night
	 * @mbggenerated
	 */
	private String temperature_night;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column temperature.weather_type
	 * @mbggenerated
	 */
	private String weather_type;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column temperature.weather_type_name
	 * @mbggenerated
	 */
	private String weather_type_name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column temperature.update_date
	 * @mbggenerated
	 */
	private Date update_date;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column temperature.note
	 * @mbggenerated
	 */
	private String note;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column temperature.id
	 * @return  the value of temperature.id
	 * @mbggenerated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column temperature.id
	 * @param id  the value for temperature.id
	 * @mbggenerated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column temperature.cityId
	 * @return  the value of temperature.cityId
	 * @mbggenerated
	 */
	public String getCityId() {
		return cityId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column temperature.cityId
	 * @param cityId  the value for temperature.cityId
	 * @mbggenerated
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId == null ? null : cityId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column temperature.cityName
	 * @return  the value of temperature.cityName
	 * @mbggenerated
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column temperature.cityName
	 * @param cityName  the value for temperature.cityName
	 * @mbggenerated
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName == null ? null : cityName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column temperature.wind_direction
	 * @return  the value of temperature.wind_direction
	 * @mbggenerated
	 */
	public String getWind_direction() {
		return wind_direction;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column temperature.wind_direction
	 * @param wind_direction  the value for temperature.wind_direction
	 * @mbggenerated
	 */
	public void setWind_direction(String wind_direction) {
		this.wind_direction = wind_direction == null ? null : wind_direction
				.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column temperature.wind_force
	 * @return  the value of temperature.wind_force
	 * @mbggenerated
	 */
	public String getWind_force() {
		return wind_force;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column temperature.wind_force
	 * @param wind_force  the value for temperature.wind_force
	 * @mbggenerated
	 */
	public void setWind_force(String wind_force) {
		this.wind_force = wind_force == null ? null : wind_force.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column temperature.temperature_day
	 * @return  the value of temperature.temperature_day
	 * @mbggenerated
	 */
	public String getTemperature_day() {
		return temperature_day;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column temperature.temperature_day
	 * @param temperature_day  the value for temperature.temperature_day
	 * @mbggenerated
	 */
	public void setTemperature_day(String temperature_day) {
		this.temperature_day = temperature_day == null ? null : temperature_day
				.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column temperature.temperature_night
	 * @return  the value of temperature.temperature_night
	 * @mbggenerated
	 */
	public String getTemperature_night() {
		return temperature_night;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column temperature.temperature_night
	 * @param temperature_night  the value for temperature.temperature_night
	 * @mbggenerated
	 */
	public void setTemperature_night(String temperature_night) {
		this.temperature_night = temperature_night == null ? null
				: temperature_night.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column temperature.weather_type
	 * @return  the value of temperature.weather_type
	 * @mbggenerated
	 */
	public String getWeather_type() {
		return weather_type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column temperature.weather_type
	 * @param weather_type  the value for temperature.weather_type
	 * @mbggenerated
	 */
	public void setWeather_type(String weather_type) {
		this.weather_type = weather_type == null ? null : weather_type.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column temperature.weather_type_name
	 * @return  the value of temperature.weather_type_name
	 * @mbggenerated
	 */
	public String getWeather_type_name() {
		return weather_type_name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column temperature.weather_type_name
	 * @param weather_type_name  the value for temperature.weather_type_name
	 * @mbggenerated
	 */
	public void setWeather_type_name(String weather_type_name) {
		this.weather_type_name = weather_type_name == null ? null
				: weather_type_name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column temperature.update_date
	 * @return  the value of temperature.update_date
	 * @mbggenerated
	 */
	public Date getUpdate_date() {
		return update_date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column temperature.update_date
	 * @param update_date  the value for temperature.update_date
	 * @mbggenerated
	 */
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column temperature.note
	 * @return  the value of temperature.note
	 * @mbggenerated
	 */
	public String getNote() {
		return note;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column temperature.note
	 * @param note  the value for temperature.note
	 * @mbggenerated
	 */
	public void setNote(String note) {
		this.note = note == null ? null : note.trim();
	}
}
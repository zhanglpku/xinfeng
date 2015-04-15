/**
 * 
 */
package com.pku.xinfeng.service;

import java.util.List;

import com.pku.xinfeng.model.Command;
import com.pku.xinfeng.model.SensorData;
import com.pku.xinfeng.model.UserWeather;
import com.pku.xinfeng.model.Weather;

/**
 * @author zhangl
 *
 */
public interface MainViewService {
	public List<SensorData> getEquipSensorListByUserId(String userId);
	public List<Weather> getWeatherListByUserId(String userId);
	public int deleteEquipByUser(String userId,String equipId);
	public int deleteWeatherByUser(String userId,String weatherId);
	public boolean addWeather(UserWeather obj);
	List<Command> loadEquipCommand(String equipId);
	public UserWeather getWeatherByUserIdAndCity(String userId, String city);
	
	public List<SensorData> getHistory(String equipId);
	
	public String getNowAllStatus(String equipId);
	
}

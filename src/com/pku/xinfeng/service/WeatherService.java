/**
 * 
 */
package com.pku.xinfeng.service;

import java.util.List;

import com.pku.xinfeng.model.Pm;
import com.pku.xinfeng.model.Temperature;
import com.pku.xinfeng.model.Weather;


/**
 * @author zhangl
 *
 */
public interface WeatherService {
	public List<Weather> getCityList();
	
	public List<Pm> getPmFromInterface();
	public void insertPmBatch(List<Pm> list);
	
	public List<Temperature> getTempFromInterface();
	public void insertTempBatch(List<Temperature> list);
}

/**
 * 
 */
package com.pku.xinfeng.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pku.xinfeng.dao.PmMapper;
import com.pku.xinfeng.dao.TemperatureMapper;
import com.pku.xinfeng.dao.WeatherMapper;
import com.pku.xinfeng.model.Pm;
import com.pku.xinfeng.model.Temperature;
import com.pku.xinfeng.model.Weather;
import com.pku.xinfeng.service.WeatherService;
import com.pku.xinfeng.utils.HttpUtil;
import com.pku.xinfeng.utils.StringUtil;
import com.pku.xinfeng.utils.SysCodeServlet;
import com.pku.xinfeng.utils.WeatherInterfaceUtil;


/**
 * @author zhangl
 *
 */
@Service("weatherService")
public class WeatherServiceImpl implements WeatherService {
	static String pmUrl = "http://www.pm25.in/api/querys/aqi_ranking.json?token=4VvFga4pGFz5mcojfd9Z";
	
	@Autowired
	private WeatherMapper weatherMapper;
	
	@Autowired
	private PmMapper pmMapper;
	
	@Autowired
	private TemperatureMapper tempMapper;
	

	@Override
	public List<Weather> getCityList() {
		// TODO Auto-generated method stub
		List<Weather> list = weatherMapper.getCityList();
		return list;
	}

	@Override
	public List<Temperature> getTempFromInterface() {
		List<Temperature> list = new ArrayList<Temperature>();

		List<Weather> tempList = weatherMapper.selectByExample(null);
		if(null != tempList){
			for(int i=0; i<tempList.size(); i++){
				Weather weather = tempList.get(i);
				
				String cityId = weather.getCityId();
				if(!StringUtil.isEmpty(cityId)){
					String weatherUrl = WeatherInterfaceUtil.getRequestUrl(cityId);
					String weaJsonStr = HttpUtil.sendGet(weatherUrl);
					
					if (!StringUtil.isEmpty(weaJsonStr)) {
						JSONObject weaJson = JSONObject.fromObject(weaJsonStr);
						
						if(null != weaJson){
							JSONArray weaAry = weaJson.getJSONObject("f")
									.getJSONArray("f1");// 三天天气预报，0是第一天
							
							// 设置天气数据
							JSONObject wJson = weaAry.getJSONObject(0);
							
							Temperature obj = new Temperature();
							obj.setCityId(cityId);
							obj.setCityName(weather.getCityName());
							// 白天气温(摄氏度)
							String fc = wJson.getString("fc");
							if(!StringUtil.isEmpty(fc))
								obj.setTemperature_day(fc);
							else
								obj.setTemperature_day(weather.getTemperature_day());
							// 夜间气温(摄氏度)
							String fd = wJson.getString("fd");
							if(!StringUtil.isEmpty(fd))
								obj.setTemperature_night(fd);
							else
								obj.setTemperature_night(weather.getTemperature_night());
							// 白天风向编号
							String fe = wJson.getString("fe");
							if(!StringUtil.isEmpty(fe))
								obj.setWind_direction(StringUtil
									.makeNullToEmptyString(SysCodeServlet.windDireMap.get(fe)));
							else
								obj.setWind_direction(weather.getWind_direction());
							// 白天风力编号
							String fg = wJson.getString("fg");
							if(!StringUtil.isEmpty(fg))
								obj.setWind_force(StringUtil
									.makeNullToEmptyString(SysCodeServlet.windForceMap.get(fg)));
							else
								obj.setWind_force(weather.getWind_force());
							// 天气现象编码表
							String fa = wJson.getString("fa");
							if (!StringUtil.isEmpty(fa)){
								obj.setWeather_type(StringUtil.makeNullToEmptyString(fa));
								obj.setWeather_type_name(StringUtil
										.makeNullToEmptyString(SysCodeServlet.weatherTypeMap
												.get(fa)));// 天气现象中文
							}else{
								obj.setWeather_type(weather.getWeather_type());
								obj.setWeather_type_name(weather.getWeather_type_name());
							}
							obj.setUpdate_date(new Date());	
							list.add(obj);
						}
					}
				}
			}
		}
		return list;
	}

	@Override
	public List<Pm> getPmFromInterface() {
		List<Pm> list = new ArrayList<Pm>();
		try {
			String pmJsonStr = HttpUtil.sendGet(pmUrl);
			if (!StringUtil.isEmpty(pmJsonStr)) {
				JSONArray pmAry = JSONArray.fromObject(pmJsonStr);
				if (null != pmAry) {
					for (int i = 0; i < pmAry.size(); i++) {
						JSONObject json = pmAry.getJSONObject(i);
						if(null != json && !StringUtil.isEmpty(json.getString("area"))){
							Pm pmObj = new Pm();
							
							String cityName = json.getString("area");
							if(!StringUtil.isEmpty(cityName))
								continue;
							String cityId = SysCodeServlet.cityMap.get(json.getString("area"));// 查找cityID
							if(!StringUtil.isEmpty(cityId)){
								pmObj.setCityId(cityId);
								pmObj.setCityName(cityName);
								// 设置pm数据
								pmObj.setAqi(json.getString("aqi"));
								pmObj.setCo(json.getString("co"));
								pmObj.setNo2(json.getString("no2"));
								pmObj.setO3(json.getString("o3"));
								pmObj.setPm10(json.getString("pm10"));
								pmObj.setPm25(json.getString("pm2_5"));
								pmObj.setSo2(json.getString("so2"));
								pmObj.setUpdate_date(new Date());
								list.add(pmObj);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	@Transactional
	public void insertPmBatch(List<Pm> list) {
		// TODO Auto-generated method stub
		if(null != list && list.size() > 0){
			// 1.删除全部
			pmMapper.deleteAll();
			// 2.批量插入
			pmMapper.insertBatch(list);
		}
	}

	@Override
	@Transactional
	public void insertTempBatch(List<Temperature> list) {
		// TODO Auto-generated method stub
		if(null != list && list.size() > 0){
			// 1.删除全部
			tempMapper.deleteAll();
			// 2.批量插入
			tempMapper.insertBatch(list);
		}
	}
}

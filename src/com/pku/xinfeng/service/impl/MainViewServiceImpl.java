package com.pku.xinfeng.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pku.xinfeng.dao.CommandMapper;
import com.pku.xinfeng.dao.SensorDataMapper;
import com.pku.xinfeng.dao.UserEquipmentMapper;
import com.pku.xinfeng.dao.UserWeatherMapper;
import com.pku.xinfeng.dao.WeatherMapper;
import com.pku.xinfeng.dataCache.DataCacheMap;
import com.pku.xinfeng.model.Command;
import com.pku.xinfeng.model.SensorData;
import com.pku.xinfeng.model.UserEquipmentExample;
import com.pku.xinfeng.model.UserWeather;
import com.pku.xinfeng.model.UserWeatherExample;
import com.pku.xinfeng.model.Weather;
import com.pku.xinfeng.service.MainViewService;
import com.pku.xinfeng.service.OperLogService;
import com.pku.xinfeng.utils.Constant;
import com.pku.xinfeng.utils.StringUtil;

@Service("mainViewService")
public class MainViewServiceImpl implements MainViewService {
	@Autowired
	private SensorDataMapper sensorDataMapper;
	@Autowired
	private UserWeatherMapper userWeatherMapper;
	@Autowired
	private UserEquipmentMapper userEquipmentMapper;
	@Autowired
	private CommandMapper commandMapper;
	@Autowired
	private WeatherMapper weatherMapper;

	@Autowired
	private OperLogService operLogService;

	@Override
	public List<SensorData> getEquipSensorListByUserId(String userId) {
		// TODO Auto-generated method stub
		List<SensorData> list = new ArrayList<SensorData>();
		list = sensorDataMapper.getEquipSensorListByUserId(userId);

		return list;
	}

	@Override
	public List<Weather> getWeatherListByUserId(String userId) {
		// TODO Auto-generated method stub
		List<Weather> list = new ArrayList<Weather>();
		list = weatherMapper.getWeatherListByUserId(userId);
		return list;
	}

	@Override
	public int deleteEquipByUser(String userId, String equipId) {
		// TODO Auto-generated method stub
		UserEquipmentExample example = new UserEquipmentExample();
		com.pku.xinfeng.model.UserEquipmentExample.Criteria cr = example
				.createCriteria();
		cr.andEquip_idEqualTo(equipId);
		cr.andUser_idEqualTo(userId);
		example.or(cr);

		int f = userEquipmentMapper.deleteByExample(example);

		String data = "delete user_equip where userId=" + userId + ",equipId=" + equipId;
		operLogService.insert(Constant.OPER_TYPE_DELETE,
				Constant.OPER_TYPE_COMMON, data, userId, equipId);
		return f;
	}

	@Override
	public int deleteWeatherByUser(String userId, String weatherId) {
		// TODO Auto-generated method stub
		UserWeatherExample example = new UserWeatherExample();
		com.pku.xinfeng.model.UserWeatherExample.Criteria cr = example
				.createCriteria();
		cr.andIdEqualTo(Integer.parseInt(weatherId));
		cr.andUser_idEqualTo(userId);
		example.or(cr);

		int f = userWeatherMapper.deleteByExample(example);
		
		String data = "delete user_weather where userId=" + userId + ",weatherId=" + weatherId;
		operLogService.insert(Constant.OPER_TYPE_DELETE,
				Constant.OPER_TYPE_COMMON, data, userId, "");
		return f;
	}

	@Override
	public List<Command> loadEquipCommand(String equipId) {
		// TODO Auto-generated method stub
		List<Command> list = null;
		list = commandMapper.getNowCommands(equipId);
		return list;
	}
	
	@Override
	public boolean addWeather(UserWeather obj) {
		// TODO Auto-generated method stub
		boolean flag = false;
		//新增设备
		int f = userWeatherMapper.insert(obj);
		if(f > 0){
			flag = true;
		}
		
		String data = "新增天气:insert into user_weather where userId=" + obj.getUser_id() 
				+ ",city=" + obj.getCity();
		operLogService.insert(Constant.OPER_TYPE_ADD,
				Constant.OPER_TYPE_COMMON, data, obj.getUser_id(), "");
		return flag;
	}

	@Override
	public UserWeather getWeatherByUserIdAndCity(String userId, String city) {
		// TODO Auto-generated method stub
		UserWeather weather = null;
		UserWeatherExample example = new UserWeatherExample();
		com.pku.xinfeng.model.UserWeatherExample.Criteria cr = example.createCriteria();
		cr.andUser_idEqualTo(userId);
		cr.andCityEqualTo(city);
		example.or(cr);
		List<UserWeather> list = userWeatherMapper.selectByExample(example);
		if(null != list && list.size() > 0){
			weather = list.get(0);
		}			
		
		return weather;
	}

	@Override
	public List<SensorData> getHistory(String equipId) {
		// TODO Auto-generated method stub
		List<SensorData> list = new ArrayList<SensorData>();
		list = sensorDataMapper.getHistory(equipId);
		return list;
	}
	
	@Override
	public String getNowAllStatus(String equipId){
		JSONObject settingJson = new JSONObject();
		Map<String, String> comMap = DataCacheMap.getInstance().getDataMap(equipId);
		if(null == comMap){
			List<Command> comlist = commandMapper.getNowCommands(equipId);
        	if(null != comlist && comlist.size() > 0){
        		for(int k=0; k< comlist.size(); k++){
        			Command comObj = comlist.get(k);
        			if(null != comObj){
        				String type = comObj.getType();
        				if(!StringUtil.isEmpty(comObj.getType())){
        					if(!Constant.CONSTANT_COMMAND_TIME.equals(type)){
                				String comStr = StringUtil.makeNullToEmptyString(comObj.getCommand());
                				if(StringUtil.isBoolean(comStr)){
                					settingJson.put(type, StringUtil.toBoolean(comStr));
                				}else{
                					settingJson.put(type, comStr);
                				}
                				//存入缓存
                    			DataCacheMap.getInstance().saveData(equipId, type, settingJson.getString(type));
                			}
        				}
        			}
        		}
        	}
		}else{
			Iterator<String> it = (Iterator<String>) comMap.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();// 命令类型
				if(!StringUtil.isEmpty(key)){
					if (Constant.CONSTANT_COMMAND_ONOROFF.equals(key) // 电源开关
							|| Constant.CONSTANT_COMMAND_SLEEP.equals(key)// 睡眠
							|| Constant.CONSTANT_COMMAND_HEAT.equals(key)// 加热
							|| Constant.CONSTANT_COMMAND_DISINFECTION.equals(key)
							|| Constant.CONSTANT_COMMAND_MODEL.equals(key)// 自动/手动
							|| Constant.CONSTANT_COMMAND_AIRLOOP.equals(key)// 内/外循环
							|| Constant.CONSTANT_COMMAND_STATICELECTRICITY.equals(key)) {// 静电
						settingJson.put(key, StringUtil.toBoolean(comMap.get(key)));
					}else{
						settingJson.put(key, comMap.get(key));
					}
				}
			}
		}
		System.out.println("@@@@@@@@:" + settingJson.toString());
		return settingJson.toString();
	}
}

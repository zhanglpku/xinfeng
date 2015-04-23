package com.pku.xinfeng.dataCache;

import java.util.HashMap;
import java.util.Map;

import com.pku.xinfeng.utils.Constant;
import com.pku.xinfeng.utils.StringUtil;

public class DataCacheMap {
	
	static private DataCacheMap instance;
	
	private static Map<String,Object> map;
	private DataCacheMap(){
		map = new HashMap<String,Object>();
	}
	
	static public DataCacheMap getInstance(){
		if(instance != null){
			return instance;
		}else{
			instance = new DataCacheMap();
			return instance;
		}
	}
	@SuppressWarnings("unchecked")
	public void saveData(String equipId,String type,String command){
		Map<String,String> commandMap = (Map<String, String>) map.get(equipId);
		if(null == commandMap)
			commandMap = new HashMap<String,String>();
		if(Constant.CONSTANT_COMMAND_ONOROFF.equals(type)){
			commandMap.put(Constant.CONSTANT_COMMAND_ONOROFF, StringUtil.toBoolean(command)+"");
		}else if(Constant.CONSTANT_COMMAND_SLEEP.equals(type)){
			commandMap.put(Constant.CONSTANT_COMMAND_SLEEP, StringUtil.toBoolean(command)+"");
		}else if(Constant.CONSTANT_COMMAND_HEAT.equals(type)){
			commandMap.put(Constant.CONSTANT_COMMAND_HEAT, StringUtil.toBoolean(command)+"");
		}else if(Constant.CONSTANT_COMMAND_MODEL.equals(type)){
			commandMap.put(Constant.CONSTANT_COMMAND_MODEL, StringUtil.toBoolean(command)+"");
		}else if(Constant.CONSTANT_COMMAND_AIRLOOP.equals(type)){
			commandMap.put(Constant.CONSTANT_COMMAND_AIRLOOP, StringUtil.toBoolean(command)+"");
		}else if(Constant.CONSTANT_COMMAND_AIRFLOW.equals(type)){
			commandMap.put(Constant.CONSTANT_COMMAND_AIRFLOW, command);
		}else if(Constant.CONSTANT_COMMAND_TIME.equals(type)){
			commandMap.put(Constant.CONSTANT_COMMAND_TIME, StringUtil.toBoolean(command)+"");
		}else if(Constant.CONSTANT_COMMAND_STATICELECTRICITY.equals(type)){
			commandMap.put(Constant.CONSTANT_COMMAND_STATICELECTRICITY, StringUtil.toBoolean(command)+"");
		}
		map.put(equipId, commandMap);
	}
	@SuppressWarnings("unchecked")
	public Map<String,String> getDataMap(String equipId){
		return (Map<String, String>) map.get(equipId);
	} 
	
	public void setDataMap(String equipId,Map<String, String> dataMap){
		map.put(equipId, dataMap);
	} 
}

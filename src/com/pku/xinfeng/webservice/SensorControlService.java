package com.pku.xinfeng.webservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.pku.xinfeng.dao.CommandMapper;
import com.pku.xinfeng.dao.EquipmentMapper;
import com.pku.xinfeng.dao.SensorDataMapper;
import com.pku.xinfeng.dao.TimeSetMapper;
import com.pku.xinfeng.dataCache.DataCacheMap;
import com.pku.xinfeng.model.Command;
import com.pku.xinfeng.model.SensorData;
import com.pku.xinfeng.model.TimeSet;
import com.pku.xinfeng.model.TimeSetExample;
import com.pku.xinfeng.utils.Constant;
import com.pku.xinfeng.utils.DateUtil;
import com.pku.xinfeng.utils.StringUtil;

public class SensorControlService extends SpringBeanAutowiringSupport {
	@Autowired
	private SensorDataMapper sensorDataMapper;

	@Autowired
	private CommandMapper commandMapper;

	@Autowired
	private TimeSetMapper timeSetMapper;
	
	@Autowired
	private EquipmentMapper equipmentMapper;
	
	public static Map<String,Integer> maxFlowMap;//key:设备id，value：最大风量

	/**
	 * 
	 * @param sensorDataJson:json格式字符串
	 * {
		    "equipId": "xx",
		    "sensorData": [
		        {
		            "pm25": 25
		        },
		        {
		            "pm10": 25
		        }
		    ],
		    "commandData": [
		        {
		            "pm25": 25
		        },
		        {
		            "pm10": 25
		        }
		    ]
		}
	 *	@return: {type:command,type:command}
	 */
	public String saveDatas(String sensorDataJson) {
		String returnStr = "";
		System.out.println("%%%%%%%%%%%%%%%%%%s----saveDatas-----------");
		System.out.println(sensorDataJson);
		if (StringUtil.isEmpty(sensorDataJson))
			return returnStr;
		/**
		 * 1.从json串中解析出数据
		 * 2.存储传感器数据
		 * 3.存储控制器状态
		 * 4.取最新控制器状态,并更新状态
		 */
		//1.从json串中解析出数据
		JSONObject jsonObj = JSONObject.fromObject(sensorDataJson);
		String equipId = jsonObj.getString("equipId");
		JSONObject sensorJson = jsonObj.getJSONObject("sensorData");
		JSONObject commandJson = jsonObj.getJSONObject("commandData");

		// 2.存储传感器数据，有：PM2.5、CO2、甲醇、温度、湿度、过滤网状态
		if(0 != (jsonObj.getInt("saveFlag") & 0x01)){//最低位为1才保存传感器数据
			SensorData record = new SensorData();
			record.setEquip_id(equipId);
			record.setUpdate_date(new Date());
			record.setCo2(sensorJson.getString(Constant.CONSTANT_COMMAND_CO2));
			record.setHumidity(sensorJson.getString(Constant.CONSTANT_COMMAND_HUMIDITY));
			record.setMethanol(sensorJson.getString(Constant.CONSTANT_COMMAND_METHANOL));
			record.setPm25(sensorJson.getString(Constant.CONSTANT_COMMAND_PM25));
			record.setStrainer(sensorJson.getString(Constant.CONSTANT_COMMAND_STRAINER));
			record.setTemperature(sensorJson.getString(Constant.CONSTANT_COMMAND_TEMPERATURE));
			sensorDataMapper.insert(record);
		}
		
		//3.与缓存作比对，存储数据
		Map<String,String> chacheMap = DataCacheMap.getInstance().getDataMap(equipId);
		//服务器刚启动时内存中无数据，此时内存中的数据就是控制器传送过来的，同时向数据库插入数据

		/**
		 * 1.判断控制器的命令是否需要保存
		 * 2.如果不需要保存，则把缓存中内容直接返回给控制器
		 * 3.如果需要保存，此时需要判断缓存中的数据是否有app发的指令
		 *   3.1如果没有，则缓存中数据变为控制器发送过来的数据，并保存到数据库
		 *   3.3如果有，则不对控制器发送过来的数据做任何处理，将app的指令返回给控制器，同时保存在数据库中
		 */
		//现在app没有命令，则控制器的状态有效。原样返回控制器给的命令，并将其更新到缓存和数据库
		if(null == chacheMap || !Constant.COMMAND_CHANGE_FLAG_APP.equals(chacheMap
				.get(Constant.COMMAND_CHANGE_FLAG))){
			returnStr = saveJsonToCacheAndBase(equipId, commandJson);
			//定时
			JSONObject returnJson = JSONObject.fromObject(returnStr);
			String timeAction = this.getTimeStatus(equipId);
			if(!StringUtil.isEmpty(timeAction))
				returnJson.put(Constant.CONSTANT_COMMAND_ONOROFF, timeAction);
		}else{//app有命令：控制器发送的不做处理，直接将app的命令返给控制器
			returnStr = getCommandsFromCache(equipId);
		}
		System.out.println("sensor webservice 得到最新的控制命令：" + returnStr);
		
		return returnStr;
	}
	
	String saveJsonToCacheAndBase(String equipId, JSONObject commandJson) {
		Map<String, String> chacheMap = DataCacheMap.getInstance().getDataMap(
				equipId);
		// 服务器刚启动时内存中无数据，此时内存中的数据为控制器传送过来的，同时向数据库插入数据
		if(null == chacheMap)
			chacheMap = new HashMap<String, String>();
		
		System.out.println("................." + chacheMap
				.get(Constant.COMMAND_CHANGE_FLAG) + ".................");
		List<Command> commList = new ArrayList<Command>();
		
		//从发送过来的json串中解析命令
		Iterator<?> cit = commandJson.keys();
		while (cit.hasNext()) {
			String id = (String) cit.next();
			String command = commandJson.getString(id);
			System.out.println(id + "$$$$$$$$$$$$$" + command);
			if(!StringUtil.isEmpty(command)){
				// 存储数据库
				Command obj = new Command();
				obj.setEquip_id(equipId);
				obj.setType(id);
				obj.setStatus(true);
				obj.setUpdate_date(new Date());
				if (Constant.CONSTANT_COMMAND_ONOROFF.equals(id) // 电源开关
						|| Constant.CONSTANT_COMMAND_SLEEP.equals(id)// 睡眠
						|| Constant.CONSTANT_COMMAND_HEAT.equals(id)// 加热
						|| Constant.CONSTANT_COMMAND_DISINFECTION.equals(id)
						|| Constant.CONSTANT_COMMAND_MODEL.equals(id)// 自动/手动
						|| Constant.CONSTANT_COMMAND_AIRLOOP.equals(id)// 内/外循环
						|| Constant.CONSTANT_COMMAND_STATICELECTRICITY
								.equals(id)) {// 静电
					//存到数据库
					obj.setCommand(StringUtil.toBoolean(command)+"");
					commList.add(obj);
				} else if (Constant.CONSTANT_COMMAND_AIRFLOW.equals(id)) {// 风量
					Integer maxflow = null;
					if (null == maxFlowMap) {
						Integer flw = equipmentMapper.getMaxAirflow(equipId);
						if (null != flw) {
							maxFlowMap = new HashMap<String, Integer>();
							maxFlowMap.put(equipId, flw);
							maxflow = flw;
						}
					} else
						maxflow = maxFlowMap.get(equipId);
					// 存储数据库
					if (null != maxflow)
						obj.setNote((int) Math.floor(Float.valueOf(command) / maxflow * 100)
								+ "");// 计算百分比
					obj.setCommand(command);
					commList.add(obj);
				}
				// 放入缓存
				chacheMap.put(id, obj.getCommand());
				chacheMap.put(Constant.CONSTANT_COMMAND_AIRFLOWPERCENT,
						obj.getNote());//风量比例
			}
			System.out.println("command$$$$$$$$$: " + command);
		}
		//插入数据库
		if(null != commList && commList.size()>0)
			commandMapper.insertBatch(commList);
		//存入缓存
		DataCacheMap.getInstance().setDataMap(equipId, chacheMap);
		System.out.println("onOrOffonOrOffonOrOffonOrOffonOrOfOffonOrOffonOrOffonOrOff==" + chacheMap.get("onOrOff"));
		System.out.println(commandJson.toString() + "....." );
		
		System.out.println("................." + chacheMap
				.get(Constant.COMMAND_CHANGE_FLAG) + ".................");
		return commandJson.toString();
	}
	
	/**
	 * 将缓存中数据取出，返回给控制器并保存到数据库
	 */
	String getCommandsFromCache(String equipId){
		JSONObject settingJson = new JSONObject();
		Map<String,String> comMap = DataCacheMap.getInstance().getDataMap(equipId);
		if(null == comMap){//从数据库取数据
			/**
			 * 此时说明缓存中没数据，需要从数据库中将最新的数据取出
			 */
			List<Command> comlist = commandMapper.getNowCommands(equipId);
        	if(null != comlist && comlist.size() > 0){
        		for(int k=0; k< comlist.size(); k++){
        			Command comObj = comlist.get(k);
        			if(null != comObj){
        				String type = comObj.getType();
        				String comStr = comObj.getCommand();
        				if(StringUtil.isBoolean(comStr)){
        					settingJson.put(type, StringUtil.toBoolean(comStr));
        				}else{
        					settingJson.put(type, comStr);
        				}
        				if(Constant.CONSTANT_COMMAND_AIRFLOW.equals(type))
        					settingJson.put(type, comObj.getNote());
            			//存入缓存
            			DataCacheMap.getInstance().saveData(equipId, type, settingJson.getString(type));
        			}
        		}
        	}
		}else{
			/**
			 * 将缓存中数据返回给控制器并保存到数据库中，同时修改缓存中的标志位为未修改
			 */
			List<Command> commList = new ArrayList<Command>();
			Iterator<String> it = (Iterator<String>) comMap.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();// 命令类型
				String command = comMap.get(key);// 命令值
				if(!StringUtil.isEmpty(command)){
					// 存储数据库
					Command obj = new Command();
					obj.setEquip_id(equipId);
					obj.setType(key);
					obj.setStatus(true);
					obj.setUpdate_date(new Date());
					if(Constant.CONSTANT_COMMAND_AIRFLOW.equals(key))//风量比例
						obj.setNote(command);
					
					if(Constant.CONSTANT_COMMAND_ONOROFF.equals(key) // 电源开关
							|| Constant.CONSTANT_COMMAND_SLEEP.equals(key)// 睡眠
							|| Constant.CONSTANT_COMMAND_HEAT.equals(key)// 加热
							|| Constant.CONSTANT_COMMAND_DISINFECTION.equals(key)
							|| Constant.CONSTANT_COMMAND_MODEL.equals(key)// 自动/手动
							|| Constant.CONSTANT_COMMAND_AIRLOOP.equals(key)// 内/外循环
							|| Constant.CONSTANT_COMMAND_STATICELECTRICITY.equals(key)){//静电
						obj.setCommand(StringUtil.toBoolean(command)+"");
						commList.add(obj);
					}else{
						obj.setCommand(command);
						commList.add(obj);
					}
					settingJson.put(key, obj.getCommand());
				}
			}
			comMap.put(Constant.COMMAND_CHANGE_FLAG,"");//修改标志位
			//插入数据库
			if(null != commList && commList.size()>0)
				commandMapper.insertBatch(commList);
		}
		return settingJson.toString();
	}
	//开或者关
	String getTimeStatus(String equipId){
		String returnStr = "";
		//查询出此设备的所有定时信息
		TimeSetExample example = new TimeSetExample();
		com.pku.xinfeng.model.TimeSetExample.Criteria cr = example
				.createCriteria();
		cr.andEquip_idEqualTo(equipId);
		cr.andStatusEqualTo(true);
		example.or(cr);
		List<TimeSet> timeList = timeSetMapper.selectByExample(example);
		if (null != timeList && timeList.size() > 0) {
			for(int i=0; i< timeList.size(); i++){
				TimeSet timeSet = timeList.get(i);

				// 0 仅一次（默认） 1 每天 2 周一至周五 3周六日 4 每周
				int cycle = timeSet.getCycle();
				Date upDate = timeSet.getUpdate_date();// HH:mm
				Date time = timeSet.getTime();

				Date nowDate = new Date();
				int  cycleType = cycle & 0xFF;
				switch (cycleType) {
				case 1://仅一次：当天且当时
					if (DateUtil.formatDate(nowDate).equals(
							DateUtil.formatDate(upDate))
							&& DateUtil.formatTime(nowDate).equals(
									DateUtil.formatTime(time)))
						returnStr = timeSet.getAction() + "";// true开/false关
					break;
				case 2://每天：当时
					if (DateUtil.formatTime(nowDate).equals(
							DateUtil.formatTime(time)))
						returnStr = timeSet.getAction() + "";// true开/false关
					break;
				case 4://工作日：周一到周五，当时
					if (DateUtil.getWeekOfDate(nowDate) >= 1
							&& DateUtil.getWeekOfDate(nowDate) <= 5
							&& DateUtil.formatTime(nowDate).equals(
									DateUtil.formatTime(time)))
						returnStr = timeSet.getAction() + "";// true开/false关
					break;
				case 8://周末：周六日，当时
					if (DateUtil.getWeekOfDate(nowDate) >= 6
							&& DateUtil.getWeekOfDate(nowDate) <= 7
							&& DateUtil.formatTime(nowDate).equals(
									DateUtil.formatTime(time)))
						returnStr = timeSet.getAction() + "";// true开/false关
					break;
				case 16://自定义（每周*）：周几？当时
					int week1 = (cycle >> 8) & 0x01;
					int week2 = (cycle >> 8) & 0x02;
					int week3 = (cycle >> 8) & 0x04;
					int week4 = (cycle >> 8) & 0x08;
					int week5 = (cycle >> 8) & 0x10;
					int week6 = (cycle >> 8) & 0x20;
					int week7 = (cycle >> 8) & 0x40;
					
					int nowWeek = DateUtil.getWeekOfDate(nowDate);
					boolean flag = false;
					if(1 == nowWeek && 0 != week1){
						flag = true;
					}else if(2 == nowWeek && 0 != week2){
						flag = true;
					}else if(3 == nowWeek && 0 != week3){
						flag = true;
					}else if(4 == nowWeek && 0 != week4){
						flag = true;
					}else if(5 == nowWeek && 0 != week5){
						flag = true;
					}else if(6 == nowWeek && 0 != week6){
						flag = true;
					}else if(7 == nowWeek && 0 != week7){
						flag = true;
					}
					if(flag && DateUtil.formatTime(nowDate).equals(
							DateUtil.formatTime(time))){
						returnStr = timeSet.getAction() + "";// true开/false关
					}
					break;
				default:
					break;
				}
			}
		}
		return returnStr;
	}
}

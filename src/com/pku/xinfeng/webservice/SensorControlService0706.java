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
import com.pku.xinfeng.model.CommandExample;
import com.pku.xinfeng.model.SensorData;
import com.pku.xinfeng.model.TimeSet;
import com.pku.xinfeng.model.TimeSetExample;
import com.pku.xinfeng.utils.Constant;
import com.pku.xinfeng.utils.DateUtil;
import com.pku.xinfeng.utils.StringUtil;

public class SensorControlService0706 extends SpringBeanAutowiringSupport {
	@Autowired
	private SensorDataMapper sensorDataMapper;

	@Autowired
	private CommandMapper commandMapper;

	@Autowired
	private TimeSetMapper timeSetMapper;
	
	@Autowired
	private EquipmentMapper equipmentMapper;
	
	public static Map<String,Integer> maxFlowMap;//key:设备id，value：最大风量

	// public boolean saveSensorData(String equipId, String commands) {
	// System.out.println("%%%%%%%%%%%%%%%%%%s----saveSensorData-----------");
	// if(StringUtil.isEmpty(equipId) || StringUtil.isEmpty(commands))
	// return false;
	//
	// boolean flag = false;
	//
	// // String ary[] = commands.split(",");
	// // if(null != ary && ary.length > 0){
	// // 查询出上一条记录
	// // select * from sensor_data order by update_date desc limit 1;
	// // for(int i = 0; i < ary.length ;i ++){
	// //
	// // }
	//
	// SensorData record = new SensorData();
	// record.setEquip_id(equipId);
	// record.setUpdate_date(new Date());
	// record.setCo2("c"+equipId);
	// record.setHumidity("1");
	// record.setMethanol("m"+equipId);
	// record.setPm10("p"+equipId);
	// record.setPm25("p"+equipId);
	// record.setStrainer("1");
	// record.setTemperature("t"+equipId);
	// record.setVoc("v"+equipId);
	// int f = sensorDataMapper.insert(record);
	// System.out.println("f : " + f);
	// if(f > 0)
	// flag =true;
	// // }
	// System.out.println(equipId + "--" + commands);
	// return flag;
	// }
	/**
	 * @param equipId
	 * @param sensorDataJson
	 *            {"sensorData": [{pm25:25},{}],"commandData": {type:command}}
	 * @return
	 */
	public boolean saveSensorData(String equipId, String sensorDataJson) {
		System.out.println("%%%%%%%%%%%%%%%%%%s----saveSensorData-----------");
		System.out.println(sensorDataJson);
		if (StringUtil.isEmpty(equipId) || StringUtil.isEmpty(sensorDataJson))
			return false;

		boolean flag = false;

		SensorData record = new SensorData();
		record.setEquip_id(equipId);
		record.setUpdate_date(new Date());

		/**
		 * 1.保存传感器数据 2.判断是否是新增的设备，如果是新增的设备的话，设备命令无数据，所以需要控制器传给服务器之后，app才可显示。
		 * 对于这种情况需要做的处理是：判断command中是否有数据，如无，则插入；如有不做任何处理。
		 */
		JSONObject jsonObj = JSONObject.fromObject(sensorDataJson);
		JSONObject sensorJson = jsonObj.getJSONObject("sensorData");
		JSONObject commandJson = jsonObj.getJSONObject("commandData");

		// 1.保存传感器数据，有：PM2.5、CO2、甲醇、温度、湿度、过滤网状态
		record.setCo2(sensorJson.getString(Constant.CONSTANT_COMMAND_CO2));
		record.setHumidity(sensorJson.getString(Constant.CONSTANT_COMMAND_HUMIDITY));
		record.setMethanol(sensorJson.getString(Constant.CONSTANT_COMMAND_METHANOL));
		record.setPm25(sensorJson.getString(Constant.CONSTANT_COMMAND_PM25));
		record.setStrainer(sensorJson.getString(Constant.CONSTANT_COMMAND_STRAINER));
		record.setTemperature(sensorJson.getString(Constant.CONSTANT_COMMAND_TEMPERATURE));
		int f = sensorDataMapper.insert(record);
		// 2.
		CommandExample example = new CommandExample();
		com.pku.xinfeng.model.CommandExample.Criteria cr = example
				.createCriteria();
		cr.andEquip_idEqualTo(equipId);
		example.or(cr);
		List<Command> list = commandMapper.selectByExample(example);
		if (null == list || list.size() == 0) {
			List<Command> commList = new ArrayList<Command>();

			Iterator<?> cit = commandJson.keys();
			while (cit.hasNext()) {
				String id = (String) cit.next();
				//电源开关、内/外循环、加热、自动/手动、静电、风量
				if(Constant.CONSTANT_COMMAND_ONOROFF.equals(id) //电源开关
						|| Constant.CONSTANT_COMMAND_SLEEP.equals(id)//睡眠
						|| Constant.CONSTANT_COMMAND_HEAT.equals(id)//加热
						|| Constant.CONSTANT_COMMAND_DISINFECTION.equals(id)
						|| Constant.CONSTANT_COMMAND_MODEL.equals(id)//自动/手动
						|| Constant.CONSTANT_COMMAND_AIRLOOP.equals(id)//内/外循环
						|| Constant.CONSTANT_COMMAND_STATICELECTRICITY.equals(id)){//静电
					if(StringUtil.isBoolean(commandJson.getString(id))){
						Command obj = new Command();
						obj.setEquip_id(equipId);
						obj.setType(id);
						obj.setCommand(commandJson.getString(id));
						obj.setStatus(true);
						obj.setUpdate_date(new Date());
						obj.setNote("");
						commList.add(obj);
					}
						
				}else if(Constant.CONSTANT_COMMAND_AIRFLOW.equals(id)){//风量
					Command obj = new Command();
					String comd = commandJson.getString(id);
					obj.setEquip_id(equipId);
					obj.setType(id);
					obj.setCommand(comd);
					obj.setStatus(true);
					obj.setUpdate_date(new Date());
					
					Integer maxflow = null;
					if(null == maxFlowMap){
						Integer flw = equipmentMapper.getMaxAirflow(equipId);
						if(null != flw){
							maxFlowMap = new HashMap<String,Integer>();
							maxFlowMap.put(equipId,flw);
							maxflow = flw;
						}
					}else
						maxflow = maxFlowMap.get(equipId);
					
					if(null != maxflow)
						obj.setNote((int)Math.floor(Float.valueOf(comd)/maxflow * 100) + "");//计算百分比
					commList.add(obj);
				}
			}
			commandMapper.insertBatch(commList);
		}

		System.out.println("f : " + f);
		if (f > 0)
			flag = true;
		System.out.println(equipId + "--" + equipId);
		return flag;
	}

	/**
	 * @param equipId
	 * @return: {type:command,type:command}
	 */
	public String getSensorCommands(String equipId) {
		System.out.println("-----------getSensorCommands--------------");
		String resultStr = "";

		if (!StringUtil.isEmpty(equipId)) {
			/**
			 * 1.查询出：最新的、status=false的命令 
			 * 2.将这些命令状态修改为true 
			 * 3.定时信息 4.返回1查询出的命令
			 */
			// 1.查询
			List<Command> list = commandMapper.getNewCommands(equipId);
			// 3.获得返回数据
			if (null != list && list.size() > 0) {
				Integer[] ary = new Integer[list.size()];
				JSONObject json = new JSONObject();
				for (int i = 0; i < list.size(); i++) {
					Command obj = list.get(i);
					String command = obj.getCommand();
					String type = obj.getType();

					Integer commanId = list.get(i).getId();
					if(null != commanId){//空表示此类型命令无值
						ary[i] = commanId;
					}
					if (StringUtil.isBoolean(command))
						json.put(type, StringUtil.toBoolean(command));
					else
						json.put(type, StringUtil.makeNullToEmptyString(command));
					if (Constant.CONSTANT_COMMAND_AIRFLOW.equals(type))// 风量百分比
						json.put(Constant.CONSTANT_COMMAND_AIRFLOWPERCENT,
								obj.getNote());

					System.out.println("得到返回的command : " + type + "----" +  command);
				}
				// 2.更新状态
				if (ary.length > 0)
					// 根据id更新状态及时间
					commandMapper.updateCommandStatusByIds(ary);
				System.out.println("-----更新命令的commandId---begin-----------");
				for(int i=0;i<ary.length;i++){
					System.out.println(ary[i]);
				}
				System.out.println("-----更新命令的commandId ---end-----------");
				// 3.定时
				String action = this.getTimeStatus(equipId);
				json.put(Constant.CONSTANT_COMMAND_TIME, action);
				// 4.返回json串
				resultStr = json.toString();
			}
		}
		System.out.println("--" + equipId + "5555--");
		return resultStr;
	}
	
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
		if(0 != (jsonObj.getInt("saveFlag") & 0x02)){//倒数第二低位保存状态
			if(null == chacheMap){
				//将接口数据放入缓存，并存入数据库
				saveJsonToCacheAndBase(equipId,commandJson);
			}else{
				if (Constant.COMMAND_CHANGE_FLAG_APP.equals(chacheMap
						.get(Constant.COMMAND_CHANGE_FLAG))) {//表示app有修改的数据
					//从内存中取状态
					returnStr = getCommandsFromCache(equipId);
				}else{//表示app没有修改的数据
					//将接口数据放入缓存，并存入数据库
					saveJsonToCacheAndBase(equipId,commandJson);
				}
			}
		}else{//不需要保存
			//从内存中取状态
			returnStr = getCommandsFromCache(equipId);
		}
	
//		//4.从内存中取状态
//		returnStr = getCommandsFromCache(equipId);
		//{type:command,type:command}
		System.out.println("sensor webservice 得到最新的控制命令：" + returnStr);
		
		return returnStr;
	}
	
	void saveJsonToCacheAndBase(String equipId, JSONObject commandJson) {
		Map<String, String> chacheMap = DataCacheMap.getInstance().getDataMap(
				equipId);
		// 服务器刚启动时内存中无数据，此时内存中的数据为控制器传送过来的，同时向数据库插入数据
		if(null == chacheMap)
			chacheMap = new HashMap<String, String>();

		List<Command> commList = new ArrayList<Command>();
		Iterator<?> cit = commandJson.keys();
		while (cit.hasNext()) {
			String id = (String) cit.next();
			String command = commandJson.getString(id);
			if (Constant.CONSTANT_COMMAND_ONOROFF.equals(id) // 电源开关
					|| Constant.CONSTANT_COMMAND_SLEEP.equals(id)// 睡眠
					|| Constant.CONSTANT_COMMAND_HEAT.equals(id)// 加热
					|| Constant.CONSTANT_COMMAND_DISINFECTION.equals(id)
					|| Constant.CONSTANT_COMMAND_MODEL.equals(id)// 自动/手动
					|| Constant.CONSTANT_COMMAND_AIRLOOP.equals(id)// 内/外循环
					|| Constant.CONSTANT_COMMAND_STATICELECTRICITY
							.equals(id)) {// 静电
				// 存储数据库
				Command obj = new Command();
				obj.setEquip_id(equipId);
				obj.setType(id);
				obj.setCommand(commandJson.getString(id));
				obj.setStatus(true);
				obj.setUpdate_date(new Date());
				obj.setNote("");
				commList.add(obj);
				// 放入缓存
				chacheMap.put(id, StringUtil.toBoolean(command)+"");
			} else if (Constant.CONSTANT_COMMAND_AIRFLOW.equals(id)) {// 风量
				// 存储数据库
				Command obj = new Command();
				String comd = commandJson.getString(id);
				obj.setEquip_id(equipId);
				obj.setType(id);
				obj.setCommand(comd);
				obj.setStatus(true);
				obj.setUpdate_date(new Date());

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

				if (null != maxflow)
					obj.setNote((int) Math.floor(Float.valueOf(comd)
							/ maxflow * 100)
							+ "");// 计算百分比
				commList.add(obj);
				// 放入缓存
				chacheMap.put(id, command);
				chacheMap.put(Constant.CONSTANT_COMMAND_AIRFLOWPERCENT,
						obj.getNote());
			}
		}
		//插入数据库
		if(null != commList && commList.size()>0)
			commandMapper.insertBatch(commList);
	}
	
	String getCommandsFromCache(String equipId){
		JSONObject settingJson = new JSONObject();
		Map<String,String> comMap = DataCacheMap.getInstance().getDataMap(equipId);
		if(null == comMap){//从数据库取数据
			List<Command> comlist = commandMapper.getNowCommands(equipId);
        	if(null != comlist && comlist.size() > 0){
        		for(int k=0; k< comlist.size(); k++){
        			Command comObj = comlist.get(k);
        			if(null != comObj){
        				String type = comObj.getType();
            			if(!Constant.CONSTANT_COMMAND_TIME.equals(type)){
            				String comStr = comObj.getCommand();
            				if(StringUtil.isBoolean(comStr))
            					settingJson.put(type, StringUtil.toBoolean(comStr));
            				else
            				settingJson.put(type, comStr);
            			}
        			}
        		}
        	}
		}
		if(null != comMap){
			Iterator<String> it = (Iterator<String>) comMap.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();// 命令类型
				String command = (String) comMap.get(key);// 命令值
				if(Constant.CONSTANT_COMMAND_ONOROFF.equals(key) // 电源开关
						|| Constant.CONSTANT_COMMAND_SLEEP.equals(key)// 睡眠
						|| Constant.CONSTANT_COMMAND_HEAT.equals(key)// 加热
						|| Constant.CONSTANT_COMMAND_DISINFECTION.equals(key)
						|| Constant.CONSTANT_COMMAND_MODEL.equals(key)// 自动/手动
						|| Constant.CONSTANT_COMMAND_AIRLOOP.equals(key)// 内/外循环
						|| Constant.CONSTANT_COMMAND_STATICELECTRICITY.equals(key)){//静电
					settingJson.put(key, StringUtil.toBoolean(command));
				}else{
					settingJson.put(key, command);
				}
			}
			comMap.put(Constant.COMMAND_CHANGE_FLAG,"");//修改标志位
		}
		return settingJson.toString();
	}
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
				switch (cycle) {
				case 0:
					if (DateUtil.formatDate(nowDate).equals(
							DateUtil.formatDate(upDate))
							&& DateUtil.formatTime(nowDate).equals(
									DateUtil.formatTime(time)))
						returnStr = timeSet.getAction() + "";// true开/false关
					break;
				case 1:
					if (DateUtil.formatTime(nowDate).equals(
							DateUtil.formatTime(time)))
						returnStr = timeSet.getAction() + "";// true开/false关
					break;
				case 2:
					if (DateUtil.getWeekOfDate(nowDate) >= 1
							&& DateUtil.getWeekOfDate(nowDate) <= 5
							&& DateUtil.formatTime(nowDate).equals(
									DateUtil.formatTime(time)))
						returnStr = timeSet.getAction() + "";// true开/false关
					break;
				case 3:
					if (DateUtil.getWeekOfDate(nowDate) >= 6
							&& DateUtil.getWeekOfDate(nowDate) <= 7
							&& DateUtil.formatTime(nowDate).equals(
									DateUtil.formatTime(time)))
						returnStr = timeSet.getAction() + "";// true开/false关
					break;
				case 4:
					if (DateUtil.getWeekOfDate(nowDate) == DateUtil
							.getWeekOfDate(upDate)
							&& DateUtil.formatTime(nowDate).equals(
									DateUtil.formatTime(time)))
						returnStr = timeSet.getAction() + "";// true开/false关
					break;
				default:
					break;
				}
			}
		}
		return returnStr;
	}

}

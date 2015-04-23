package com.pku.xinfeng.utils;

public class Constant {
	public final static String SESSION_USER = "sessionUser";
	// 控制命令
	public final static String CONSTANT_COMMAND_ONOROFF = "onOrOff";//设备开关
	public final static String CONSTANT_COMMAND_MODEL = "autoOrManual";//自动手动
	public final static String CONSTANT_COMMAND_AIRLOOP = "airloop";//内外循环
	public final static String CONSTANT_COMMAND_SLEEP = "sleep";//睡眠
	public final static String CONSTANT_COMMAND_TIME = "time";//定时
	
	public final static String CONSTANT_COMMAND_HEAT = "heat";//加热
	public final static String CONSTANT_COMMAND_STATICELECTRICITY = "staticElectricity";//静电
	public final static String CONSTANT_COMMAND_DISINFECTION = "disinfection";//杀菌
	
	public final static String CONSTANT_COMMAND_AIRFLOW = "airflow";//风量
	public final static String CONSTANT_COMMAND_AIRFLOWPERCENT = "airflowPercent";//风量百分比
	public final static String CONSTANT_COMMAND_VENT = "vent";//风机
	// 传感器数据
	public final static String CONSTANT_COMMAND_CO2 = "CO2";
	public final static String CONSTANT_COMMAND_HUMIDITY = "HUMIDITY";//湿度
	public final static String CONSTANT_COMMAND_METHANOL = "METHANOL";//甲醇
	public final static String CONSTANT_COMMAND_PM10 = "PM10";
	public final static String CONSTANT_COMMAND_PM25 = "PM25";
	public final static String CONSTANT_COMMAND_STRAINER = "STRAINER";//过滤网状态
	public final static String CONSTANT_COMMAND_TEMPERATURE = "TEMPERATURE";//温度
	public final static String CONSTANT_COMMAND_VOC = "VOC";
	// 日志操作
	public final static String OPER_TYPE_ADD = "add";
	public final static String OPER_TYPE_DELETE = "delete";
	public final static String OPER_TYPE_MODIFY = "modify";

	public final static String OPER_TYPE_SYS = "sys";
	public final static String OPER_TYPE_COMMON = "common";
	public final static String OPER_TYPE_AUTO = "auto";
	
	//excel 字段
	public static final String[] Excel_User_colu = {"username", "phone","equip_id", "equip_name", 
											"repair_date","filter_date","max_airflow","version","code"};
	//excel  表头名称
	public static final String[] Excel_User_value =  {"用户名", "电话", "设备ID", "设备名", 
												"设备维修时间", "滤网更换时间", "最大风量", "型号", "生产编码"};

	//excel 字段
	public static final String[] Excel_EQUIP_colu = {"equipId", "pm25", 
											"pm10","co2","voc","methanol","temperature"
											,"humidity","strainer","update_date"};
	//excel  表头名称
	public static final String[] Excel_EQUIP_value =  {"设备ID", "pm2.5", 
													"pm10", "co2", "voc", "甲醛", "温度"
													,"湿度","过滤网状态(0正常，1更换预警，2及时更换)","更新时间"};

	//缓存中标志位
	public final static String COMMAND_CHANGE_FLAG = "command_change_flag";
	public final static String COMMAND_CHANGE_FLAG_APP = "true";
}

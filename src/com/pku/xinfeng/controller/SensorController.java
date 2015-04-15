package com.pku.xinfeng.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pku.xinfeng.model.Command;
import com.pku.xinfeng.model.SensorData;
import com.pku.xinfeng.model.UserWeather;
import com.pku.xinfeng.model.Weather;
import com.pku.xinfeng.service.MainViewService;
import com.pku.xinfeng.service.WeatherService;
import com.pku.xinfeng.utils.CommonUtil;
import com.pku.xinfeng.utils.Constant;
import com.pku.xinfeng.utils.DateUtil;
import com.pku.xinfeng.utils.StringUtil;

@Controller
public class SensorController {
	private static final Logger logger = LoggerFactory
            .getLogger(SensorController.class);
	@Autowired
    private MainViewService mainViewService;
	
	@Autowired
    private WeatherService weatherService;
	
	@RequestMapping(value = "loadMainView", method = RequestMethod.POST)
	@ResponseBody
    public void loadMainView(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("---------loadMainView-----------");
		
		String userId = request.getParameter("userId");
        if (StringUtil.isEmpty(userId)) {
        	CommonUtil.returnFalse(response);
			return;
        }
        try {
        	List<SensorData> list = mainViewService.getEquipSensorListByUserId(userId);
        	
        	JSONArray equipAry = new JSONArray();
        	for(int i =0; i < list.size(); i++){
        		SensorData obj = list.get(i);
        		if(null == obj)
        			continue;
        		
        		JSONObject equipJson = new JSONObject();
        		equipJson.put("equipId", obj.getEquip_id());
        		equipJson.put("equipName", obj.getEquip_name());
        		equipJson.put("maxAirflow", obj.getMax_airflow());
        		equipJson.put("repairDate", null == obj.getRepair_date()?"":DateUtil.formatDate(obj.getRepair_date()));
        		equipJson.put("filterDate", null == obj.getFilter_date()?"":DateUtil.formatDate(obj.getFilter_date()));
        		equipJson.put("installDate", null == obj.getInstall_date()?"":DateUtil.formatDate(obj.getInstall_date()));
        		equipJson.put("factoryDate", null == obj.getFactory_date()?"":DateUtil.formatDate(obj.getFactory_date()));
        		
        		JSONObject warningJson = new JSONObject();
        		if(null != obj.getStrainer())
        			warningJson.put("strainer", Integer.parseInt(obj.getStrainer()));
        		else
        			warningJson.put("strainer", 0);//默认0：正常
        		equipJson.put("warning",warningJson );
        		//sensor
        		JSONObject sensorJson = new JSONObject();
        		sensorJson.put("pm25", StringUtil.makeNullToSymbol(obj.getPm25(),"-"));
        		sensorJson.put("co2", StringUtil.makeNullToSymbol(obj.getCo2(),"-"));
        		sensorJson.put("methanol", StringUtil.makeNullToSymbol(obj.getMethanol(),"-"));
        		sensorJson.put("temperature", StringUtil.makeNullToSymbol(obj.getTemperature(),"-"));
        		sensorJson.put("humidity", StringUtil.makeNullToSymbol(obj.getHumidity(),"-"));
        		//equipSettings,根据equipId查询
        		String statusStr = mainViewService.getNowAllStatus(obj.getEquip_id());
        		JSONObject settingJson = JSONObject.fromObject(statusStr);
        		equipJson.put("sensor", sensorJson);
        		equipJson.put("equipSettings", settingJson);
        		
        		equipAry.add(equipJson);
        	}
        	//天气
        	JSONArray weatherAry = new JSONArray();
        	List<Weather> wList = mainViewService.getWeatherListByUserId(userId);
        	
        	for(int i =0; i < wList.size(); i++){
        		JSONObject weatherJson = new JSONObject();
        		Weather obj = wList.get(i);
        		
        		weatherJson.put("weatherId", obj.getId());
        		weatherJson.put("city", obj.getCityName());
        		weatherJson.put("aqi", obj.getAqi());
        		weatherJson.put("co", obj.getCo());
        		weatherJson.put("o3", obj.getO3());
        		weatherJson.put("so2", obj.getSo2());
        		weatherJson.put("no2", obj.getNo2());
        		weatherJson.put("pm10", obj.getPm10());
        		weatherJson.put("pm25", obj.getPm25());
        		weatherJson.put("temperatureInterval", 
        				StringUtil.makeNullToEmptyString(obj.getTemperature_night()) 
        				+ "-" + StringUtil.makeNullToEmptyString(obj.getTemperature_day()));
        		weatherJson.put("windDirection", obj.getWind_direction());
        		weatherJson.put("weatherType", obj.getWeather_type());
        		weatherJson.put("weatherTypeName", obj.getWeather_type_name());
        		weatherAry.add(weatherJson);
        	}
        	
        	JSONObject jsonObj = new JSONObject();
        	jsonObj.put("equipment", equipAry);
        	jsonObj.put("weather", weatherAry);
        	
        	response.setContentType("application/json;charset=UTF-8");
        	response.getWriter().println(jsonObj.toString());
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
    }
	
	@RequestMapping(value = "loadMainView0", method = RequestMethod.POST)
	@ResponseBody
    public void loadMainView0(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("---------loadMainView-----------");
		
		String userId = request.getParameter("userId");
        if (StringUtil.isEmpty(userId)) {
        	CommonUtil.returnFalse(response);
			return;
        }
        try {
        	String returnString = "";
        	List<SensorData> list = mainViewService.getEquipSensorListByUserId(userId);
        	returnString += "{\"equipment\":[";
        	for(int i =0; i < list.size(); i++){
        		SensorData obj = list.get(i);
        		//传感器数据
        		int strainer = 0;
        		
        		returnString += "{";
        		returnString += "\"equipId\":\"" + obj.getEquip_id()+"\",";
        		returnString += "\"equipName\":\"" + obj.getEquip_name()+"\",";
        		returnString += "\"warning\":{\"strainer\":\"" + strainer + "\"},";
        		returnString += "\"sensor\":{";
        		returnString += "\"pm25\":\"" + StringUtil.makeNullToSymbol(obj.getPm25(),"-")+"\",";
        		returnString += "\"co2\":\"" + StringUtil.makeNullToSymbol(obj.getCo2(),"-")+"\",";
        		returnString += "\"methanol\":\"" + StringUtil.makeNullToSymbol(obj.getMethanol(),"-")+"\",";
        		returnString += "\"temperature\":\"" + StringUtil.makeNullToSymbol(obj.getTemperature(),"-")+"\",";
        		returnString += "\"humidity\":\"" + StringUtil.makeNullToSymbol(obj.getHumidity(),"-")+"\"";
        		returnString += "}}";
        		if(i < list.size() - 1)
        			returnString += ",";
        	}
        	returnString += "]";
        	//天气
        	List<Weather> wList = mainViewService.getWeatherListByUserId(userId);
        	
        	returnString += ",\"weather\":[";
        	for(int i =0; i < wList.size(); i++){
        		returnString += "{";
        		returnString += "\"weatherId\":\"" + wList.get(i).getId()+"\",";
        		returnString += "\"city\":\"" + wList.get(i).getCityName()+"\"";
        		returnString += "}";
        		if(i < wList.size() - 1)
        			returnString += ",";
        	}
        	returnString += "]}";
        	
        	response.setContentType("application/json;charset=UTF-8");
        	response.getWriter().println(returnString);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
    }
	
	@RequestMapping(value = "deleteEquip", method = RequestMethod.POST)
	@ResponseBody
    public void deleteEquip(HttpServletRequest request, HttpServletResponse response) {

        String userId = request.getParameter("userId");
        String equipId = request.getParameter("equipId");
        
        System.out.println("--------deleteEquip-------- ");
        System.out.println("userId : " + userId);
        System.out.println("equipId : " + equipId);
        
        if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(equipId)) {
        	CommonUtil.returnFalse(response);
			return;
        }
        try {
        	int f = mainViewService.deleteEquipByUser(userId, equipId);
        	JSONObject jsonObj = new JSONObject();
        	if(f > 0){
        		jsonObj.put("state", true);
        		jsonObj.put("description", "删除成功！");
        	}else{
        		jsonObj.put("state", false);
        		jsonObj.put("description", "删除失败！");
        	}
        	response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(jsonObj.toString());
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
	}
	
	@RequestMapping(value = "deleteCity", method = RequestMethod.POST)
	@ResponseBody
    public void deleteCity(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String weatherId = request.getParameter("weatherId");
        
        System.out.println("--------deleteCity-------- ");
        System.out.println("userId : " + userId);
        System.out.println("weatherId : " + weatherId);
        
        if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(weatherId)) {
        	CommonUtil.returnFalse(response);
			return;
        }
        try {
        	int f = mainViewService.deleteWeatherByUser(userId, weatherId);
        	JSONObject jsonObj = new JSONObject();
        	if(f > 0){
        		jsonObj.put("state", true);
        		jsonObj.put("description", "删除成功！");
        	}else{
        		jsonObj.put("state", false);
        		jsonObj.put("description", "删除失败！");
        	}
        	response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(jsonObj.toString());
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
	}
	
	@RequestMapping(value = "loadEquipDetail", method = RequestMethod.POST)
	@ResponseBody
    public void loadEquipDetail(HttpServletRequest request, HttpServletResponse response) {
        String equipId = request.getParameter("equipId");
        
        System.out.println("--------loadEquipDetail-------- ");
        System.out.println("equipId : " + equipId);
        
        if (StringUtil.isEmpty(equipId)) {
        	CommonUtil.returnFalse(response);
			return;
        }
        try {
        	List<Command> list = mainViewService.loadEquipCommand(equipId);
        	JSONObject jsonObj = new JSONObject();
        	if(null != list && list.size() > 0){
        		jsonObj.put("state", true);
        		jsonObj.put("description", "查询成功！");
        		
        		JSONObject json = new JSONObject();
        		
        		for(int i=0; i< list.size(); i++){
        			Command obj = list.get(i);
        			String type = obj.getType();
        			if(!Constant.CONSTANT_COMMAND_TIME.equals(type)){
        				json.put(type, StringUtil.makeNullToEmptyString(obj.getCommand()));
        			}
        		}
        		jsonObj.put("equipSettings", json);
        	}

        	response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(jsonObj.toString());
            System.out.println("return json:");
            System.out.println(jsonObj.toString());
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
	}
	
	@RequestMapping(value = "addWeather", method = RequestMethod.POST)
	@ResponseBody
    public void addWeather(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String city = request.getParameter("cityId");
        
        System.out.println("userId : " + userId);
        System.out.println("city : " + city);
        
        if (StringUtil.isEmpty(userId) 
        		|| StringUtil.isEmpty(city)) {
        	CommonUtil.returnFalse(response);
			return;
        }
        try {
        	//判断此城市是否已经添加
        	UserWeather wobj = mainViewService.getWeatherByUserIdAndCity(userId,city);
        	
        	JSONObject jsonObj = new JSONObject();
        	if(null == wobj || StringUtil.isEmpty(wobj.getUser_id())){//表示未添加此城市
        		UserWeather obj = new UserWeather();
                obj.setCity(city);
                obj.setUser_id(userId);
                obj.setUpdate_date(new Date());
            	boolean flag = mainViewService.addWeather(obj);
            	
            	if(flag){
            		jsonObj.put("state", true);
            		jsonObj.put("description", "添加成功！");
            	}else{
    	    		jsonObj.put("state", false);
    	    		jsonObj.put("description", "添加失败！");
            	}
        	}else{
        		jsonObj.put("state", false);
	    		jsonObj.put("description", "该城市已经添加！");
        	}
        	response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(jsonObj.toString());
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
    }
	
	@RequestMapping(value = "getHistory", method = RequestMethod.POST)
	@ResponseBody
    public void getHistory(HttpServletRequest request, HttpServletResponse response) {
        String equipId = request.getParameter("equipId");
        
        System.out.println("--------getHistory-------- ");
        System.out.println("equipId : " + equipId);
        
        if (StringUtil.isEmpty(equipId)) {
        	CommonUtil.returnFalse(response);
			return;
        }
        try {
        	List<SensorData> list = mainViewService.getHistory(equipId);
        	JSONObject jsonObj = new JSONObject();
        	if(null != list && list.size() > 0){
        		jsonObj.put("state", true);
        		jsonObj.put("description", "查询成功！");
        		
        		JSONArray hisAry = new JSONArray();
        		
        		for(int i=0; i< list.size(); i++){
        			SensorData obj = list.get(i);
        			JSONObject json = new JSONObject();
        			json.put("day", DateUtil.formatDate(obj.getYmDate()));
        			json.put("pm25", obj.getPm25());
        			json.put("pm10", obj.getPm10());
        			json.put("co2", obj.getCo2());
        			json.put("voc", obj.getVoc());
        			json.put("methanol", obj.getMethanol());
        			json.put("temperature", obj.getTemperature());
        			json.put("humidity", obj.getHumidity());
        			
        			hisAry.add(json);

        		}
        		jsonObj.put("history", hisAry);
        	}

        	response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(jsonObj.toString());
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
	}
	
	@RequestMapping(value = "getCityList", method = RequestMethod.POST)
	@ResponseBody
    public void getCityList(HttpServletRequest request, HttpServletResponse response) {
		List<Weather> list = weatherService.getCityList();
        try {
        	JSONArray jsonAry = new JSONArray();
        	if(null != list){
        		for(int i=0; i< list.size(); i++){
        			JSONObject json = new JSONObject();
        			json.put("city", list.get(i).getCityName());
        			json.put("cityNameEn", list.get(i).getCityNameEn());
        			jsonAry.add(json);
        		}
        	}
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("state", true);
    		jsonObj.put("description", "查询成功！");
    		jsonObj.put("citys", jsonAry);

        	response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(jsonObj.toString());
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
	}

}

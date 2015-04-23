package com.pku.xinfeng.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pku.xinfeng.model.OperLog;
import com.pku.xinfeng.model.SensorData;
import com.pku.xinfeng.model.User;
import com.pku.xinfeng.model.Version;
import com.pku.xinfeng.service.OperLogService;
import com.pku.xinfeng.service.SysService;
import com.pku.xinfeng.service.UserService;
import com.pku.xinfeng.utils.CommonUtil;
import com.pku.xinfeng.utils.Constant;
import com.pku.xinfeng.utils.DateUtil;
import com.pku.xinfeng.utils.ExplortExcel;
import com.pku.xinfeng.utils.StringUtil;

@Controller
@RequestMapping("sys")
public class SysController {
	private static final Logger logger = LoggerFactory
			.getLogger(SysController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private OperLogService operLogService;
	
	@Autowired
	private SysService sysService;

	@SuppressWarnings("unused")
	private void setResponseHeader(HttpServletResponse response, String fileName) throws Exception {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		response.setDateHeader("Expires", 0);
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("-------syslogin-------------");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		//默认账号密码：admin/admin
		System.out.println("username : " + username);
		System.out.println("password : " + password);

		if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) {
			return "redirect:/sys/login.jsp";
		}
		try {
			User user = userService.selectByUnameAndPsw(username,
					StringUtil.MD5Encode(password),"1");
			
	            if (null == user || StringUtil.isEmpty(user.getId())) {
	            	JSONObject resultJson = new JSONObject();
	            	resultJson.put("state", false);
        			resultJson.put("description", "用户名或密码错误！");
	            	response.setContentType("application/json;charset=UTF-8");
	        		response.getWriter().println(resultJson.toString());
					return "redirect:/sys/login.jsp";
	            } else {
	                request.getSession().setAttribute(Constant.SESSION_USER, user);;
	            }
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:/sys/index.jsp";
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.getSession().invalidate();
		return "redirect:/sys/index.jsp";
	}
	
	@RequestMapping(value = "getLogList", method = RequestMethod.POST)
    public void getLogList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String operType = request.getParameter("operType");
		String operDateBegin = request.getParameter("operDateBegin");
		String operDateEnd = request.getParameter("operDateEnd");
		String operLevel = request.getParameter("operLevel");
		
		System.out.println("$$$$--getLogList----$$$$$$$");
		System.out.println("operType : " + operType);
		System.out.println("operDateBegin : " + operDateBegin);
		System.out.println("operDateEnd : " + operDateEnd);
		System.out.println("operLevel : " + operLevel);
		
		OperLog obj = new OperLog();
		obj.setOper_level(operLevel);
		obj.setOper_type(operType);
		obj.setOperDateBegin(DateUtil.parseDateTime(operDateBegin + " 00:00"));
		obj.setOperDateEnd(DateUtil.parseDateTime(operDateEnd + " 23:59"));
		List<OperLog> list = operLogService.selectByOperLog(obj);
		
		JSONObject resultJson = new JSONObject();
		if(null != list && list.size() > 0){
			resultJson.put("state", true);
			resultJson.put("description", "查询成功！");
			JSONArray jsonArray = new JSONArray();
			for (OperLog logObj: list) {
			     JSONObject jsonObj = new JSONObject();
				 jsonObj.put("operData", logObj.getOper_data());
				 jsonObj.put("operLevel", logObj.getOper_level());
				 jsonObj.put("operTime", DateUtil.formatDateTime(logObj.getOper_time()));
				 jsonObj.put("operType", logObj.getOper_type());
			     jsonArray.add(jsonObj);
			 }
			resultJson.put("list", jsonArray);
		}
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().println(resultJson.toString());
    }
	
	@RequestMapping(value = "deleteVersion", method = RequestMethod.POST)
    public void deleteVersion(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("$$$$--deleteVersion----$$$$$$$");
		String id = request.getParameter("id");
		
		if (StringUtil.isEmpty(id)) {
			CommonUtil.returnFalse(response);
			return;
		}
		JSONObject resultJson = new JSONObject();
		List<HashMap<String,Object>> list = sysService.getListByVersion(Integer.parseInt(id));
		if(null == list || list.size() == 0){
			Version obj = sysService.deleteVersion(Integer.parseInt(id));
			
			if(null != obj){
				resultJson.put("state", true);
				resultJson.put("description", "删除成功！");
			}else{
				resultJson.put("state", false);
				resultJson.put("description", "删除失败！");
			}
		}else{
			resultJson.put("state", false);
			resultJson.put("description", "该型号已经添加设备，无法删除！");
		}
		
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().println(resultJson.toString());
    }
	
	@RequestMapping(value = "addVersion", method = RequestMethod.POST)
    public void addVersion(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("$$$$--addVersion----$$$$$$$");
		String version = request.getParameter("version");
		String commands = request.getParameter("commands");
		
		if (StringUtil.isEmpty(version) || StringUtil.isEmpty(commands)) {
			CommonUtil.returnFalse(response);
			return;
		}
		
		Version obj = new Version();
		obj.setVersion(version);
		obj.setCommands(commands);
		obj.setUpdate_date(new Date());
		boolean flag = sysService.addVersion(obj);
		
		JSONObject resultJson = new JSONObject();
		if(flag){
			resultJson.put("state", true);
			resultJson.put("description", "保存成功！");
		}else{
			resultJson.put("state", false);
			resultJson.put("description", "保存失败！");
		}
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().println(resultJson.toString());
    }
	
	@RequestMapping(value = "getVersion", method = RequestMethod.POST)
    public void getVersion(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("$$$$--getVersion----$$$$$$$");
		
		List<Version> list = sysService.getVersionList();
		
		JSONObject resultJson = new JSONObject();
		if(null != list && list.size() > 0){
			resultJson.put("state", true);
			resultJson.put("description", "查询成功！");
			JSONArray jsonArray = new JSONArray();
			for (Version obj: list) {
			     JSONObject jsonObj = new JSONObject();
			     jsonObj.put("id", obj.getId());
				 jsonObj.put("version", obj.getVersion());
				 String commands = obj.getCommands();
				 if(!StringUtil.isEmpty(commands)){
					 String[] ary = commands.split(",");
					 int len = ary.length;
					 commands = "";
					 for(int i=0; i<len;i++){
						 if(Constant.CONSTANT_COMMAND_MODEL.equals(ary[i])){
							 commands += "自动手动";
						 }else if(Constant.CONSTANT_COMMAND_AIRLOOP.equals(ary[i])){
							 commands += "内外循环";
						 }else if(Constant.CONSTANT_COMMAND_SLEEP.equals(ary[i])){
							 commands += "睡眠";
						 }else if(Constant.CONSTANT_COMMAND_HEAT.equals(ary[i])){
							 commands += "加热";
						 }else if(Constant.CONSTANT_COMMAND_STATICELECTRICITY.equals(ary[i])){
							 commands += "静电";
						 }else if(Constant.CONSTANT_COMMAND_TIME.equals(ary[i])){
							 commands += "定时";
						 }else if(Constant.CONSTANT_COMMAND_ONOROFF.equals(ary[i])){
							 commands += "电源开关";
						 }
						 if(i < len-1)
							 commands += "，";
					 }
				 }
				 jsonObj.put("commands", commands);
			     jsonArray.add(jsonObj);
			 }
			resultJson.put("list", jsonArray);
		}
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().println(resultJson.toString());
    }
	@RequestMapping(value = "modifySysPassword", method = RequestMethod.POST)
	@ResponseBody
	public void modifySysPassword(HttpServletRequest request,
			HttpServletResponse response) {
		User sessionUser = (User) request.getSession().getAttribute(Constant.SESSION_USER);
    	
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");

		System.out.println("--------modifySysPassword-------- ");
		System.out.println("oldPassword : " + oldPassword);
		System.out.println("newPassword : " + newPassword);

		if (StringUtil.isEmpty(oldPassword)
				|| StringUtil.isEmpty(newPassword)) {
			CommonUtil.returnFalse(response);
			return;
		}
		try {
			JSONObject jsonObj = new JSONObject();
			String userId = sessionUser.getId();
			User checkUser = userService.selectByUidAndPsw(userId, StringUtil.MD5Encode(oldPassword));
			if(null != checkUser && !StringUtil.isEmpty(checkUser.getId())){//验证用户
				User user = new User();
				user.setId(userId);
				user.setPassword(StringUtil.MD5Encode(newPassword));
				int f = userService.modifyUserById(user);
				if(f > 0){
					jsonObj.put("state", true);
					jsonObj.put("description", "更新成功！");
				}else{
					jsonObj.put("state", false);
					jsonObj.put("description", "更新失败！");
				}
			}else{
				jsonObj.put("state", false);
				jsonObj.put("description", "旧密码错误！");
			}
				
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(jsonObj.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}
	
	@RequestMapping(value = "exportUserDetailList", method = RequestMethod.POST)
    public void exportUserDetailList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("$$$$--exportUserDetailList----$$$$$$$");
		List<HashMap<String, Object>> list = sysService.getUserEquipList();
		if(null == list)
			list = new ArrayList<HashMap<String, Object>>();
		
		String[] cellAttr = Constant.Excel_User_colu;// 字段
		String[] cellHeader = Constant.Excel_User_value;// 表头
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		workbook = ExplortExcel.creatWorkbookMap(list, "用户详细信息",cellHeader, cellAttr);
		response.setHeader("Content-Type","application/vnd.ms-excel");
		response.setHeader("Pragma", "No-cache");  
		response.setHeader("Cache-Control", "No-cache");  
		response.setDateHeader("Expires", 0);
        response.setHeader("Content-Disposition", "attachment;filename=userInfoDetail.xls");  
        OutputStream ouputStream = response.getOutputStream();  
        workbook.write(ouputStream);  
        ouputStream.flush();  
        ouputStream.close();  
	}
	
	@RequestMapping(value = "exportEquipSensorDatas", method = RequestMethod.POST)
    public void exportEquipSensorDatas(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("$$$$--exportEquipSensorDatas----$$$$$$$");
		List<SensorData> list = sysService.getEquipSensorDatas();
		if(null == list)
			list = new ArrayList<SensorData>();
		
		List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
		for(int i=0; i<list.size(); i++){
			SensorData obj = list.get(i);
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("equipId", obj.getEquip_id());
			map.put("pm25", obj.getPm25());
			map.put("pm10", obj.getPm10());
			map.put("co2", obj.getCo2());
			map.put("voc", obj.getVoc());
			map.put("methanol", obj.getMethanol());
			map.put("temperature", obj.getTemperature());
			map.put("humidity", obj.getHumidity());
			map.put("strainer", obj.getStrainer());
			map.put("update_date", DateUtil.formatDateTime(obj.getUpdate_date()));
			mapList.add(map);
		}
		
		String[] cellAttr = Constant.Excel_EQUIP_colu;// 字段
		String[] cellHeader = Constant.Excel_EQUIP_value;// 表头
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		workbook = ExplortExcel.creatWorkbookMap(mapList, "设备传感器信息",cellHeader, cellAttr);
		response.setHeader("Content-Type","application/vnd.ms-excel");
		response.setHeader("Pragma", "No-cache");  
		response.setHeader("Cache-Control", "No-cache");  
		response.setDateHeader("Expires", 0);
        response.setHeader("Content-Disposition", "attachment;filename=equipSensorDatas.xls");  
        OutputStream ouputStream = response.getOutputStream();  
        workbook.write(ouputStream);  
        ouputStream.flush();  
        ouputStream.close();  
	}
}

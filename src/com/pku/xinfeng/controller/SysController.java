package com.pku.xinfeng.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
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

import com.pku.xinfeng.model.OperLog;
import com.pku.xinfeng.model.User;
import com.pku.xinfeng.pojo.UserDetail;
import com.pku.xinfeng.service.OperLogService;
import com.pku.xinfeng.service.UserService;
import com.pku.xinfeng.utils.Constant;
import com.pku.xinfeng.utils.DateUtil;
import com.pku.xinfeng.utils.Export;
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
	
	@RequestMapping(value = "getUserEquipList", method = RequestMethod.POST)
    public void getUserEquipList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		List<UserDetail> list = userService.getUserEquipList();
		
		List<HashMap<String,Object>> sessionList = new ArrayList<HashMap<String,Object>>();
		
		JSONObject resultJson = new JSONObject();
		if(null != list && list.size() > 0){
			resultJson.put("state", true);
			resultJson.put("description", "查询成功！");
			
			JSONArray jsonArray = new JSONArray();
			for (int i=0; i < list.size(); i++) {
				UserDetail obj = list.get(i);
				
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("userId", obj.getUserId());
				jsonObj.put("userName", obj.getUserName());
				jsonObj.put("phone", obj.getPhone());
				jsonObj.put("equipId", obj.getEquipId());
				jsonObj.put("equipName", obj.getEquipName());
				jsonObj.put("repairDate", DateUtil.formatDate(obj.getRepairDate()));
				jsonObj.put("filterDate", DateUtil.formatDate(obj.getFilterDate()));
				jsonArray.add(jsonObj);
			     
				HashMap<String,Object> map = new HashMap<String,Object>();
				map.put("order", i+1);
				map.put("userId", obj.getUserId());
				map.put("userName", obj.getUserName());
				map.put("phone", obj.getPhone());
				map.put("equipId", obj.getEquipId());
				map.put("equipName", obj.getEquipName());
				map.put("repairDate", DateUtil.formatDate(obj.getRepairDate()));
				map.put("filterDate", DateUtil.formatDate(obj.getFilterDate()));
				sessionList.add(map);
			 }
			resultJson.put("list", jsonArray);
			request.getSession().setAttribute(Constant.SESSION_USER_DETAIL, jsonArray);
		}
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().println(resultJson.toString());
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "exportLogList", method = RequestMethod.POST)
    public void exportUserDetailList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<HashMap> list = (List<HashMap>) request.getSession().getAttribute(Constant.SESSION_USER_DETAIL);
		
		String[] cellAttr = Constant.Excel_User_colu;// 字段
		String[] cellHeader = Constant.Excel_User_value;// 表头
		String sheetName = "countBfJjYear";
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		workbook = Export.getHSSFWorkbook(list, sheetName,cellHeader, cellAttr);
		
		try {
			response.setCharacterEncoding("UTF-8");   

			setResponseHeader(response, sheetName + ".xls");
			workbook.write(response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void setResponseHeader(HttpServletResponse response, String fileName) throws Exception {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		response.setDateHeader("Expires", 0);
	}
}

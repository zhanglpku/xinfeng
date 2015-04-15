package com.pku.xinfeng.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pku.xinfeng.model.User;
import com.pku.xinfeng.service.UserService;
import com.pku.xinfeng.utils.CommonUtil;
import com.pku.xinfeng.utils.DateUtil;
import com.pku.xinfeng.utils.HttpUtil;
import com.pku.xinfeng.utils.ID;
import com.pku.xinfeng.utils.StringUtil;

@Controller
public class UserController {
	private static final Logger logger = Logger.getLogger(UserController.class.getName());
	
	private static Map<String,String> randCodeMap = new HashMap<String,String>();

	@Autowired
	private UserService userService;

	@RequestMapping(value = "login2", method = RequestMethod.POST)
	@ResponseBody
	public void login2(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		System.out.println("username : " + username);
		System.out.println("password : " + password);

		if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) {
			return;
		}
		try {
			JSONObject jsonObj = new JSONObject();
			User user = userService.selectByUnameAndPsw(username,
					StringUtil.MD5Encode(password),"");
			if (StringUtil.isEmpty(user.getUsername())) {
				jsonObj.put("userId", "");
				jsonObj.put("state", false);
				jsonObj.put("description", "用户名或密码错误！");
			} else {
				jsonObj.put("userId", user.getId());
				jsonObj.put("state", true);
				jsonObj.put("description", "登陆成功！");
			}
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(jsonObj.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public void login(HttpServletRequest request, HttpServletResponse response) {
		String phoneNumber = request.getParameter("phoneNumber");
		String password = request.getParameter("password");

		System.out.println("phoneNumber : " + phoneNumber);
		System.out.println("password : " + password);

		if (StringUtil.isEmpty(phoneNumber) || StringUtil.isEmpty(password)) {
			CommonUtil.returnFalse(response);
			return;
		}
		try {
			JSONObject jsonObj = new JSONObject();
			User user = userService.selectByPhoneAndPsw(phoneNumber,
					StringUtil.MD5Encode(password));
			if (StringUtil.isEmpty(user.getUsername())) {
				jsonObj.put("userId", "");
				jsonObj.put("state", false);
				jsonObj.put("description", "手机号或密码错误！");
			} else {
				JSONObject userJson = new JSONObject();
				userJson.put("userId", user.getId());
				userJson.put("phoneNumber", user.getPhone());
				userJson.put("wechat", StringUtil.makeNullToEmptyString(user.getWechat()));
				userJson.put("QQ", StringUtil.makeNullToEmptyString(user.getQq()));
				userJson.put("address", StringUtil.makeNullToEmptyString(user.getAddress()));
				userJson.put("mail", StringUtil.makeNullToEmptyString(user.getMail()));
				userJson.put("username", StringUtil.makeNullToEmptyString(user.getUsername()));	
				jsonObj.put("user", userJson);
				jsonObj.put("state", true);
				jsonObj.put("description", "登陆成功！");
			}
			logger.debug("%%%%%%%%%%%%%%%%43s5gdsgfd");
			logger.info("S@@@@@@@@@@@@@@@@@@@@@@@");
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(jsonObj.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	@ResponseBody
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public void signup(HttpServletRequest request, HttpServletResponse response) {
		String time = userService.getTimeStatus("c8123456789a");
		System.out.println("time : " + time);
		System.out.println("$$$ *********signup*******");
		String phoneNumber = request.getParameter("phoneNumber");
		String verifyCode = request.getParameter("verifyCode");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)
				|| StringUtil.isEmpty(phoneNumber) || StringUtil.isEmpty(verifyCode)) {
			CommonUtil.returnFalse(response);
			return;
		}
		try {
			JSONObject jsonObj = new JSONObject();
			String checkStr = checkcode(phoneNumber,verifyCode);
        	System.out.println("checkStr=" + checkStr);
        	if(StringUtil.toBoolean(checkStr)){//验证码
				User obj = userService.selectByPhone(phoneNumber);
				if (null == obj || StringUtil.isEmpty(obj.getId())) {

					User user = new User();
					user.setId(ID.get());
					user.setUsername(username);
					user.setPassword(StringUtil.MD5Encode(password));
					user.setPhone(phoneNumber);
					user.setUpdate_date(new Date());
					int flag = userService.addUser(user);

					if (flag <= 0) {// 插入失败
						jsonObj.put("userId", "");
						jsonObj.put("state", false);
						jsonObj.put("description", "注册失败！");
					} else {// 插入成功
						User loginUser = userService.selectByPhoneAndPsw(phoneNumber,
								StringUtil.MD5Encode(password));
						JSONObject userJson = new JSONObject();
						userJson.put("userId", loginUser.getId());
						userJson.put("phoneNumber", user.getPhone());
						userJson.put("wechat", StringUtil.makeNullToEmptyString(loginUser.getWechat()));
						userJson.put("QQ", StringUtil.makeNullToEmptyString(loginUser.getQq()));
						userJson.put("address", StringUtil.makeNullToEmptyString(loginUser.getAddress()));
						userJson.put("mail", StringUtil.makeNullToEmptyString(loginUser.getMail()));
						userJson.put("username", StringUtil.makeNullToEmptyString(loginUser.getUsername()));	
						jsonObj.put("user", userJson);
						jsonObj.put("state", true);
						jsonObj.put("description", "注册成功！");
					}
				} else {
					jsonObj.put("userId", "");
					jsonObj.put("state", false);
					jsonObj.put("description", "手机号已注册！");
				}
			}else {
				jsonObj.put("userId", "");
				jsonObj.put("state", false);
				jsonObj.put("description", checkStr);
			}
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(jsonObj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "sendCode", method = RequestMethod.POST)
	@ResponseBody
	public void sendCode(HttpServletRequest request,
			HttpServletResponse response) {
		String phoneNumber = request.getParameter("phoneNumber");
		String isExistingUser = request.getParameter("isExistingUser");

		System.out.println("--------sendCode-------- ");
		System.out.println("phoneNumber : " + phoneNumber);
		System.out.println("isExistingUser : " + isExistingUser);

		if (StringUtil.isEmpty(phoneNumber)) {
			CommonUtil.returnFalse(response);
			return;
		}
		try {
			String rand = StringUtil.getRandcode();
			//发送短信
			boolean flag = HttpUtil.sendMsg(phoneNumber, rand);
			System.out.println(rand + "---sendcode,flag = " + flag);
			JSONObject jsonObj = new JSONObject();
			if(flag){
				//设置session
				if(null == randCodeMap)
					randCodeMap = new HashMap<String,String>();
				randCodeMap.put(phoneNumber, rand+"#"+DateUtil.formatDateTime(new Date()));
				
				System.out.println("-----randCodeMap.get-------- : " + randCodeMap.get(phoneNumber));
				jsonObj.put("description", "验证码发送成功！");
			}else
				jsonObj.put("description", "验证码发送失败！");
			
			jsonObj.put("state", flag);
			
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(jsonObj.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	@RequestMapping(value = "findPassword", method = RequestMethod.POST)
	@ResponseBody
	public void findPassword(HttpServletRequest request,
			HttpServletResponse response) {
		String phoneNumber = request.getParameter("phoneNumber");
		String verifyCode = request.getParameter("verifyCode");
		String newPassword = request.getParameter("newPassword");

		System.out.println("--------findPassword-------- ");
		System.out.println("phoneNumber : " + phoneNumber);
		System.out.println("verifyCode : " + verifyCode);
		System.out.println("newPassword : " + newPassword);

		if (StringUtil.isEmpty(phoneNumber) || StringUtil.isEmpty(verifyCode)
				|| StringUtil.isEmpty(newPassword)) {
			CommonUtil.returnFalse(response);
			return;
		}
		try {
			JSONObject jsonObj = new JSONObject();
			String checkStr = checkcode(phoneNumber,verifyCode);
        	System.out.println("checkStr=" + checkStr);
        	if(StringUtil.toBoolean(checkStr)){//验证码
				int f = userService.updatePswByPhone(phoneNumber,
						StringUtil.MD5Encode(newPassword));
				if (f > 0) {
					jsonObj.put("state", true);
					jsonObj.put("description", "操作成功！");
				} else {
					jsonObj.put("state", false);
					jsonObj.put("description", "密码更新操作失败！");
				}
			} else {
				jsonObj.put("state", false);
				jsonObj.put("description", checkStr);
			}

			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(jsonObj.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	@RequestMapping(value = "modifyPassword", method = RequestMethod.POST)
	@ResponseBody
	public void modifyPassword(HttpServletRequest request,
			HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");

		System.out.println("--------modifyPassword-------- ");
		System.out.println("userId : " + userId);
		System.out.println("oldPassword : " + oldPassword);
		System.out.println("newPassword : " + newPassword);

		if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(oldPassword)
				|| StringUtil.isEmpty(newPassword)) {
			CommonUtil.returnFalse(response);
			return;
		}
		try {
			JSONObject jsonObj = new JSONObject();
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
				jsonObj.put("description", "密码错误！");
			}
				
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(jsonObj.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	@RequestMapping(value = "modifyWechat", method = RequestMethod.POST)
	@ResponseBody
    public void modifyWechat(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String newValue = request.getParameter("newValue");
        
        System.out.println("userId : " + userId);
        System.out.println("newValue : " + newValue);
        
        if (StringUtil.isEmpty(userId) 
        		|| StringUtil.isEmpty(newValue)) {
        	CommonUtil.returnFalse(response);
			return;
        }
        try {
        	User obj = new User();
        	obj.setId(userId);
        	obj.setWechat(newValue);;
        	int flag = userService.modifyUserById(obj);
        	
        	JSONObject jsonObj = new JSONObject();
        	if(flag > 0){
        		jsonObj.put("state", true);
        		jsonObj.put("description", "更新成功！");
        	}else{
	    		jsonObj.put("state", false);
	    		jsonObj.put("description", "更新失败！");
        	}
        	response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(jsonObj.toString());
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
    }
	
	@RequestMapping(value = "modifyQQ", method = RequestMethod.POST)
	@ResponseBody
    public void modifyQQ(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String newValue = request.getParameter("newValue");
        
        System.out.println("userId : " + userId);
        System.out.println("newValue : " + newValue);
        
        if (StringUtil.isEmpty(userId) 
        		|| StringUtil.isEmpty(newValue)) {
        	CommonUtil.returnFalse(response);
			return;
        }
        try {
        	User obj = new User();
        	obj.setId(userId);;
        	obj.setQq(newValue);
        	int flag = userService.modifyUserById(obj);
        	
        	JSONObject jsonObj = new JSONObject();
        	if(flag > 0){
        		jsonObj.put("state", true);
        		jsonObj.put("description", "更新成功！");
        	}else{
	    		jsonObj.put("state", false);
	    		jsonObj.put("description", "更新失败！");
        	}
        	response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(jsonObj.toString());
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
    }
	
	@RequestMapping(value = "modifyAddress", method = RequestMethod.POST)
	@ResponseBody
    public void modifyAddress(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String newValue = request.getParameter("newValue");
        
        System.out.println("userId : " + userId);
        System.out.println("newValue : " + newValue);
        
        if (StringUtil.isEmpty(userId) 
        		|| StringUtil.isEmpty(newValue)) {
        	CommonUtil.returnFalse(response);
			return;
        }
        try {
        	User obj = new User();
        	obj.setId(userId);;
        	obj.setAddress(newValue);
        	int flag = userService.modifyUserById(obj);
        	
        	JSONObject jsonObj = new JSONObject();
        	if(flag > 0){
        		jsonObj.put("state", true);
        		jsonObj.put("description", "更新成功！");
        	}else{
	    		jsonObj.put("state", false);
	    		jsonObj.put("description", "更新失败！");
        	}
        	response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(jsonObj.toString());
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
    }
	
	@RequestMapping(value = "modifyMail", method = RequestMethod.POST)
	@ResponseBody
    public void modifyMail(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String newValue = request.getParameter("newValue");
        
        System.out.println("userId : " + userId);
        System.out.println("newValue : " + newValue);
        
        if (StringUtil.isEmpty(userId) 
        		|| StringUtil.isEmpty(newValue)) {
        	CommonUtil.returnFalse(response);
			return;
        }
        try {
        	User obj = new User();
        	obj.setId(userId);;
        	obj.setMail(newValue);
        	int flag = userService.modifyUserById(obj);
        	
        	JSONObject jsonObj = new JSONObject();
        	if(flag > 0){
        		jsonObj.put("state", true);
        		jsonObj.put("description", "更新成功！");
        	}else{
	    		jsonObj.put("state", false);
	    		jsonObj.put("description", "更新失败！");
        	}
        	response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(jsonObj.toString());
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
    }
	
	@RequestMapping(value = "modifyUsername", method = RequestMethod.POST)
	@ResponseBody
    public void modifyUsername(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String newValue = request.getParameter("newValue");
        
        System.out.println("userId : " + userId);
        System.out.println("newValue : " + newValue);
        
        if (StringUtil.isEmpty(userId) 
        		|| StringUtil.isEmpty(newValue)) {
        	CommonUtil.returnFalse(response);
			return;
        }
        try {
        	User obj = new User();
        	obj.setId(userId);;
        	obj.setUsername(newValue);
        	int flag = userService.modifyUserById(obj);
        	
        	JSONObject jsonObj = new JSONObject();
        	if(flag > 0){
        		jsonObj.put("state", true);
        		jsonObj.put("description", "更新成功！");
        	}else{
	    		jsonObj.put("state", false);
	    		jsonObj.put("description", "更新失败！");
        	}
        	response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(jsonObj.toString());
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
    }
	
	@RequestMapping(value = "modifyPhoneNumber", method = RequestMethod.POST)
	@ResponseBody
    public void modifyPhoneNumber(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String phoneNumber = request.getParameter("phoneNumber");
        String verifyCode = request.getParameter("verifyCode");
        
        System.out.println("----------modifyPhoneNumber---------------");
        System.out.println("userId : " + userId);
        System.out.println("phoneNumber : " + phoneNumber);
        System.out.println("verifyCode : " + verifyCode);
        
        if (StringUtil.isEmpty(userId) 
        		|| StringUtil.isEmpty(phoneNumber) 
        		|| StringUtil.isEmpty(verifyCode)) {
        	CommonUtil.returnFalse(response);
			return;
        }
        try {
        	JSONObject jsonObj = new JSONObject();
        	String checkStr = checkcode(phoneNumber,verifyCode);
        	System.out.println("checkStr=" + checkStr);
        	if(StringUtil.toBoolean(checkStr)){//验证码
        		User obj = new User();
            	obj.setId(userId);;
            	obj.setPhone(phoneNumber);
            	int flag = userService.modifyUserById(obj);
            	
            	if(flag > 0){
            		jsonObj.put("state", true);
            		jsonObj.put("description", "更新成功！");
            	}else{
    	    		jsonObj.put("state", false);
    	    		jsonObj.put("description", "更新失败！");
            	}
        	}else{
        		jsonObj.put("state", false);
				jsonObj.put("description", checkStr);
        	}
        	response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(jsonObj.toString());
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
    }
	private String checkcode(String phoneNumber,String verifyCode){
		String returnStr = "验证码超时或错误！";
		if(!StringUtil.isEmpty(verifyCode)){
			String sessionCode = randCodeMap.get(phoneNumber);
			if(!StringUtil.isEmpty(sessionCode)){
				String[] ary = sessionCode.split("#");
				String code = ary[0];
				String date = ary[1];
				
				long beginTime = DateUtil.parseDateTime(date).getTime(); 
				long endTime = new Date().getTime(); 
				long betweenDays = (endTime - beginTime) / (1000 * 60); 
				System.out.println(code + "--" + date + " -- " + betweenDays);
				if(betweenDays <= 10){
					if(verifyCode.equals(code))
						returnStr = "true";
					else
						returnStr = "验证码错误！";
				}else{
					returnStr = "验证码超时！";
				}
			}
		}
		return returnStr;
	}
}

package com.pku.xinfeng.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class CommonUtil {
	public static void returnFalse(HttpServletResponse response){
		try {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("state", false);
			jsonObj.put("description", "输入参数有问题，可能为空，可能格式有问题，或者其他原因！");
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(jsonObj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

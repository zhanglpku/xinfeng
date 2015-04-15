package com.pku.xinfeng.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.pku.xinfeng.dao.CityCodeMapper;
import com.pku.xinfeng.model.CityCode;

public class SysCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 6392574525124256492L;

	public static Map<String,String> cityMap = new HashMap<String,String>();
	public static Map<String,String> windDireMap = new HashMap<String,String>();
	public static Map<String,String> windForceMap = new HashMap<String,String>();
	public static Map<String,String> weatherTypeMap = new HashMap<String,String>();
	/**
	 * Constructor of the object.
	 */
	public SysCodeServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.setContentType("text/html");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.setContentType("text/html");
	}

	public void init() throws ServletException {
		// Put your code here
		System.out.println("** map init ******  begin");
		try {
			this.initMapValues();
			this.initCitycodeMap();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("** map init ******  end");
	}

	public void initMapValues() {
		//风力
		windForceMap.put("0", "微风");
		windForceMap.put("1", "3-4级");
		windForceMap.put("2", "4-5级");
		windForceMap.put("3", "5-6级");
		windForceMap.put("4", "6-7级");
		windForceMap.put("5", "7-8级");
		windForceMap.put("6", "8-9级");
		windForceMap.put("7", "8-10级");
		windForceMap.put("8", "10-11级");
		windForceMap.put("9", "11-12级");
		//风向
		windDireMap.put("0", "无持续风向");
		windDireMap.put("1", "东北风");
		windDireMap.put("2", "东风");
		windDireMap.put("3", "东南风");
		windDireMap.put("4", "南风");
		windDireMap.put("5", "西南风");
		windDireMap.put("6", "西风");
		windDireMap.put("7", "西北风");
		windDireMap.put("8", "北风");
		windDireMap.put("9", "旋转风");
		//天气现象
		weatherTypeMap.put("00", "晴");
		weatherTypeMap.put("01", "多云");
		weatherTypeMap.put("02", "阴");
		weatherTypeMap.put("03", "阵雨");
		weatherTypeMap.put("04", "雷阵雨");
		weatherTypeMap.put("05", "雷阵雨伴有冰雹");
		weatherTypeMap.put("06", "雨夹雪");
		weatherTypeMap.put("07", "小雨");
		weatherTypeMap.put("08", "中雨");
		weatherTypeMap.put("09", "大雨");
		weatherTypeMap.put("10", "暴雨");
		weatherTypeMap.put("11", "大暴雨");
		weatherTypeMap.put("12", "特大暴雨");
		weatherTypeMap.put("13", "阵雪");
		weatherTypeMap.put("14", "小雪");
		weatherTypeMap.put("15", "中雪");
		weatherTypeMap.put("16", "大雪");
		weatherTypeMap.put("17", "暴雪");
		weatherTypeMap.put("18", "雾");
		weatherTypeMap.put("19", "冻雨");
		weatherTypeMap.put("20", "沙尘暴");
		weatherTypeMap.put("21", "小到中雨");
		weatherTypeMap.put("22", "中到大雨");
		weatherTypeMap.put("23", "大到暴雨");
		weatherTypeMap.put("24", "暴雨到大暴雨");
		weatherTypeMap.put("25", "大暴雨到特大暴雨");
		weatherTypeMap.put("26", "小到中雪");
		weatherTypeMap.put("27", "中到大雪");
		weatherTypeMap.put("28", "大到暴雪");
		weatherTypeMap.put("29", "浮尘");
		weatherTypeMap.put("30", "扬沙");
		weatherTypeMap.put("31", "强沙尘暴");
		weatherTypeMap.put("53", "霾");
		weatherTypeMap.put("99", "无");
	}
	
	public void initCitycodeMap(){
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		CityCodeMapper cityCodeMapper = (CityCodeMapper)ctx.getBean("cityCodeMapper");
		
		List<CityCode> codeList = cityCodeMapper.selectByExample(null);
		if(null != codeList){
			for(int i = 0; i < codeList.size(); i++){
				CityCode obj = codeList.get(i);
				cityMap.put(obj.getCityName(), obj.getId());
			}
		}
	}
}

package com.pku.xinfeng.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pku.xinfeng.dao.PmMapper;
import com.pku.xinfeng.dao.TemperatureMapper;
import com.pku.xinfeng.dao.WeatherMapper;
import com.pku.xinfeng.model.Pm;
import com.pku.xinfeng.model.Temperature;
import com.pku.xinfeng.model.UserEquipment;
import com.pku.xinfeng.model.Weather;
import com.pku.xinfeng.service.SysService;
import com.pku.xinfeng.utils.HttpUtil;
import com.pku.xinfeng.utils.StringUtil;
import com.pku.xinfeng.utils.SysCodeServlet;
import com.pku.xinfeng.utils.WeatherInterfaceUtil;


/**
 * @author zhangl
 *
 */
@Service("sysService")
public class SysServiceImpl implements SysService {
	

	@Override
	public HashMap<String,Object> getUserEquipList() {
		// TODO Auto-generated method stub
		return null;
	}}

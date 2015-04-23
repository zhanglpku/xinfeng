package com.pku.xinfeng.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pku.xinfeng.dao.SensorDataMapper;
import com.pku.xinfeng.dao.UserEquipmentMapper;
import com.pku.xinfeng.dao.VersionMapper;
import com.pku.xinfeng.model.SensorData;
import com.pku.xinfeng.model.SensorDataExample;
import com.pku.xinfeng.model.Version;
import com.pku.xinfeng.service.SysService;


/**
 * @author zhangl
 *
 */
@Service("sysService")
public class SysServiceImpl implements SysService {
	@Autowired
	private VersionMapper versionMapper;
	
	@Autowired
	private UserEquipmentMapper userEquipmentMapper;
	
	@Autowired
	private SensorDataMapper sensorDataMapper;

	@Override
	public List<Version> getVersionList() {
		// TODO Auto-generated method stub
		List<Version> list = new ArrayList<Version>();
		list = versionMapper.selectByExample(null);
		return list;
	}

	@Override
	public Version deleteVersion(Integer id) {
		// TODO Auto-generated method stub
		Version obj = versionMapper.selectByPrimaryKey(id);
		
		int ff = versionMapper.deleteByPrimaryKey(id);
		if(ff <= 0)//表示删除失败
			obj = null;
		return obj;
	}

	@Override
	public boolean addVersion(Version obj) {
		// TODO Auto-generated method stub
		boolean flag = false;
		int ff = versionMapper.insert(obj);
		if(ff > 0)
			flag = true;
		return flag;
	}

	@Override
	public List<HashMap<String, Object>> getUserEquipList() {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> list = userEquipmentMapper.getUserDetail();
		return list;
	}

	@Override
	public List<HashMap<String, Object>> getListByVersion(Integer id) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> list = versionMapper.getListByVersion(id);
		return list;
	}

	@Override
	public List<SensorData> getEquipSensorDatas() {
		// TODO Auto-generated method stub
		SensorDataExample example = new SensorDataExample();
		example.setOrderByClause("equip_id,update_date");
		List<SensorData> list = sensorDataMapper.selectByExample(example);
		return list;
	}
}

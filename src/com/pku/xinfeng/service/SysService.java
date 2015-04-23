/**
 * 
 */
package com.pku.xinfeng.service;

import java.util.HashMap;
import java.util.List;

import com.pku.xinfeng.model.SensorData;
import com.pku.xinfeng.model.Version;


/**
 * @author zhangl
 *
 */
public interface SysService {
	public List<HashMap<String, Object>> getUserEquipList();
	public List<Version> getVersionList();
	
	public Version deleteVersion(Integer id);
	public boolean addVersion(Version obj);
	List<HashMap<String,Object>> getListByVersion(Integer id);
	
	public List<SensorData> getEquipSensorDatas();
}

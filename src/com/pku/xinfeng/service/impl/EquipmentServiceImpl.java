package com.pku.xinfeng.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pku.xinfeng.dao.CommandMapper;
import com.pku.xinfeng.dao.EquipmentMapper;
import com.pku.xinfeng.dao.TimeSetMapper;
import com.pku.xinfeng.dao.UserEquipmentMapper;
import com.pku.xinfeng.dataCache.DataCacheMap;
import com.pku.xinfeng.model.Command;
import com.pku.xinfeng.model.Equipment;
import com.pku.xinfeng.model.EquipmentExample;
import com.pku.xinfeng.model.TimeSet;
import com.pku.xinfeng.model.TimeSetExample;
import com.pku.xinfeng.model.UserEquipment;
import com.pku.xinfeng.model.UserEquipmentExample;
import com.pku.xinfeng.service.EquipmentService;
import com.pku.xinfeng.service.OperLogService;
import com.pku.xinfeng.utils.Constant;

@Service("equipmentService")
public class EquipmentServiceImpl implements EquipmentService {
	@Autowired
	private EquipmentMapper equipmentMapper;
	
	@Autowired
	private UserEquipmentMapper userEquipmentMapper;

	@Autowired
	private CommandMapper commandMapper;

	@Autowired
	private TimeSetMapper timeSetMapper;
	
	@Autowired
	private OperLogService operLogService;
	
	@Override
	@Transactional
	public boolean addEquipment(UserEquipment record,Equipment obj) {
		// TODO Auto-generated method stub
		boolean flag = false;
		//判断此设备是否已在设备表中
		EquipmentExample example = new EquipmentExample();
		com.pku.xinfeng.model.EquipmentExample.Criteria cr = example.createCriteria();
		cr.andIdEqualTo(obj.getId());
		example.or(cr);
		List<Equipment> list = equipmentMapper.selectByExample(example);
		if(null == list || list.size() == 0){
			//新增设备
			equipmentMapper.addEquipment(obj);
		}			
		
		//用户设备关系
		int ff = userEquipmentMapper.insert(record);
		if(ff > 0)
			flag = true;
		
		String data = "新增设备:insert into equipment,user_equipment where userId=" + record.getUser_id() + ",equipId=" + obj.getId();
		operLogService.insert(Constant.OPER_TYPE_ADD,
				Constant.OPER_TYPE_COMMON, data, record.getUser_id(), obj.getId());
		return flag;
	}

//	@Override
//	public boolean saveCommand(String type, String command,String note, String equipId) {
//		// TODO Auto-generated method stub
//		boolean flag = false;
//		
//		Command record = new Command();
//		record.setCommand(command);
//		record.setEquip_id(equipId);
//		record.setStatus(false);
//		record.setUpdate_date(new Date());
//		record.setNote(note);
//		if(Constant.CONSTANT_COMMAND_ONOROFF.equals(type)){
//			record.setType(Constant.CONSTANT_COMMAND_ONOROFF);
//		}else if(Constant.CONSTANT_COMMAND_SLEEP.equals(type)){
//			record.setType(Constant.CONSTANT_COMMAND_SLEEP);
//		}else if(Constant.CONSTANT_COMMAND_HEAT.equals(type)){
//			record.setType(Constant.CONSTANT_COMMAND_HEAT);
//		}else if(Constant.CONSTANT_COMMAND_DISINFECTION.equals(type)){
//			record.setType(Constant.CONSTANT_COMMAND_DISINFECTION);
//		}else if(Constant.CONSTANT_COMMAND_MODEL.equals(type)){
//			record.setType(Constant.CONSTANT_COMMAND_MODEL);
//		}else if(Constant.CONSTANT_COMMAND_AIRLOOP.equals(type)){
//			record.setType(Constant.CONSTANT_COMMAND_AIRLOOP);
//		}else if(Constant.CONSTANT_COMMAND_AIRFLOW.equals(type)){
//			record.setType(Constant.CONSTANT_COMMAND_AIRFLOW);
//		}else if(Constant.CONSTANT_COMMAND_TIME.equals(type)){
//			record.setType(Constant.CONSTANT_COMMAND_TIME);
//		}else if(Constant.CONSTANT_COMMAND_STATICELECTRICITY.equals(type)){
//			record.setType(Constant.CONSTANT_COMMAND_STATICELECTRICITY);
//		}
//		
//		////////
//		//风量比例有问题
//		DataCacheMap.getInstance().saveData(equipId,type, command);
//		DataCacheMap.getInstance().getDataMap(equipId).put(Constant.COMMAND_CHANGE_FLAG, Constant.COMMAND_CHANGE_FLAG_APP);
//		//////////////
//		
//		int f = commandMapper.insertSelective(record);
//		if(f > 0)
//			flag = true;
//		
//		String data = "保存控制命令:insert into command where type=" + type 
//				+ ",command=" + command + ",equipId=" + equipId;
//		operLogService.insert(Constant.OPER_TYPE_ADD,
//				Constant.OPER_TYPE_COMMON, data, "", equipId);
//		return flag;
//	}
	@Override
	public boolean saveCommand(String type, String command,String note, String equipId) {
		// TODO Auto-generated method stub
		boolean flag = true;
		//风量比例有问题
		DataCacheMap.getInstance().saveData(equipId,type, command);
		DataCacheMap.getInstance().getDataMap(equipId).put(Constant.COMMAND_CHANGE_FLAG, Constant.COMMAND_CHANGE_FLAG_APP);
		return flag;
	}

	@Override
	public int modifyEquip(Equipment obj) {
		// TODO Auto-generated method stub
		int f = equipmentMapper.updateByPrimaryKeySelective(obj);
		
		String data = "更新设备信息:update equipment where name=" + obj.getEquip_name() 
				+ ",repairData=" + obj.getRepair_date() 
				+ ",filterDate=" + obj.getFilter_date() + ",equipId=" + obj.getId();
		operLogService.insert(Constant.OPER_TYPE_MODIFY,
				Constant.OPER_TYPE_COMMON, data, "", obj.getId());
		return f;
	}

	@Override
	@Transactional
	public int updateTimeSetByPrimaryKeySelective(TimeSet record ) {
		// TODO Auto-generated method stub
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("equipId", record.getEquip_id());
//		map.put("status", false);
//		timeSetMapper.updateStatusByEquipId(map);
		
		int f = timeSetMapper.updateByPrimaryKeySelective(record);
		return f;
	}

	@Override
	public int modifyEquipName(String userId, String equipId, String equipName) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("equip_name", equipName);
		map.put("equip_id", equipId);
		map.put("user_id", userId);
		map.put("update_date", new Date());
		int f = userEquipmentMapper.updateEquipName(map);
		return f;
	}

	@Override
	public UserEquipment selectEquipByUserIdAndEquipId(String userId,
			String equipId) {
		// TODO Auto-generated method stub
		UserEquipment obj = null;
		
		UserEquipmentExample example = new UserEquipmentExample();
		com.pku.xinfeng.model.UserEquipmentExample.Criteria cr = example.createCriteria();
		cr.andEquip_idEqualTo(equipId);
		cr.andUser_idEqualTo(userId);
		example.or(cr);
		List<UserEquipment> list = userEquipmentMapper.selectByExample(example);
		if(null != list && list.size() > 0)
			obj = list.get(0);
		
		return obj;
	}

	@Override
	@Transactional
	public TimeSet addTimeSet(TimeSet obj) {
		// TODO Auto-generated method stub
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("equipId", obj.getEquip_id());
//		map.put("status", false);
//		timeSetMapper.updateStatusByEquipId(map);
		
		obj.setStatus(true);
		obj.setUpdate_date(new Date());
		timeSetMapper.insert(obj);
		
		String data = "新增定时:inset into time_set where time=" + obj.getTime()
				+ ",cycle=" + obj.getCycle()
				+ ",action=" + obj.getAction() + ",equipId=" + obj.getEquip_id();
		operLogService.insert(Constant.OPER_TYPE_ADD,
				Constant.OPER_TYPE_COMMON, data, "", obj.getEquip_id());
		return obj;
	}

	@Override
	public List<TimeSet> selectTimeByEquipId(String equipId) {
		// TODO Auto-generated method stub
		List<TimeSet> list = null;
		
		TimeSetExample example = new TimeSetExample();
		com.pku.xinfeng.model.TimeSetExample.Criteria cr = example.createCriteria();
		cr.andEquip_idEqualTo(equipId);
		example.or(cr);
		list = timeSetMapper.selectByExample(example);

		return list;
	}
	
	@Override
	public int deleteTimeSetById(Integer id) {
		// TODO Auto-generated method stub
		int f = timeSetMapper.deleteByPrimaryKey(id);
		return f;
	}
	
	@Override
	public TimeSet selectTimeById(String timeId) {
		// TODO Auto-generated method stub
		TimeSet obj = timeSetMapper.selectByPrimaryKey(Integer.parseInt(timeId));
		return obj;
	}

	@Override
	public void saveCommandToCache(String equipId,List<Command> comList) {
		// TODO Auto-generated method stub
		if(null != comList && comList.size() > 0){
			Map<String, String> dataMap = DataCacheMap.getInstance().getDataMap(equipId);
			if(null == dataMap)
				dataMap = new HashMap<String, String>();
			for(int i=0; i<comList.size();i++){
				Command obj = comList.get(i);
				String type = obj.getType();
				dataMap.put(type, obj.getCommand());
				if(Constant.CONSTANT_COMMAND_AIRFLOW.equals(type)){//风量比例有问题
					dataMap.put(Constant.CONSTANT_COMMAND_AIRFLOWPERCENT,obj.getNote());//修改标志位
				}
			}
			dataMap.put(Constant.COMMAND_CHANGE_FLAG,Constant.COMMAND_CHANGE_FLAG_APP);//修改标志位
			DataCacheMap.getInstance().setDataMap(equipId, dataMap);
		}
	}

}

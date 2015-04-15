package com.pku.xinfeng.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import com.pku.xinfeng.model.Equipment;
import com.pku.xinfeng.model.TimeSet;
import com.pku.xinfeng.model.UserEquipment;
import com.pku.xinfeng.service.EquipmentService;
import com.pku.xinfeng.service.MainViewService;
import com.pku.xinfeng.utils.CommonUtil;
import com.pku.xinfeng.utils.Constant;
import com.pku.xinfeng.utils.DateUtil;
import com.pku.xinfeng.utils.StringUtil;

@Controller
public class EquipmentController {
	private static final Logger logger = LoggerFactory
			.getLogger(EquipmentController.class);

	@Autowired
	private EquipmentService equipmentService;

	@Autowired
	private MainViewService mainViewService;
	
	@RequestMapping(value = "addEquipment", method = RequestMethod.POST)
	@ResponseBody
	public void addEquipment(HttpServletRequest request,
			HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String equipId = request.getParameter("equipId");
		String equipName = request.getParameter("equipName");
		String maxAirflow = request.getParameter("maxAirflow");
		String position = request.getParameter("position");
		String version = request.getParameter("version");
		String code = request.getParameter("code");

		System.out.println("userId : " + userId);
		System.out.println("equipId : " + equipId);
		System.out.println("equipName : " + equipName);
		System.out.println("maxAirflow : " + maxAirflow);
		System.out.println("code : " + code);
		System.out.println("version : " + version);

		if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(equipId)
				|| StringUtil.isEmpty(equipName)
				|| StringUtil.isEmpty(maxAirflow) 
				|| StringUtil.isEmpty(code)
				|| StringUtil.isEmpty(version)) {
			CommonUtil.returnFalse(response);
			return;
		}
		try {
			JSONObject jsonObj = new JSONObject();
			UserEquipment ueObj = equipmentService
					.selectEquipByUserIdAndEquipId(userId, equipId);
			if (null == ueObj || StringUtil.isEmpty(ueObj.getUser_id())) {
				Equipment obj = new Equipment();
				obj.setId(equipId);
				obj.setMax_airflow(Integer.parseInt(maxAirflow));
				obj.setInstall_date(new Date());
				obj.setCode(code);
				obj.setVersion(version);

				UserEquipment record = new UserEquipment();
				record.setEquip_id(obj.getId());
				record.setEquip_name(equipName);
				record.setUser_id(userId);
				record.setUpdate_date(new Date());
				record.setAdd_position(position);
				//如果此设备已经在设备表中，则设备表中不插入数据，反之插入；
				//用户设备表中添加用户与设备的关系
				boolean flag = equipmentService.addEquipment(record, obj);

				if (flag) {
					jsonObj.put("state", true);
					jsonObj.put("description", "新增成功！");
				} else {
					jsonObj.put("state", false);
					jsonObj.put("description", "新增失败！");
				}
			} else {
				jsonObj.put("state", false);
				jsonObj.put("description", "本用户已经添加此设备！");
			}

			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(jsonObj.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	@RequestMapping(value = "onOrOff", method = RequestMethod.POST)
	@ResponseBody
	public void onOrOff(HttpServletRequest request, HttpServletResponse response) {
		String equipId = request.getParameter("equipId");
		String value = request.getParameter("value");

		System.out.println("equipId : " + equipId);
		System.out.println("value : " + value);

		if (StringUtil.isEmpty(equipId) || StringUtil.isEmpty(value)
				|| !StringUtil.isBoolean(value)) {
			CommonUtil.returnFalse(response);
			return;
		}
		try {
			boolean flag = equipmentService.saveCommand(
					Constant.CONSTANT_COMMAND_ONOROFF, StringUtil.toBoolean(value)+"", "", equipId);

			JSONObject jsonObj = new JSONObject();
			if (flag) {
				jsonObj.put("state", true);
				jsonObj.put("description", "保存成功！");
			} else {
				jsonObj.put("state", false);
				jsonObj.put("description", "保存失败！");
			}
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(jsonObj.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	@RequestMapping(value = "setAirflow", method = RequestMethod.POST)
	@ResponseBody
	public void setAirflow(HttpServletRequest request,
			HttpServletResponse response) {
		String equipId = request.getParameter("equipId");
		String value = request.getParameter("value");
		String percentValue = request.getParameter("percentValue");

		System.out.println("equipId : " + equipId);
		System.out.println("value : " + value);
		System.out.println("percentValue : " + percentValue);

		if (StringUtil.isEmpty(equipId) || StringUtil.isEmpty(value)
				|| StringUtil.isEmpty(percentValue)) {
			CommonUtil.returnFalse(response);
			return;
		}
		try {
			boolean flag = equipmentService.saveCommand(
					Constant.CONSTANT_COMMAND_AIRFLOW, value, percentValue,
					equipId);

			JSONObject jsonObj = new JSONObject();
			if (flag) {
				jsonObj.put("state", true);
				jsonObj.put("description", "保存成功！");
			} else {
				jsonObj.put("state", false);
				jsonObj.put("description", "保存失败！");
			}
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(jsonObj.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	@RequestMapping(value = "setHeat", method = RequestMethod.POST)
	@ResponseBody
	public void setHeat(HttpServletRequest request, HttpServletResponse response) {
		String equipId = request.getParameter("equipId");
		String value = request.getParameter("value");

		System.out.println("equipId : " + equipId);
		System.out.println("value : " + value);

		if (StringUtil.isEmpty(equipId) || StringUtil.isEmpty(value)
				|| !StringUtil.isBoolean(value)) {
			CommonUtil.returnFalse(response);
			return;
		}
		try {
			boolean flag = equipmentService.saveCommand(
					Constant.CONSTANT_COMMAND_HEAT, StringUtil.toBoolean(value)+"", "", equipId);

			JSONObject jsonObj = new JSONObject();
			if (flag) {
				jsonObj.put("state", true);
				jsonObj.put("description", "保存成功！");
			} else {
				jsonObj.put("state", false);
				jsonObj.put("description", "保存失败！");
			}
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(jsonObj.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	@RequestMapping(value = "setSleep", method = RequestMethod.POST)
	@ResponseBody
	public void setSleep(HttpServletRequest request,
			HttpServletResponse response) {
		String equipId = request.getParameter("equipId");
		String value = request.getParameter("value");

		System.out.println("equipId : " + equipId);
		System.out.println("value : " + value);

		if (StringUtil.isEmpty(equipId) || StringUtil.isEmpty(value)
				|| !StringUtil.isBoolean(value)) {
			CommonUtil.returnFalse(response);
			return;
		}
		try {
			boolean flag = equipmentService.saveCommand(
					Constant.CONSTANT_COMMAND_SLEEP, StringUtil.toBoolean(value)+"", "", equipId);

			JSONObject jsonObj = new JSONObject();
			if (flag) {
				jsonObj.put("state", true);
				jsonObj.put("description", "保存成功！");
			} else {
				jsonObj.put("state", false);
				jsonObj.put("description", "保存失败！");
			}
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(jsonObj.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}
	
	@RequestMapping(value = "setStaticElectricity", method = RequestMethod.POST)
	@ResponseBody
	public void setStaticElectricity(HttpServletRequest request,
			HttpServletResponse response) {
		String equipId = request.getParameter("equipId");
		String value = request.getParameter("value");

		System.out.println("equipId : " + equipId);
		System.out.println("value : " + value);

		if (StringUtil.isEmpty(equipId) || StringUtil.isEmpty(value)
				|| !StringUtil.isBoolean(value)) {
			CommonUtil.returnFalse(response);
			return;
		}
		try {
			boolean flag = equipmentService.saveCommand(
					Constant.CONSTANT_COMMAND_STATICELECTRICITY, StringUtil.toBoolean(value)+"", "", equipId);

			JSONObject jsonObj = new JSONObject();
			if (flag) {
				jsonObj.put("state", true);
				jsonObj.put("description", "保存成功！");
			} else {
				jsonObj.put("state", false);
				jsonObj.put("description", "保存失败！");
			}
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(jsonObj.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	@RequestMapping(value = "setAirLoop", method = RequestMethod.POST)
	@ResponseBody
	public void setAirLoop(HttpServletRequest request,
			HttpServletResponse response) {
		String equipId = request.getParameter("equipId");
		String value = request.getParameter("value");

		System.out.println("equipId : " + equipId);
		System.out.println("value : " + value);

		if (StringUtil.isEmpty(equipId) || StringUtil.isEmpty(value)
				|| !StringUtil.isBoolean(value)) {
			CommonUtil.returnFalse(response);
			return;
		}
		try {
			boolean flag = equipmentService.saveCommand(
					Constant.CONSTANT_COMMAND_AIRLOOP, StringUtil.toBoolean(value)+"", "", equipId);

			JSONObject jsonObj = new JSONObject();
			if (flag) {
				jsonObj.put("state", true);
				jsonObj.put("description", "保存成功！");
			} else {
				jsonObj.put("state", false);
				jsonObj.put("description", "保存失败！");
			}
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(jsonObj.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	@RequestMapping(value = "setDisinfection", method = RequestMethod.POST)
	@ResponseBody
	public void setDisinfection(HttpServletRequest request,
			HttpServletResponse response) {
		String equipId = request.getParameter("equipId");
		String value = request.getParameter("value");

		System.out.println("equipId : " + equipId);
		System.out.println("value : " + value);

		if (StringUtil.isEmpty(equipId) || StringUtil.isEmpty(value)
				|| !StringUtil.isBoolean(value)) {
			CommonUtil.returnFalse(response);
			return;
		}
		try {
			boolean flag = equipmentService.saveCommand(
					Constant.CONSTANT_COMMAND_DISINFECTION, StringUtil.toBoolean(value)+"", "", equipId);

			JSONObject jsonObj = new JSONObject();
			if (flag) {
				jsonObj.put("state", true);
				jsonObj.put("description", "保存成功！");
			} else {
				jsonObj.put("state", false);
				jsonObj.put("description", "保存失败！");
			}
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(jsonObj.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	@RequestMapping(value = "addTimeSet", method = RequestMethod.POST)
	@ResponseBody
	public void addTimeSet(HttpServletRequest request,
			HttpServletResponse response) {
		String equipId = request.getParameter("equipId");
		String action = request.getParameter("action");
		String cycle = request.getParameter("cycle");
		String time = request.getParameter("time");
		Date timeParse = DateUtil.parseTime(time);

		System.out.println("equipId : " + equipId);
		System.out.println("action : " + action);
		System.out.println("cycle : " + cycle);
		System.out.println("time : " + time);

		if (StringUtil.isEmpty(equipId) || StringUtil.isEmpty(action)
				|| StringUtil.isEmpty(cycle) || StringUtil.isEmpty(time)
				|| null == timeParse
				|| !StringUtil.isBoolean(action)) {
			CommonUtil.returnFalse(response);
			return;
		}
		try {
			TimeSet obj = new TimeSet();
			obj.setAction(StringUtil.toBoolean(action));
			obj.setCycle(Integer.parseInt(cycle));
			obj.setTime(timeParse);
			obj.setEquip_id(equipId);
			equipmentService.addTimeSet(obj);

			JSONObject jsonObj = new JSONObject();
			if (null != obj.getId()) {
				jsonObj.put("timeId", obj.getId());
				jsonObj.put("state", true);
				jsonObj.put("description", "保存成功！");
			} else {
				jsonObj.put("state", false);
				jsonObj.put("description", "保存失败！");
			}
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(jsonObj.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	@RequestMapping(value = "autoOrManual", method = RequestMethod.POST)
	@ResponseBody
	public void autoOrManual(HttpServletRequest request,
			HttpServletResponse response) {
		String equipId = request.getParameter("equipId");
		String value = request.getParameter("value");

		System.out.println("equipId : " + equipId);
		System.out.println("value : " + value);

		if (StringUtil.isEmpty(equipId) || StringUtil.isEmpty(value)
				|| !StringUtil.isBoolean(value)) {
			CommonUtil.returnFalse(response);
			return;
		}
		try {
			boolean flag = equipmentService.saveCommand(
					Constant.CONSTANT_COMMAND_MODEL, value, "", equipId);

			JSONObject jsonObj = new JSONObject();
			if (flag) {
				jsonObj.put("state", true);
				jsonObj.put("description", "保存成功！");
				JSONObject json = new JSONObject();
				List<Command> list = mainViewService.loadEquipCommand(equipId);
				if (null != list && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						Command obj = list.get(i);
						String type = obj.getType();
						if (Constant.CONSTANT_COMMAND_AIRFLOW.equals(type)) {
							json.put(type, StringUtil.makeNullToEmptyString(obj
									.getCommand()));
						} else if (Constant.CONSTANT_COMMAND_AIRLOOP
								.equals(type)) {
							json.put(type, StringUtil.makeNullToEmptyString(obj
									.getCommand()));
						} else if (Constant.CONSTANT_COMMAND_HEAT.equals(type)) {
							json.put(type, StringUtil.makeNullToEmptyString(obj
									.getCommand()));
						} else if (Constant.CONSTANT_COMMAND_SLEEP.equals(type)) {
							json.put(type, StringUtil.makeNullToEmptyString(obj
									.getCommand()));
						}
					}
				}
				jsonObj.put("manualItems", json);
			} else {
				jsonObj.put("state", false);
				jsonObj.put("description", "保存失败！");
			}
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(jsonObj.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	@RequestMapping(value = "modifyEquipName", method = RequestMethod.POST)
	@ResponseBody
	public void modifyEquipName(HttpServletRequest request,
			HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String equipId = request.getParameter("equipId");
		String newValue = request.getParameter("newValue");

		System.out.println("userId : " + userId);
		System.out.println("equipId : " + equipId);
		System.out.println("newValue : " + newValue);

		if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(equipId)
				|| StringUtil.isEmpty(newValue)) {
			CommonUtil.returnFalse(response);
			return;
		}
		try {
			int flag = equipmentService.modifyEquipName(userId, equipId,
					newValue);

			JSONObject jsonObj = new JSONObject();
			if (flag > 0) {
				jsonObj.put("state", true);
				jsonObj.put("description", "保存成功！");
			} else {
				jsonObj.put("state", false);
				jsonObj.put("description", "保存失败！");
			}
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(jsonObj.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	@RequestMapping(value = "modifyEquipRepairDate", method = RequestMethod.POST)
	@ResponseBody
	public void modifyEquipRepairDate(HttpServletRequest request,
			HttpServletResponse response) {
		String equipId = request.getParameter("equipId");
		String newValue = request.getParameter("newValue");

		System.out.println("equipId : " + equipId);
		System.out.println("newValue : " + newValue);

		if (StringUtil.isEmpty(equipId) || StringUtil.isEmpty(newValue)) {
			CommonUtil.returnFalse(response);
			return;
		}
		try {
			Equipment obj = new Equipment();
			obj.setId(equipId);
			Date repairDate = DateUtil.parseDate(newValue);
			JSONObject jsonObj = new JSONObject();
			if (null != repairDate) {
				obj.setRepair_date(repairDate);
				int flag = equipmentService.modifyEquip(obj);

				if (flag > 0) {
					jsonObj.put("state", true);
					jsonObj.put("description", "保存成功！");
				} else {
					jsonObj.put("state", false);
					jsonObj.put("description", "保存失败！");
				}
			} else {
				jsonObj.put("state", false);
				jsonObj.put("description", "日期格式错误！");
			}
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(jsonObj.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	@RequestMapping(value = "modifyEquipFilterDate", method = RequestMethod.POST)
	@ResponseBody
	public void modifyEquipFilterDate(HttpServletRequest request,
			HttpServletResponse response) {
		String equipId = request.getParameter("equipId");
		String newValue = request.getParameter("newValue");

		System.out.println("equipId : " + equipId);
		System.out.println("newValue : " + newValue);

		if (StringUtil.isEmpty(equipId) || StringUtil.isEmpty(newValue)) {
			CommonUtil.returnFalse(response);
			return;
		}
		try {
			Equipment obj = new Equipment();
			obj.setId(equipId);
			Date filterDate = DateUtil.parseDate(newValue);
			JSONObject jsonObj = new JSONObject();
			if (null != filterDate) {
				obj.setFilter_date(filterDate);
				int flag = equipmentService.modifyEquip(obj);

				if (flag > 0) {
					jsonObj.put("state", true);
					jsonObj.put("description", "保存成功！");
				} else {
					jsonObj.put("state", false);
					jsonObj.put("description", "保存失败！");
				}
			} else {
				jsonObj.put("state", false);
				jsonObj.put("description", "日期格式错误！");
			}
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(jsonObj.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	@RequestMapping(value = "loadTimeSet", method = RequestMethod.POST)
	@ResponseBody
	public void loadTimeSet(HttpServletRequest request,
			HttpServletResponse response) {
		String equipId = request.getParameter("equipId");

		System.out.println("equipId : " + equipId);

		if (StringUtil.isEmpty(equipId)) {
			CommonUtil.returnFalse(response);
			return;
		}
		try {
			List<TimeSet> list = equipmentService.selectTimeByEquipId(equipId);

			JSONObject jsonObj = new JSONObject();
			if (null != list && list.size() > 0) {
				JSONArray timeAry = new JSONArray();

				for (int i = 0; i < list.size(); i++) {
					JSONObject timeJson = new JSONObject();
					TimeSet obj = list.get(i);
					timeJson.put("timeId", obj.getId());
					timeJson.put("action", obj.getAction());
					timeJson.put("cycle", obj.getCycle());
					timeJson.put("time", DateUtil.formatTime(obj.getTime()));
					timeJson.put("status", obj.getStatus());
					timeAry.add(timeJson);
				}
				jsonObj.put("timeItems", timeAry);
				jsonObj.put("state", true);
				jsonObj.put("description", "查询成功！");
			}else{
				jsonObj.put("timeItems", "{}");
				jsonObj.put("state", true);
				jsonObj.put("description", "查询成功！");
			}
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(jsonObj.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	@RequestMapping(value = "modifyTimeSet", method = RequestMethod.POST)
	@ResponseBody
	public void modifyTimeSet(HttpServletRequest request,
			HttpServletResponse response) {
		String timeId = request.getParameter("timeId");
		String time = request.getParameter("time");
		String action = request.getParameter("action");
		String cycle = request.getParameter("cycle");
		String status = request.getParameter("status");

		System.out.println("timeId : " + timeId);
		System.out.println("time : " + time);
		System.out.println("action : " + action);
		System.out.println("cycle : " + cycle);
		System.out.println("status : " + status);

		if (StringUtil.isEmpty(timeId)) {
			CommonUtil.returnFalse(response);
			return;
		}
		try {
			TimeSet record = equipmentService.selectTimeById(timeId);
			record.setId(Integer.parseInt(timeId));
			record.setStatus(StringUtil.toBoolean(status));
			record.setAction(StringUtil.toBoolean(action));
			if (!StringUtil.isEmpty(cycle))
				record.setCycle(Integer.parseInt(cycle));
			Date timeParse = DateUtil.parseTime(time);
			if (null != timeParse)
				record.setTime(timeParse);

			JSONObject jsonObj = new JSONObject();
			if (null == record.getAction() && null == record.getCycle()
					&& null == record.getStatus() && null == record.getTime()) {
				jsonObj.put("state", false);
				jsonObj.put("description", "无满足条件的更新数据！");
			} else {
				int flag = equipmentService
						.updateTimeSetByPrimaryKeySelective(record);

				if (flag > 0) {
					jsonObj.put("state", true);
					jsonObj.put("description", "更新成功！");
				} else {
					jsonObj.put("state", false);
					jsonObj.put("description", "更新失败！");
				}
			}
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(jsonObj.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	@RequestMapping(value = "deleteTimeSet", method = RequestMethod.POST)
	@ResponseBody
	public void deleteTimeSet(HttpServletRequest request,
			HttpServletResponse response) {
		String timeId = request.getParameter("timeId");

		System.out.println("timeId : " + timeId);

		if (StringUtil.isEmpty(timeId)) {
			CommonUtil.returnFalse(response);
			return;
		}
		try {
			int flag = equipmentService.deleteTimeSetById(Integer
					.parseInt(timeId));

			JSONObject jsonObj = new JSONObject();
			if (flag > 0) {
				jsonObj.put("state", true);
				jsonObj.put("description", "删除成功！");
			} else {
				jsonObj.put("state", false);
				jsonObj.put("description", "删除失败！");
			}
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(jsonObj.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}
	
	@RequestMapping(value = "syncStatus", method = RequestMethod.POST)
	@ResponseBody
	public void syncStatus(HttpServletRequest request, HttpServletResponse response) {
		String equipId = request.getParameter("equipId");
		String actions = request.getParameter("actions");

		System.out.println("equipId : " + equipId);
		System.out.println("actions : " + actions);

		if (StringUtil.isEmpty(equipId)) {
			CommonUtil.returnFalse(response);
			return;
		}
		try {
			JSONObject jsonObj = new JSONObject();
			if(!StringUtil.isEmpty(actions)){
				JSONArray jsonAry = JSONArray.fromObject(actions);
				if(null == jsonAry){
					jsonObj.put("state", false);
					jsonObj.put("description", "输入数据格式有问题!");
				}else{
					List<Command> comList = new ArrayList<Command>();
					for(int i=0; i<jsonAry.size();i++){
						JSONObject actionJson = jsonAry.getJSONObject(i);
						Iterator<?> cit = actionJson.keys();
						Command obj = new Command();
						while (cit.hasNext()) {
							String key = (String) cit.next();
							String value = actionJson.getString(key);
							if("type".equals(key))
								obj.setType(value);
							if("value".equals(key))
								obj.setCommand(value);
							if("value2".equals(key))
								obj.setNote(value);
						}
						comList.add(obj);
					}
					equipmentService.saveCommandToCache(equipId, comList);
					jsonObj.put("state", true);
					jsonObj.put("description", "保存成功!");
				}
			}else{
				jsonObj.put("state", true);
				jsonObj.put("description", "查询成功!");
			}
			//从缓存中取当前命令状态
			String statusStr = mainViewService.getNowAllStatus(equipId);
    		JSONObject settingJson = JSONObject.fromObject(statusStr);
    		jsonObj.put("equipSettings", settingJson);
    		
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(jsonObj.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}
	
}

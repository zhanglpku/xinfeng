package com.pku.xinfeng.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pku.xinfeng.dao.TimeSetMapper;
import com.pku.xinfeng.dao.UserMapper;
import com.pku.xinfeng.model.TimeSet;
import com.pku.xinfeng.model.TimeSetExample;
import com.pku.xinfeng.model.User;
import com.pku.xinfeng.model.UserExample;
import com.pku.xinfeng.model.UserExample.Criteria;
import com.pku.xinfeng.service.OperLogService;
import com.pku.xinfeng.service.UserService;
import com.pku.xinfeng.utils.Constant;
import com.pku.xinfeng.utils.DateUtil;
import com.pku.xinfeng.utils.StringUtil;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private TimeSetMapper timeSetMapper;
	
	
	@Autowired
	private OperLogService operLogService;

	@Override
	public User selectByPhoneAndPsw(String phone,String password) {
		// TODO Auto-generated method stub
		UserExample userExample = new UserExample();
		Criteria cr = userExample.createCriteria();
		cr.andPhoneEqualTo(phone);
		cr.andPasswordEqualTo(password);
		userExample.or(cr);
		List<User> list = userMapper.selectByExample(userExample);

		User user = new User();
		if (null != list && list.size() > 0)
			user = list.get(0);

		return user;
	}
	
	@Override
	public User selectByUnameAndPsw(String username, String password,String admin) {
		// TODO Auto-generated method stub
		UserExample userExample = new UserExample();
		Criteria cr = userExample.createCriteria();
		cr.andUsernameEqualTo(username);
		cr.andPasswordEqualTo(password);
		if(!StringUtil.isEmpty(admin))
			cr.andIsAdminEqualTo(admin);
		userExample.or(cr);
		List<User> list = userMapper.selectByExample(userExample);

		User user = new User();
		if (null != list && list.size() > 0)
			user = list.get(0);

		return user;
	}

	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		int f = userMapper.addUser(user);
		String data = "insert into user,id=" + user.getId() 
				+ ",username=" + user.getUsername() + ",phone=" + user.getPhone();
		operLogService.insert(Constant.OPER_TYPE_ADD, Constant.OPER_TYPE_COMMON, data, user.getId(), "");
		return f;
	}

	@Override
	public int updatePswByPhone(String phone,String password) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("password", password);
		params.put("phone", phone);
		int f = userMapper.updatePswByPhone(params);
		
		String data = "找回密码：update user,phone=" + phone 
				+ ",password=" + password;
		operLogService.insert(Constant.OPER_TYPE_MODIFY, Constant.OPER_TYPE_COMMON, data, "", "");
		
		return f;
	}

	@Override
	public User selectByPhone(String phone) {
		// TODO Auto-generated method stub
		UserExample userExample = new UserExample();
		Criteria cr = userExample.createCriteria();
		cr.andPhoneEqualTo(phone);
		userExample.or(cr);
		List<User> list = userMapper.selectByExample(userExample);

		User user = new User();
		if (null != list && list.size() > 0)
			user = list.get(0);

		return user;
	}

	@Override
	public int modifyUserById(User user) {
		// TODO Auto-generated method stub
		user.setUpdate_date(new Date());
		int f = userMapper.updateByPrimaryKeySelective(user);

		String data = "更新用户信息：update user,username=" + user.getUsername() + "phone=" + user.getPhone() 
				+ ",password=" + user.getPassword() + ",QQ=" + user.getQq()
				+ ",Address=" + user.getAddress() + ",Mail=" + user.getMail()
				+ ",Wechat=" + user.getWechat();
		operLogService.insert(Constant.OPER_TYPE_MODIFY, Constant.OPER_TYPE_COMMON, data, user.getId(), "");
		
		return f;
	}

	@Override
	public User selectByUidAndPsw(String userId,String password) {
		// TODO Auto-generated method stub
		UserExample userExample = new UserExample();
		Criteria cr = userExample.createCriteria();
		cr.andIdEqualTo(userId);
		cr.andPasswordEqualTo(password);
		userExample.or(cr);
		List<User> list = userMapper.selectByExample(userExample);

		User user = null;
		if (null != list && list.size() > 0)
			user = list.get(0);

		return user;
	}
	
	public String getTimeStatus(String equipId){
		String returnStr = "";
		//查询出此设备的所有定时信息
		TimeSetExample example = new TimeSetExample();
		com.pku.xinfeng.model.TimeSetExample.Criteria cr = example
				.createCriteria();
		cr.andEquip_idEqualTo(equipId);
		cr.andStatusEqualTo(true);
		example.or(cr);
		List<TimeSet> timeList = timeSetMapper.selectByExample(example);
		if (null != timeList && timeList.size() > 0) {
			for(int i=0; i< timeList.size(); i++){
				TimeSet timeSet = timeList.get(i);

				// 0 仅一次（默认） 1 每天 2 周一至周五 3周六日 4 每周
				int cycle = timeSet.getCycle();
				Date upDate = timeSet.getUpdate_date();// HH:mm
				Date time = timeSet.getTime();

				Date nowDate = new Date();
				int  cycleType = cycle & 0xFF;
				switch (cycleType) {
				case 1://仅一次：当天且当时
					if (DateUtil.formatDate(nowDate).equals(
							DateUtil.formatDate(upDate))
							&& DateUtil.formatTime(nowDate).equals(
									DateUtil.formatTime(time)))
						returnStr = timeSet.getAction() + "";// true开/false关
					break;
				case 2://每天：当时
					if (DateUtil.formatTime(nowDate).equals(
							DateUtil.formatTime(time)))
						returnStr = timeSet.getAction() + "";// true开/false关
					break;
				case 4://工作日：周一到周五，当时
					if (DateUtil.getWeekOfDate(nowDate) >= 1
							&& DateUtil.getWeekOfDate(nowDate) <= 5
							&& DateUtil.formatTime(nowDate).equals(
									DateUtil.formatTime(time)))
						returnStr = timeSet.getAction() + "";// true开/false关
					break;
				case 8://周末：周六日，当时
					if (DateUtil.getWeekOfDate(nowDate) >= 6
							&& DateUtil.getWeekOfDate(nowDate) <= 7
							&& DateUtil.formatTime(nowDate).equals(
									DateUtil.formatTime(time)))
						returnStr = timeSet.getAction() + "";// true开/false关
					break;
				case 16://自定义（每周*）：周几？当时
					int week1 = (cycle >> 8) & 0x01;
					int week2 = (cycle >> 8) & 0x02;
					int week3 = (cycle >> 8) & 0x04;
					int week4 = (cycle >> 8) & 0x08;
					int week5 = (cycle >> 8) & 0x10;
					int week6 = (cycle >> 8) & 0x20;
					int week7 = (cycle >> 8) & 0x40;
					
					int nowWeek = DateUtil.getWeekOfDate(nowDate);
					boolean flag = false;
					if(1 == nowWeek && 0 != week1){
						flag = true;
					}else if(2 == nowWeek && 0 != week2){
						flag = true;
					}else if(3 == nowWeek && 0 != week3){
						flag = true;
					}else if(4 == nowWeek && 0 != week4){
						flag = true;
					}else if(5 == nowWeek && 0 != week5){
						flag = true;
					}else if(6 == nowWeek && 0 != week6){
						flag = true;
					}else if(7 == nowWeek && 0 != week7){
						flag = true;
					}
					if(flag && DateUtil.formatTime(nowDate).equals(
							DateUtil.formatTime(time))){
						returnStr = timeSet.getAction() + "";// true开/false关
					}
					break;
				default:
					break;
				}
			}
		}
		return returnStr;
	}
}

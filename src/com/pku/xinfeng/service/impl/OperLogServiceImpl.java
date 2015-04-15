package com.pku.xinfeng.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pku.xinfeng.dao.OperLogMapper;
import com.pku.xinfeng.model.OperLog;
import com.pku.xinfeng.model.OperLogExample;
import com.pku.xinfeng.service.OperLogService;
import com.pku.xinfeng.utils.StringUtil;

@Service("operLogService")
public class OperLogServiceImpl implements OperLogService {
	@Autowired
	private OperLogMapper operLogMapper;

	@Override
	public int insert(String type, String level, String data, String userId,
			String equipId) {
		// TODO Auto-generated method stub
		OperLog record = new OperLog();
		record.setOper_data(data);
		record.setOper_equi_id(equipId);
		record.setOper_user_id(userId);
		record.setOper_level(level);
		record.setOper_type(type);
		record.setOper_time(new Date());
		int f = operLogMapper.insert(record);
		return f;
	}

	@Override
	public List<OperLog> selectByOperLog(OperLog obj) {
		// TODO Auto-generated method stub
		OperLogExample example = new OperLogExample();
		com.pku.xinfeng.model.OperLogExample.Criteria cr = example
				.createCriteria();
		if (!StringUtil.isEmpty(obj.getOper_data()))
			cr.andOper_dataLike(obj.getOper_data());
		
		if (!StringUtil.isEmpty(obj.getOper_equi_id()))
			cr.andOper_equi_idEqualTo(obj.getOper_equi_id());
		
		if (!StringUtil.isEmpty(obj.getOper_level()))
			cr.andOper_levelEqualTo(obj.getOper_level());
		
		if (!StringUtil.isEmpty(obj.getOper_user_id()))
			cr.andOper_user_idEqualTo(obj.getOper_user_id());
		
		if (!StringUtil.isEmpty(obj.getOper_type()))
			cr.andOper_typeEqualTo(obj.getOper_type());

		if(null != obj.getOperDateBegin())
			cr.andOper_timeGreaterThanOrEqualTo(obj.getOperDateBegin());
		if(null != obj.getOperDateEnd())
			cr.andOper_timeLessThanOrEqualTo(obj.getOperDateEnd());

		example.or(cr);

		List<OperLog> list = operLogMapper.selectByExample(example);

		return list;
	}
}

/**
 * 
 */
package com.pku.xinfeng.service;

import java.util.List;

import com.pku.xinfeng.model.Command;
import com.pku.xinfeng.model.Equipment;
import com.pku.xinfeng.model.TimeSet;
import com.pku.xinfeng.model.UserEquipment;

/**
 * @author zhangl
 *
 */
public interface EquipmentService {
	public boolean addEquipment(UserEquipment record,Equipment obj);
	//设备详细页—控制命令
	public boolean saveCommand(String type,String command,String note,String equipId);
	
	public int modifyEquip(Equipment obj);
	public int modifyEquipName(String userId,String equipId,String equipName);
	
	public TimeSet addTimeSet(TimeSet obj);
	
	public List<TimeSet> selectTimeByEquipId(String equipId);
	
	public int updateTimeSetByPrimaryKeySelective(TimeSet record );
	
	public UserEquipment selectEquipByUserIdAndEquipId(String userId,String equipId);
	
	public int deleteTimeSetById(Integer id);
	
	public TimeSet selectTimeById(String timeId); 
	
	public void saveCommandToCache(String equipId,List<Command> comList);
}

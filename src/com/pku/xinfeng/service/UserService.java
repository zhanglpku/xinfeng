/**
 * 
 */
package com.pku.xinfeng.service;

import java.util.List;

import com.pku.xinfeng.model.User;

/**
 * @author zhangl
 *
 */
public interface UserService {
	public User selectByPhoneAndPsw(String phone,String password);
	public User selectByUnameAndPsw(String username,String password,String admin);
	public int addUser(User user);
	public int updatePswByPhone(String phone,String password);
	public User selectByPhone(String phone);
	public int modifyUserById(User user);
	public User selectByUidAndPsw(String userId,String password);
	
	public List<com.pku.xinfeng.pojo.UserDetail> getUserEquipList();
	
	String getTimeStatus(String equipId);
}

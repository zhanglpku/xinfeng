package com.pku.xinfeng.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pku.xinfeng.model.User;
import com.pku.xinfeng.model.UserExample;

public interface UserMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated
	 */
	int countByExample(UserExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated
	 */
	int deleteByExample(UserExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated
	 */
	int deleteByPrimaryKey(String id);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated
	 */
	int insert(User record);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated
	 */
	int insertSelective(User record);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated
	 */
	List<User> selectByExample(UserExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated
	 */
	User selectByPrimaryKey(String id);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated
	 */
	int updateByExampleSelective(@Param("record") User record,
			@Param("example") UserExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated
	 */
	int updateByExample(@Param("record") User record,
			@Param("example") UserExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated
	 */
	int updateByPrimaryKeySelective(User record);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated
	 */
	int updateByPrimaryKey(User record);
	int addUser(User user);//注册
	int updatePswByPhone(Map<String, Object> map);
}
package com.pku.xinfeng.dao;

import com.pku.xinfeng.model.Command;
import com.pku.xinfeng.model.CommandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommandMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table command
	 * @mbggenerated
	 */
	int countByExample(CommandExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table command
	 * @mbggenerated
	 */
	int deleteByExample(CommandExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table command
	 * @mbggenerated
	 */
	int deleteByPrimaryKey(Integer id);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table command
	 * @mbggenerated
	 */
	int insert(Command record);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table command
	 * @mbggenerated
	 */
	int insertSelective(Command record);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table command
	 * @mbggenerated
	 */
	List<Command> selectByExample(CommandExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table command
	 * @mbggenerated
	 */
	Command selectByPrimaryKey(Integer id);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table command
	 * @mbggenerated
	 */
	int updateByExampleSelective(@Param("record") Command record,
			@Param("example") CommandExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table command
	 * @mbggenerated
	 */
	int updateByExample(@Param("record") Command record,
			@Param("example") CommandExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table command
	 * @mbggenerated
	 */
	int updateByPrimaryKeySelective(Command record);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table command
	 * @mbggenerated
	 */
	int updateByPrimaryKey(Command record);
	List<Command> getNewCommands(String equipId);
	List<Command> getNowCommands(String equipId);
	
	int updateCommandStatusByIds(Integer[] ids);
	
	int insertBatch(List<Command> list);
}
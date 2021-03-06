package com.pku.xinfeng.dao;

import com.pku.xinfeng.model.OperLog;
import com.pku.xinfeng.model.OperLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OperLogMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table oper_log
	 * @mbggenerated
	 */
	int countByExample(OperLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table oper_log
	 * @mbggenerated
	 */
	int deleteByExample(OperLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table oper_log
	 * @mbggenerated
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table oper_log
	 * @mbggenerated
	 */
	int insert(OperLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table oper_log
	 * @mbggenerated
	 */
	int insertSelective(OperLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table oper_log
	 * @mbggenerated
	 */
	List<OperLog> selectByExample(OperLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table oper_log
	 * @mbggenerated
	 */
	OperLog selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table oper_log
	 * @mbggenerated
	 */
	int updateByExampleSelective(@Param("record") OperLog record,
			@Param("example") OperLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table oper_log
	 * @mbggenerated
	 */
	int updateByExample(@Param("record") OperLog record,
			@Param("example") OperLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table oper_log
	 * @mbggenerated
	 */
	int updateByPrimaryKeySelective(OperLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table oper_log
	 * @mbggenerated
	 */
	int updateByPrimaryKey(OperLog record);
}
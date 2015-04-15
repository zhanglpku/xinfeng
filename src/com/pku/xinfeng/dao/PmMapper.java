package com.pku.xinfeng.dao;

import com.pku.xinfeng.model.Pm;
import com.pku.xinfeng.model.PmExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PmMapper {
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pm
	 * @mbggenerated
	 */
	int countByExample(PmExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pm
	 * @mbggenerated
	 */
	int deleteByExample(PmExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pm
	 * @mbggenerated
	 */
	int deleteByPrimaryKey(Integer id);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pm
	 * @mbggenerated
	 */
	int insert(Pm record);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pm
	 * @mbggenerated
	 */
	int insertSelective(Pm record);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pm
	 * @mbggenerated
	 */
	List<Pm> selectByExample(PmExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pm
	 * @mbggenerated
	 */
	Pm selectByPrimaryKey(Integer id);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pm
	 * @mbggenerated
	 */
	int updateByExampleSelective(@Param("record") Pm record,
			@Param("example") PmExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pm
	 * @mbggenerated
	 */
	int updateByExample(@Param("record") Pm record,
			@Param("example") PmExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pm
	 * @mbggenerated
	 */
	int updateByPrimaryKeySelective(Pm record);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pm
	 * @mbggenerated
	 */
	int updateByPrimaryKey(Pm record);
	int deleteAll();
    int insertBatch(List<Pm> list);
}
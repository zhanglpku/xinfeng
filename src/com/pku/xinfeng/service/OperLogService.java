/**
 * 
 */
package com.pku.xinfeng.service;

import java.util.List;

import com.pku.xinfeng.model.OperLog;

/**
 * @author zhangl
 *
 */
public interface OperLogService {
	public int insert(String type,String level,String data,String userId,String equipId);
	public List<OperLog> selectByOperLog(OperLog obj);
}

package com.pku.xinfeng.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Export {
	//Excel导出的实现
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static HSSFWorkbook getHSSFWorkbook(List<HashMap> list,String sheetName,String[] cellHeader,String[] cellAttr) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(sheetName);
		
		//表头
		HSSFRow row = sheet.createRow((short) 0);// 创建第一行,为exel表头
		for (int i = 0; i < cellHeader.length; i++) {
			HSSFCell cell = row.createCell((short) i);
			cell.setCellValue(new HSSFRichTextString(cellHeader[i]));
		}
		if(null!= list && list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				HashMap<Object, Object> map = list.get(i);
				HSSFRow dataRow = sheet.createRow(i + 1);// 创建其他各数据行
				for (int j = 0; j < cellAttr.length; j++) {
					HSSFCell cell = dataRow.createCell((short) j);
					cell.setCellValue(new HSSFRichTextString(map.get(cellAttr[j]) == null ? "" : map.get(cellAttr[j]).toString()));
				}
			}
		}
		return workbook;
	}
	
	/**
	 * 将list 转换为xml格式的字符串
	 * @param tName 表名
	 * @param list HashMap的集合
	 * @return xml格式的字符串
	 */
	@SuppressWarnings("rawtypes")
	public static String getMapXml(String tName, List<Object> list) {
		String xml = "<" + tName + ">";
		if(list.size() > 0) {
			Iterator lit = list.iterator();
			while(lit.hasNext()) {
				HashMap map = (HashMap)lit.next();
				Iterator it = map.entrySet().iterator();
				while(it.hasNext()) {
					String kv = it.next().toString();
					xml += "<" + kv.substring(0,kv.indexOf("=")) + ">";
					xml += kv.substring(kv.indexOf("=")+1,kv.length());
					xml += "</" + kv.substring(0,kv.indexOf("=")) + ">";
				}
			}
		}		
		xml += "</" + tName + ">";
		return xml;
	}
}

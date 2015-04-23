package com.pku.xinfeng.utils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;


/**
 * 导出excel
 * @author wangzw
 * @date：2011-02-26
 */
public class ExplortExcel {
	
	
	/**
	 * 导出报表公用方法
	 * @param list
	 * @param titleName
	 * @param cellHeader
	 * @param cellValue
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static HSSFWorkbook  creatWorkbook(List list,String titleName,String[] cellHeader,String[] cellValue,Object obj) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(titleName);
		//设置默认的宽和高
		sheet.setDefaultColumnWidth((short) 15);
		sheet.setDefaultRowHeight((short) 24);
		// 设置样式
		HSSFCellStyle style = workbook.createCellStyle(); //大标题样式
		HSSFCellStyle style2 = workbook.createCellStyle();//表头样式
		HSSFCellStyle style3 = workbook.createCellStyle();//正文样式
		
		style.setFillForegroundColor(HSSFColor.WHITE.index);//背景色无
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//将背景色加入面板
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
		// 字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 22);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到样式
		style.setFont(font);
		
		style2.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		HSSFFont font2 = workbook.createFont();
		font2.setColor(HSSFColor.BLACK.index);
		font2.setFontHeightInPoints((short) 12);
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style2.setFont(font2);
		
		//大标题
		int length = cellHeader.length-1;
		sheet.addMergedRegion(new Region(0, (short) 0,1, (short) (length+1)));//起始行、起始列、结束行、结束列
		HSSFRow row = sheet.createRow((short) 0);// 创建第一行
		HSSFCell titleCell = row.createCell((short) 0);
		titleCell.setCellValue(new HSSFRichTextString(titleName));
		//把样式应用到大标题单元格
		titleCell.setCellStyle(style);
		//下面这个for循环的作用是给合并的单元格整体外面画上边框
		/*for(int k = 1;k<=length;k++){
			HSSFCell titleCell2 = row.createCell((short) k);
			//titleCell2.setCellValue(new HSSFRichTextString("d"));
			titleCell2.setCellStyle(style);
		}*/
		
		HSSFRow tableheadRow = sheet.createRow((short) 2);// 创建表头
		HSSFCell cellxh = tableheadRow.createCell((short) 0);
		cellxh.setCellValue(new HSSFRichTextString("序号"));
		cellxh.setCellStyle(style2);
		for (int i = 0; i < cellHeader.length; i++) {
			HSSFCell cell = tableheadRow.createCell((short) (i+1));
			cell.setCellValue(new HSSFRichTextString(cellHeader[i]));
			cell.setCellStyle(style2);
		}
		//正文内容样式
		style3.setFillForegroundColor(HSSFColor.WHITE.index);
		style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				obj =  list.get(i);
				HSSFRow dataRow = sheet.createRow(i + 3);// 创建正文数据行
				HSSFCell cellone = dataRow.createCell((short) 0) ;
				if(i==list.size()-1){
					cellone.setCellValue(new HSSFRichTextString("合计")); //合计
				}else{
					cellone.setCellValue(new HSSFRichTextString(""+(i+1))); //序号
				}
				cellone.setCellStyle(style3);
				try {
					for(int k=0 ;k<cellValue.length;k++){
						//执行数组中提供的方法名的方法
						Method addMethod = obj.getClass().getMethod(cellValue[k]);
						Object resultString = new Object();
						resultString = addMethod.invoke(obj);
						if(resultString == null){ //这么做可以防止页面上出现NULL的显示
							resultString = new String("");
						}
						//把读出的数据插入到excel的单元格中并赋予样式
						HSSFCell cell = dataRow.createCell((short) (k+1));
						cell.setCellValue(new HSSFRichTextString(""+resultString));
						cell.setCellStyle(style3);
					}
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	
		return workbook;
	}
	/**
	 * 导出excel，结果集是Map
	 * @param list
	 * @param sheetName
	 * @param cellHeader
	 * @param cellAttr
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static HSSFWorkbook creatWorkbookMap(List list,String titleName,String[] cellHeader,String[] cellAttr) {
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(titleName);
		//设置默认的宽和高
		sheet.setDefaultColumnWidth((short) 15);
		sheet.setDefaultRowHeight((short) 24);
		// 设置样式
		HSSFCellStyle style = workbook.createCellStyle(); //大标题样式
		HSSFCellStyle style2 = workbook.createCellStyle();//表头样式
		HSSFCellStyle style3 = workbook.createCellStyle();//正文样式
		
		style.setFillForegroundColor(HSSFColor.WHITE.index);//背景色无
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//将背景色加入面板
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
		// 字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 22);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到样式
		style.setFont(font);
		
		style2.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		HSSFFont font2 = workbook.createFont();
		font2.setColor(HSSFColor.BLACK.index);
		font2.setFontHeightInPoints((short) 12);
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style2.setFont(font2);
		
		//大标题
		int length = cellHeader.length-1;
		sheet.addMergedRegion(new Region(0, (short) 0,1, (short) (length+1)));//起始行、起始列、结束行、结束列
		HSSFRow row = sheet.createRow((short) 0);// 创建第一行
		HSSFCell titleCell = row.createCell((short) 0);
		titleCell.setCellValue(new HSSFRichTextString(titleName));
		//把样式应用到大标题单元格
		titleCell.setCellStyle(style);
		//下面这个for循环的作用是给合并的单元格整体外面画上边框
		/*for(int k = 1;k<=length;k++){
			HSSFCell titleCell2 = row.createCell((short) k);
			//titleCell2.setCellValue(new HSSFRichTextString("d"));
			titleCell2.setCellStyle(style);
		}*/
		
		HSSFRow tableheadRow = sheet.createRow((short) 2);// 创建表头
		HSSFCell cellxh = tableheadRow.createCell((short) 0);
		cellxh.setCellValue(new HSSFRichTextString("序号"));
		cellxh.setCellStyle(style2);
		for (int i = 0; i < cellHeader.length; i++) {
			HSSFCell cell = tableheadRow.createCell((short) (i+1));
			cell.setCellValue(new HSSFRichTextString(cellHeader[i]));
			cell.setCellStyle(style2);
		}
		//正文内容样式
		style3.setFillForegroundColor(HSSFColor.WHITE.index);
		style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				HashMap map = new HashMap();
				map = (HashMap) list.get(i);
				HSSFRow dataRow = sheet.createRow(i + 3);// 创建其他各数据行
				HSSFCell cellone = dataRow.createCell((short) 0) ;
				cellone.setCellValue(new HSSFRichTextString(""+(i+1))); //序号
				cellone.setCellStyle(style3);
				for (int j = 0; j < cellAttr.length; j++) {
					int j1 = j+1;
					HSSFCell cell = dataRow.createCell((short) j1);
					
					cell.setCellValue(new HSSFRichTextString(map.get(cellAttr[j]) == null ? "" : map.get(cellAttr[j]).toString()));
					cell.setCellStyle(style3);
				}
			}
		}
		return workbook;
	}
	
	/**
	 * 根据需要过滤不需要的方法
	 * @param methodName
	 * @return
	 */
	public static boolean validateName(String methodName){
		
		boolean b = true;
		if(!methodName.startsWith("get")){//首先过滤掉那些方法不是以get开头的
			b = false;
		}else if(methodName.startsWith("getId") || methodName.startsWith("getEBuildses")){ //id属性一般都不要求打印
			b = false;
		}
		
		
		return b;
	}
}

package com.pku.xinfeng.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 字符串处理类
 * 
 */
public class StringUtil {

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 *            字符串
	 * @return 是否为空
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.trim().length() == 0);
	}

	/**
	 * 判断字符串是否由数字组成
	 * 
	 * @param str
	 * @return 是否全为数字
	 */
	public static boolean isNumber(String str) {
		if (str == null) {
			return false;
		}
		return str.trim().matches("^[0-9]+$");
	}

	/**
	 * String转化Int
	 * 
	 * @param str
	 * @return str为空或不为数字 返回-1
	 */
	public static int parseInt(String str) {
		if (str == null || str.trim().length() == 0) {
			return -1;
		}
		try {
			Integer.parseInt(str.trim());
		} catch (Exception e) {
			return -1;
		}
		return Integer.parseInt(str.trim());
	}

	public static int parseInt(String num, int def) {
		if (num == null || num.trim().length() == 0) {
			return def;
		}
		try {
			Integer.parseInt(num.trim());
		} catch (Exception e) {
			return def;
		}
		return Integer.parseInt(num.trim());
	}

	/**
	 * 判断字符串是否为DOUBLE类型
	 * 
	 * @param str
	 * @return 是否DOUBLE类型
	 */
	public static boolean isDouble(String str) {
		if (str == null) {
			return false;
		}
		String req = "^\\d*(?:$|\\.\\d*$)";
		return str.trim().matches(req);
	}

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * 转换字节数组为16进制字符串
	 * 
	 * @param b
	 *            字节数组
	 * @return 16进制字符串
	 */
	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * MD5加密一个字符串
	 * 
	 * @param origin
	 *            原始字符串
	 * @return 加密后字符串
	 */
	public static String MD5Encode(String origin) {
		String resultString = null;

		resultString = new String(origin);
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		resultString = byteArrayToHexString(md.digest(resultString.getBytes()));

		return resultString;
	}

	/**
	 * 删除input字符串中的html格式 并截取字符
	 * 
	 * @param input
	 * @param length
	 * @return
	 */
	public static String splitAndFilterString(String input, int length) {
		String str = removeHtml(input);
		int len = str.length();
		if (len <= length) {
			return str;
		} else {
			str = str.substring(0, length);
			str += "...";
		}
		return str;
	}

	/**
	 * 截取字符串设定长度
	 * 
	 * @param str
	 * @param cutCount
	 *            设定长度，字节数
	 * @return
	 */
	public static String getSubStr(String str, int cutCount) {
		if (str == null)
			return "";
		String resultStr = "";
		char[] ch = str.toCharArray();
		int count = ch.length;
		int strBLen = str.getBytes().length;
		int temp = 0;
		for (int i = 0; i < count; i++) {
			resultStr += ch[i];
			temp = resultStr.getBytes().length;
			if (temp >= cutCount && temp < strBLen) {
				// resultStr += "...";
				break;
			}
		}
		return resultStr;
	}

	/**
	 * 删除input中html格式
	 * 
	 * @param input
	 * @return
	 */
	public static String removeHtml(String input) {
		if (input == null || input.trim().equals("")) {
			return "";
		}
		// 去掉所有html元素,
		String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
				"<[^>]*>", "");
		str = str.replaceAll("[(/>)<]", "");
		return str;
	}

	/**
	 * 删除input中span和div标记
	 * 
	 * @param input
	 * @return
	 */
	public static String removeStyle(String input) {
		if (input == null || input.trim().equals("")) {
			return "";
		}
		// String str =
		// input.replaceAll("<\\s{0,}((span)|(div)|(p)|(SPAN)|(DIV)|(P)|(/P)|(/DIV)|(/SPAN)|(/p)|(/div)|(/span))\\s{0,}[^>]*>",
		// "");
		String str = input
				.replaceAll(
						"<\\s{0,}((span)|(div)|(SPAN)|(DIV)|(/DIV)|(/SPAN)|(/div)|(/span))\\s{0,}[^>]*>",
						"");
		return str;
	}

	/**
	 * 将NULL转化为空字符串
	 * 
	 * @param strTarget
	 *            转化目标
	 * @return 转化结果
	 */
	public static String makeNullToEmptyString(String strTarget) {
		if (strTarget == null) {
			strTarget = "";
		}
		return strTarget;
	}

	/**
	 * 将空字符串转化为0
	 * 
	 * @param strTarget
	 *            转化目标
	 * @return 转化结果
	 */
	public static String makeEmptyStringToZero(String strTarget) {
		if (strTarget == null) {
			strTarget = null;
		} else if (strTarget.equals("")) {
			strTarget = "0";
		}
		return strTarget;
	}

	public static String makeNullOrEmptyToDef(String source, String def) {
		return StringUtil.isEmpty(source) == true ? def : source;
	}

	public static String character2utf8(String character) {
		if (StringUtil.isEmpty(character)) {
			return "";
		}
		try {
			return new String(character.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	public static String replace(String strSource, String strFrom, String strTo) {
		if (strFrom == null || strFrom.equals("")) {
			return strSource;
		}
		String strDest = "";

		int intFromLen = strFrom.length();
		int intPos;

		while ((intPos = strSource.indexOf(strFrom)) != -1) {
			strDest = strDest + strSource.substring(0, intPos);

			strDest = strDest + strTo;

			strSource = strSource.substring(intPos + intFromLen);
		}

		strDest = strDest + strSource;

		return strDest;
	}

	public static double round(double v, int scale) {
		String temp = "#0.";
		for (int i = 0; i < scale; i++) {
			temp += "0";
		}
		return Double.valueOf(new java.text.DecimalFormat(temp).format(v))
				.doubleValue();
	}

	public static Map<Integer, Integer> sortByMap(Map<Integer, Integer> map) {
		if (map == null || map.isEmpty() || map.size() == 0) {
			return null;
		}
		Map<Integer, Integer> rsltMap = new HashMap<Integer, Integer>();
		List<Map.Entry<Integer, Integer>> infoIds = new ArrayList<Map.Entry<Integer, Integer>>(
				map.entrySet());

		// 排序
		Collections.sort(infoIds,
				new Comparator<Map.Entry<Integer, Integer>>() {
					public int compare(Map.Entry<Integer, Integer> o1,
							Map.Entry<Integer, Integer> o2) {
						return (o2.getValue() - o1.getValue());
					}
				});

		for (int i = 0; i < infoIds.size(); i++) {
			// String id = infoIds.get(i).toString();
			int key = infoIds.get(i).getKey();
			int value = infoIds.get(i).getValue();
			rsltMap.put(key, value);
		}
		return rsltMap;
	}

	public static String replaceAll(StringBuffer sb, String what, String with) {
		int pos = sb.indexOf(what);

		while (pos > -1) {
			sb.replace(pos, pos + what.length(), with);
			pos = sb.indexOf(what);
		}
		return sb.toString();
	}

	public static String replaceAll(String contents, String what, String with) {
		return replaceAll(new StringBuffer(contents), what, with);
	}

	public static String getRandcode() {
		char[] candidate = new char[36];
		for (int j = 0, i = 48; i < 58; j++, i++)
			candidate[j] = (char) i;

		String rand = "";
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			int idx = random.nextInt(10);
			rand = rand + candidate[idx];
		}

		return rand;
	}

	public static String makeNullToSymbol(String strSource, String symbol) {
		if (null == strSource || "".equals(strSource.trim()))
			return symbol;
		else
			return strSource;
	}

	public static boolean isBoolean(String strSource) {
		if (null == strSource || "".equals(strSource.trim()))
			return false;
		else if ("0".equals(strSource) || "1".equals(strSource)
				|| "true".equalsIgnoreCase(strSource)
				|| "false".equalsIgnoreCase(strSource))
			return true;
		else
			return false;
	}
	
	public static boolean toBoolean(String strSource) {
		if (null == strSource || "".equals(strSource.trim()))
			return false;
		else if ("1".equals(strSource) || "true".equals(strSource))
			return true;
		else if ("0".equals(strSource) || "false".equals(strSource))
			return false;
		else 
			return false;
    }
}

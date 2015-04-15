package com.pku.xinfeng.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class HttpUtil {
	public static String sendGet(String url) {
		String returnStr = "";
		
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		StringBuffer resultBuffer = new StringBuffer();
		String tempLine = null;
		try {
			URL localURL = new URL(url);
			URLConnection connection = localURL.openConnection();
			HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
			
			if(null != httpURLConnection){
				httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
				httpURLConnection.setRequestProperty("Content-Type",
						"application/json; charset=UTF-8");

				if (httpURLConnection.getResponseCode() >= 300) {
					throw new Exception(
							"HTTP Request is not success, Response code is "
									+ httpURLConnection.getResponseCode());
				}

				inputStream = httpURLConnection.getInputStream();
				inputStreamReader = new InputStreamReader(inputStream,"utf-8");
				reader = new BufferedReader(inputStreamReader);
				
				while ((tempLine = reader.readLine()) != null) {
					resultBuffer.append(tempLine);
				}
				returnStr = resultBuffer.toString();
			}
		} catch (Exception e) {
			System.out.println("*******" + new Date() + "*****************");
			System.out.println(url);
			System.out.println("发送GET请求出现异常:" + e);
			e.printStackTrace();
			
		}finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (inputStreamReader != null) {
					inputStreamReader.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return returnStr;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url) {
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常:" + e);
			e.printStackTrace();
		}finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public static boolean sendMsg(String phoneNumber, String reandCode) {
		boolean flag = false;
		try {
			String url = "http://xintx.telhk.cn/sms.aspx?"
					+ "action=send&userid=&account=t00151&password=852369&"
					+ "mobile=" + phoneNumber.trim() + "&"
					+ "content=" + URLEncoder.encode("尊敬的用户，您的手机验证码为：" + reandCode + "，请谨慎保管。【朗视慧洁】","utf-8");// 13439531780
			
			String result = HttpUtil.sendGet(url);
			System.out.println(result);
			if (null != result && !StringUtil.isEmpty(result)
					&& readStatusXmlOut(result).toLowerCase().equals("success"))
				flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 读取短信发送发，返回的状态
	 */
	public static String readStatusXmlOut(String xml) {
		String returnStr = "";
		try {
			if(!StringUtil.isEmpty(xml)){
				Document doc = DocumentHelper.parseText(xml); // 将字符串转为XML
				Element rootElt = doc.getRootElement(); // 获取根节点
				returnStr = rootElt.elementText("returnstatus");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnStr;
	}

	public static void main(String[] args) throws Exception {
//		String url = "http://open.weather.com.cn/data/?areaid=101290101&type=forecast_f&date=201503231911&appid=79e888&key=oOu2uHVAsDJrERqC%2Bh%2FVyj%2Bd2KI%3D";
		sendMsg("222", "2222");
	}
}

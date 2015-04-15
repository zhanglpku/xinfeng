package com.pku.xinfeng.utils;

import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;


public class WeatherInterfaceUtil {

	private static final char last2byte = (char) Integer.parseInt("00000011", 2);
	private static final char last4byte = (char) Integer.parseInt("00001111", 2);
	private static final char last6byte = (char) Integer.parseInt("00111111", 2);
	private static final char lead6byte = (char) Integer.parseInt("11111100", 2);
	private static final char lead4byte = (char) Integer.parseInt("11110000", 2);
	private static final char lead2byte = (char) Integer.parseInt("11000000", 2);
	private static final char[] encodeTable = new char[] { 'A', 'B', 'C', 'D',
			'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
			'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
			'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
			'4', '5', '6', '7', '8', '9', '+', '/' 
	};

	public static String standardURLEncoder(String data, String key) {
		byte[] byteHMAC = null;
		String urlEncoder = "";
		try {
			Mac mac = Mac.getInstance("HmacSHA1");
			SecretKeySpec spec = new SecretKeySpec(key.getBytes(), "HmacSHA1");
			mac.init(spec);
			byteHMAC = mac.doFinal(data.getBytes());
			if (byteHMAC != null) {
				String oauth = encode(byteHMAC);
				if (oauth != null) {
					urlEncoder = URLEncoder.encode(oauth, "utf8");
				}
			}
		} catch (InvalidKeyException e1) {
			e1.printStackTrace();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return urlEncoder;
	}

	public static String encode(byte[] from) {
		StringBuffer to = new StringBuffer((int) (from.length * 1.34) + 3);
		int num = 0;
		char currentByte = 0;
		for (int i = 0; i < from.length; i++) {
			num = num % 8;
			while (num < 8) {
				switch (num) {
				case 0:
					currentByte = (char) (from[i] & lead6byte);
					currentByte = (char) (currentByte >>> 2);
					break;
				case 2:
					currentByte = (char) (from[i] & last6byte);
					break;
				case 4:
					currentByte = (char) (from[i] & last4byte);
					currentByte = (char) (currentByte << 2);
					if ((i + 1) < from.length) {
						currentByte |= (from[i + 1] & lead2byte) >>> 6;
					}
					break;
				case 6:
					currentByte = (char) (from[i] & last2byte);
					currentByte = (char) (currentByte << 4);
					if ((i + 1) < from.length) {
						currentByte |= (from[i + 1] & lead4byte) >>> 4;
					}
					break;
				}
				to.append(encodeTable[currentByte]);
				num += 6;
			}
		}
		if (to.length() % 4 != 0) {
			for (int i = 4 - to.length() % 4; i > 0; i--) {
				to.append("=");
			}
		}
		return to.toString();
	}

	public static String fun(String public_key,String private_key) throws Exception{
		SecretKeySpec signingKey = new SecretKeySpec(private_key.getBytes(), "HmacSHA1");  
		byte[] rawHmac =null;
        Mac mac;
        mac = Mac.getInstance("HmacSHA1");
		mac.init(signingKey);
		rawHmac = mac.doFinal(public_key.getBytes());
        byte[] key= Base64.encodeBase64(rawHmac);
        return new String(key, "UTF-8");
	}
	
	public static String getRequestUrl(String areaid){
		String url = "";
		
		String date = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
		
		String data = "http://open.weather.com.cn/data/?" + "areaid=" + areaid
				+ "&type=forecast_f&date=" + date;
		// 密钥
        String privateKey = "d6e376_SmartWeatherAPI_6d234ed";  
        url = data;
		data += "&appid=79e8885ee9656c45";
        url += "&appid=79e888&key=" + standardURLEncoder(data, privateKey);
		return url;
	}
	public static void main(String[] args) {
		try {
//			//需要加密的数据  
//            String data = "http://open.weather.com.cn/data/?"
//            		+ "areaid=101010100&type=forecast_f&date=201503201750&appid=79e8885ee9656c45";  
//            //密钥  
//            String key = "d6e376_SmartWeatherAPI_6d234ed";  
//            
//            String str = standardURLEncoder(data, key);
//
//            System.out.println(str);
//            
//            String date = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
//            System.out.println(date);
			
			System.out.println(getRequestUrl("101010100"));
            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
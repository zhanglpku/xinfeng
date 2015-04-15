
/**
 * @(#)ArrayUtils.java, 2013年10月24日. Copyright 2013 Wiseserc. All rights
 *                      reserved. This software is for bdrj. For more
 *                      information, please see the related documentation
 */
package com.pku.xinfeng.utils;

/**
 * @author huangsz
 */
public class ArrayUtils {
    public static String byte2HexStr(byte[] bytes) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {
            String s = Integer.toHexString(bytes[i] & 0xFF);
            if (s.length() == 1) {
                sb.append("0");
            }
            sb.append(s.toUpperCase());
        }

        return sb.toString();
    }
}

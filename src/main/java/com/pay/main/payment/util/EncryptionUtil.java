package com.pay.main.payment.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 数据加密工具类
 * 
 * @author guo
 */
public class EncryptionUtil {
	public static String sha1(String var0) {
		if (var0 != null && var0.length() != 0) {
			char[] var1 = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			try {
				MessageDigest var2;
				(var2 = MessageDigest.getInstance("SHA1")).update(var0.getBytes());
				byte[] var8;
				int var9;
				char[] var3 = new char[(var9 = (var8 = var2.digest()).length) << 1];
				int var4 = 0;

				for (int var5 = 0; var5 < var9; ++var5) {
					byte var6 = var8[var5];
					var3[var4++] = var1[var6 >>> 4 & 15];
					var3[var4++] = var1[var6 & 15];
				}
				return new String(var3);
			} catch (Exception var7) {
				return null;
			}
		} else {
			return null;
		}
	}
	
	public static String md5s(String var0) {
		try {
			MessageDigest var1;
			(var1 = MessageDigest.getInstance("MD5")).update(var0.getBytes());
			byte[] var5 = var1.digest();
			StringBuffer var2 = new StringBuffer("");
			for (int var3 = 0; var3 < var5.length; ++var3) {
				int var6;
				if ((var6 = var5[var3]) < 0) {
					var6 += 256;
				}
				if (var6 < 16) {
					var2.append("0");
				}
				var2.append(Integer.toHexString(var6));
			}
			return var2.toString();
		} catch (NoSuchAlgorithmException var4) {
			var4.printStackTrace();
			return "";
		}
	}
}
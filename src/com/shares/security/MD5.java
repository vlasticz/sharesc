package com.shares.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	private static MessageDigest digester;

	static {
		try {
			digester = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException arg0) {
			arg0.printStackTrace();
		}

	}

	public static String crypt(String str) {
		if (str != null && str.length() != 0) {
			digester.update(str.getBytes());
			byte[] hash = digester.digest();
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < hash.length; ++i) {
				if ((255 & hash[i]) < 16) {
					hexString.append("0" + Integer.toHexString(255 & hash[i]));
				} else {
					hexString.append(Integer.toHexString(255 & hash[i]));
				}
			}

			return hexString.toString();
		} else {
			throw new IllegalArgumentException("String to encript cannot be null or zero length");
		}
	}
}
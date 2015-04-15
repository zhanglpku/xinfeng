package com.pku.xinfeng.utils;

import java.util.UUID;

public class ID {
	synchronized public static String get() {
		return UUID.randomUUID().toString();
	}
}

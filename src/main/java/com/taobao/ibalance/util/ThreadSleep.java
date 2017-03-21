package com.taobao.ibalance.util;

import java.util.Random;

public class ThreadSleep {
	public static void sleep(int seconds) {
		int number = new Random().nextInt(10) + 1;
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

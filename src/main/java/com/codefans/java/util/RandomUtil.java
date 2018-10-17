package com.codefans.java.util;

import java.util.Random;

public class RandomUtil {

	public int nextInt(int start, int end) {
		return new Random().nextInt(end - start) + start;
	}

	public int nextInt(int end) {
		return nextInt(0, end);
	}
}

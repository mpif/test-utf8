package com.codefans.algorithm.combination;

import java.util.Iterator;
import java.util.TreeSet;

public class TestQuestion {
	private String[] b = new String[] { "1", "2", "2", "3", "4", "5" };
	private int n = b.length;
	private boolean[] visited = new boolean[n];
	private int[][] a = new int[n][n];
	private String result = "";
	private TreeSet set = new TreeSet();

	public static void main(String[] args) {
		new TestQuestion().start();
	}

	private void start() {

		// Initial the map a[][]
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) {
					a[i][j] = 0;
				} else {
					a[i][j] = 1;
				}
			}
		}

		// 3 and 5 can not be the neighbor.
		a[3][5] = 0;
		a[5][3] = 0;

		// Begin to depth search.
		for (int i = 0; i < n; i++) {
			this.depthFirstSearch(i);
		}

		// Print result treeset.
		Iterator it = set.iterator();
		int count = 0;
		int fourCount = 0;
		while (it.hasNext()) {
			String string = (String) it.next();
			// "4" can not be the third position.
			if (string.indexOf("4") != 2) {
				if (isTemple(string)) {
					System.out.println(string);
					count++;
				}
			} else {
				fourCount++;
			}
		}
		System.out.println("size:" + set.size());
		System.out.println("count:" + count);
		System.out.println("fourCount:" + fourCount);
	}

	// temple: 1 2 2 3 4 5
	public boolean isTemple(String str) {
		boolean flag = true;
		char[] chars = str.toCharArray();
		int twoCount = 0;
		boolean one = false;
		boolean two = false;
		boolean two2 = false;
		boolean three = false;
		boolean four = false;
		boolean five = false;

		for (char c : chars) {
			if (c == '1') {
				one = true;
			}
			if (c == '2') {
				if (twoCount == 0) {
					two = true;
					twoCount++;
				} else {
					two2 = true;
				}
			}
			if (c == '3') {
				three = true;
			}
			if (c == '4') {
				four = true;
			}
			if (c == '5') {
				five = true;
			}
		}

		flag = one && two && two2 && three && four && five;
		return flag;
	}

	private void depthFirstSearch(int startIndex) {
		visited[startIndex] = true;
		result = result + b[startIndex];
		if (result.length() == n) {
			// Filt the duplicate value.
			set.add(result);
		}
		for (int j = 0; j < n; j++) {
			if (a[startIndex][j] == 1 && visited[j] == false) {
				depthFirstSearch(j);
			} else {
				continue;
			}
		}

		// restore the result value and visited value after listing a node.
		result = result.substring(0, result.length() - 1);
		visited[startIndex] = false;
	}
}

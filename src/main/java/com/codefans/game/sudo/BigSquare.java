package com.codefans.game.sudo;

import java.util.Arrays;
import java.util.Random;

public class BigSquare {

	Square[][] squares = new Square[3][3];
	int[][] data = new int[9][9];
	int[] original_data = new int[81];
	int[] original = new int[9];

	int[] ar = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	int[] arr = new int[] { 5, 4, 3, 6, 9, 7, 2, 8, 1 };

	public static void main(String args[]) {
		BigSquare big = new BigSquare();
		big.getOriginalData();

		// big.print(big.getRundom(1, 9));
		// big.validateRow(3);

		for (int i = 0; i < 9; i++) {
			if (i < 5) {
				big.data[i][2] = i + 1;
			} else {
				big.data[i][2] = 5;
			}
		}
		big.print(big.validateColumn(3));// false
		for (int i = 0; i < 9; i++) {
			big.data[i][2] = i + 1;
		}
		big.print(big.validateColumn(3));// true

		for (int i = 0; i < 9; i++) {
			if (i < 5) {
				big.data[2][i] = i + 1;
			} else {
				big.data[2][i] = 5;
			}
		}
		big.print(big.validateRow(3));// false

		for (int i = 0; i < 9; i++) {
			big.data[2][i] = i + 1;
		}
		big.print(big.validateRow(3)); // true

		// int[] arr = new int[9];
		// for(int i = 0; i < 9; i ++) {
		// int index = Arrays.binarySearch(arr, i + 1);
		// if(index <= 0) {
		// arr[i] = i + 1;
		// } else {
		// big.print("index: " + index);
		// }
		// }

		int n = 3;
		// big.print("search for: " + n + ", result: " +
		// big.binarySearch(big.ar, n));
		big.print("search for: " + n + ", result: " + big.binarySearch(big.arr, n));
	}

	public boolean binarySearch(int[] arr, int key) {
		boolean flag = false;
		int low = 0;
		int high = arr.length - 1;

		while (low <= high) {
			int mid = (low + high) >>> 1;
			int midValue = arr[mid];
			if (key < midValue) {
				high = mid - 1;
			} else if (midValue < key) {
				low = mid + 1;
			} else {
				flag = true;
				break;
			}
		}

		return flag;
	}

	public int search(int[] arr, int key) {
		int index = -1;
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] == key) {
					index = i;
					break;
				}
			}
		}
		return index;
	}

	public void create() {

		SmallSquare[][] smallSquares = null;
		Square square = null;
		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[i].length; j++) {
				square = squares[i][j];
				if (square != null) {
					smallSquares = square.getSmallSquares();
					for (int m = 0; m < smallSquares.length; m++) {
						for (int n = 0; n < smallSquares[m].length; n++) {

						}
					}
				}
			}
		}
	}

	public int getRundom(int low, int high) {
		int temp = 0;
		try {
			if (low > high) {
				temp = new Random().nextInt(low - high);
				return temp + high;
			} else {
				temp = new Random().nextInt(high - low);
				return temp + low;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp + low;
	}

	public int[] getOriginalData() {

		// init data[9][9]
		// for(int i = 0; i < 9; i ++) {
		// for(int j = 0; j < 9; j ++) {
		// data[i][j] = i + 1;
		// }
		// }
		// print("data: ");
		// print(data);

		// init original_data[81]
		int n = 1;
		for (int i = 0; i < original_data.length; i++) {
			original_data[i] = n;
			if ((i + 1) % 9 == 0) {
				n++;
			}
		}
		print("original_data: ");
		print(original_data);

		// init original[9]
		for (int i = 0; i < 9; i++) {
			original[i] = i + 1;
		}
		print("original: ");
		print(original);
		return original_data;
	}

	public void print(Object[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + ", ");
			}
			System.out.println();
		}
	}

	public void print(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + ", ");
			if ((i + 1) % 9 == 0) {
				System.out.println();
			}
		}
	}

	public void println() {
		System.out.println();
	}

	public void print(Object o) {
		System.out.println(o);
	}

	public void validate() {

	}

	public boolean validateColumn(int col) {
		boolean flag = true;
		int[] arr = new int[9];
		int j = 0;
		int index = 0;
		for (int i = 0; i < 9; i++) {
			index = search(arr, data[i][col - 1]);
			if (index < 0) {
				arr[j++] = data[i][col - 1];
			} else {
				flag = false;
				break;
			}
			// print(data[i][col - 1]);
		}
		return flag;
	}

	public boolean validateRow(int row) {
		boolean flag = true;
		int[] arr = new int[9];
		int j = 0;
		int index = 0;
		int n = 0;
		for (int i = 0; i < 9; i++) {
			n = data[row - 1][i];
			index = search(arr, n);
			if (index < 0) {
				arr[j++] = n;
			} else {
				flag = false;
				break;
			}
			print(n);
		}

		// for(int i = 0; i < 9; i ++) {
		// print(data[row - 1][i]);
		// }
		return flag;
	}

	public void start() {

	}

	public void restart() {

	}

	public void pause() {

	}

	public void exit() {

	}

	public Square[][] getSquares() {
		return squares;
	}

	public void setSquares(Square[][] squares) {
		this.squares = squares;
	}
}

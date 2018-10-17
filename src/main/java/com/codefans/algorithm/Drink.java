package com.codefans.algorithm;

import java.util.HashSet;
import java.util.Stack;

public class Drink {

	// state 为32位整数，每4bit表示一个cup
	// cup 0-2 : cup x,y,z
	// cup 3-6 : people a,b,c,d
	// state := [D][C][B][A][Z][Y][X]

	int[] maxVol = { 8, 8, 3, 4, 4, 4, 4 }; // 最大容量
	int stopState = makeState(new int[] { 0, 0, 0, 4, 4, 4, 4 }); // 最终状态

	Stack<Integer> stateStack = new Stack<Integer>();
	HashSet<Integer> stateStackCache = new HashSet<Integer>();
	HashSet<Integer> failStates = new HashSet<Integer>();

	public static void main(String[] args) {
		new Drink().start();
	}

	static int getCup(int state, int i) {
		return (state & (0xF << (i * 4))) >> (i * 4);
	}

	static int makeState(int curState, int cupIdx, int value) {
		return value << (4 * cupIdx) | (curState & (~(0xF << (4 * cupIdx))));
	}

	static int makeState(int curState, int cupIdx1, int value1, int cupIdx2, int value2)

	{
		return makeState(makeState(curState, cupIdx1, value1), cupIdx2, value2);
	}

	// cup.length MUST == 7
	static int makeState(int[] cup) {
		int state = 0;
		for (int i = 6; i >= 0; i--) {
			state |= cup[i];
			if (i > 0)
				state <<= 4;
		}
		return state;
	}

	void start() {
		boolean r = testState(makeState(new int[] { 8, 8, 0, 0, 0, 0, 0 }));
		System.out.println(r ? "found" : "not found");
	}

	boolean testState(int curState) {
		if (stateStackCache.contains(curState) || failStates.contains(curState))
			return false;

		pushState(curState);
		try {
			if (curState == stopState) {
				printStack();
				return true;
			}

			if (testMoves(curState)) {
				return true;
			} else {
				failStates.add(curState);
				return false;
			}
		} finally {
			popState(curState);
		}
	}

	private boolean testMoves(int curState) {
		for (int from = 0; from < 3; from++) {
			int curCupFrom = getCup(curState, from);
			if (curCupFrom == 0)
				continue;

			for (int to = 6; to >= 0; to--) {
				if (to == from)
					continue;

				int curCupTo = getCup(curState, to);
				int maxTo = maxVol[to] - curCupTo;
				if (maxTo == 0)
					continue;

				int moveValue = curCupFrom;
				if (curCupFrom > maxTo) {
					if (to >= 3) // people
						continue;
					moveValue = maxTo;
				}

				int newCupFrom = curCupFrom - moveValue;
				int newCupTo = curCupTo + moveValue;

				if (from < 2 && to < 2 && newCupTo == curCupFrom) {
					// 两个同样的杯子倒来倒去
					continue;
				}

				final int newState = makeState(curState, from, newCupFrom, to, newCupTo);

				if (testState(newState))
					return true;
			}
		}
		return false;
	}

	private void printStack() {
		System.out.println("Success! all steps: " + stateStack.size());
		int step = 0;
		for (Integer state : stateStack) {
			System.out.print("\t" + (++step) + ":\t");
			for (int i = 0; i < 7; i++) {
				System.out.print(getCup(state, i));
				if (i == 2)
					System.out.print(" | ");
			}
			System.out.println();
		}
	}

	private void popState(int newState) {
		stateStack.pop();
		stateStackCache.remove(newState);
	}

	private void pushState(int newState) {
		stateStack.push(newState);
		stateStackCache.add(newState);
	}

	public void p(Object o) {
		System.out.println(o);
	}

}

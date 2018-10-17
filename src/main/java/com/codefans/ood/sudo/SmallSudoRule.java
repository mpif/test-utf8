package com.codefans.ood.sudo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SmallSudoRule {

	private ArrayList<Integer> expectDatas = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
	private final int existsDataCount = expectDatas.size(); //9
	
	public boolean match(List<Integer> datas) {
		boolean match = false;
		if(datas != null && datas.size() == expectDatas.size()) {
			Set<Integer> setDatas = new HashSet<Integer>();
			for(int i = 0; i < datas.size(); i ++) {
				int data = datas.get(i);
				if(expectDatas.contains(data)) {
					setDatas.add(data);
				}
			}
			if(setDatas.size() == existsDataCount) {
				match = true;
			}
		}
		
		return match;
	}
	
}

package com.codefans.leetcode;

/**
 * Created by Administrator on 2017/3/31.
 */
public class Solution1 {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 4};
        int target = 6;
        Solution1 s = new Solution1();
        int[] resIndex = s.twoSum(nums, target);
        System.out.println(resIndex[0]);
        System.out.println(resIndex[1]);
    }

    public int[] twoSum(int[] nums, int target) {
        if(nums == null) {
            return null;
        }

        int[] res = new int[2];
        Loop:
        for(int i = 0; i <= nums.length - 1; i ++) {
            for(int j = i + 1; j <= nums.length - 1; j ++) {
                if(nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                    break Loop;
                }
            }
        }
        return res;
    }

}

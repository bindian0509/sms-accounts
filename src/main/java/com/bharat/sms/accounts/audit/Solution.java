package com.bharat.sms.accounts.audit;

import java.net.Inet4Address;
import java.util.Arrays;
import java.util.HashMap;

/*
 * @author Bharat V. <bindian0509@gmail.com>
 * @created Wednesday, 16 October 2024
 */
public class Solution {

    public static int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> index = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if(index.containsKey(target-nums[i])) {
                return new int[] {i, index.get(target - nums[i])};
            } else {
                index.put(nums[i], i);
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    public static void main(String[] args) {
        int [] test = {2,4,11,3};
        System.out.println(Arrays.toString(twoSum(test, 6)));
        test = new int[]  {3,3};
        System.out.println(Arrays.toString(twoSum(test, 6)));
        test = new int[]  {3,2,4};
        System.out.println(Arrays.toString(twoSum(test, 6)));
    }
}

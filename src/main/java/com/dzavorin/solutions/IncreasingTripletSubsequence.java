package com.dzavorin.solutions;

public class IncreasingTripletSubsequence {

    public boolean increasingTriplet(int[] nums) {
        if(nums == null || nums.length == 0){
            return false;
        }

        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;


        for(int i = 0 ; i < nums.length ; i++) {
            if (nums[i] <= first) {
                first = nums[i];
            } else if (nums[i] <= second) {
                second = nums[i];
            } else {
                return true;
            }
        }

        return false;
    }

    public boolean increasingTriplet2(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n];

        for (int i = 0; i < n ; i++) {
            arr[i] = nums[i];
        }

        for (int i = 0; i < n ; i++) {
            int cur = nums[i];
            for (int j = 0; j < i; j++) {
                if (arr[j] != nums[j] && arr[j] < cur) {
                    return true;
                } else if (arr[j] != nums[j] && cur < arr[j] && cur > nums[j]) {
                    arr[j] = cur;
                } else if (arr[j] == nums[j] && cur > arr[j]) {
                    arr[j] = cur;
                }
            }
        }

        return false;
    }

}

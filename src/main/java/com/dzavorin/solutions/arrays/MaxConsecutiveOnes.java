package com.dzavorin.solutions.arrays;

public class MaxConsecutiveOnes {

    public int solution(int[] nums) {
        if (nums.length == 0) return 0;
        int lastLen = 0;
        int max = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                boolean withinOneZero = false;
                boolean canAddOneBefore = false;

                if (i - 1 >= 0 && nums[i - 1] == 0) {
                    canAddOneBefore = true;
                }
                if (i - 2 >= 0 && canAddOneBefore && nums[i - 2] == 1) {
                    withinOneZero = true;
                }

                int curLen = 1;
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] == 1) {
                        curLen++;
                    } else {
                        i = j;
                        break;
                    }
                }

                if (withinOneZero) {
                    max = Math.max(max, lastLen + curLen + 1);
                } else {
                    if (canAddOneBefore || nums[i] == 0) {
                        max = Math.max(max, curLen + 1);
                    } else {
                        max = Math.max(max, curLen);
                    }
                }
                lastLen = curLen;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new MaxConsecutiveOnes().solution(new int[] {0,0,1,1,0,1}));
        System.out.println(new MaxConsecutiveOnes().solution(new int[] {0,0,1,1,0,0,1})); // 3
        System.out.println(new MaxConsecutiveOnes().solution(new int[] {1,1,1,1,0,0,1,1,0,0,1}));
        System.out.println(new MaxConsecutiveOnes().solution(new int[] {1,1,1,1,0})); // 5
        System.out.println(new MaxConsecutiveOnes().solution(new int[] {0}));
        System.out.println(new MaxConsecutiveOnes().solution(new int[] {1,0}));
        System.out.println(new MaxConsecutiveOnes().solution(new int[] {0,1}));
        System.out.println(new MaxConsecutiveOnes().solution(new int[] {1}));
        System.out.println(new MaxConsecutiveOnes().solution(new int[] {1,0,0,1}));
        System.out.println(new MaxConsecutiveOnes().solution(new int[] {1,0,1}));
        System.out.println(new MaxConsecutiveOnes().solution(new int[] {}));
    }
}

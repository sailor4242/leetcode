package com.dzavorin.solutions;

public class MinCostClimbingStairs {

    public int minCostClimbingStairs(int[] cost) {
        int arr[] = new int[cost.length + 1];
        arr[0] = cost[0];
        arr[1] = cost[1];

        for (int i = 2; i < cost.length; i++) {
            arr[i] = cost[i] + Math.min(arr[i - 1], arr[i - 2]);
        }
        return Math.min(arr[cost.length - 1], arr[cost.length - 2]);
    }


    public static void main(String[] args) {
        System.out.println(new MinCostClimbingStairs().minCostClimbingStairs(new int[]{0,0,1,1})); // 1
        System.out.println(new MinCostClimbingStairs().minCostClimbingStairs(new int[]{0,0,0,1})); // 0
    }
}

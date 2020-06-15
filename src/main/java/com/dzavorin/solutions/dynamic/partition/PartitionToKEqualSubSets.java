package com.dzavorin.solutions.dynamic.partition;

import java.util.stream.IntStream;

public class PartitionToKEqualSubSets {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = IntStream.of(nums).sum();
        if(sum %k > 0) return false;
        int[] visited = new int[nums.length];
        int targetSum = sum / k;

        boolean isFound = subsetSum(nums, visited, 0, targetSum, 0, k);

        for (int i = 0; i < k; i++) {
            System.out.print("Partition " + i + " is: ");
            for (int j = 0; j < nums.length; j++)
                if (visited[j] == i + 1)
                    System.out.print(nums[j] + " ");
            System.out.println();
        }

        return isFound;
    }
    // private  int globalTarget = 30;
    private boolean subsetSum(int[] nums, int[] visited, int i,  int target, int cur, int k){

        if (k == 0) return true;
        /*
            we found a pair we will keep on searching until it is we found all
         */
        if(target == cur) {
            return subsetSum(nums, visited, 0, target, 0, k - 1);
        }
        if(target <  0 || i >= nums.length){
            return false;
        }

        /*
         * why this for loop, because every element has to contribute.
         * why not reducing target and matching current sum with target,
         * because we want target to be same after we found a pair. try using globalTarget, should work !
         *
         */
        for (int j = i; j < nums.length; j++) {
            if (visited[j] == 0) {
                visited[j] = k;
                boolean selecting = subsetSum(nums, visited, j + 1, target, cur+ nums[j], k);
                if (selecting ){
                    return true;
                }
                visited[j] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        PartitionToKEqualSubSets sum = new PartitionToKEqualSubSets();
        // System.out.println(sum.canPartitionKSubsets(new int[]{2,2,2,2,3,4,5},4));
        // System.out.println(sum.canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1},4));
        System.out.println(sum.canPartitionKSubsets(new int[]{10,10,10,7,7,7,7,7,7,6,6,6},3));

    }

}

package com.dzavorin.solutions.arrays;

public class FindTheDuplicateNumber {

    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        slow = 0;

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public int findDuplicate2(int[] nums) {
        int i = 0;
        while (nums[i] > 0) {
            nums[i] *= -1;
            i = -1 * nums[i];
        }
        return i;
    }

    public int findDuplicate3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int lo = 1;
        int hi = nums.length;
        while (lo < hi) {
            int count = 0;
            int mid = lo + (hi - lo) / 2;
            for (int num : nums) {
                if (num <= mid) count++;
            }
            if (count <= mid) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    public static void main(String[] args) {
        System.out.println(new FindTheDuplicateNumber().findDuplicate(new int[]{3,1,2,4,3}));
    }

}

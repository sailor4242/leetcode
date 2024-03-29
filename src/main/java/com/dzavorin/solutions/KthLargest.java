package com.dzavorin.solutions;

import java.util.PriorityQueue;

class KthLargest {

    PriorityQueue<Integer> pq = new PriorityQueue<>();
    int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int n : nums) {
            pq.add(n);
        }
        while (k < pq.size()) {
            pq.remove();
        }
    }

    public int add(int val) {
        pq.add(val);
        if (k < pq.size()) {
            pq.remove();
        }
        return pq.peek();
    }

    //quickselect

    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    private int quickSelect(int[] nums, int start, int end, int k) {
        int left = start;
        int right = end - 1;
        while (left <= right) {
            if (nums[left] > nums[end]) {
                swap(nums, left, right);
                right--;
            } else {
                left++;
            }
        }

        // [2,3,7,34,6]
        swap(nums, left, end);

        int rank = nums.length - left;
        if (rank == k) return nums[left];
        if (rank > k) return quickSelect(nums, left + 1, end, k);
        return quickSelect(nums, start, left - 1, k);
    }

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public static void main(String[] args) {
        int k = 3;
        int[] arr = new int[]{4,5,8,2};
        KthLargest kthLargest = new KthLargest(3, arr);
        kthLargest.add(3);   // returns 4
        kthLargest.add(5);   // returns 5
        kthLargest.add(10);  // returns 5
        kthLargest.add(9);   // returns 8
        System.out.println(kthLargest.add(4));   // returns 8

        int kk = 2;
        int[] arrr = new int[]{0};
        KthLargest kthLargest2 = new KthLargest(kk, arrr);
        kthLargest2.add(-1);   // returns 4
        kthLargest2.add(1);   // returns 5
        kthLargest2.add(-2);  // returns 5
        kthLargest2.add(-4);   // returns 8
        System.out.println(kthLargest2.add(3));   // returns 8
    }
}

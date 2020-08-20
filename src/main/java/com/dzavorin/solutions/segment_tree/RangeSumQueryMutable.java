package com.dzavorin.solutions.segment_tree;

public class RangeSumQueryMutable {

    int[] segt;
    int[] nums;

    public RangeSumQueryMutable(int[] nums) {
        if (nums.length == 0) return;

        int len = 2 * (Integer.highestOneBit(nums.length) << 1) - 1;
        this.segt = new int[len];
        this.nums = nums;

        buildTree(0, 0, nums.length - 1);

    }

    void buildTree(int pos, int lo, int hi) {
        if (lo == hi) {
            segt[pos] = nums[lo];
            return;
        }
        int mid = lo + (hi - lo) / 2;

        buildTree(2 * pos + 1, lo, mid);
        buildTree(2 * pos + 2, mid + 1, hi);

        segt[pos] = segt[2 * pos + 1] + segt[2 * pos + 2];
    }

    public void update(int i, int val) {
        int diff = val - nums[i];
        nums[i] = val;
        update(0, 0, nums.length - 1, diff, i);
    }

    void update(int pos, int left, int right, int diff, int i) {
        if (i < left || i > right) return;

        segt[pos] += diff;
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        update(2 * pos + 1, left, mid, diff, i);
        update(2 * pos + 2, mid + 1, right, diff, i);
    }

    public int sumRange(int i, int j) {
        return query(0, 0, nums.length - 1, i, j);
    }

    int query(int pos, int lo, int hi, int qlo, int qhi) {
        if (lo >= qlo && hi <= qhi) return segt[pos];    //complete overlap

        if (hi < qlo || lo > qhi) return 0; //outside the range

        int mid = lo + (hi - lo) / 2;

        return query(2 * pos + 1, lo, mid, qlo, qhi)
                + query(2 * pos + 2, mid + 1, hi, qlo, qhi);
    }

    public static void main(String[] args) {
        var rs = new RangeSumQueryMutable(new int[]{0, -2, 5, -1, 3, 1, 8});
        System.out.println(rs.sumRange(1,3));
        System.out.println(rs.sumRange(3,4));
        System.out.println(rs.sumRange(3,5));
        rs.update(5, 100);
        System.out.println(rs.sumRange(3,5));
        System.out.println(rs.sumRange(4,5));


//        var rs = new RangeSumQueryMutable(new int[]{0, 9, 5, 7, 3});
//        System.out.println(rs.sumRange(4, 4));
//        System.out.println(rs.sumRange(2, 4));
//        System.out.println(rs.sumRange(3, 3));

//        var rs2 = new RangeSumQueryMutable(new int[]{-1});
//        rs2.update(0, 1);
//        System.out.println(rs2.sumRange(0, 0));

    }
}

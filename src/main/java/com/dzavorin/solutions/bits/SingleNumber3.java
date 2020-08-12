package com.dzavorin.solutions.bits;

public class SingleNumber3 {

    public int[] singleNumber(int[] nums) {
        int[] res = new int[2];
        int both = 0;
        for(int num : nums) both ^= num;
        both &= (~both+1);
        for(int num : nums) res[(num & both) == 0 ? 0 : 1] ^= num;
        return res;

    }

    public static void main(String[] args) {
        System.out.println(new SingleNumber3().singleNumber(new int[]{1,2,1,3,2,5})); // res - 3, 5
    }
}

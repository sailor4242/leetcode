package com.dzavorin.solutions;

import java.util.*;

public class CountOfSmallerNumbersAfterSelf {


    // Brute force counting

    public List<Integer> countSmaller2(int[] nums) {

        LinkedList<Integer> list = new LinkedList<>();

        int[] arr = new int[20001];
        for (int i = nums.length - 1; i >= 0; i--) {
            int res = 0;
            for (int j = 0; j < nums[i] + 10000; j++) {
                res += arr[j];
            }
            arr[nums[i] + 10000]++;
            list.addFirst(res);
        }

        return list;
    }

    //// Sort and Binary Search

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> out = new ArrayList<>(nums.length);

        // Sort list
        List<Integer> sorted = new ArrayList<>(nums.length);
        for (int n : nums) {
            sorted.add(n);
        }
        Collections.sort(sorted);

        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            int index = indexOf(value, sorted, 0, sorted.size() - 1);

            sorted.remove(index);
            int count = countLessItem(value, sorted, index);
            out.set(i, count);
        }

        return out;
    }

    /**
     * Binary search
     **/
    private int indexOf(int value, List<Integer> list, int start, int end) {
        int middle = start + (end - start) / 2;
        int middleValue = list.get(middle);

        if (value == middleValue) {
            return middle;
        }
        if (end - start == 1) {
            if (list.get(start) == value) return start;
            if (list.get(end) == value) return end;
        }

        if (value < middleValue) {
            // search in the left half
            return indexOf(value, list, start, middle);
        } else {
            // search in the right half
            return indexOf(value, list, middle, end);
        }
    }

    private int countLessItem(int value, List<Integer> sorted, int position) {
        for (int i = position - 1; i >= 0; i--) {
            int current = sorted.get(i);
            if (current < value) {
                // if this value is less than duplicated one - the count of items with less amount equals position of element
                return i + 1;
            }
        }
        return 0;
    }

    ///// Binary indexed tree

    public List<Integer> countSmaller3(int[] nums) {
        LinkedList<Integer> res = new LinkedList<>();
        if (nums == null || nums.length == 0) return res;

        int[] helper = new int[nums.length];
        System.arraycopy(nums, 0, helper, 0, nums.length);
        Arrays.sort(helper);

        HashMap<Integer, Integer> map = new HashMap<>((nums.length * 4 / 3) + 1);
        int num = 1;
        int prev = helper[0];
        map.put(prev, num);
        for (int i = 1; i < helper.length; i++) {
            if (helper[i] != prev) {
                prev = helper[i];
                map.put(prev, ++num);
            }
        }

        int[] tree = new int[num + 1];
        for (int i = nums.length - 1; i >= 0; i--) {
            int treeIndex = map.get(nums[i]);
            res.addFirst(get(tree, treeIndex - 1));
            update(tree, treeIndex);
        }
        return res;
    }

    private int get(int[] tree, int i) {
        int count = 0;
        while (i > 0) {
            count += tree[i];
            i = i & (i - 1);
        }
        return count;
    }

    private void update(int[] tree, int i) {
        while (i < tree.length) {
            tree[i]++;
            i += i & (-i);
        }
    }

    public static void main(String[] args) {
        System.out.println(new CountOfSmallerNumbersAfterSelf().countSmaller(new int[]{5, 2, 6, 1}));
    }

}

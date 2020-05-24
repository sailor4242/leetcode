package com.dzavorin.solutions.sorting;

import java.util.*;

public class TopKFrequentElements {

    int[] unique;
    Map<Integer, Integer> map;

    //// quickselect

    public void swap(int a, int b) {
        int tmp = unique[a];
        unique[a] = unique[b];
        unique[b] = tmp;
    }

    public int partition(int left, int right, int pivotIndex) {
        int pivotFrequency = map.get(unique[pivotIndex]);
        // 1. move pivot to end
        swap(pivotIndex, right);
        int storeIndex = left;

        // 2. move all less frequent elements to the left
        for (int i = left; i <= right; i++) {
            if (map.get(unique[i]) < pivotFrequency) {
                swap(storeIndex, i);
                storeIndex++;
            }
        }

        // 3. move pivot to its final place
        swap(storeIndex, right);

        return storeIndex;
    }

    public void quickselect(int left, int right, int kSmallest) {
        /*
        Sort a list within left..right till kth less frequent element
        takes its place.
        */

        // base case: the list contains only one element
        if (left == right) return;

        // select a random pivotIndex
        Random random = new Random();
        int pivotIndex = left + random.nextInt(right - left);

        // find the pivot position in a sorted list
        pivotIndex = partition(left, right, pivotIndex);

        // if the pivot is in its final sorted position
        if (kSmallest == pivotIndex) {
            return;
        } else if (kSmallest < pivotIndex) {
            // go left
            quickselect(left, pivotIndex - 1, kSmallest);
        } else {
            // go right
            quickselect(pivotIndex + 1, right, kSmallest);
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        // build hash map : character and how often it appears
        map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // array of unique elements
        int n = map.size();
        unique = new int[n];
        int i = 0;
        for (int num : map.keySet()) {
            unique[i] = num;
            i++;
        }

        // kth top frequent element is (n - k)th less frequent.
        // Do a partial sort: from less frequent to the most frequent, till
        // (n - k)th less frequent element takes its place (n - k) in a sorted array.
        // All element on the left are less frequent.
        // All the elements on the right are more frequent.
        quickselect(0, n - 1, n - k);
        // Return top k frequent elements
        return Arrays.copyOfRange(unique, n - k, n);
    }

    // bucketsort

    public List<Integer> topKFrequent2(int[] nums, int k) {

        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        List<Integer> res = new ArrayList<>();

        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }
        return res;
    }

    public int[] topKFrequent3(int[] nums, int k) {

        if (k > nums.length) {
            k = nums.length;
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        Comparator<Map.Entry<Integer, Integer>> comparator = Collections.reverseOrder(
                Map.Entry.comparingByValue());
        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(comparator);

        heap.addAll(map.entrySet());

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = heap.poll().getKey();
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new TopKFrequentElements().topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
    }
}

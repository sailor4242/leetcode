package com.dzavorin.solutions;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache {

    private Map<Integer, Integer> cache;
    private LinkedList<Integer> list;
    int capacity;

    public LRUCache(int capacity) {
        this.cache = new HashMap<>();
        this.capacity = capacity;
        this.list = new LinkedList<>();
    }

    public int get(int key) {
        if (cache.isEmpty() || !cache.containsKey(key)) {
            return -1;
        }

        if (cache.containsKey(key)) {
            list.remove((Integer) key);
        } else {
            list.pollFirst();
        }

        list.add(key);
        return cache.get(key);
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            list.remove((Integer) key);
        } else if (cache.size() >= capacity) {
            int first = list.pollFirst();
            cache.remove(first);
        }
        list.add(key);
        cache.put(key, value);
    }

    private static class Solution {

        private LinkedHashMap<Integer, Integer> cache;
        private int N;

        public Solution(int capacity) {
            this.N = capacity;
            this.cache = new LinkedHashMap<>();
        }

        public int get(int key) {
            int result = -1;

            if (cache.containsKey(key)) {
                result = cache.get(key);
                cache.remove(key);
                cache.put(key, result);
            }

            return result;
        }

        public void put(int key, int value) {
            if (cache.containsKey(key)) {
                cache.remove(key);
            } else {
                if (cache.size() == N) {
                    Integer firstKey = cache.keySet().iterator().next();
                    cache.remove(firstKey);
                }
            }

            this.cache.put(key, value);
        }
    }

}

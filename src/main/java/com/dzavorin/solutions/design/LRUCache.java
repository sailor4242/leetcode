package com.dzavorin.solutions.design;

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

    ////

    private static class Solution2 {

        static class Node {
            int key;
            int value;
            Node prev;
            Node next;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }


        private final int capacity;

        private Map<Integer, Node> map = new HashMap<>();

        // head -> ... -> tail
        private final Node dummyHead = new Node(0, 0);
        private final Node dummyTail = new Node(0, 0);

        public Solution2(int capacity) {
            this.capacity = capacity;
            dummyHead.next = dummyTail;
            dummyTail.prev = dummyHead;
        }

        public int get(int key) {
            if (!map.containsKey(key)) return -1;
            Node node = map.get(key);
            removeFromList(node);
            insertToTail(node);
            return node.value;
        }

        public void put(int key, int value) {
            Node node = null;
            if (!map.containsKey(key)) {
                if (map.size() == capacity && dummyHead.next != dummyTail) {
                    map.remove(dummyHead.next.key);
                    removeFromList(dummyHead.next);
                }
                if (map.size() < capacity) {
                    node = new Node(key, value);
                    map.put(key, node);
                }
            } else {
                node = map.get(key);
                node.value = value;
                removeFromList(node);
            }
            if (node != null) insertToTail(node);
        }


        private void removeFromList(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void insertToTail(Node node) {
            node.prev = dummyTail.prev;
            node.next = dummyTail;
            dummyTail.prev.next = node;
            dummyTail.prev = node;
        }
    }

}

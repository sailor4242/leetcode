package com.dzavorin.solutions.design;

import java.util.HashMap;
import java.util.TreeSet;

public class LFUCache {

    int cap, timestamp;
    TreeSet<Node> freqSet;
    HashMap<Integer, Node> map;

    public LFUCache(int capacity) {
        this.cap = capacity;
        this.timestamp = -1;
        this.freqSet = new TreeSet<>();
        this.map = new HashMap<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        timestamp++;
        Node node = map.get(key);
        freqSet.remove(node);
        node.freq++;
        node.timestamp = timestamp;
        freqSet.add(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (this.cap == 0) {
            return;
        }
        timestamp++;
        if (map.containsKey(key)) {
            Node node = map.get(key);
            freqSet.remove(node);
            node.value = value;
            node.freq++;
            node.timestamp = timestamp;
            freqSet.add(node);
        } else {
            if (this.cap == map.size()) {
                Node removeNode = freqSet.pollLast();
                if (removeNode != null)
                    map.remove(removeNode.key);
            }
            Node node = new Node(key, value, 1, timestamp);
            freqSet.add(node);
            map.put(key, node);
        }
    }

    static class Node implements Comparable<Node> {
        int key, value, freq, timestamp;

        public Node(int key, int value, int freq, int timestamp) {
            this.key = key;
            this.value = value;
            this.freq = freq;
            this.timestamp = timestamp;
        }

        public int compareTo(Node o) {
            if (this.freq == o.freq) {
                return o.timestamp - this.timestamp;
            }
            return o.freq - this.freq;
        }
    }

}

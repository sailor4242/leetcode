package com.dzavorin.solutions.design;

import java.util.*;

public class AllInOneDataStructure {

    HashMap<String, Node> map = new HashMap<>();
    ListN list = new ListN();


    static class ListN {
        Node head = null;
        Node tail = null;

        public Node add(String key, int n) {
            if (head == null) {
                var node = new Node(n);
                node.set.add(key);
                return head = tail = node;
            } else {
                var node = new Node(n);
                node.set.add(key);
                tail.next = node;
                node.prev = tail;
                tail = node;
                return tail;
            }
        }

        public Node addFirst(String key) {
            if (head == null) {
                return add(key, 1);
            } else {
                if (head.val > 1) {
                    var node = new Node(1);
                    node.set.add(key);
                    head.prev = node;
                    node.next = head;
                    head = node;
                    return head;
                } else {
                    head.set.add(key);
                    return head;
                }
            }
        }

        public Node insertNext(String key, Node node) {
            if (node.next == null) {

                if (node.set.size() == 1 && node == head) {
                    head.val++;
                    return head;
                } else {
                    node.set.remove(key);
                    return add(key, node.val + 1);
                }

            } else {
                var nextNode = node.next;

                if (nextNode.val == node.val + 1) {
                    nextNode.set.add(key);
                    node.set.remove(key);

                    if (node.set.isEmpty()) {
                        Node prev = node.prev;
                        nextNode.prev = prev;
                        if (prev != null) {
                            prev.next = nextNode;
                        } else {
                            head = nextNode;
                        }
                    }

                    return nextNode;

                } else {

                    var inNode = new Node(node.val + 1);
                    inNode.next = nextNode;
                    inNode.set.add(key);

                    node.set.remove(key);
                    if (node.set.isEmpty()) {
                        Node prev = node.prev;
                        inNode.prev = prev;
                        if (prev != null) {
                            prev.next = inNode;
                        } else {
                            head = inNode;
                        }
                    } else {
                        inNode.prev = node;
                        node.next = inNode;
                    }

                    return inNode;
                }

            }
        }

        public Node remove(String key, Node node) {
            if (node == head) {
                if (node.val == 1) {
                    if (node.set.size() == 1) {
                        head = head.next;
                        head.prev = null;
                    } else {
                        node.set.remove(key);
                    }
                    return null;
                } else {
                    if (node.set.size() == 1) {
                        head.val--;
                        return head;
                    } else {
                        var prev = new Node(node.val - 1);
                        prev.set.add(key);
                        prev.next = head;
                        head.prev = prev;
                        head = prev;
                        return head;
                    }
                }
            } else if (node == tail) {
                if (node.set.size() == 1) {
                    tail = tail.prev;
                    tail.set.add(key);
                    tail.next = null;
                    return tail;
                } else {
                    tail.set.remove(key);
                    tail.prev.set.add(key);
                    return tail.prev;
                }

            } else {
                var prev = node.prev;
                if (node.set.size() == 1) {

                    var next = node.next;
                    prev.set.add(key);
                    prev.next = next;
                    next.prev = prev;

                } else {
                    prev.set.add(key);
                    node.set.remove(key);
                }

                return prev;
            }
        }

    }

    static class Node {
        int val;
        Node next;
        Node prev;
        Set<String> set = new LinkedHashSet<>();

        Node(int val) {
            this.val = val;
        }

    }

    /** Initialize your data structure here. */
    public AllInOneDataStructure() {
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        var node = map.get(key);
        if (node == null) {
            map.put(key, list.addFirst(key));
        } else {
            map.put(key, list.insertNext(key, node));
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        var node = map.get(key);
        if (node == null) return;

        map.put(key, list.remove(key, node));

    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return list.tail == null ? "" : list.tail.set.iterator().next();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return list.head == null ? "" : list.head.set.iterator().next();
    }

    public static void main(String[] args) {
//        AllInOneDataStructure all = new AllInOneDataStructure();
//        all.inc("a");
//        all.inc("a");
//        all.inc("b");
//        System.out.println(all.getMaxKey());
//        System.out.println(all.getMinKey());

//        AllInOneDataStructure all = new AllInOneDataStructure();
//        all.inc("a");
//        all.inc("b");
//        all.inc("b");
//        all.inc("b");
//        all.inc("b");
//        all.dec("b");
//        all.dec("b");
//        System.out.println(all.getMaxKey());
//        System.out.println(all.getMinKey());

//        AllInOneDataStructure all2 = new AllInOneDataStructure();
//        all2.inc("a");
//        all2.inc("b");
//        all2.inc("b");
//        all2.inc("c");
//        all2.inc("c");
//        all2.inc("c");
//        all2.dec("b");
//        all2.dec("b");
//        all2.dec("a");
//        System.out.println(all2.getMaxKey());
//        System.out.println(all2.getMinKey());

//        AllInOneDataStructure all3 = new AllInOneDataStructure();
//        all3.inc("a");
//        all3.inc("b");
//        all3.inc("c");
//        all3.inc("d");
//
//        all3.inc("a");
//        all3.inc("b");
//        all3.inc("c");
//        all3.inc("d");
//
//        all3.inc("c");
//        all3.inc("d");
//        all3.inc("d");
//        all3.inc("a");
//        System.out.println(all3.getMinKey());

        AllInOneDataStructure all3 = new AllInOneDataStructure();
        all3.inc("a");
        all3.inc("a");
        all3.inc("b");
        all3.inc("b");

        all3.inc("a");
        all3.dec("b");

        System.out.println(all3.getMaxKey());
        System.out.println(all3.getMinKey());

    }
}

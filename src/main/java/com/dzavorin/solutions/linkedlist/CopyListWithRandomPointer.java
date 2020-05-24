package com.dzavorin.solutions.linkedlist;

public class CopyListWithRandomPointer {

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node node = head;

        while (node != null) {
            Node tmp = new Node(node.val);
            Node next = node.next;
            node.next = tmp;
            tmp.next = next;
            node = next;
        }

        node = head;
        while (node != null) {
            Node rand = node.random;
            Node inter = node.next;
            if (rand != null) {
                inter.random = rand.next;
            }
            node = inter.next;
        }

        node = head;
        Node newHead = head.next;
        Node tmp = newHead;
        node.next = node.next.next;
        node = node.next;
        while (node != null) {

            tmp.next = node.next;
            tmp = tmp.next;
            node.next = tmp.next;
            node = node.next;
        }

        return newHead;

    }
}

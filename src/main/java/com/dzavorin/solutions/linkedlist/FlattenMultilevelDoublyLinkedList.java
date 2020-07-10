package com.dzavorin.solutions.linkedlist;

public class FlattenMultilevelDoublyLinkedList {

    public Node flatten(Node head) {

        flattenL(head);

        return head;
    }

    public Node flattenL(Node head) {
        if (head == null) {
            return null;
        }
        Node node = head;

        while (true) {
            Node next = node.next;

            if (node.child != null) {
                Node child = node.child;
                node.child = null;
                child.prev = node;
                node.next = child;

                Node lastChild = flattenL(child);
                lastChild.next = next;
                if (next == null) {
                    node = lastChild;
                    break;
                } else {
                    next.prev = lastChild;
                }
            }
            if (next == null) {
                break;
            } else {
                node = next;
            }

        }

        return node;
    }

    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }
}

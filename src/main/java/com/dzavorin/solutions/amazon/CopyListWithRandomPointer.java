package com.dzavorin.solutions.amazon;

import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {

    class Node {
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

        Node cur = head;
        Node newHead = new Node(-1);
        Node newCur = newHead;

        Map<Node, Node> nodes = new HashMap<>();

        while (cur != null) {
            newCur.next = new Node(cur.val);
            newCur = newCur.next;
            newCur.random = cur.random;
            nodes.put(cur, newCur);
            cur = cur.next;
        }
        newCur = newHead.next;
        while (newCur != null) {
            Node rand = nodes.get(newCur.random);
            if (rand != null) {
                newCur.random = rand;
            }
            newCur = newCur.next;
        }

        return newHead.next;
    }

}

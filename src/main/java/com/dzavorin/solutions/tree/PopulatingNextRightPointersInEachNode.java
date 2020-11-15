package com.dzavorin.solutions.tree;

import java.util.Iterator;
import java.util.LinkedList;

public class PopulatingNextRightPointersInEachNode {

    ///// dfs

    public Node connect(Node root) {

        if (root == null || root.left == null) {
            return root;
        }

        root.left.next = root.right;
        if (root.next != null) {
            root.right.next = root.next.left;
        }

        connect(root.left);
        connect(root.right);

        return root;
    }

    ///////// bfs

    public Node connect2(Node root) {
        if (root == null) return null;

        LinkedList<Node> list = new LinkedList<>();
        list.add(root);

        while (!list.isEmpty()) {
            LinkedList<Node> nextList = new LinkedList<>();

            while (!list.isEmpty()) {
                Node node = list.removeFirst();
                //System.out.println(node.val);
                if (node.left != null && node.right != null) {
                    nextList.add(node.left);
                    nextList.add(node.right);
                }
            }

            Iterator<Node> it = nextList.iterator();

            Node prev = null;

            while (it.hasNext()) {
                Node l = it.next();
                if (prev != null) {
                    prev.next = l;
                }
                if (it.hasNext()) {
                    Node r = it.next();
                    l.next = r;
                    prev = r;
                }

            }

            list = nextList;
        }

        return root;
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}

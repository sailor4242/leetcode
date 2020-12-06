package com.dzavorin.solutions.tree;

import static com.dzavorin.solutions.tree.PopulatingNextRightPointersInEachNode.*;

public class PopulatingNextRightPointersInEachNode2 {

    public Node connect(Node node) {
        if (node == null) return null;

        if (node.left != null) {
            node.left.next = node.right != null ? node.right : getNext(node.next);
        }

        if (node.right != null) {
            node.right.next = getNext(node.next);
        }

        // go right first so we have those 'next' connections ready, when we go left
        connect(node.right);
        connect(node.left);

        return node;
    }

    private Node getNext(Node node) {
        if (node == null) return null;
        if (node.left != null) return node.left;
        if (node.right != null) return node.right;
        return getNext(node.next);
    }

}

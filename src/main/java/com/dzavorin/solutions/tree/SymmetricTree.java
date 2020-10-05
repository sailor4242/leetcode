package com.dzavorin.solutions.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import static com.dzavorin.solutions.tree.BinaryTreeMaximumPathSum.*;

public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return false;
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);

        while (!list.isEmpty()) {
            LinkedList<TreeNode> nextList = new LinkedList<>();
            while (!list.isEmpty()) {
                TreeNode node = list.poll();
                if (node != null) {
                    nextList.add(node.left);
                    nextList.add(node.right);
                }
            }

            Iterator<TreeNode> it1 = nextList.iterator();
            Iterator<TreeNode> it2 = nextList.descendingIterator();
            int n = nextList.size() / 2;
            while (it1.hasNext() && it2.hasNext() && n >= 0) {
                TreeNode l = it1.next();
                TreeNode r = it2.next();
                if (((l == null || r == null) && l != r)
                        || (l != null && r != null && l.val != r.val)) {
                    return false;
                }
                n--;
            }

            list = nextList;
        }

        return true;
    }

    // rec

    public boolean isSymmetric2(TreeNode root) {
        return isMirror(root, root);
    }

    public boolean isMirror(TreeNode nodeOne, TreeNode nodeTwo) {
        if (nodeOne == null && nodeTwo == null) return true;
        if (nodeOne == null || nodeTwo == null) return false;
        return (nodeOne.val == nodeTwo.val)
                && isMirror(nodeOne.right, nodeTwo.left)
                && isMirror(nodeOne.left, nodeTwo.right);
    }

    // it
    public boolean isSymmetric3(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode nodeOne = q.poll();
            TreeNode nodeTwo = q.poll();
            if (nodeOne == null && nodeTwo == null) continue;
            if (nodeOne == null || nodeTwo == null) return false;
            if (nodeOne.val != nodeTwo.val) return false;
            q.add(nodeOne.left);
            q.add(nodeTwo.right);
            q.add(nodeOne.right);
            q.add(nodeTwo.left);
        }
        return true;
    }

}

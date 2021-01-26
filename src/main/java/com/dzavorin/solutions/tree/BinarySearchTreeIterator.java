package com.dzavorin.solutions.tree;

import com.dzavorin.solutions.tree.BinaryTreeMaximumPathSum.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinarySearchTreeIterator {

    LinkedList<TreeNode> stack;

    // O(height) space
    public BinarySearchTreeIterator(TreeNode root) {
        stack = new LinkedList<>();
        leftMostInorder(root);
    }

    public void leftMostInorder(TreeNode root) {
        while(root!=null) {
            stack.add(root);
            root=root.left;
        }
    }

    public int next() {
        TreeNode node = stack.removeLast();
        if(node.right!=null)
            leftMostInorder(node.right);
        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }


    //////

    // O(n) space
    static class Solution2 {

        List<Integer> list = new ArrayList<>();

        int i = 0;

        public Solution2(TreeNode root) {
            fill(root);
        }

        public int next() {
            return list.get(i++);
        }

        public boolean hasNext() {
            return i < list.size();
        }

        void fill(TreeNode root) {
            if (root == null) return;

            fill(root.left);
            list.add(root.val);
            fill(root.right);
        }

    }

}

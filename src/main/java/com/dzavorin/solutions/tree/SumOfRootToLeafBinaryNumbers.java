package com.dzavorin.solutions.tree;

import java.util.ArrayDeque;
import java.util.Deque;

import static com.dzavorin.solutions.tree.BinaryTreeMaximumPathSum.*;
import static com.dzavorin.solutions.tree.MaximumWidthOfBinaryTree.*;

public class SumOfRootToLeafBinaryNumbers {

    int rootToLeaf = 0;

    public void preorder(TreeNode node, int currNumber) {
        if (node != null) {
            currNumber = (currNumber << 1) | node.val;
            if (node.left == null && node.right == null) {
                rootToLeaf += currNumber;
            }
            preorder(node.left, currNumber);
            preorder(node.right, currNumber);
        }
    }

    public int sumRootToLeaf3(TreeNode root) {
        preorder(root, 0);
        return rootToLeaf;
    }

    // sb
    int res = 0;
    public int sumRootToLeaf(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        sumRootToLeaf(root, sb);
        return res;
    }

    private void sumRootToLeaf(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.val);

        if (root.left == null && root.right == null) {
            res += Integer.parseInt(sb.toString(), 2);
        }
        sumRootToLeaf(root.left, sb);
        sumRootToLeaf(root.right, sb);
        sb.deleteCharAt(sb.length() - 1);
    }

    //stack
    public int sumRootToLeaf2(TreeNode root) {
        int rootToLeaf = 0, currNumber = 0;
        Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque<>();
        stack.push(new Pair<>(root, 0));

        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> p = stack.pop();
            root = p.getKey();
            currNumber = p.getValue();

            if (root != null) {
                currNumber = (currNumber << 1) | root.val;
                // if it's a leaf, update root-to-leaf sum
                if (root.left == null && root.right == null) {
                    rootToLeaf += currNumber;
                } else {
                    stack.push(new Pair<>(root.right, currNumber));
                    stack.push(new Pair<>(root.left, currNumber));
                }
            }
        }
        return rootToLeaf;
    }
}

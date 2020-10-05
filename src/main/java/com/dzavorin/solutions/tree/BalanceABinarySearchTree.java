package com.dzavorin.solutions.tree;

import java.util.ArrayList;
import java.util.List;

import static com.dzavorin.solutions.tree.BinaryTreeMaximumPathSum.*;

public class BalanceABinarySearchTree {

    public TreeNode balanceBST(TreeNode root) {
        if (root == null) return null;

        List<Integer> list = new ArrayList<>();

        inOrder(root, list);

        return construct(0, list.size() - 1, list);
    }


    public void inOrder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        inOrder(node.left, list);
        list.add(node.val);
        inOrder(node.right, list);
    }

    public TreeNode construct(int lo, int hi, List<Integer> list) {
        if (lo > hi) return null;

        int mid = lo + (hi - lo) / 2;

        TreeNode node = new TreeNode(list.get(mid));

        node.left = construct(lo, mid - 1, list);
        node.right = construct(mid + 1, hi, list);

        return node;
    }

}

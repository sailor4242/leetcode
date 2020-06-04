package com.dzavorin.solutions.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class BinaryTreeZigZagOrderTraversal {

    public List<List<Integer>> zigzagLevelOrder(BinaryTreeMaximumPathSum.TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        LinkedList<BinaryTreeMaximumPathSum.TreeNode> list = new LinkedList<>();

        list.add(root);

        boolean reverse = false;

        while (!list.isEmpty()) {

            LinkedList<BinaryTreeMaximumPathSum.TreeNode> nextList = new LinkedList<>();

            list.forEach(l -> {
                if (l.left != null) {
                    nextList.add(l.left);
                }
                if (l.right != null) {
                    nextList.add(l.right);
                }
            });

            List<Integer> resl = list.stream().map(l -> l.val).collect(Collectors.toList());
            if (!reverse) {
                res.add(resl);
                reverse = true;
            } else {
                Collections.reverse(resl);
                res.add(resl);
                reverse = false;
            }

            list = nextList;
        }
        return res;
    }

}

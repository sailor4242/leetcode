package com.dzavorin.solutions.tree;

import java.util.LinkedList;

import static com.dzavorin.solutions.tree.BinaryTreeMaximumPathSum.*;

public class DeepestLeavesSum {

    public int deepestLeavesSum(TreeNode root) {

        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);

        int res = 0;
        while (!list.isEmpty()) {

            LinkedList<TreeNode> nextList = new LinkedList<>();
            res = 0;
            while (!list.isEmpty()) {

                TreeNode node = list.poll();
                res += node.val;
                if (node.left != null) nextList.add(node.left);
                if (node.right != null) nextList.add(node.right);

            }

            list = nextList;

        }
        return res;

    }

}

package com.dzavorin.solutions.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(KthSmallestInBST.TreeNode root) {
        LinkedList<KthSmallestInBST.TreeNode> list = new LinkedList<>();
        if (root != null) {
            list.add(root);
        }
        List<List<Integer>> res = new ArrayList<>();
        while (!list.isEmpty()) {

            LinkedList<KthSmallestInBST.TreeNode> curList = new LinkedList<>();
            List<Integer> curRes = new ArrayList<>();

            while (!list.isEmpty()) {
                KthSmallestInBST.TreeNode cur = list.removeFirst();
                curRes.add(cur.val);

                if (cur.left != null) {
                    curList.add(cur.left);
                }
                if (cur.right != null) {
                    curList.add(cur.right);
                }

            }

            res.add(curRes);
            list = curList;
        }
        return res;
    }

}

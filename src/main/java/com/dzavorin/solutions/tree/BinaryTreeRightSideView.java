package com.dzavorin.solutions.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.dzavorin.solutions.tree.BinaryTreeMaximumPathSum.*;

public class BinaryTreeRightSideView {


    /// rec

    List<Integer> list = new ArrayList<>();
    int j = 0;

    public List<Integer> rightSideView(KthSmallestInBST.TreeNode root) {
        rightSideView(root, 1);
        return list;
    }

    public void rightSideView(KthSmallestInBST.TreeNode root, int i) {

        if (root == null) {
            return;
        }
        if (i > j) {
            list.add(root.val);
            j++;
        }
        rightSideView(root.right, i + 1);
        rightSideView(root.left, i + 1);

    }

    /// bfs

    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        res.add(root.val);

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            LinkedList<TreeNode> nextQueue = new LinkedList<>();

            while (!queue.isEmpty()) {
                TreeNode cur = queue.removeFirst();
                if (cur.left != null) nextQueue.add(cur.left);
                if (cur.right != null) nextQueue.add(cur.right);
            }
            if (!nextQueue.isEmpty()) {
                res.add(nextQueue.getLast().val);
            }
            queue = nextQueue;
        }

        return res;
    }

}

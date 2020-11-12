package com.dzavorin.solutions.tree;

import java.util.ArrayList;
import java.util.List;

import static com.dzavorin.solutions.tree.BinaryTreeMaximumPathSum.*;

public class AllNodesDistanceKInBinaryTree {

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList();
        findTarget(res, root, target, K);
        return res;
    }

    public int findTarget(List<Integer> res, TreeNode root, TreeNode target, int K) {
        if (root == null)
            return -1;

        if (root == target) {
            findNodesFromK(res, root, K);
            return 1;
        }

        int left = findTarget(res, root.left, target, K);
        int right = findTarget(res, root.right, target, K);

        if (left == K || right == K) {
            res.add(root.val);

        } else if (left != -1) {
            findNodesFromK(res, root.right, K - left - 1);
            return left + 1;
        } else if (right != -1) {
            findNodesFromK(res, root.left, K - right - 1);
            return right + 1;
        }
        return -1;
    }

    public void findNodesFromK(List<Integer> res, TreeNode root, int K) {
        if (root == null)
            return;

        if (K == 0) {
            res.add(root.val);
            return;
        }
        findNodesFromK(res, root.left, K - 1);
        findNodesFromK(res, root.right, K - 1);
    }

}

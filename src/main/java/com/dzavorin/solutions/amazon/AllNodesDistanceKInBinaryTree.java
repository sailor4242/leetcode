package com.dzavorin.solutions.amazon;

import com.dzavorin.solutions.tree.SumOfNodesWithEvenValuedGrandParent;

import java.util.ArrayList;
import java.util.List;

import static com.dzavorin.solutions.tree.SumOfNodesWithEvenValuedGrandParent.*;

public class AllNodesDistanceKInBinaryTree {

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        if (root == null) return List.of();
        if (K == 0) return List.of(target.val);
        List<Integer> res = new ArrayList<>();
        distanceK(root, target, K, res);
        return res;
    }

    public int distanceK(TreeNode root, TreeNode target, int k, List<Integer> res) {
        if (root == null) return -1;
        if (k == 0) {
            res.add(root.val);
            return -1;
        }

        if (root == target) {
            distanceK(root.left, root.left, k - 1, res);
            distanceK(root.right, root.right, k - 1, res);
            return k - 1;
        } else {
            int l = distanceK(root.left, target, k, res);
            int r = distanceK(root.right, target, k, res);

            if (l == 0) {
                res.add(root.val);
            } else if (l > 0) {
                distanceK(root.right, root.right, l - 1, res);
                return l - 1;
            }

            if (r == 0) {
                res.add(root.val);
            } else if (r > 0) {
                distanceK(root.left, root.left, r - 1, res);
                return r - 1;
            }

        }
        return -1;
    }

}

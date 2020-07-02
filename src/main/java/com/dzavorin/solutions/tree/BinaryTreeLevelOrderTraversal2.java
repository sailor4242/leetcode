package com.dzavorin.solutions.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryTreeLevelOrderTraversal2 {

    public List<List<Integer>> levelOrderBottom(KthSmallestInBST.TreeNode root) {

        Map<Integer, List<Integer>> map = new HashMap<>();

        levelOrderBottom(root, 0, map);

        List<List<Integer>> res = new ArrayList<>();

        for (int i = map.size() - 1; i >= 0; i--) {
            res.add(map.get(i));
        }

        return res;
    }

    public void levelOrderBottom(KthSmallestInBST.TreeNode root, int i, Map<Integer, List<Integer>> map) {

        if (root == null) {
            return;
        }

        if (map.containsKey(i)) {
            map.get(i).add(root.val);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(root.val);
            map.put(i, list);
        }
        levelOrderBottom(root.left, i + 1, map);
        levelOrderBottom(root.right, i + 1, map);
    }

}

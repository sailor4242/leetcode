package com.dzavorin.solutions.dfs;

import com.dzavorin.solutions.tree.SumOfNodesWithEvenValuedGrandParent.TreeNode;

import java.util.HashMap;

public class PathSum3 {

    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return pathSum(root, 0, sum, map);
    }

    private int pathSum(TreeNode root, int cSum, int sum, HashMap<Integer, Integer> map) {
        int count = 0;
        if (root == null) {
            return count;
        }

        cSum += root.val;

        if (map.containsKey(cSum - sum)) {
            count += map.get(cSum - sum);
        }

        map.put(cSum, map.getOrDefault(cSum, 0) + 1);

        count += pathSum(root.left, cSum, sum, map) +
                 pathSum(root.right, cSum, sum, map);

        map.put(cSum, map.get(cSum) - 1);

        return count;
    }

}

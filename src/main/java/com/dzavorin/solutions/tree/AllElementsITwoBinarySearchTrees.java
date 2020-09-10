package com.dzavorin.solutions.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.dzavorin.solutions.tree.BinaryTreeMaximumPathSum.*;

public class AllElementsITwoBinarySearchTrees {

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> res1 = new ArrayList<>();
        getAllElements(root1, res1);

        List<Integer> res2 = new ArrayList<>();
        getAllElements(root2, res2);

        List<Integer> res = new LinkedList<>();

        int i = 0;
        int j = 0;

        while (i < res1.size() || j < res2.size()) {

            if (i >= res1.size()) {
                res.add(res2.get(j));
                j++;
            } else if (j >= res2.size()) {
                res.add(res1.get(i));
                i++;
            } else if (res1.get(i) < res2.get(j)) {
                res.add(res1.get(i));
                i++;
            } else {
                res.add(res2.get(j));
                j++;
            }
        }

        return res;
    }

    private void getAllElements(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        getAllElements(node.left, res);
        res.add(node.val);
        getAllElements(node.right, res);
    }

}

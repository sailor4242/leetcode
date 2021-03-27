package com.dzavorin.solutions.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.dzavorin.solutions.tree.SumOfNodesWithEvenValuedGrandParent.*;

public class AverageOfLevelsInABinaryTree {

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        while (!list.isEmpty()) {
            LinkedList<TreeNode> nextList = new LinkedList<>();
            double sum = 0d;
            int count = 0;
            while (!list.isEmpty()) {
                TreeNode node = list.removeFirst();
                sum += node.val;
                count++;
                if (node.left != null) nextList.add(node.left);
                if (node.right != null) nextList.add(node.right);
            }
            res.add(sum / count);
            list = nextList;
        }
        return res;
    }

}

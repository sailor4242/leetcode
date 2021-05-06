package com.dzavorin.solutions.amazon;

import static com.dzavorin.solutions.tree.SumOfNodesWithEvenValuedGrandParent.*;

public class MaximumAverageSubtree {

    double avg = 0d;

    public double maximumAverageSubtree(TreeNode root) {
        if (root == null) return 0d;
        helper(root);
        return avg;
    }

    public double[] helper(TreeNode root) {
        if (root == null) return new double[]{0d, 0d};

        double[] left = helper(root.left);
        double[] right = helper(root.right);
        double sum = left[0] + right[0] + root.val;
        double count = left[1] + right[1] + 1;
        avg = Math.max(avg, sum / count);

        return new double[]{sum, count};
    }

}

package com.dzavorin.solutions.tree;

public class SumRootToLeafNUmbers {

    public int sumNumbers(KthSmallestInBST.TreeNode root) {
        return sumNumbersRec(root, 0);
    }

    public int sumNumbersRec(KthSmallestInBST.TreeNode root, int sum) {
        if (root == null) return 0;
        sum = sum * 10 + root.val;
        if (root.left == null && root.right == null) return sum;
        return sumNumbersRec(root.left, sum) + sumNumbersRec(root.right, sum);
    }

}

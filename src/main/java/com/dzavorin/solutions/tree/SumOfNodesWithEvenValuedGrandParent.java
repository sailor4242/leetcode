package com.dzavorin.solutions.tree;

public class SumOfNodesWithEvenValuedGrandParent {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int sumEvenGrandparent(TreeNode root) {
        if (root == null) return 0;
        return sumGrandParents(root.left, root.val % 2 == 0)
                + sumGrandParents(root.right, root.val % 2 == 0);
    }

    public int sumGrandParents(TreeNode node, boolean grany) {
        if (node == null) {
            return 0;
        }
        int res = 0;

        if (grany) {
            res += (node.left == null ? 0 : node.left.val) +
                    (node.right == null ? 0 : node.right.val);
        }

        return res + sumGrandParents(node.left, node.val % 2 == 0)
                + sumGrandParents(node.right, node.val % 2 == 0);

    }

}

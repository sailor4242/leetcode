package com.dzavorin.solutions.tree;

import java.util.*;

import static com.dzavorin.solutions.tree.BinaryTreeMaximumPathSum.*;

public class AllPossibleFullBinaryTrees {


    //////  builds a graph with multiple roots, not distinct trees

    private Map<Integer, List<TreeNode>> memo = new HashMap<>();

    public List<TreeNode> allPossibleFBT(int N) {
        if (N % 2 == 0)
            return new ArrayList<>(); //impossible to build a full tree with even number of nodes
        if (N == 1)
            return Arrays.asList(new TreeNode(0));
        if (memo.containsKey(N))
            return memo.get(N);

        List<TreeNode> res = new ArrayList<>();

        for (int i = 1; i < N; i++) {
            List<TreeNode> leftRes = allPossibleFBT(i);
            List<TreeNode> rightRes = allPossibleFBT(N - i - 1); // exclude the root, subtract 1

            for (TreeNode leftRoot : leftRes) {
                for (TreeNode rightRoot : rightRes) {
                    res.add(new TreeNode(0, leftRoot, rightRoot));
                }
            }
        }

        memo.put(N, res);
        return res;
    }


    ///////// better approach with cloning


    private TreeNode clone(TreeNode root) {
        if (root == null) return null;
        TreeNode clone = new TreeNode(root.val);
        clone.left = clone(root.left);
        clone.right = clone(root.right);
        return clone;
    }

    // recursive
    public List<TreeNode> allPossibleFBT2(int N) {
        List<TreeNode> res = new ArrayList<>();
        if (1 == N) {
            res.add(new TreeNode(0));
        } else if (N % 2 != 0) {
            for (int i = 2; i <= N; i += 2) {
                List<TreeNode> leftRes = allPossibleFBT(i - 1);
                List<TreeNode> rightRes = allPossibleFBT(N - i);

                for (TreeNode left : leftRes) {
                    for (TreeNode right : rightRes) {
                        TreeNode tree = new TreeNode(0, clone(left), clone(right));
                        res.add(tree);
                    }
                }
            }
        }
        return res;
    }

    // dp
    public List<TreeNode> allPossibleFBT4(int N) {
        List<TreeNode>[] dp = new ArrayList[N + 1];
        if (N % 2 == 0) return new ArrayList<>();
        dp[1] = new ArrayList<>();
        dp[1].add(new TreeNode(0));

        // numNode = number of nodes in tree
        // i.e. dp[3] = how many full binary tree can we make with 3 nodes with value 0 ?
        for (int numNode = 3; numNode <= N; numNode += 2) {
            dp[numNode] = new ArrayList<>();
            for (int numLeftNode = 1; numLeftNode < numNode; numLeftNode += 2) {
                for (TreeNode left : dp[numLeftNode]) {
                    for (TreeNode right : dp[numNode - 1 - numLeftNode]) {
                        // need to clone both left and right as all nodes will have value of 0
                        // (unlike problem #96) and this prevents children (non-root) nodes
                        // from being referenced by multiple parents
                        TreeNode root = new TreeNode(0);
                        root.left = clone(left);
                        root.right = clone(right);
                        dp[numNode].add(root);
                    }
                }
            }
        }
        return dp[N];
    }


    public static void main(String[] args) {
        System.out.println(new AllPossibleFullBinaryTrees().allPossibleFBT(7));
    }
}

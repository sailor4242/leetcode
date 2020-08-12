package com.dzavorin.solutions.tree;

import com.dzavorin.solutions.tree.KthSmallestInBST.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return helper(postorder.length - 1, 0, inorder.length, inorder, postorder);
    }

    private TreeNode helper(int postIdx, int inStart, int inEnd, int[] inorder, int[] postorder) {
        if (postIdx < 0 || inStart >= inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postIdx]);
        int inIdx = 0;
        for (int i = inStart; i < inEnd; i++) {
            if (inorder[i] == root.val) {
                inIdx = i;
                break;
            }
        }
        root.right = helper(postIdx - 1, inIdx + 1, inEnd, inorder, postorder);
        root.left = helper(postIdx - (inEnd - inIdx), inStart, inIdx, inorder, postorder);
        return root;
    }

    // if no duplicates we can easy use map to store all inorder values to indexes

    public TreeNode buildTreeUtil(int[] post, int idx, int lo, int hi, Map<Integer, Integer> map) {

        if (lo > hi) return null;
        int rootVal = post[idx];
        TreeNode root = new TreeNode(rootVal);

        int rootIdx = map.get(rootVal);
        idx--;

        root.right = buildTreeUtil(post, idx, rootIdx + 1, hi, map);
        root.left = buildTreeUtil(post, idx - (hi - rootIdx), lo, rootIdx - 1, map);
        return root;
    }

    public TreeNode buildTree2(int[] in, int[] post) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++)
            map.put(in[i], i);

        return buildTreeUtil(post, post.length - 1, 0, in.length - 1, map);

    }

}

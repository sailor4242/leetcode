package com.dzavorin.solutions.tree;

public class KthAncestorOfATreeNode {

    int[][] tree;
    int[] depth;
    int log;

    // Binary lifting ; https://www.youtube.com/watch?v=oib-XsjFa-M
    public KthAncestorOfATreeNode(int n, int[] parent) {
        this.depth = new int[n];
        this.log = (int) (Math.log(n) / Math.log(2)) + 1;
        this.tree = new int[n][log];
        parent[0] = 0;
        for (int i = 0; i < n; i++) {
            tree[i][0] = parent[i];
            if (i != 0) {
                depth[i] = depth[parent[i]] + 1;
            }
            for (int j = 1; j < log; j++) {
                tree[i][j] = tree[ tree[i][j - 1] ][j - 1];
            }
        }
        // System.out.println(Arrays.deepToString(tree));
    }

    public int getKthAncestor(int node, int k) {
        if (depth[node] < k) return -1;
        for (int i = 0; i < log; i++) {
            if ((k & (1 << i)) != 0) {
                node = tree[node][i];
            }
        }
        return node;
    }

}

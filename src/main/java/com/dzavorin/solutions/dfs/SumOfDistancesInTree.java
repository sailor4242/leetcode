package com.dzavorin.solutions.dfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SumOfDistancesInTree {

    static class Node {
        int key;
        List<Node> children = new LinkedList<>();
        Node parent = null;
        int cnt = 1;
        int sum = 0;

        public Node(int key) {
            this.key = key;
        }

        public String toString() {
            return String.format("{cnt=%d, sum=%d}", cnt, sum);
        }
    }

    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        if (N == 1) return new int[]{0};
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] e : edges) {
            map.putIfAbsent(e[0], new LinkedList<>());
            map.putIfAbsent(e[1], new LinkedList<>());
            map.get(e[0]).add(e[1]);
            map.get(e[1]).add(e[0]);
        }

        boolean[] used = new boolean[N];
        used[0] = true;
        Node root = build(map, 0, used);
        int[] res = new int[N];
        update(root, res);
        return res;
    }

    private void update(Node node, int[] res) {
        res[node.key] = node.sum;
        if (node.parent != null) {
            Node p = node.parent;
            int otherside = res[p.key] - node.sum - node.cnt;
            res[node.key] = node.sum + otherside + res.length - node.cnt;
        }
        for (Node child : node.children) {
            update(child, res);
        }
    }

    private Node build(Map<Integer, List<Integer>> edges, int curr, boolean[] used) {
        Node n = new Node(curr);
        for (int next : edges.get(curr)) {
            if (!used[next]) {
                used[next] = true;
                Node nnode = build(edges, next, used);
                nnode.parent = n;
                n.cnt += nnode.cnt;
                n.children.add(nnode);
                n.sum += (nnode.sum + nnode.cnt);
            }
        }
        return n;
    }

}

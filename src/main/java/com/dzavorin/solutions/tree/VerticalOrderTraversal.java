package com.dzavorin.solutions.tree;

import com.dzavorin.solutions.tree.KthSmallestInBST.TreeNode;
import com.dzavorin.solutions.tree.MaximumWidthOfBinaryTree.Pair;

import java.util.*;

public class VerticalOrderTraversal {
    Map<Integer, PriorityQueue<Pair<Integer, Integer>>> map = new HashMap<>();
    Comparator<Pair<Integer, Integer>> comp = (p1, p2) -> {
        if (p1.getKey() == p2.getKey()) {
            return p1.getValue() - p2.getValue();
        } else {
            return p1.getKey() - p2.getKey();
        }
    };

    int min = 0;

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        verticalTraversal(root, 0, 0);

        for (int i = min; i < map.size(); i++) {
            if (map.get(i) != null) {
                List<Integer> cur = new ArrayList<>();
                PriorityQueue<Pair<Integer, Integer>> temp = map.get(i);
                while (temp.size() > 0) {
                    cur.add(temp.poll().getValue());
                }
                res.add(cur);
            }

        }
        return res;
    }

    public void verticalTraversal(TreeNode node, int x, int y) {
        if (node == null) {
            return;
        }

        min = Math.min(x, min);

        if (map.containsKey(x)) {
            map.get(x).add(new Pair<>(y, node.val));
        } else {
            PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(comp);
            pq.add(new Pair<>(y, node.val));
            map.put(x, pq);
        }

        verticalTraversal(node.left, x - 1, y + 1);
        verticalTraversal(node.right, x + 1, y + 1);
    }
}

package com.dzavorin.solutions.bfs;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class CloneGraph {

    public Node cloneGraph(Node node) {
        if (node == null) return null;

        LinkedHashMap<Integer, Node> created = new LinkedHashMap<>();

        LinkedList<Node> list = new LinkedList<>();

        list.add(node);

        while (!list.isEmpty()) {
            Node cur = list.poll();
            Node copy = created.get(cur.val);
            if (copy == null) {
                copy = new Node(cur.val);
                created.put(cur.val, copy);
            }

            List<Node> neighbors = cur.neighbors;
            for (Node n : neighbors) {
                Node copyN = created.get(n.val);
                if (copyN == null) {
                    copyN = new Node(n.val);
                    created.put(n.val, copyN);
                    list.add(n);
                }
                copy.neighbors.add(copyN);
            }
        }

        return created.entrySet().iterator().next().getValue();
    }

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

}

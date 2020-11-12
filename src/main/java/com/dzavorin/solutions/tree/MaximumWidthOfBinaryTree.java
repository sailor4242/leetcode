package com.dzavorin.solutions.tree;

import java.util.LinkedList;
import java.util.Objects;

import static com.dzavorin.solutions.tree.KthSmallestInBST.*;

public class MaximumWidthOfBinaryTree {

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int res = 1;
        LinkedList<Pair<Integer, TreeNode>> list = new LinkedList<Pair<Integer, TreeNode>>();
        list.add(new Pair<>(0, root));

        while (!list.isEmpty()) {

            LinkedList<Pair<Integer, TreeNode>> nextList = new LinkedList<Pair<Integer, TreeNode>>();

            int min = list.getFirst().getKey();
            int max = list.getLast().getKey();

            while (!list.isEmpty()) {
                Pair<Integer, TreeNode> pair = list.removeFirst();
                TreeNode cur = pair.getValue();
                Integer i = pair.getKey();

                if (cur.left != null) {
                    nextList.add(new Pair<>(2*i, cur.left));

                }
                if (cur.right != null) {
                    nextList.add(new Pair<>(2*i + 1, cur.right));
                }
            }

            res = Math.max(res, max - min + 1);
            list = nextList;

        }
        return res;
    }

    static public class Pair<K, V> {
        K key;
        V value;

        public Pair(K k, V v) {
            this.key = k;
            this.value = v;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(key, pair.key) &&
                    Objects.equals(value, pair.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
}

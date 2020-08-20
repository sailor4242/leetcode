package com.dzavorin.solutions.segment_tree;

import java.util.*;

public class MaximumNumberOfEventsThatCanBeAttended {

    public int maxEvents(int[][] events) {

        if (events == null || events.length == 0) {
            return 0;
        }

        List<int[]> eventList = new ArrayList<>();
        int initialDay = Integer.MAX_VALUE;
        int lastDay = Integer.MIN_VALUE;
        for (int[] event : events) {
            initialDay = Math.min(initialDay, event[0]);
            lastDay = Math.max(lastDay, event[1]);
            eventList.add(event);
        }

        Collections.sort(eventList, (ev1, ev2) -> {
            if (ev1[1] == ev2[1]) {
                return Integer.compare(ev1[0], ev2[0]);
            }
            return Integer.compare(ev1[1], ev2[1]);
        });

        int count = 0;

        TreeSet<Integer> set = new TreeSet<>();
        for (int i = initialDay; i <= lastDay; i++) {
            set.add(i);
        }

        for (int[] event : eventList) {
            Integer candidateDay = set.ceiling(event[0]);
            if (candidateDay == null) {
                break;
            }
            if (candidateDay <= event[1]) {
                count++;
                set.remove(candidateDay);
            }
        }

        return count;
    }


    // ----------------------------- Segment tree solution

    public int maxEvents2(int[][] events) {
        if (events == null || events.length == 0) {
            return 0;
        }

        List<int[]> eventList = new ArrayList<>();
        int lastDay = Integer.MIN_VALUE;
        for (int[] event : events) {
            lastDay = Math.max(lastDay, event[1]);
            eventList.add(event);
        }

        Collections.sort(eventList, (a, b) -> {
            if (a[1] == b[1]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });


        SegmentTree sg = new SegmentTree(lastDay);
        int count = 0;
        for (int[] event : eventList) {
            int earliestDay = sg.getRangeQuery(event[0], event[1]);
            if (earliestDay != Integer.MAX_VALUE) {
                count++;
                sg.update(earliestDay);
            }
        }

        return count;
    }

    static class SegmentTree {
        SegmentTreeNode root;

        SegmentTree(int n) {
            root = new SegmentTreeNode(0, n);
        }

        public int getRangeQuery(int start, int end) {
            return getRangeQuery(start, end, root);
        }

        private int getRangeQuery(int start, int end, SegmentTreeNode curr) {
            if (curr.start == start && curr.end == end) {
                return curr.val;
            }

            int nodeStart = curr.start;
            int nodeEnd = curr.end;
            int mid = nodeStart + (nodeEnd - nodeStart) / 2;

            if (mid >= end) {
                return getRangeQuery(start, end, curr.left);
            } else if (mid + 1 <= start) {
                return getRangeQuery(start, end, curr.right);
            } else {
                return Math.min(getRangeQuery(start, mid, curr.left), getRangeQuery(mid + 1, end, curr.right));
            }

        }


        public void update(int point) {
            update(point, root);
        }

        private void update(int point, SegmentTreeNode curr) {
            if (curr.start == curr.end) {
                curr.val = Integer.MAX_VALUE;
                return;
            }

            int nodeStart = curr.start;
            int nodeEnd = curr.end;
            int mid = nodeStart + (nodeEnd - nodeStart) / 2;

            if (point <= mid) {
                update(point, curr.left);
                curr.val = Math.min(curr.left.val, curr.right.val);
            } else {
                update(point, curr.right);
                curr.val = Math.min(curr.left.val, curr.right.val);
            }

        }

        static class SegmentTreeNode {
            SegmentTreeNode left;
            SegmentTreeNode right;
            int val;
            int start;
            int end;

            public SegmentTreeNode(int start, int end) {
                this.start = start;
                this.end = end;

                if (start == end) {
                    val = start;
                } else {
                    int mid = start + (end - start) / 2;
                    this.left = new SegmentTreeNode(start, mid);
                    this.right = new SegmentTreeNode(mid + 1, end);
                    val = start;
                }
            }

        }
    }

    public static void main(String[] args) {
        System.out.println(new MaximumNumberOfEventsThatCanBeAttended().maxEvents(new int[][]{{1, 4}, {4, 4}, {2, 2}, {3, 4}, {1, 1}})); //4
        System.out.println(new MaximumNumberOfEventsThatCanBeAttended().maxEvents(new int[][]{{1, 2}, {2, 2}, {3, 3}, {3, 4}, {3, 4}})); //4
        System.out.println(new MaximumNumberOfEventsThatCanBeAttended().maxEvents(new int[][]{{1, 5}, {1, 5}, {1, 5}, {2, 3}, {2, 3}})); //5
        System.out.println(new MaximumNumberOfEventsThatCanBeAttended().maxEvents(new int[][]{{1, 2}, {1, 2}, {1, 6}, {1, 2}, {1, 2}})); //3
        System.out.println(new MaximumNumberOfEventsThatCanBeAttended().maxEvents(new int[][]{{1, 10}, {2, 2}, {2, 2}, {2, 2}, {2, 2}})); //2
        System.out.println(new MaximumNumberOfEventsThatCanBeAttended().maxEvents(new int[][]{{7, 11}, {7, 11}, {7, 11}, {9, 10}, {9, 11}})); //5
    }
}

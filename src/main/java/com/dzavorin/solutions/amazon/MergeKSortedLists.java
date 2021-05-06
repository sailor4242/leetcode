package com.dzavorin.solutions.amazon;

import com.dzavorin.solutions.linkedlist.OddEvenLinkedList;

import static com.dzavorin.solutions.linkedlist.OddEvenLinkedList.*;

public class MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergeKLists(lists, 0, lists.length - 1);
    }

    public ListNode mergeKLists(ListNode[] lists, int lo, int hi) {
        if (lo == hi) {
            return lists[lo];
        }

        int mid = lo + (hi - lo) / 2;
        if (mid == lo || mid == hi) {
            return mergeTwoLists(lists[lo], lists[hi]);
        } else {
            return mergeTwoLists(
                    mergeKLists(lists, lo, mid),
                    mergeKLists(lists, mid + 1, hi)
            );
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode root = new ListNode();
        ListNode cur = root;
        while (l1 != null && l2 != null) {
            if (l2.val > l1.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        ListNode rest = l1 == null ? l2 : l1;
        while (rest != null) {
            cur.next = rest;
            rest = rest.next;
            cur = cur.next;
        }

        return root.next;
    }

}

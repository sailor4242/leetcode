package com.dzavorin.solutions.linkedlist;

import com.dzavorin.solutions.linkedlist.OddEvenLinkedList;

import static com.dzavorin.solutions.linkedlist.OddEvenLinkedList.*;

public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode root = new ListNode(-1);
        ListNode mover = root;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                mover.next = l1;
                l1 = l1.next;
            } else {
                mover.next = l2;
                l2 = l2.next;
            }
            mover = mover.next;
        }
        if (l1 != null) {
            mover.next = l1;
        }
        if (l2 != null) {
            mover.next = l2;
        }
        return root.next;
    }

}

package com.dzavorin.solutions.amazon;

import com.dzavorin.solutions.linkedlist.OddEvenLinkedList;

import static com.dzavorin.solutions.linkedlist.OddEvenLinkedList.*;

public class MergeTwoSortedLists {

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

package com.dzavorin.solutions.linkedlist;

import static com.dzavorin.solutions.linkedlist.OddEvenLinkedList.*;

public class RemoveDupliacatesFromSortedList2 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(-1);
        ListNode root = dummy;
        ListNode prev = head;
        ListNode cur = head.next;

        boolean shouldSkip = false;
        while (cur != null) {
            if (prev.val != cur.val) {
                if (shouldSkip) {
                    shouldSkip = false;
                } else {
                    dummy.next = new ListNode(prev.val);
                    dummy = dummy.next;
                }
            } else {
                shouldSkip = true;
            }
            prev = prev.next;
            cur = cur.next;
        }

        if (!shouldSkip) {
            dummy.next = new ListNode(prev.val);
        }

        return root.next;
    }


    ///


    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode cur = dummy;

        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int cacheVal = cur.next.val;

                while (cur.next != null && cur.next.val == cacheVal) {
                    cur.next = cur.next.next;
                }

            } else {
                cur = cur.next;
            }
        }

        return dummy.next;
    }
}

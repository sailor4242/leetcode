package com.dzavorin.solutions.linkedlist;

import static com.dzavorin.solutions.linkedlist.OddEvenLinkedList.*;

public class MiddleOfTheLinkedList {

    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode slow = head;
        ListNode fast = head;
        int c = 1;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            c *= 2;
        }

        if (fast.next != null) {
            return slow.next;
        } else {
            return slow;
        }

    }

}

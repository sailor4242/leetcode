package com.dzavorin.solutions.linkedlist;

import static com.dzavorin.solutions.linkedlist.OddEvenLinkedList.*;

public class RemoveLinkedListElements {

    public ListNode removeElements(ListNode head, int val) {
        ListNode node = head;
        ListNode prev = null;

        while (node != null) {

            ListNode next = node.next;

            if (node.val == val) {
                if (prev == null) {
                    head = next;
                    node = next;
                    continue;
                }
                prev.next = next;
            } else {
                prev = node;
            }
            node = next;
        }

        return head;
    }

}

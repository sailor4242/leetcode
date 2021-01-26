package com.dzavorin.solutions.linkedlist;

import static com.dzavorin.solutions.linkedlist.OddEvenLinkedList.*;

public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(-1);
        ListNode cur = root;

        int carry = 0;
        while (l1 != null && l2 != null) {
            int n = l1.val + l2.val + carry;

            if (n < 10) {
                cur.next = new ListNode(n);
                carry = 0;
            } else {
                cur.next = new ListNode(n % 10);
                carry = n / 10;
            }
            l1 = l1.next;
            l2 = l2.next;
            cur = cur.next;
        }

        ListNode nl = l1 == null ? l2 : l1;

        while (nl != null) {
            int n = nl.val + carry;

            if (n < 10) {
                cur.next = new ListNode(n);
                carry = 0;
            } else {
                cur.next = new ListNode(n % 10);
                carry = n / 10;
            }
            nl = nl.next;
            cur = cur.next;
        }

        if (carry != 0) {
            cur.next = new ListNode(carry);
        }

        return root.next;
    }

}

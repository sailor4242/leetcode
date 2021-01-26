package com.dzavorin.solutions.linkedlist;

import static com.dzavorin.solutions.linkedlist.OddEvenLinkedList.*;

public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode cur = head;
        ListNode tmp = null;
        ListNode prev = null;
        while (cur.next != null) {
            tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
        cur.next = prev;
        return cur;
    }

    //rec

    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return prev;
    }

}

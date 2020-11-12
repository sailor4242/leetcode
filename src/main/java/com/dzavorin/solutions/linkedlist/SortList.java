package com.dzavorin.solutions.linkedlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.dzavorin.solutions.linkedlist.OddEvenLinkedList.*;

public class SortList {

    public ListNode sortList2(ListNode head) {
        if (head == null) return null;

        List<Integer> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }

        Collections.sort(list);

        node = new ListNode(list.get(0));
        ListNode head2 = node;
        for (int i = 1; i < list.size(); i++) {
            ListNode next = new ListNode(list.get(i));
            node.next = next;
            node = next;
        }
        return head2;
    }

    // O(1) space bottom up merge sort
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode right = slow.next;
        slow.next = null;
        head = sortList(head);
        right = sortList(right);
        return merge(head, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        if (left.val > right.val) {
            ListNode temp = left;
            left = right;
            right = temp;
        }
        ListNode head = left;
        ListNode prev = left;
        ListNode curr = left.next;

        while (right != null && curr != null) {
            if (curr.val <= right.val) {
                prev = curr;
                curr = curr.next;
            } else {
                prev.next = right;
                ListNode temp = right.next;
                right.next = curr;
                right = temp;
                prev = prev.next;
            }
        }

        if (right != null) prev.next = right;
        return head;
    }

}

package com.dzavorin.solutions.linkedlist;

import com.dzavorin.solutions.linkedlist.OddEvenLinkedList.ListNode;

import java.util.*;

public class ReorderList {

    public void reorderList(ListNode head) {

        List<ListNode> list = new ArrayList<>();

        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }

        node = head;

        for (int i = 0; i < list.size() / 2; i++) {
            ListNode in = list.get(list.size() - 1 - i);

            ListNode next = node.next;

            node.next = in;
            in.next = next;
            node = next;

        }

        if (node != null) {
            node.next = null;
        }

    }

    public void reorderList2(ListNode head) {
        if (head == null) {
            return;
        }

        order(head, head);
    }

    private ListNode order(ListNode head, ListNode tail) {
        var cur = head;
        if (tail.next != null) {
            cur = order(head, tail.next);
            if (cur == null) {
                return null;
            }
        }

        var next = cur.next;
        if (cur == tail || next == tail) {
            tail.next = null;
            return null;
        }
        cur.next = tail;
        tail.next = next;
        return next;
    }

    public void reorderList3(ListNode head) {

        int count = 0;
        Stack<ListNode> st = new Stack<>();
        Queue<ListNode> q = new LinkedList<>();

        while (head != null) {
            count += 1;
            q.add(head);
            st.add(head);
            head = head.next;
        }

        if (count != 0) {
            int i = 2;
            head = q.poll();
            ListNode temp = head;
            while (i <= count) {
                if (i % 2 != 0) {
                    temp.next = q.poll();
                    temp = temp.next;
                } else {
                    temp.next = st.pop();
                    temp = temp.next;
                }

                i++;
            }
            temp.next = null;
        }
    }
}


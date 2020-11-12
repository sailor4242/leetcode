package com.dzavorin.solutions.linkedlist;

import static com.dzavorin.solutions.linkedlist.OddEvenLinkedList.*;

public class ConvertBinaryNumberInALinkedList {

    public int getDecimalValue(ListNode head) {
        return go(head)[0];
    }

    public int[] go(ListNode node) {
        if (node == null) {
            return new int[]{0, -1};
        }
        int[] res = go(node.next);

        res[1] += 1;
        res[0] += node.val == 1 ? (int) Math.pow(2, res[1]) : 0;

        return res;
    }

    ////////////


    public int getDecimalValue2(ListNode head) {
        int num = head.val;
        while (head.next != null) {
            num = num * 2 + head.next.val;
            head = head.next;
        }
        return num;
    }

    ////////////

    public int getDecimalValue3(ListNode head) {
        int num = head.val;
        while (head.next != null) {
            num = (num << 1) | head.next.val;
            head = head.next;
        }
        return num;
    }

}

package com.dzavorin.solutions.linkedlist;

import com.dzavorin.solutions.tree.MaximumWidthOfBinaryTree;

import java.util.Stack;

import static com.dzavorin.solutions.linkedlist.OddEvenLinkedList.*;
import static com.dzavorin.solutions.tree.MaximumWidthOfBinaryTree.*;

public class AddTwoNumbers2 {


    // recursion
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if (l1 == null && l2 == null) {
            return null;
        } else if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }


        int len1 = 1;
        ListNode node1 = l1;

        while (node1.next != null) {
            node1 = node1.next;
            len1++;
        }

        int len2 = 1;
        ListNode node2 = l2;

        while (node2.next != null) {
            node2 = node2.next;
            len2++;
        }

        int diff = Math.abs(len1 - len2);
        if (len2 > len1) {
            ListNode tmp = l1;
            l1 = l2;
            l2 = tmp;
        }
        ListNode nodeA = l1;
        ListNode nodeB = l2;
        int i = diff;
        while (i > 0) {
            nodeA = nodeA.next;
            i--;
        }


        Pair<ListNode, Integer> tailSum = addTwoNumbersEqSize(nodeA, nodeB);

        Pair<ListNode, Integer> remaining = carry(l1, tailSum.getValue(), tailSum.getKey(), diff);

        if (remaining.getValue() == 0) {
            return remaining.getKey();
        } else {
            return new ListNode(remaining.getValue(), remaining.getKey());
        }

    }


    // stacks
    public Pair<ListNode, Integer> addTwoNumbersEqSize(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return new Pair(null, 0);
        }

        Pair<ListNode, Integer> prev = addTwoNumbersEqSize(l1.next, l2.next);

        int caryInNext = 0;
        int sum = l1.val + l2.val + prev.getValue();

        if (sum > 9) {
            caryInNext = sum / 10;
            sum = sum % 10;
        }
        return new Pair(new ListNode(sum, prev.getKey()), caryInNext);
    }

    public Pair<ListNode, Integer> carry(ListNode l1, int caryOn, ListNode connect, int diff) {
        if (diff == 0) {
            return new Pair(connect, caryOn);
        }

        Pair<ListNode, Integer> prev = carry(l1.next, caryOn, connect, diff - 1);

        int caryInNext = 0;
        int sum = l1.val + prev.getValue();

        if (sum > 9) {
            caryInNext = sum / 10;
            sum = sum % 10;
        }
        return new Pair<>(new ListNode(sum, prev.getKey()), caryInNext);
    }

    /////
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = listToStack(l1);
        Stack<Integer> s2 = listToStack(l2);

        ListNode head = null;
        int carry = 0;
        while(s1.size() > 0 || s2.size() > 0 || carry != 0) {
            int sum = carry;
            if(s1.size() > 0) sum += s1.pop();
            if(s2.size() > 0) sum += s2.pop();
            ListNode newNode = new ListNode(sum % 10);
            newNode.next = head;
            head = newNode;
            carry = sum / 10;
        }

        return head;
    }

    private Stack<Integer> listToStack(ListNode l) {
        Stack<Integer> stack = new Stack<>();
        while(l != null) {
            stack.add(l.val);
            l = l.next;
        }
        return stack;
    }

}

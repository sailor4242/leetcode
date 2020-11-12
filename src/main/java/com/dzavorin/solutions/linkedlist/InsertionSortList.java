package com.dzavorin.solutions.linkedlist;

import static com.dzavorin.solutions.linkedlist.OddEvenLinkedList.*;

public class InsertionSortList {

    // O(n^2)

    public ListNode insertionSortList(ListNode head) {

        ListNode pseudoHead = new ListNode();
        ListNode curr = head, prevNode, nextNode;

        while (curr != null) {
            // At each iteration, we insert an element into the resulting list.
            prevNode = pseudoHead;
            nextNode = pseudoHead.next;

            // find the position to insert the current node
            while (nextNode != null) {
                if (curr.val < nextNode.val)
                    break;
                prevNode = nextNode;
                nextNode = nextNode.next;
            }
            ListNode nextIter = curr.next;
            // insert the current node to the new list
            curr.next = nextNode;
            prevNode.next = curr;

            // moving on to the next iteration
            curr = nextIter;
        }

        return pseudoHead.next;
    }


    ///// fastest

    public ListNode insertionSortList2(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode tmpPrev = new ListNode(0);
        ListNode prev = tmpPrev;

        while (head != null) {
            if (prev.val >= head.val)
                prev = tmpPrev;

            while (prev.next != null && prev.next.val < head.val)
                prev = prev.next;

            ListNode tmp = head.next;
            head.next = prev.next;
            prev.next = head;
            head = tmp;
        }

        return tmpPrev.next;
    }

}

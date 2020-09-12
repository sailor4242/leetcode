package com.dzavorin.solutions.linkedlist;

import static com.dzavorin.solutions.linkedlist.OddEvenLinkedList.*;

public class IntersectionOfTwoLinkedLists {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode itA = headA;
        ListNode itB = headB;
        int lenA = 0;
        while (itA != null) {
            lenA++;
            itA = itA.next;
        }
        int lenB = 0;
        while (itB != null) {
            lenB++;
            itB = itB.next;
        }

        itA = headA;
        itB = headB;

        if (lenA < lenB) {
            int diff = lenB - lenA;
            while (diff > 0) {
                itB = itB.next;
                diff--;
            }
        } else {
            int diff = lenA - lenB;
            while (diff > 0) {
                itA = itA.next;
                diff--;
            }
        }


        while (itA != null && itB != null) {
            if (itA == itB) {
                return itA;
            }
            itA = itA.next;
            itB = itB.next;
        }

        return null;
    }

}

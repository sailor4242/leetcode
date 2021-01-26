package com.dzavorin.solutions.linkedlist;

import static com.dzavorin.solutions.linkedlist.OddEvenLinkedList.*;

public class IsPalindrome {

    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        ListNode node = head;

        int len = 0;
        while (node.next != null) {
            node = node.next;
            len++;
        }

        int toSkip = len - len / 2;
        node = head;
        while (toSkip > 0) {
            node = node.next;
            toSkip--;
        }


        ListNode reversedHalfHead = reverse(node);
        ListNode cur1 = head;
        ListNode cur2 = reversedHalfHead;
        ListNode cur1Prev = cur1;
        while (cur2 != null) {
            if (cur1.val != cur2.val) {
                return false;
            }
            cur1Prev = cur1;
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        cur1Prev.next = reverse(reversedHalfHead); // set back the reversed part so the list is not changed

        return true;
    }

    public ListNode reverse(ListNode head) {
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

    public static void main(String[] args) {
        System.out.println(new IsPalindrome().isPalindrome(ListNode.build(1,2,2,1)));
        System.out.println(new IsPalindrome().isPalindrome(ListNode.build(1,2,3,2,1)));
        System.out.println(new IsPalindrome().isPalindrome(ListNode.build(1,2,3,4,2,1)));
        System.out.println(new IsPalindrome().isPalindrome(ListNode.build(1,2)));
    }

}

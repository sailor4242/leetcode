package com.dzavorin.solutions.linkedlist;

public class OddEvenLinkedList {

    public ListNode oddEvenList(ListNode head) {
        ListNode evenHead = null;
        ListNode even = null;
        ListNode oddHead = null;
        ListNode odd = null;
        ListNode node = head;
        for (int i = 1; node != null; i++) {

            if (i % 2 != 0) {
                if (oddHead == null) {
                    oddHead = node;
                    odd = oddHead;
                } else {
                    odd.next = node;
                    odd = odd.next;
                }
            } else {
                if (evenHead == null) {
                    evenHead = node;
                    even = evenHead;
                } else {
                    even.next = node;
                    even = even.next;
                }
            }
            if (node.next == null) {
                even.next = null;
            }
            node = node.next;
        }
        //odd.next = evenHead;

        return evenHead;
    }

    public static class ListNode {
      public int val;
      public ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        System.out.println(new OddEvenLinkedList().oddEvenList(head));
    }
}

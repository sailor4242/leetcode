package com.dzavorin.solutions.linkedlist;

public class DeleteNodeInLinkedList {

    public void deleteNode(OddEvenLinkedList.ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

}

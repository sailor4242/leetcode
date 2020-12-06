package com.dzavorin.solutions;

import com.dzavorin.solutions.linkedlist.OddEvenLinkedList;
import com.dzavorin.solutions.linkedlist.OddEvenLinkedList.ListNode;

import java.util.ArrayList;

public class LinkedListRandomNode {


    //// Brute-force

    private ArrayList<Integer> range = new ArrayList<>();

    public LinkedListRandomNode(ListNode head) {
        while (head != null) {
            this.range.add(head.val);
            head = head.next;
        }
    }

    public int getRandom() {
        int pick = (int)(Math.random() * this.range.size());
        return this.range.get(pick);
    }


    //// Reservoir Sampling (Algorithm R by Alan Waterman.)
    static class Solution {
        private ListNode head;


        public Solution(ListNode head) {
            this.head = head;
        }

        /** Returns a random node's value. */
        public int getRandom() {
            int scope = 1, chosenValue = 0;
            ListNode curr = this.head;
            while (curr != null) {
                // decide whether to include the element in reservoir
                if (Math.random() < 1.0 / scope)
                    chosenValue = curr.val;
                // move on to the next node
                scope += 1;
                curr = curr.next;
            }
            return chosenValue;
        }
    }
}

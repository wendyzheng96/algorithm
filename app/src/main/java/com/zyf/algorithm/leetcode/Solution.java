package com.zyf.algorithm.leetcode;

import com.zyf.algorithm.ListNode;

/**
 * leetcode
 */
public class Solution {

    /**
     * Given a singly linked list L: L 0→L 1→…→L n-1→L n,
     * reorder it to: L 0→L n →L 1→L n-1→L 2→L n-2→…
     *
     * You must do this in-place without altering the nodes' values.
     *
     * For example,
     * Given{1,2,3,4}, reorder it to{1,4,2,3}.
     */
    private void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        fast = slow;
        ListNode lastHead = reverse(slow.next);
        fast.next = null;

        ListNode newHead = head;
        head = head.next;

        while (head != null && lastHead != null) {
            newHead.next = lastHead;
            lastHead = lastHead.next;
            newHead = newHead.next;

            newHead.next = head;
            head = head.next;
            newHead = newHead.next;
        }
        if (lastHead != null) {
            newHead.next = lastHead;
        }
    }

    /**
     * 反转字链表
     * @param head 头结点
     */
    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

}

package com.zyf.algorithm.leetcode;

import com.zyf.algorithm.bean.ListNode;

/**
 * Leetcode
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
     * 反转链表
     * 方法1：循环
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

    /**
     * 反转链表
     * 方法2：递归
     * @param head 头结点
     */
    private ListNode reverseRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 给你两个单链表，判断一个链表是不是另一个的反转
     */
    private boolean isReverse(ListNode head1, ListNode head2){
        if (head1 == null || head2 == null) {
            return false;
        }
        ListNode newNode = reverse(head1);
        while (newNode != null && head2 != null) {
            if (newNode.val != head2.val) {
                return false;
            }
            newNode = newNode.next;
            head2 = head2.next;
        }
        return newNode == null && head2 == null;
    }
}

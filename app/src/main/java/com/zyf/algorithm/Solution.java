package com.zyf.algorithm;

/**
 * Created by zyf on 2019/3/20.
 */
public class Solution {

    /**
     * 判断一个字符串是否是回文字符串，例如:level
     * @param head 链表头结点
     *
     * 思路：使用快慢两个指针找到链表中点，慢指针每次前进一步，快指针每次前进两步。
     * 在慢指针前进的过程中，同时修改其 next 指针，使得链表前半部分反序。
     * 最后比较中点两侧的链表是否相等。
     */
    private static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node pre = null;
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            Node next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }

        if (fast != null) {
            slow = slow.next;
        }
        while (slow.next != null) {
            if (slow.val != pre.val) {
                return false;
            }
            pre.next = pre;
            slow.next = slow;
        }
        return true;
    }

    /**
     * 反转字符串
     * @param head 头结点
     */
    private static Node reverse(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next; //持有下一个结点的引用
            head.next = pre; //将当前结点对下一个结点的引用指向前一个结点
            pre = head; //将前一个结点指向当前结点
            head = next; //将当前结点指向下一个结点
        }
        return pre;
    }

    /**
     * 链表中环的检测
     */
    private static boolean isLoop(Node head) {
        if (head == null || head.next == null) {
            return false;
        }

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取链表中环的起点
     */
    private static Node loopStarPoint(Node head) {
        if (head == null || head.next == null) {
            return null;
        }

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                fast = head;
                while (slow != fast) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }
}

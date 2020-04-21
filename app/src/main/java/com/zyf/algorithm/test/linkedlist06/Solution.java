package com.zyf.algorithm.test.linkedlist06;

import com.zyf.algorithm.ListNode;

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
    private static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode pre = null;
        ListNode next;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }

        if (fast != null) {
            slow = slow.next;
        }
        while (slow.next != null && pre != null) {
            if (slow.val != pre.val) {
                return false;
            }
            pre.next = pre;
            slow.next = slow;
        }
        return true;
    }

}

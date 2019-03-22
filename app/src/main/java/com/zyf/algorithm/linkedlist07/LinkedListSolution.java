package com.zyf.algorithm.linkedlist07;

import com.zyf.algorithm.ListNode;

/**
 * Created by zyf on 2019/3/22.
 */
public class LinkedListSolution {

    /**
     * 反转字符串
     * @param head 头结点
     */
    private static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = null;
        ListNode next = null;
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
    private static boolean isLoop(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

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
    private static ListNode loopStarPoint(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

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

    /**
     * 合并两个递增有序的链表，最优解：递归实现
     */
    private static ListNode merge(ListNode h1, ListNode h2) {
        if (h1 == null) {
            return h2;
        }
        if (h2 == null) {
            return h1;
        }
        ListNode head = null;
        if (h1.val < h2.val) {
            head = h1;
            head.next = merge(h1.next, h2);
        } else {
            head = h2;
            head.next = merge(h1, h2.next);
        }
        return head;
    }

    /**
     * 删除链表倒数第n个结点，n保证是有效的
     */
    private static ListNode delNodeN(ListNode head, int n) {
        if (head == null || n <= 0) {
            return head;
        }
        ListNode first = head;
        ListNode second = head;
        for (int i = 0; i < n; i++) {
            first = first.next;
        }
        if (first == null) {
            return head.next;
        }
        while (first.next != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return head;
    }

    /**
     * 求链表的中间结点，如果有两个中间结点，则返回第二个中间结点
     */
    private static ListNode findMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args){

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        ListNode head = l1;
//        head = reverse(l1);
//        head = delNodeN(l1, 2);
        while (head != null) {
            if (head.next != null) {
                System.out.print(head.val + " --> ");
            } else {
                System.out.println(head.val);
            }
            head = head.next;
        }
        System.out.println(findMiddle(l1).val);
    }
}

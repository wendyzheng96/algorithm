package com.zyf.algorithm.leetcode;

import com.zyf.algorithm.bean.ListNode;
import com.zyf.algorithm.bean.RandomListNode;

import java.util.List;

/**
 * 链表相关
 */
public class LinkedListTest {

    /**
     * LC5：使用插入排序对链表进行排序。
     */
    public ListNode insertionSortList (ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode pHead = new ListNode(0);
        ListNode p;
        ListNode q;
        while(head != null){
            p = pHead;
            q = p.next;
            ListNode temp = new ListNode(head.val);
            while(q != null && q.val < temp.val){
                q = q.next;
                p = p.next;
            }
            temp.next = q;
            p.next = temp;
            head = head.next;
        }
        return pHead.next;
    }

    /**
     * LC9:对于一个给定的链表，返回环的入口节点，如果没有环，返回null
     */
    public ListNode detectCycle(ListNode head){
        if(head == null){
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                fast = head;
                while (fast != slow){
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }

    /**
     * LC13:现在有一个这样的链表：链表的每一个节点都附加了一个随机指针，随机指针可能指向链表中的任意
     * 一个节点或者指向空。请对这个链表进行深拷贝。
     */
    public RandomListNode copyRandomList(RandomListNode head){
        if(head == null){
            return null;
        }
        RandomListNode node = head;
        //A->A'->B->B'->C->C'
        while (node != null) {
            RandomListNode temp = new RandomListNode(node.label);
            temp.next = node.next;
            node.next = temp;
            node = temp.next;
        }
        node = head;
        while(node != null){
            node.next.random = node.random == null ? null : node.random.next;
            node = node.next.next;
        }
        node = head.next;
        while(head.next != null){
            RandomListNode temp = head.next;
            head.next = temp.next;
            head = temp;
        }
        return node;
    }

    /**
     * LC67:删除给出链表中的重复元素（链表中元素从小到大有序），使链表中的所有元素都只出现一次
     * 例如：
     * 给出的链表为1->1->2,返回1->2.
     * 给出的链表为1->1->2->3->3,返回1->2->3.
     */
    public ListNode deleteDuplicates (ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode node = head;
        while(node.next != null){
            if(node.next.val == node.val){
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
        return head;
    }

    /**
     * LC85:将两个有序的链表合并为一个新链表，要求新的链表是通过拼接两个链表的节点来生成的。
     */
    public ListNode mergeTwoLists (ListNode l1, ListNode l2){
        ListNode pHead = new ListNode(-1);
        ListNode node = pHead;
        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                node.next = l1;
                l1 = l1.next;
            } else {
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }
        if(l1 != null){
            node.next = l1;
        }
        if(l2 != null){
            node.next = l2;
        }
        return pHead.next;
    }
}

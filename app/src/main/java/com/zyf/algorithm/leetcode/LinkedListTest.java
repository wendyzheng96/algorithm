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
     * LC63:给出一个链表和一个值x，以x为参照将链表划分成两部分，使所有小于x的节点都位于大于或等于x的节点之前。
     * 两个部分之内的节点之间要保持的原始相对顺序。
     * 例如：
     * 给出1->4->3->2->5->2和x = 3,
     * 返回1->2->2->4->3->5.
     */
    public ListNode partition (ListNode head, int x){
        ListNode lowPre = new ListNode(-1);
        ListNode low = lowPre;
        ListNode highPre = new ListNode(-1);
        ListNode high = highPre;
        while (head != null){
            if(head.val < x){
                low.next = head;
                low = low.next;
            } else {
                high.next = head;
                high = high.next;
            }
            head = head.next;
        }
        high.next = null;
        low.next = highPre.next;
        return lowPre.next;
    }

    /**
     * LC66:给出一个排好序的链表，删除链表中的所有重复出现的元素，只保留原链表中只出现一次的元素。
     * 例如：
     * 给出的链表为1->2->3->3->4->4->5, 返回1->2->5.
     * 给出的链表为1->1->1->2->3,  返回2->3.
     */
    public ListNode deleteDuplicates (ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        if(head.val != head.next.val){
            head.next = deleteDuplicates(head.next);
            return head;
        }
        ListNode node = head.next.next;
        while (node != null && node.val == head.val){
            node = node.next;
        }
        return deleteDuplicates2(node);
    }

    /**
     * LC67:删除给出链表中的重复元素（链表中元素从小到大有序），使链表中的所有元素都只出现一次
     * 例如：
     * 给出的链表为1->1->2,返回1->2.
     * 给出的链表为1->1->2->3->3,返回1->2->3.
     */
    public ListNode deleteDuplicates2 (ListNode head){
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

    /**
     * LC125:reverse-nodes-in-k-group
     * 将给出的链表中的节点每k个一组翻转，返回翻转后的链表
     * 如果链表中的节点数不是k的倍数，将最后剩下的节点保持原样
     * 你不能更改节点中的值，只能更改节点本身。
     * 只允许使用常数级的空间
     * 例如：
     * 给定的链表是1->2->3->4->5
     *
     * 对于 k = 2, 你应该返回 2->1->4->3->5
     *
     * 对于 k = 3, y你应该返回 3->2->1->4->5
     */
    public ListNode reverseKGroup(ListNode head, int k){
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode tail = dummy;
        while (tail.next != null){
            for(int i = 0; i < k - 1 && tail != null; i++){
                tail = tail.next;
            }
            if(tail == null){
                break;
            }
            ListNode start = pre.next;
            ListNode next = tail.next;

            tail.next = null;
            pre.next = reverse(start);

            start.next = next;
            pre = start;
            tail = pre;
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode head){
        ListNode pre = null;
        ListNode next;
        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * LC126:swap-nodes-in-pairs
     * 将给定的链表中每两个相邻的节点交换一次，返回链表的头指针
     * 例如,
     * 给出1->2->3->4，你应该返回链表2->1->4->3。
     * 你给出的算法只能使用常量级的空间。你不能修改列表中的值，只能修改节点本身。
     */
    public ListNode swapPairs (ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        //1.递归
//        ListNode newHead = head.next;
//        ListNode temp = head.next.next;
//        newHead.next = head;
//        head.next = swapPairs(temp);
//        return newHead;

        //2.迭代
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        while(head != null && head.next != null){
            ListNode temp = head.next;
            head.next = temp.next;
            temp.next = head;
            pre.next = temp;

            pre = head;
            head = head.next;
        }
        return dummy.next;
    }
}

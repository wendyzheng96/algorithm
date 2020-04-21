package com.zyf.algorithm.test.queue09;

import com.zyf.algorithm.ListNode;

/**
 * 通过链表实现队列
 */
public class LinkedQueue {

    private ListNode head;//队头指针

    private ListNode tail;//队尾指针

    /**
     * 入队
     */
    private void enqueue(int num) {
        ListNode node = new ListNode(num);
        if (tail == null) {
            head = node;
            tail = node;
            return;
        }
        tail.next = node;
        tail = node;
    }

    /**
     * 出队
     */
    private int dequeue() {
        if (head == null) {
            return -1;
        }
        int tmp = head.val;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return tmp;
    }

    public static void main(String[] args){
        LinkedQueue queue = new LinkedQueue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        printAll(queue.head);

        System.out.println("dequeue: " + queue.dequeue());
        System.out.println("dequeue: " + queue.dequeue());
        System.out.println("dequeue: " + queue.dequeue());
        System.out.println("dequeue: " + queue.dequeue());
        System.out.println("dequeue: " + queue.dequeue());
        printAll(queue.head);

        queue.enqueue(6);
        printAll(queue.head);
        queue.enqueue(7);
        printAll(queue.head);
    }

    private static void printAll(ListNode head) {
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        while (head != null) {
            if (head.next != null) {
                System.out.print(head.val + " --> ");
            } else {
                System.out.println(head.val);
            }
            head = head.next;
        }
    }
}

package com.zyf.algorithm.stack08;

import com.zyf.algorithm.ListNode;

/**
 * 使用链表实现栈
 */
public class LinkedStack {

    private ListNode head;

    private void push(int num) {
        ListNode p = new ListNode(num);
        p.next = head;
        head = p;
    }

    private int pop() {
        if (head == null) {
            return -1;
        }
        ListNode p = head;
        head = head.next;
        return p.val;
    }

    public static void main(String[] args){
        LinkedStack stack = new LinkedStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        printStack(stack.head);

        System.out.println("pop: " + stack.pop());
        System.out.println("pop: " + stack.pop());
        System.out.println("pop: " + stack.pop());
        printStack(stack.head);
    }

    private static void printStack(ListNode head) {
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

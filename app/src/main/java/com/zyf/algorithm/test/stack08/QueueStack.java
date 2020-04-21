package com.zyf.algorithm.test.stack08;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * 两个队列实现一个栈
 */
public class QueueStack {

    private Queue<Integer> queue1 = new LinkedList<>();
    private Queue<Integer> queue2 = new LinkedList<>();

    private void push(int num) {
        queue1.offer(num);
    }

    private int pop(){
        if (queue1.isEmpty() && queue2.isEmpty()) {
            return -1;
        }
        if (!queue1.isEmpty()) {
            int size = queue1.size();
            for (int i = 0; i < size - 1; i++) {
                queue2.offer(queue1.poll());
            }
            return queue1.poll();
        } else {
            int size = queue2.size();
            for (int i = 0; i < size - 1; i++) {
                queue1.offer(queue2.poll());
            }
            return queue2.poll();
        }
    }

    private boolean isEmpty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }

    public static void main(String[] args) {
        QueueStack stack = new QueueStack();
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            int data = rand.nextInt(100);
            System.out.print(data + " ");
            stack.push(data);
        }
        System.out.println();
        System.out.println("出栈：");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}

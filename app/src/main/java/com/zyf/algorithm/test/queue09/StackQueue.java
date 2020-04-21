package com.zyf.algorithm.test.queue09;

import java.util.Random;
import java.util.Stack;

/**
 * 两个栈实现一个队列
 */
public class StackQueue {

    private Stack<Integer> in = new Stack<>();
    private Stack<Integer> out = new Stack<>();

    /**
     * 入队
     */
    private void enqueue(int num){
        in.push(num);
    }

    /**
     * 出队
     */
    private int dequeue() {
        if (in.isEmpty() && out.empty()) {
            return -1;
        }
        if (!out.isEmpty()) {
            return out.pop();
        }
        while (!in.isEmpty()) {
            out.push(in.pop());
        }
        return out.pop();
    }

    private boolean isEmpty(){
        return in.isEmpty() && out.isEmpty();
    }

    public static void main(String[] args) {
        StackQueue queue = new StackQueue();
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            int data = rand.nextInt(100);
            System.out.print(data + " ");
            queue.enqueue(data);
        }
        System.out.println();
        System.out.println("出队一个：" + queue.dequeue());
        System.out.println("入队一个：12" );
        queue.enqueue(12);
        System.out.println("出队：");
        while (!queue.isEmpty()) {
            System.out.print(queue.dequeue() + " ");
        }
    }


}

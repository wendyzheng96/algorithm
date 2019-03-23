package com.zyf.algorithm.queue09;

import java.util.Arrays;

/**
 * 使用数组实现队列
 */
public class ArrayQueue {

    private String[] items;//数组

    private int n;//数组容量

    private int head = 0;//队头下标

    private int tail = 0;//队尾下标

    public ArrayQueue(int n) {
        items = new String[n];
        this.n = n;
    }

    /**
     * 入队
     */
    private boolean enqueue(String item) {
        if (tail == n) {//队列已满
            if (head == 0) {
                return false;
            }
            for (int i = head; i < tail; i++) {
                items[i-head] = items[i];
            }
            tail -= head;
            head = 0;
        }
        items[tail] = item;
        tail++;
        return true;
    }

    /**
     * 出队
     */
    private String dequeue() {
        if (head == tail) {//队列为空
            return null;
        }
        String tmp = items[head];
        head++;
        return tmp;
    }

    private void printAll() {
        if (0 == n) return;
        for (int i = head; i < tail; ++i) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(5);
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");
        queue.printAll();
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.printAll();

        System.out.println(queue.enqueue("6"));
        queue.printAll();
    }

}

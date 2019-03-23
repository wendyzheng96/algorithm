package com.zyf.algorithm.queue09;

import java.util.Arrays;

/**
 * 通过数组实现循环队列
 */
public class CircularQueue {

    private String[] items;//数组

    private int n;//数组容量

    private int head = 0;//队头下标

    private int tail = 0;//队尾下标

    public CircularQueue(int n) {
        items = new String[n];
        this.n = n;
    }

    /**
     * 入队
     */
    private boolean enqueue(String item) {
        if ((tail + 1) % n == head) {//队列已满
            return false;
        }
        items[tail] = item;
        tail = (tail + 1) % n;
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
        head = (head + 1) % n;
        return tmp;
    }

    private void printAll() {
        if (0 == n) return;
        for (int i = head; i % n != tail; ++i) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(5);
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");
        queue.printAll();

        System.out.println("dequeue " + queue.dequeue());
        System.out.println("dequeue " + queue.dequeue());
        queue.printAll();

        System.out.println("enqueue " + queue.enqueue("6"));
        queue.printAll();

        System.out.println("dequeue " + queue.dequeue());
        System.out.println("dequeue " + queue.dequeue());
        System.out.println("dequeue " + queue.dequeue());
        System.out.println("dequeue " + queue.dequeue());
        queue.printAll();
    }
}

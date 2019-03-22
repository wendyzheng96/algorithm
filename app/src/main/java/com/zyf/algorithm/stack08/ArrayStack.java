package com.zyf.algorithm.stack08;

import java.util.Arrays;

/**
 * 使用数组实现栈
 */
public class ArrayStack {

    private String[] items;
    private int count;   //栈中元素个数
    private int n;       //栈的大小

    public ArrayStack(int n) {
        items = new String[n];
        count = 0;
        this.n = n;
    }

    public boolean push(String item) {
        if (count == n) {
            return false;
        }
        items[count] = item;
        count++;
        return true;
    }

    public String pop() {
        if (count == 0) {
            return null;
        }
        String item = items[count - 1];
        count--;
        return item;
    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        stack.push("5");
        printStack(stack);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push("6");
        printStack(stack);
    }

    private static void printStack(ArrayStack stack) {
        if (stack.items == null) {
            return;
        }
        System.out.println(Arrays.toString(stack.items));
    }
}

package com.zyf.algorithm.stack08;

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
}

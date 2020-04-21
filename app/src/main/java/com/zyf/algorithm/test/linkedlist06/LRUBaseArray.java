package com.zyf.algorithm.test.linkedlist06;

/**
 * 基于数组实现LRU缓存算法
 */
public class LRUBaseArray<T> {

    /**
     * 默认数组容量
     */
    private static final int DEFAULT_CAPACITY = (1 << 3);

    /**
     * 数组
     */
    private T[] values;

    /**
     * 数组长度
     */
    private int length;

    /**
     * 数组容量
     */
    private int capacity;

}

package com.zyf.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 线性排序
 */
public class LinearSort {

    /**
     * 桶排序
     * @param a 数组
     * @param n 数组长度
     */
    private void bucketSort(int[] a, int n, int bucketSize) {
        if (n <= 1) {
            return;
        }

        int max = a[0];//最大值
        int min = a[0];//最小值
        for (int num : a) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        ArrayList<Integer>[] buckets = new ArrayList[bucketSize];

        for (int i = 0; i < n; i++) {
            int tmp = a[i] / bucketSize;
            if (buckets[tmp] == null) {
                buckets[tmp] = new ArrayList<>();
            }
            buckets[tmp].add(a[i]);
        }
        for (int j = 0; j < bucketSize; j++) {
            if (buckets[j] != null) {
                Collections.sort(buckets[j]);
                System.out.println(buckets[j]);
            }
        }
        for (int i = 0, j = 0; j < bucketSize; j++) {
            if (buckets[j] != null) {
                for (int num : buckets[j]) {
                    a[i++] = num;
                }
            }
        }
    }

    /**
     * 桶排序，桶的个数为数组最大值+1
     */
    private void bucketSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }
        int max = a[0];//最大值
        for (int num : a) {
            if (num > max) {
                max = num;
            }
        }

        int[] buckets = new int[max + 1];
        for (int i = 0; i <= max; i++) {
            buckets[a[i]]++;
        }

        for (int i = 0, j = 0; i <= max; i++) {
            while (buckets[i]-- > 0) {
                a[j++] = i;
            }
        }
    }

    /**
     * 计数排序
     */
    private void countingSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }
        int max = a[0];//数组最大值
        for (int num : a) {
            if (num > max) {
                max = num;
            }
        }

        int[] c = new int[max + 1];
        for (int num : a) {
            c[num]++;
        }
        for (int i = 1; i <= max; i++) {
            c[i] += c[i - 1];
        }

        int[] r = new int[n];
        for (int i = n-1; i >= 0; i--) {
            int index = c[a[i]] - 1;
            r[index] = a[i];
            c[a[i]]--;
        }

        for (int i = 0; i < n; i++) {
            a[i] = r[i];
        }
    }

    /**
     * 基数排序
     */
    private void radix(int[] a, int count){

    }



    public static void main(String[] args) {
        LinearSort sort = new LinearSort();
        int[] a = new int[20];
        for (int i = 0; i < 20; i++) {
            a[i] = (int) (Math.random() * 49) + 1;
        }
        System.out.println(Arrays.toString(a));
//        sort.countingSort(a, a.length);
        sort.bucketSort(a, a.length, 10);
        System.out.println(Arrays.toString(a));
    }
}

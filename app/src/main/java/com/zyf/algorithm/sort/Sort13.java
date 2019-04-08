package com.zyf.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 线性排序
 */
public class Sort13 {

    /**
     * 桶排序
     * @param a 数组
     * @param n 数组长度
     * @param bucketCount 桶个数
     */
    private void bucketSort(int[] a, int n, int bucketCount) {
        if (n <= 1) {
            return;
        }

        int min = a[0];
        int max = a[0];
        for (int num : a) {
            min = num < min ? num : min;
            max = num > max ? num : max;
        }
        double space = (max - min + 1) * 1.0 / bucketCount;
        System.out.println(space);

        ArrayList<Integer>[] buckets = new ArrayList[bucketCount];

        for (int i = 0; i < n; i++) {
            int tmp = (int) ((a[i] - min) / space);
            if (buckets[tmp] == null) {
                buckets[tmp] = new ArrayList<>();
            }
            buckets[tmp].add(a[i]);
        }
        for (int j = 0; j < bucketCount; j++) {
            if (buckets[j] != null) {
                Collections.sort(buckets[j]);
                System.out.println(buckets[j]);
            }
        }
        for (int i = 0, j = 0; j < bucketCount; j++) {
            if (buckets[j] != null) {
                for (int num : buckets[j]) {
                    a[i++] = num;
                }
            }
        }
    }

    /**
     * 桶排序，bucketCount为数组最大值+1
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
     * @param a 数组
     */
    private void radixSort(int[] a){
        if (a.length <= 1) {
            return;
        }

        int d = 0;
        int max = a[0];
        for (int num : a) {
            max = num > max ? num : max;
        }
        while (max > 0) {
            max = max / 10;
            d++;
        }
        System.out.println("最大位数: " + d);

        int[] tmp = new int[a.length];
        int[] bucket = new int[10];
        for (int i = 0, rate = 1; i < d; i++) {
            for (int j = 0; j < bucket.length; j++) {
                bucket[j] = 0;
            }
            for (int j = 0; j < a.length; j++) {
                tmp[j] = a[j];
                int subKey = (tmp[j] / rate) % 10;
                bucket[subKey]++;
            }
            for (int j = 1; j < 10; j++) {
                bucket[j] += bucket[j - 1];
            }
            // 按子关键字对指定的数据进行排序
            for (int m = a.length - 1; m >= 0; m--) {
                int subKey = (tmp[m] / rate) % 10;
                a[--bucket[subKey]] = tmp[m];
            }
            rate *= 10;
        }
    }



    public static void main(String[] args) {
        Sort13 sort = new Sort13();
        int[] a = new int[20];
        for (int i = 0; i < 20; i++) {
            a[i] = (int) (Math.random() * 499) + 1;
        }
        System.out.println(Arrays.toString(a));
//        sort.countingSort(a, a.length);
//        sort.bucketSort(a, a.length, 5);
        sort.radixSort(a);
        System.out.println(Arrays.toString(a));
    }
}

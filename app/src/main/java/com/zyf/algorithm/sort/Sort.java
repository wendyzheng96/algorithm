package com.zyf.algorithm.sort;

/**
 * 排序
 */
public class Sort {

    /**
     * 冒泡排序
     */
    public void bubbleSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    flag = true;//表示有数据交换
                }
            }
            if (!flag) {//没有数据交换，提前退出
                break;
            }
        }
    }

    /**
     * 插入排序
     */
    public void insertSort(int[] a, int n){
        if (n < 1) {
            return;
        }
        for (int i = 1; i < n; i++) {
            int value = a[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (a[j] > value) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = value;
        }
    }

    /**
     * 选择排序
     */
    public void selectSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            int k = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[k]) {
                    k = j;
                }
            }
            if (k > i) {
                int tmp = a[i];
                a[i] = a[k];
                a[k] = tmp;
            }
        }
    }
}

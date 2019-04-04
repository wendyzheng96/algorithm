package com.zyf.algorithm.sort;

import java.util.Arrays;

/**
 * 排序
 */
public class Sort11 {

    /**
     * 冒泡排序，空间复杂度O(1),时间复杂度O(n2)
     */
    private void bubbleSort(int[] a, int n) {
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
     * 插入排序,空间复杂度O(1),时间复杂度O(n2)
     */
    private void insertSort(int[] a, int n){
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
     * 选择排序,空间复杂度O(1),时间复杂度O(n2)
     */
    private void selectSort(int[] a, int n) {
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

    public static void main(String[] args) {
        Sort11 sort = new Sort11();
        int[] a = {7, 1, 4, 3, 9, 6, 8, 5};
        System.out.println(Arrays.toString(a));
//        sort.bubbleSort(a, a.length);
//        sort.insertSort(a, a.length);
        sort.selectSort(a, a.length);
        System.out.println(Arrays.toString(a));
    }

}

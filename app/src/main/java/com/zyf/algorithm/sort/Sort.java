package com.zyf.algorithm.sort;

import java.util.Arrays;

/**
 * 排序
 */
public class Sort {

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

    /**
     * 归并排序
     */
    private void mergerSort(int[] a){
        int[] tmp = new int[a.length];
        mergeSort(a, 0, a.length - 1, tmp);
    }

    private void mergeSort(int[] a, int low, int high, int[] tmp) {
        if (low >= high) {
            return;
        }
        int mid = (low + high) / 2;
        mergeSort(a, low, mid, tmp);
        mergeSort(a, mid + 1, high, tmp);
        merge(a, low, mid, high, tmp);
    }

    private void merge(int[] a, int low, int mid, int high, int[] tmp){
        int i = low;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= high) {
            if (a[i] <= a[j]) {
                tmp[t++] = a[i++];
            } else {
                tmp[t++] = a[j++];
            }
        }
        while (i <= mid) {
            tmp[t++] = a[i++];
        }
        while (j <= high) {
            tmp[t++] = a[j++];
        }
        t = 0;
        while (low <= high) {
            a[low++] = tmp[t++];
        }
    }

    /**
     * 快速排序,时间复杂度为O(nlogn),空间复杂度为O(1)
     */
    public void quickSort(int[] a, int low, int high) {
        if (low >= high) {
            return;
        }
        int index = partition(a, low, high);
        quickSort(a, low, index - 1);
        quickSort(a, index + 1, high);
    }

    private int partition(int[] a, int low, int high) {
        int tmp = a[low];
        while (low < high) {
            while (low < high && a[high] >= tmp) {
                high--;
            }
            a[low] = a[high];
            while (low < high && a[low] <= tmp) {
                low++;
            }
            a[high] = a[low];
        }
        a[low] = tmp;
        return low;
    }

    public static void main(String[] args) {
        Sort sort = new Sort();
        int[] a = {7, 1, 4, 3, 9, 6, 8, 5};
        System.out.println(Arrays.toString(a));
//        sort.bubbleSort(a, a.length);
//        sort.insertSort(a, a.length);
//        sort.selectSort(a, a.length);
        sort.mergerSort(a);
//        sort.quickSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

}

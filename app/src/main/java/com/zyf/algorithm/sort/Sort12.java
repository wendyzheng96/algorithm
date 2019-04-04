package com.zyf.algorithm.sort;

import java.util.Arrays;

/**
 * 排序
 */
public class Sort12 {

    /**
     * 归并排序,时间复杂度为O(nlogn),空间复杂度为O(n)
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
    private void quickSort(int[] a, int low, int high) {
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
        Sort12 sort = new Sort12();
        int[] a = {7, 1, 4, 3, 9, 6, 8, 5};
        System.out.println(Arrays.toString(a));
        sort.mergerSort(a);
//        sort.quickSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

}

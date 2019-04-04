package com.zyf.algorithm.sort;

import java.util.Arrays;

/**
 * 查找无序数组中的第 K 大元素
 */
public class FindKth {

    /**
     * 求无序数组中的第 K 大元素
     */
    private int findKthMaxNum(int[] a, int k) {
        if (a.length <= 0 || k > a.length || k <= 0) {
            return -1;
        }
        int low = 0;
        int high = a.length - 1;
        int index = partition(a, low, high);
        while (index != k -1) {
            if (k > index + 1) {
                low = index + 1;
            } else {
                high = index - 1;
            }
            index = partition(a, low, high);
        }
        return a[index];
    }

    private int partition(int[] a, int low, int high) {
        int tmp = a[low];
        while (low < high) {
            while (low < high && a[high] <= tmp) {
                high--;
            }
            a[low] = a[high];

            while (low < high && a[low] >= tmp) {
                low++;
            }
            a[high] = a[low];
        }
        a[low] = tmp;
        return low;
    }

    public static void main(String[] args) {
        FindKth sort = new FindKth();
        int[] a = {7, 1, 4, 3, 9, 6, 8, 5};
        System.out.println(Arrays.toString(a));
        int num = sort.findKthMaxNum(a, 4);
        System.out.println(num);
    }
}

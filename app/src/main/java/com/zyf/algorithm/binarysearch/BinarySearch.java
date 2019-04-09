package com.zyf.algorithm.binarysearch;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by zyf on 2019/4/9.
 */
public class BinarySearch {

    /**
     * 求一个数的平方根，要求精确到小数点后6位
     */
    private static double sqrt(double num) {
        double low = 0;
        double high = num;
        double mid = low + (high - low) / 2;
        while (high - low > 1e-7) {
            if (mid * mid > num) {
                high = mid;
            }
            else if (mid * mid < num) {
                low = mid;
            }
            else {
                return mid;
            }
            mid = low + ((high - low) / 2);
        }
        return mid;
    }

    /**
     * 查找第一个值等于给定值的元素
     */
    private static int binarySearchFirst(int[] a, int value) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == 0 || a[mid - 1] != value) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找最后一个值等于给定值的元素
     */
    private static int binarySearchLast(int[] a, int value) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == a.length - 1 || a[mid + 1] != value) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找第一个大于等于给定值的元素
     */
    private static int binarySearchMore(int[] a, int value){
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == 0 || a[mid - 1] < value) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找最后一个小于等于给定值的元素
     */
    private static int binarySearchLess(int[] a, int value) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else {
                if (mid == a.length - 1 || a[mid + 1] > value) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args){
        int[] a = {3, 5, 5, 5, 6, 8, 9, 9, 10};
        System.out.println(Arrays.toString(a));
        System.out.println("第一个值等于9的元素下标:" + BinarySearch.binarySearchFirst(a, 9));
        System.out.println("最后一个值等于9的元素下标:" + BinarySearch.binarySearchLast(a, 9));
        System.out.println("第一个大于等于4的元素下标:" + BinarySearch.binarySearchMore(a, 4));
        System.out.println("最后一个小于等于6的元素下标:" + BinarySearch.binarySearchLess(a, 6));

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextDouble()) {
            double num = sc.nextDouble();
            System.out.println(BinarySearch.sqrt(num));
        }
    }
}

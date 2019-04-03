package com.zyf.algorithm.sort;

import java.util.Arrays;

/**
 * 按年龄排序
 */
public class AgeSort {

    /**
     * 根据年龄给100万用户排序
     * @param a 年龄数组
     * @param n 数组长度
     */
    private void sortByAge(int[] a, int n){
        int max = 120;//设置最大年龄为120岁

        int[] bucket = new int[max + 1];

        for (int num : a) {
            bucket[num]++;
        }
        for (int i = 0, j = 0; i <= max; i++) {
            while (bucket[i]-- > 0) {
                a[j++] = i;
            }
        }
    }

    public static void main(String[] args) {
        AgeSort sort = new AgeSort();
        int[] a = new int[100000];
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 120) + 1;
        }
        System.out.println(Arrays.toString(a));
        sort.sortByAge(a, a.length);
        System.out.println(Arrays.toString(a));
    }
}

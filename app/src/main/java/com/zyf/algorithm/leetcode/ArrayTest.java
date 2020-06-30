package com.zyf.algorithm.leetcode;

import java.util.Arrays;

/**
 * 数组相关题目
 */
public class ArrayTest {

    /**
     * LC15:现在有一个整数类型的数组，数组中素只有一个元素只出现一次，其余的元素都出现两次。
     * 注意：你需要给出一个线性时间复杂度的算法，你能在不使用额外内存空间的情况下解决这个问题么？
     */
    public int singleNumber (int[] A){
        if (A == null || A.length == 0) {
            return -1;
        }
        int num = 0;
        for (int value : A) {
            num ^= value;
        }
        return num;
    }

    /**
     * LC14:现在有一个整数类型的数组，数组中只有一个元素只出现一次，其余元素都出现三次。
     * 你需要找出只出现一次的元素。
     */
    public int singleNumber2 (int[] A){
        if (A == null || A.length == 0) {
            return -1;
        }
        int num = 0;
        for(int i = 0; i < 32; i++){
            int bit = 0;
            for(int j = 0; j < A.length; j++){
                bit += (A[i] >> i) & 1;
            }
            num |= (bit % 3) << i;
        }
        return num;
    }

    /**
     * LC23:给定一个无序的整数类型数组，求最长的连续元素序列的长度。
     * 例如：
     * 给出的数组为[100, 4, 200, 1, 3, 2],
     * 最长的连续元素序列为[1, 2, 3, 4]. 返回这个序列的长度：4
     * 你需要给出时间复杂度在O（n）之内的算法
     */
    public int longestConsecutive (int[] num){
        if(num == null || num.length == 0){
            return 0;
        }
        Arrays.sort(num);
        int maxLen = 1;
        int count = 1;
        for(int i = 1; i < num.length; i++){
            if(num[i] == num[i - 1]){
                continue;
            }
            if(num[i] - num[i - 1] == 1){
                count++;
            } else {
                maxLen = Math.max(maxLen, count);
                count = 1;
            }
        }
        return Math.max(maxLen, count);
    }

    /**
     * LC29:假设你有一个数组，其中第i个元素表示某只股票在第i天的价格。
     * 设计一个算法来寻找最大的利润。你可以完成任意数量的交易(例如，多次购买和出售股票的一股)。
     * 但是，你不能同时进行多个交易(即，你必须在再次购买之前卖出之前买的股票)。
     */
    public int maxProfit (int[] prices){
        if(prices == null || prices.length <= 1){
            return 0;
        }
        int sum = 0;
        for(int i = 1; i < prices.length; i++){
            if (prices[i] - prices[i - 1] > 0){
                sum += prices[i] - prices[i - 1];
            }
        }
        return sum;
    }

    /**
     * LC30:假设你有一个数组，其中第i个元素是某只股票在第i天的价格。
     * 如果你最多只能完成一笔交易(即买一股和卖一股股票)，设计一个算法来求最大利润。
     */
    public int maxProfit2 (int[] prices){
        if(prices == null || prices.length <= 1){
            return 0;
        }
        int maxProfit = 0;
        int min = prices[0];
        for(int i = 1; i < prices.length; i++){
            if(prices[i] < min) {
                min = prices[i];
            } else {
                maxProfit = Math.max(maxProfit, prices[i] - min);
            }
        }
        return maxProfit;
    }

    /**
     * LC123:给定一个数组和一个值，使用就地算法将数组中所有等于这个值的元素删除，并返回新数组的长度。
     * 元素的顺序可以更改。你不用去关心大于当前数组长度的空间里面存储的值
     */
    public int removeElement(int[] A, int elem){
        if(A == null || A.length == 0){
            return 0;
        }
        int len = A.length;
        for(int i = 0; i < len; i++){
            if(A[i] == elem){
                int temp = A[i];
                A[i] = A[len - 1];
                A[len - 1] = temp;
                i--;
                len--;
            }
        }
        return len;
    }
}

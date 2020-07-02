package com.zyf.algorithm.leetcode;

import java.util.Arrays;
import java.util.Stack;

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
     * LC61:给出两个有序的整数数组A和B，请将数组B合并到数组A中，变成一个有序的数组
     * 注意：
     * 可以假设A数组有足够的空间存放B数组的元素，A和B中初始的元素数目分别为m和n
     */
    public void merge(int[] A, int m, int[] B, int n){
        int index = m + n - 1;
        int i = m - 1;
        int j = n - 1;
        while(i >= 0 && j >= 0){
            A[index--] = A[i] > B[j] ? A[i--] : B[j--];
        }
        while(j >= 0){
            A[index--] = B[j--];
        }
    }

    /**
     * LC65:给出n个数字，代表直方图的条高，直方图每一条的宽度为1，请计算直方图中最大矩形的面积
     * 上图是每条宽度为1, 高度 =[2,1,5,6,2,3].的直方图
     * 图中的阴影部分是该直方图中面积最大的矩形，面积为10个单位
     * 例如：
     * 给出的高度 =[2,1,5,6,2,3],
     * 返回10.
     */
    public int largestRectangleArea (int[] height){
        if(height == null || height.length == 0){
            return 0;
        }
        if(height.length == 1){
            return height[0];
        }
        int len = height.length;
        int[] left = new int[len];
        int[] right = new int[len];
        Arrays.fill(right, len);
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < len; i++){
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]){
                right[stack.pop()] = i;
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        int maxArea = 0;
        for(int i = 0; i < len; i++){
            int area = height[i] * (right[i] - left[i] - 1);
            if(area > maxArea){
                maxArea = area;
            }
        }
        return maxArea;
    }

    /**
     * LC68:假设按照升序排序的数组在预先未知的某个点上进行了旋转。数组中存在重复元素。
     *
     * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
     *
     * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
     *
     * 示例 1:
     * 输入: nums = [2,5,6,0,0,1,2], target = 0
     * 输出: true
     */
    public boolean search (int[] A, int target){
        if(A.length == 0){
            return false;
        }
        if(A.length == 1){
            return A[0] == target;
        }
        int left = 0;
        int right = A.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(A[mid] == target){
                return true;
            }
            if(A[left] == A[mid]){
                left++;
            }
            if(A[left] < A[mid]){//左边有序
                if(target >= A[left] && target < A[mid]){
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {//右边有序
                if(target > A[mid] && target <= A[right]){
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    /**
     * LC69:继续思考题目"Remove Duplicates":
     *
     * 如果数组中元素最多允许重复两次呢？
     * 例如：
     * 给出有序数组 A =[1,1,1,2,2,3],
     * 你给出的函数应该返回length =5,  A 变为[1,1,2,2,3].
     */
    public int removeDuplicates(int[] A){
        if(A.length <= 2){
            return A.length;
        }
        int count = 2;
        for(int i = 2; i < A.length; i++){
            if(A[i] != A[count - 2]){
                A[count++] = A[i];
            }
        }
        return count;
    }

    /**
     * LC82:给出用数字数组表示的一个非负整数，请对该整数加1。
     * 示例:
     * 输入 [1,2,3]
     * 输出 [1,2,4]
     */
    public int[] plusOne (int[] digits){
        if(digits == null || digits.length == 0){
            return digits;
        }
        int len = digits.length;
        for(int i = len - 1; i >= 0; i--){
            digits[i]++;
            digits[i] %= 10;
            if(digits[i] != 0){
                return digits;
            }
        }
        digits = new int[len + 1];
        digits[0] = 1;
        return digits;
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

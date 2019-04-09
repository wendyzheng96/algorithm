package com.zyf.algorithm.binaryfind;

import java.util.Scanner;

/**
 * Created by zyf on 2019/4/9.
 */
public class BinaryFind {

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

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextDouble()) {
            double num = sc.nextDouble();
            System.out.println(BinaryFind.sqrt(num));
        }
    }
}

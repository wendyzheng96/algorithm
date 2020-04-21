package com.zyf.algorithm.dynamic_plan;

/**
 * 0-1 背包问题
 */
public class Backpack0_1 {

    private int maxM = Integer.MIN_VALUE;

    private int[] weight = {2, 2, 4, 6, 3};

    private int n = 5; //物品个数

    private int w = 9; //背包承受的最大重量

    private boolean[][] mem = new boolean[5][10];

    /**
     * @param i 放置第i个物品
     * @param cw 背包内当前物品重量
     */
    public void f(int i, int cw) {
        if (cw == w || i == n) {
            if (cw > maxM) maxM = cw;
            return;
        }
        if (mem[i][cw]) {
            return;
        }
        mem[i][cw] = true;
        f(i + 1, cw);//选择不装第i个物品
        if (cw + weight[i] <= w) {
            f(i + 1, cw + weight[i]);//选择装第i个物品
        }
    }

    /**
     * 我们有 3 种不同的硬币，1 元、3 元、5 元，我们要支付 9 元，最少需要几个硬币。
     * @param money 金额数
     */
    private static int minCoinPlan(int money) {
        if (money == 1 || money == 3 || money == 5) {
            return 1;
        }
        if (money == 2 || money == 4) {
            return 2;
        }
        return 1 + Math.min(minCoinPlan(money - 1),
                Math.min(minCoinPlan(money - 3), minCoinPlan(money - 5)));
    }

    public static void main(String[] args) {
        for (int i = 6; i < 11; ++i) {
            System.out.println(Backpack0_1.minCoinPlan(i));
        }
    }
}

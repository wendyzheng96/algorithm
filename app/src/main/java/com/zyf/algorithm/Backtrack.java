package com.zyf.algorithm;

/**
 * 回溯算法
 */
public class Backtrack {

    int[] result = new int[8];

    private void cal8queens(int row) {
        if (row == 8) {
            printQueues(result);
            return;
        }
        for (int column = 0; column < 8; ++column) {
            if (isOk(row, column)) {
                result[row] = column;
                cal8queens(row + 1);
            }
        }
    }

    /**
     * 判断 row行column列放置是否合适
     * @param row 行
     * @param column 列
     * @return true:合适， false:不合适
     */
    private boolean isOk(int row, int column) {
        int leftUp = column - 1;
        int rightUp = column + 1;
        for (int i = row - 1; i >= 0; --i) {
            if (result[i] == column) return false;
            if (leftUp >= 0) {
                if (result[i] == leftUp) return false;
            }
            if (rightUp < 8) {
                if (result[i] == rightUp) return false;
            }
            --leftUp;
            ++rightUp;
        }
        return true;
    }

    private void printQueues(int[] result) {
        for (int row = 0; row < 8; ++row) {
            for (int column = 0; column < 8; ++column) {
                if (result[row] == column) System.out.print("Q ");
                else System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Backtrack backtrack = new Backtrack();
        backtrack.cal8queens(0);
    }
}

package com.zyf.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 动态规划
 */
public class DynamicProgramTest {

    /**
     * LC36:给定一个字符串S和一个字符串T，计算S中的T的不同子序列的个数。
     * 字符串的子序列是由原来的字符串删除一些字符（也可以不删除）在不改变相对位置的情况下的剩余字符
     * （例如，"ACE"is a subsequence of"ABCDE"但是"AEC"不是）
     * 例如：S ="rabbbit", T ="rabbit",返回3
     */
    public int numDistinct (String s, String t){
        if(s.length() < t.length() || s.length() == 0){
            return 0;
        }
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++){
            dp[i][0] = 1;
        }
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if(s.charAt(i - 1) == t.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    /**
     * LC70:给出一个二维字符数组和一个单词，判断单词是否在数组中出现，
     * 单词由相邻单元格的字母连接而成，相邻单元指的是上下左右相邻。同一单元格的字母不能多次使用。
     * 例如：
     * 给出的字符数组=
     * [↵  ["ABCE"],↵  ["SFCS"],↵  ["ADEE"]↵]
     * 单词 ="ABCCED", -> 返回 true,
     * 单词 ="SEE", ->返回 true,
     * 单词 ="ABCB", -> 返回 false.
     */
    public boolean exist(char[][] board, String word){
        if(board == null || board.length == 0 || board[0].length == 0){
            return false;
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if (find(board, word, m, n, i, j, 0, visited)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean find(char[][] board, String word, int m, int n, int i, int j,
                         int count, boolean[][] visited){
        if(count == word.length()){
            return true;
        }
        if(i < 0 || i >= m || j < 0 || j >= n){
            return false;
        }
        if(visited[i][j] || board[i][j] != word.charAt(count)){
            return false;
        }
        visited[i][j] = true;
        boolean isFind = find(board, word, m, n, i - 1, j, count + 1, visited)
                || find(board, word, m, n, i + 1, j, count + 1, visited)
                || find(board, word, m, n, i, j - 1, count + 1, visited)
                || find(board, word, m, n, i, j + 1, count + 1, visited);
        visited[i][j] = false;
        return isFind;
    }

    /**
     * LC71:现在有一个没有重复元素的整数集合S，求S的所有子集
     * 注意：
     * 你给出的子集中的元素必须按不下降的顺序排列
     * 给出的解集中不能出现重复的元素
     * 例如：
     * 如果S=[1,2,3], 给出的解集应为：
     * [↵  [3],↵  [1],↵  [2],↵  [1,2,3],↵  [1,3],↵  [2,3],↵  [1,2],↵  []↵]
     */
    public List<List<Integer>> subsets(int[] S){
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<Integer>());
        if(S.length == 0){
            return result;
        }
        Arrays.sort(S);
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <= S.length; i++){
            getSubsets(S, i, 0, result, list);
        }
        return result;
    }

    private void getSubsets(int[] S, int count, int start,
                            List<List<Integer>> result, List<Integer> list){
        if(count == 0){
            result.add(new ArrayList<>(list));
            return;
        }
        for(int i = start; i < S.length; i++){
            list.add(S[i]);
            getSubsets(S, count - 1, i + 1, result, list);
            list.remove(list.size() - 1);
        }
    }

    /**
     * LC72:给出两个整数n和k，返回从1到n中取k个数字的所有可能的组合
     * 例如：
     * 如果n=4，k=2，结果为
     * [↵  [2,4],↵  [3,4],↵  [2,3],↵  [1,2],↵  [1,3],↵  [1,4],↵]
     */
    public List<List<Integer>> combine (int n, int k){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        getCombines(n, k, 1, result, list);
        return result;
    }

    private void getCombines(int n, int k, int start, List<List<Integer>> result, List<Integer> list){
        if(k == 0){
            result.add(new ArrayList<>(list));
            return;
        }
        for(int i = start; i <= n - k + 1; i++){
            list.add(i);
            getCombines(n, k - 1, i + 1, result, list);
            list.remove(list.size());
        }
    }

    /**
     * LC77:edit_distance
     * 给定两个单词word1和word2，请计算将word1转换为word2至少需要多少步操作。
     * 你可以对一个单词执行以下3种操作：
     * a）在单词中插入一个字符
     * b）删除单词中的一个字符
     * c）替换单词中的一个字符
     */
    public int minDistance (String word1, String word2){
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++){
            dp[i][0] = i;
        }
        for(int j = 1; j <= n; j++){
            dp[j][0] = j;
        }
        for (int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(word1.charAt(i - 1) == word1.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int min = Math.min(dp[i - 1][j], dp[i][j - 1]);
                    dp[i][j] = Math.min(min, dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[m][n];
    }

    /**
     * LC86:给定一个由非负整数填充的m x n的二维数组，现在要从二维数组的左上角走到右下角，
     * 请找出路径上的所有数字之和最小的路径。
     * 注意：你每次只能向下或向右移动。
     */
    public int minPathSum (int[][] grid){
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for(int i = 1; i < m; i++){
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for(int j = 1; j < n; j++){
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * LC88:unique_path
     * 一个机器人在m×n大小的地图的左上角（起点，下图中的标记“start"的位置）。
     * 机器人每次向下或向右移动。机器人要到达地图的右下角。（终点，下图中的标记“Finish"的位置）。
     * 可以有多少种不同的路径从起点走到终点？
     */
    public int uniquePaths (int m, int n){
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++){
            dp[i][0] = 1;
        }
        for(int j = 1; j < n; j++){
            dp[0][j] = 1;
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * LC86:继续思考题目"Unique Paths":
     * 如果在图中加入了一些障碍，有多少不同的路径？
     * 分别用0和1代表空区域和障碍
     * 例如
     * 下图表示有一个障碍在3*3的图中央。
     * [↵  [0,0,0],↵  [0,1,0],↵  [0,0,0]↵]
     * 有2条不同的路径
     * 备注：m和n不超过100.
     */
    public int uniquePathsWithObstacles (int[][] obstacleGrid){
        if(obstacleGrid.length == 0 || obstacleGrid[0].length == 0){
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if(obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1){
            return 0;
        }
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            if (obstacleGrid[i][0] == 1){
                break;
            }
            dp[i][0] = 1;
        }
        for(int j = 1; j < n; j++){
            if(obstacleGrid[0][j] == 1){
                break;
            }
            dp[0][j] = 1;
        }
        for(int i = 1; i < m; i++){
            for(int j = 0; j < n; j++){
                if(obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * LC128:generate-parenthesis
     * 给出n对括号，请编写一个函数来生成所有的由n对括号组成的合法组合。
     * 例如，给出n=3，解集为：
     * "((()))", "(()())", "(())()", "()(())", "()()()"
     */
    public List<String> generateParenthesis (int n){
        List<String> list = new ArrayList<>();
        if(n == 0){
            return list;
        }
        generate(list, n, 0, 0, "");
        return list;
    }

    private void generate(List<String> list, int n, int left, int right, String s){
        if(right == n){
            list.add(s);
            return;
        }
        if(left < n){
            generate(list, n, left + 1, right, s + "(");
        }
        if(right < left && right < n){
            generate(list, n, left, right + 1, s + ")");
        }
    }
}

package com.zyf.algorithm.leetcode;

import com.zyf.algorithm.bean.UndirectedGraphNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Created by zyf on 2020/6/22.
 */
public class OtherTest {

    /**
     * LC17:环形路上有n个加油站，第i个加油站的汽油量是gas[i].
     * 你有一辆车，车的油箱可以无限装汽油。从加油站i走到下一个加油站（i+1）花费的油量是cost[i]，
     * 你从一个加油站出发，刚开始的时候油箱里面没有汽油。
     * 求从哪个加油站出发可以在环形路上走一圈。返回加油站的下标，如果没有答案的话返回-1。
     * 注意：答案保证唯一。
     */
    public int canCompleteCircuit (int[] gas, int[] cost){
        if(gas == null || gas.length == 0){
            return -1;
        }
        int start = gas.length - 1;
        int end = 0;
        int sum = gas[start] - cost[start];
        while (end < start){
            if(sum >= 0){
                sum += gas[end] - cost[end];
                end++;
            } else {
                start--;
                sum += gas[start] - cost[start];
            }
        }
        return sum >= 0 ? start : -1;
    }

    /**
     * LC18: 本题要求复制一个无向图，图中每个节点都包含一个标签和它的邻居列表
     * 我们无向图用以下的方法序列化：
     * 节点的标签是互不相同的，
     * 我们使用“#”作为节点之间的分隔符，使用“,”作为节点标签和节点的节点邻居的分隔符。
     * 例如：现在有一个序列化的无向图{0,1,2#1,2#2,2}.
     * 这个无向图一共有3个节点，因此序列被#分隔成三部分
     * 第一个节点的标签是0，节点0和节点1，节点2之间有边
     * 第二个节点的标签是1，节点1和节点2之间有边
     * 第三个节点的标签是2，节点2和节点2（它自己）之间有边，形成了自环
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node){
        if(node == null){
            return null;
        }
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        map.put(node, clone);

        Stack<UndirectedGraphNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()){
            UndirectedGraphNode temp = stack.pop();
            List<UndirectedGraphNode> list = new ArrayList<>();
            for(UndirectedGraphNode p : temp.neighbors){
                if(map.containsKey(p)){
                    list.add(map.get(p));
                } else {
                    stack.push(p);
                    UndirectedGraphNode q = new UndirectedGraphNode(p.label);
                    map.put(p, q);
                    list.add(q);
                }
            }
            map.get(temp).neighbors = list;
        }
        return clone;
    }

    /**
     * LC23:判断题目给出的字符串是不是回文，仅考虑字符串中的字母字符和数字字符，并且忽略大小写
     * 例如："A man, a plan, a canal: Panama"是回文
     * "race a car"不是回文
     * 注意：
     * 你有没有考虑过字符串可能为空？这是面试时应该提出的一个好问题。
     * 针对这个问题，我们定义空字符串是回文
     */
    public boolean isPalindrome (String s){
        if(s.length() <= 1){
            return true;
        }
        int left = 0;
        int right = s.length() - 1;
        while (left < right){
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))){
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))){
                right--;
            }
            if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 31:给出一个三角形，计算从三角形顶部到底部的最小路径和，每一步都可以移动到下面一行相邻的数字，
     * 例如，给出的三角形如下：
     * [↵   [2],↵
     *     [3,4],↵
     *    [6,5,7],↵
     *   [4,1,8,3]↵  ]
     * 最小的从顶部到底部的路径和是2 + 3 + 5 + 1 = 11。
     * 注意：
     * 如果你能只用O（N）的额外的空间来完成这项工作的话，就可以得到附加分，其中N是三角形中的行总数。
     */
    public int minimumTotal(List<List<Integer>> triangle){
        if(triangle.size() == 0){
            return 0;
        }
        for(int i = triangle.size() - 1; i >= 0; i--){
            for(int j = 0; j < triangle.get(i).size(); j++){
                int min = Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1));
                triangle.get(i).set(j, triangle.get(i).get(j) + min);
            }
        }
        return triangle.get(0).get(0);
    }

    /**
     * LC32:给出一个索引k，返回杨辉三角的第k行
     * 例如，k=3，
     * 返回[1,3,3,1].
     * 备注：
     * 你能将你的算法优化到只使用O(k)的额外空间吗?
     */
    public List<Integer> getRow (int rowIndex){
        List<Integer> list = new ArrayList<>();
        if (rowIndex < 0){
            return list;
        }
        for(int i = 0; i <= rowIndex; i++){
            for(int j = i - 1; j > 0; j--){
                list.set(j, list.get(j - 1) + list.get(j));
            }
            list.add(1);
        }
        return list;
    }

    /**
     * LC33:给出一个值numRows，生成杨辉三角的前numRows行
     * 例如，给出 numRows = 5,
     * 返回
     * [↵     [1],
     *  ↵    [1,1],
     *  ↵   [1,2,1],
     *  ↵  [1,3,3,1],
     *  ↵ [1,4,6,4,1]↵]
     */
    public List<List<Integer>> generate (int numRows){
        List<List<Integer>> list = new ArrayList<>();
        if(numRows < 0){
            return list;
        }
        for(int i = 0; i < numRows; i++){
            List<Integer> temp = new ArrayList<>();
            for(int j = 0; j <= i; j++){
                if(j == 0 || j == i){
                    temp.add(1);
                } else {
                    temp.add(list.get(i - 1).get(j - 1) + list.get(i - 1).get(j));
                }
            }
            list.add(temp);
        }
        return list;
    }

    /**
     * LC60:格雷码是一种二进制编码系统，如果任意两个相邻的代码只有一位二进制数不同，则称这种编码为格雷码（Gray Code）。
     * 给定一个非负整数n，表示代码的位数，打印格雷码的序列。格雷码序列必须以0开头。
     * 例如：给定n=2，返回[0,1,3,2]. 格雷码的序列为：
     * 00 - 0↵01 - 1↵11 - 3↵10 - 2
     * 注意：
     * 对于一个给定的n，格雷码的序列不一定是唯一的，
     * 例如：根据题目描述，[0,2,3,1]也是一个有效的格雷码序列
     */
    public List<Integer> grayCode (int n){
        List<Integer> list = new ArrayList<>();
        if(n == 0){
            return list;
        }
        list.add(0);
        for(int i = 0; i < n; i++){
            int len = list.size();
            for(int j = 0; j < len; j++){
                list.add(list.get(j) | (1 << i));
            }
        }
        return list;
    }

    /**
     * LC62:给定一个字符串 s1，我们可以把它递归地分割成两个非空子字符串，从而将其表示为二叉树。
     *
     * 下图是字符串 s1 = "great" 的一种可能的表示形式。
     *
     *     great
     *    /    \
     *   gr    eat
     *  / \    /  \
     * g   r  e   at
     *            / \
     *           a   t
     * 在扰乱这个字符串的过程中，我们可以挑选任何一个非叶节点，然后交换它的两个子节点。
     *
     * 例如，如果我们挑选非叶节点 "gr" ，交换它的两个子节点，将会产生扰乱字符串 "rgeat" 。
     *
     *     rgeat
     *    /    \
     *   rg    eat
     *  / \    /  \
     * r   g  e   at
     *            / \
     *           a   t
     * 我们将 "rgeat” 称作 "great" 的一个扰乱字符串。
     *
     * 同样地，如果我们继续交换节点 "eat" 和 "at" 的子节点，将会产生另一个新的扰乱字符串 "rgtae" 。
     *
     *     rgtae
     *    /    \
     *   rg    tae
     *  / \    /  \
     * r   g  ta  e
     *        / \
     *       t   a
     * 我们将 "rgtae” 称作 "great" 的一个扰乱字符串。
     *
     * 给出两个长度相等的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。
     */
    public boolean isScramble (String s1, String s2){
        if(s1.equals(s2)){
            return true;
        }
        if(s1.length() != s2.length()){
            return false;
        }
        int len = s1.length();
        int[] a = new int[128];
        for(int i = 0; i < len; i++){
            a[s1.charAt(i)]++;
            a[s2.charAt(i)]--;
        }
        for(int num : a){
            if(num != 0){
                return false;
            }
        }
        for(int i = 1; i < len; i++){
            boolean isMatch = (isScramble(s1.substring(0, i), s2.substring(0, i))
                    && isScramble(s1.substring(i), s2.substring(i)))
                    ||(isScramble(s1.substring(0, i), s2.substring(len - i))
                    && isScramble(s1.substring(i), s2.substring(0, len - i)));
            if(isMatch){
                return true;
            }
        }
        return false;
    }

    /**
     * LC73:给出两个字符串S和T，要求在O（n）的时间复杂度内在S中找出最短的包含T中所有字符的子串。
     * 例如：
     * S ="ADOBECODEBANC"
     * T ="ABC"
     * 找出的最短子串为"BANC".
     * 注意：
     * 如果S中没有包含T中所有字符的子串，返回空字符串 “”；
     * 满足条件的子串可能有很多，但是题目保证满足条件的最短的子串唯一。
     */
    public String minWindow (String S, String T){
        if(S.length() == 0 || S.length() < T.length()){
            return "";
        }
        int head = 0;
        int minLen = S.length() + 1;
        int count = T.length();
        int start = 0;
        int end = 0;
        int[] a = new int[128];
        for(int i = 0; i < T.length(); i++){
            a[T.charAt(i)]++;
        }
        while (end < S.length()){
            if(a[S.charAt(end)] > 0){
                count--;
            }
            a[S.charAt(end++)]--;
            while(count == 0){
                if(end - start < minLen){
                    minLen = end - start;
                    head = start;
                }
                if(a[S.charAt(start)] >= 0){
                    count++;
                }
                a[S.charAt(start++)]++;
            }
        }
        if(minLen > S.length()){
            return "";
        }
        return S.substring(head, head + minLen);
    }

    /**
     * LC80:实现函数 int sqrt(int x).
     * 计算并返回x的平方根
     */
    public int sqrt (int x){
        if(x < 0){
            return x;
        }
        if(x <= 1){
            return x;
        }
        long low = 1;
        long high = x / 2;
        while (low <= high){
            long mid = (low + high) / 2;
            if(x == mid * mid){
                return (int)mid;
            } else if( x < mid * mid){
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return (int)high;
    }

    /**
     * LC84:给出两个用字符串表示的二进制数，返回他们的和（也用字符串表示）
     * 例如：
     * a ="11", b ="1", 返回"100".
     */
    public String addBinary (String a, String b){
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while(i >= 0 || j >= 0 || carry != 0){
            if(i >= 0){
                carry += a.charAt(i--) - '0';
            }
            if(j >= 0){
                carry += b.charAt(j--) - '0';
            }
            sb.append(carry % 2);
            carry = carry >> 1;
        }
        return sb.reverse().toString();
    }

    /**
     * LC92:length-of-last-word
     * 给出一个只包含大小写字母和空格的字符串s，请返回字符串中最后一个单词的长度
     * 如果字符串中没有最后一个单词，则返回0
     * 注意：单词的定义是仅由非空格字符组成的字符序列。
     * 例如：
     * s ="Hello World",
     * 返回5。
     */
    public int lengthOfLastWord(String s){
        if(s == null || s.length() == 0){
            return 0;
        }
        int count = 0;
        int index = s.length() - 1;
        while (index >= 0 && s.charAt(index) == ' '){
            index--;
        }
        while (index >= 0 && s.charAt(index) != ' '){
            count++;
            index--;
        }
        return count;
    }

    /**
     * LC101:anagrams
     * 给出一个字符串数组，返回所有互为“换位词（anagrams）”的字符串的组合。
     * （换位词就是包含相同字母，但字母顺序可能不同的字符串）
     * 备注：所有的输入都是小写字母
     * 例如：
     * 输入["tea","nat","ate","eat","tan"]
     * 返回
     * [["ate", "eat","tea"],["nat","tan"]]
     */
    public List<List<String>> groupAnagrams(String[] strs){
        List<List<String>> list = new ArrayList<>();
        if(strs == null || strs.length == 0){
            return list;
        }
        HashMap<String, List<String>> map = new HashMap<>();
        for(String s : strs){
            char[] ch = s.toCharArray();
            Arrays.sort(ch);
            String baseStr = new String(ch);
            if (!map.containsKey(baseStr)){
                map.put(s, new ArrayList<String>());
            }
            map.get(baseStr).add(s);
        }
        for(String s : map.keySet()){
            list.add(map.get(s));
        }
        return list;
    }

    /**
     * LC112: count-and-say
     * 数列的前几项如下：
     * 1, 11, 21, 1211, 111221, ...
     * 1读作“1个1”或11
     * 11读作“2个1“或者21
     * 21读作”1个2，1个1“或者1211
     * 给出一个整数n，请给出序列的第n项
     * 注意：序列中的数字用字符串表示
     * 示例1
     * 输入:2,输出:"11"
     */
    public String countAndSay (int n){
        if(n <= 0){
            return "";
        }
        String result = "1";
        for(int i = 1; i < n; i++){
            StringBuilder sb = new StringBuilder();
            int count = 1;
            char ch = result.charAt(0);
            for(int j = 1; j < result.length(); j++){
                if(result.charAt(j) == ch){
                    count++;
                } else {
                    sb.append(count).append(ch);
                    count = 1;
                    ch = result.charAt(j);
                }
            }
            sb.append(count).append(ch);
            result = sb.toString();
        }
        return result;
    }

    /**
     * LC114:valid-sudoku
     * 根据数独的规则Sudoku Puzzles - The Rules.判断给出的局面是不是一个符合规则的数独局面
     * 数独盘面可以被部分填写，空的位置用字符'.'.表示
     */
    public boolean isValidSudoku(char[][] board){
        int n = board.length;
        for(int i = 0; i < n; i++){
            Set<Character> row = new HashSet<>();
            Set<Character> col = new HashSet<>();
            Set<Character> cube = new HashSet<>();
            for(int j = 0; j < n; j++){
                if(board[i][j] != '.' && !row.add(board[i][j])){
                    return false;
                }
                if(board[j][i] != '.' && !col.add(board[j][i])){
                    return false;
                }
                int cubeRow = 3 * (i / 3) + j / 3;
                int cubeCol = 3 * (i % 3) + j % 3;
                if(board[cubeRow][cubeCol] != '.' && !col.add(board[cubeRow][cubeCol])){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * LC119:实现函数next permutation（下一个排列）：
     * 将排列中的数字重新排列成字典序中的下一个更大的排列。将排列中的数字重新排列成字典序中的下一个更大的排列。
     * 如果不存在这样的排列，则将其排列为字典序最小的排列（升序排列）
     * 需要使用原地算法来解决这个问题，不能申请额外的内存空间
     * 下面有机组样例，左边是输入的数据，右边是输出的答案
     * 1,2,3→1,3,2
     * 3,2,1→1,2,3
     * 1,1,5→1,5,1
     */
    public void nextPermutation(int[] num){
        if(num == null || num.length <= 1){
            return;
        }
        int len = num.length;
        int i = len - 2;
        while(i >= 0 && num[i] >= num[i + 1]){
            i--;
        }
        if(i == -1){
            reverse(num, 0, len - 1);
            return;
        }
        int j = len - 1;
        while (j > i && num[j] <= num[i]){
            j--;
        }
        swap(num, i, j);
        reverse(num, i + 1, len - 1);
    }

    public void reverse(int[] num, int start, int end){
        while (start < end){
            swap(num, start++, end--);
        }
    }

    private void swap(int[] num, int i, int j){
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }

    /**
     * LC140:在不使用额外的内存空间的条件下判断一个整数是否是回文
     * 提示：
     * 负整数可以是回文吗？（比如-1）
     * 如果你在考虑将数字转化为字符串的话，请注意一下不能使用额外空间的限制
     * 你可以将整数翻转。但是，如果你做过题目“Reverse Integer”，你会知道将整数翻转可能会出现溢出的情况，你怎么处理这个问题？
     * 这道题有更具普遍性的解法。
     */
    public boolean isPalindrome (int x){
        if(x >= 0 && x < 10){
            return true;
        }
        if(x < 0 || x % 10 == 0){
            return false;
        }
        int num = 0;
        while(num < x){
            num = num * 10 + x % 10;
            x /= 10;
        }
        return x == num || x == num / 10;
    }

    /**
     * LC142:将给出的整数x翻转。
     * 例1:x=123，返回321
     * 例2:x=-123，返回-321
     *
     * 你有思考过下面的这些问题么？
     * 如果整数的最后一位是0，那么输出应该是什么？比如10,100
     * 你注意到翻转后的整数可能溢出吗？假设输入是32位整数，则将翻转10000000003就会溢出，你该怎么处理这样的样例？
     * 抛出异常？这样做很好，但是如果不允许抛出异常呢？这样的话你必须重新设计函数（比如添加一个额外的参数）。
     */
    public int reverse (int x){
        long num = 0;
        while(x != 0){
            num = num * 10 + x % 10;
            x /= 10;
        }
        if(num > Integer.MAX_VALUE || num < Integer.MIN_VALUE){
            return 0;
        }
        return (int)num;
    }
}

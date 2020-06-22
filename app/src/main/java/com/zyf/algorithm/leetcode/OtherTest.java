package com.zyf.algorithm.leetcode;

import com.zyf.algorithm.bean.UndirectedGraphNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
}

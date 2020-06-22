package com.zyf.algorithm.leetcode;

import com.zyf.algorithm.bean.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树相关算法
 */
public class TreeTest {

    /**
     * LC1:求给定二叉树的最小深度。最小深度是指树的根结点到最近叶子结点的最短路径上结点的数量。
     */
    public int getMinDepth (TreeNode root){
        if(root == null){
            return 0;
        }
        int left = getMinDepth(root.left);
        int right = getMinDepth(root.right);
        if(root.left == null || root.right == null){
            return  left + right + 1;
        }
        return Math.min(left, right) + 1;
    }

    /**
     * LC6:求给定的二叉树的后序遍历,使用迭代解法
     */
    public List<Integer> postOrderTraversal (TreeNode root){
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode temp = stack.pop();
            if(temp.left != null){
                stack.push(temp.left);
            }
            if(temp.right != null){
                stack.push(temp.right);
            }
            list.add(0, temp.val);
        }
        return list;
    }

    /**
     * LC7:求给定的二叉树的前序遍历,使用迭代解法
     */
    public List<Integer> preOrderTraversal (TreeNode root){
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode temp = stack.pop();
            list.add(temp.val);
            if (temp.right != null){
                stack.push(temp.right);
            }
            if (temp.left != null) {
                stack.push(temp.left);
            }
        }
        return list;
    }

    /**
     * LC22:给定一个仅包含数字0-9的二叉树，每一条从根节点到叶子节点的路径都可以用一个数字表示。
     * 例如根节点到叶子节点的一条路径是1->2->3,那么这条路径就用123来代替。
     * 找出根节点到叶子节点的所有路径表示的数字之和
     * 例如：
     *     1↵   / ↵  2   3
     * 根节点到叶子节点的路径1->2用数字12代替
     * 根节点到叶子节点的路径1->3用数字13代替
     * 所以答案为12+13=25
     */
    public int sumNumbers(TreeNode root){
        return sumNumbers(root, 0);
    }

    private int sumNumbers(TreeNode root, int sum){
        if(root == null){
            return 0;
        }
        sum = sum * 10 + root.val;
        if(root.left == null && root.right == null){
            return sum;
        }
        return sumNumbers(root.left, sum) + sumNumbers(root.right, sum);
    }

    /**
     * 判断两个二叉树是否互为镜像
     */
    public boolean equalsNode(TreeNode head1, TreeNode head2){
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1 != null && head2 != null && head1.val == head2.val) {
            return equalsNode(head1.left, head2.right) && equalsNode(head1.right, head2.left);

        }
        return false;
    }

}

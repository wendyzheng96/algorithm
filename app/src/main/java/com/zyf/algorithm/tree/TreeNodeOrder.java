package com.zyf.algorithm.tree;

import com.zyf.algorithm.bean.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 二叉树前序、中序、后序
 */
public class TreeNodeOrder {

    /**
     * 前序
     */
    public ArrayList<Integer> preOrderTraversal (TreeNode root) {
        // write code here
        Stack<TreeNode> stack = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        stack.push(root);
        while(!stack.isEmpty()){
            root = stack.pop();
            list.add(root.val);
            if(root.right != null){
                stack.push(root.right);
            }
            if(root.left != null){
                stack.push(root.left);
            }
        }
        return list;
    }

    /**
     * 中序
     */
    public ArrayList<Integer> inOrderTraversal (TreeNode root) {
        // write code here
        Stack<TreeNode> stack = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()){
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                list.add(cur.val);
                cur = cur.right;
            }
        }
        return list;
    }

    /**
     * 后序
     */
    public ArrayList<Integer> postOrderTraversal (TreeNode root) {
        // write code here
        Stack<TreeNode> stack = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            if(node.left != null){
                stack.push(node.left);
            }
            if(node.right != null){
                stack.push(node.right);
            }
            list.add(0, node.val);
        }
        return list;
    }
}

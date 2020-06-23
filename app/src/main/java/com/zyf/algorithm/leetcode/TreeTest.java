package com.zyf.algorithm.leetcode;

import com.zyf.algorithm.bean.TreeLinkNode;
import com.zyf.algorithm.bean.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
     * LC35:给定一个二叉树
     * struct TreeLinkNode {↵
     *     TreeLinkNode *left;↵
     *     TreeLinkNode *right;↵
     *     TreeLinkNode *next;↵
     * }
     * 填充所有节点的next指针，指向它右兄弟节点。如果没有右兄弟节点，则应该将next指针设置为NULL。
     * 初始时，所有的next指针都为NULL
     * 注意：
     * 你只能使用常量级的额外内存空间
     * 可以假设给出的二叉树是一个完美的二叉树(即，所有叶子节点都位于同一层，而且每个父节点都有两个孩子节点)。
     */
    public void connect(TreeLinkNode root){
        if(root == null){
            return;
        }
        if(root.left != null && root.right != null){
            root.left.next = root.right;
        }
        if(root.right != null && root.next != null){
            root.right.next = root.next.left;
        }
        connect(root.left);
        connect(root.right);
    }

    /**
     * LC34:继续思考"Populating Next Right Pointers in Each Node".这道题
     * 如果给定的树可以是任意的二叉树呢?你之前的给出的算法还有效吗?
     * 注意：
     * 1.你只能使用常量的额外内存空间。
     * 2.使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
     */
    public void connect2(TreeLinkNode root){
        if(root == null){
            return;
        }
        TreeLinkNode node = root;
        while (node != null){
            TreeLinkNode pre = new TreeLinkNode(-1);
            TreeLinkNode cur = pre;
            while (node != null){
                if(node.left != null){
                    cur.next = node.left;
                    cur = cur.next;
                }
                if(node.right != null){
                    cur.next = node.right;
                    cur = cur.next;
                }
                node = node.next;
            }
            node = pre.next;
        }
    }

    /**
     * LC38:给定一个二叉树和一个值sum，判断是否有从根节点到叶子节点的节点值之和等于sum的路径，
     * 例如：
     * 给出如下的二叉树，sum=22，
     *                5
     *              / \
     *             4   8
     *            /   / \
     *           11  13  4
     *          /  \      \
     *         7    2      1
     * 返回true，因为存在一条路径5->4->11->2的节点值之和为22
     */
    public boolean hasPathSum (TreeNode root, int sum){
        if(root == null){
            return false;
        }
        sum -= root.val;
        if(root.left == null && root.right == null){
            return sum == 0;
        }
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }

    /**
     * LC37:给定一个二叉树和一个值sum，请找出所有的根节点到叶子节点的节点值之和等于sum的路径，
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        getPath(root, sum, result, list);
        return result;
    }

    private void getPath(TreeNode root, int sum, List<List<Integer>> result, List<Integer> list){
        if(root == null){
            return;
        }
        sum -= root.val;
        list.add(root.val);
        if(root.left == null && root.right == null && sum == 0){
            result.add(new ArrayList<>(list));
            return;
        }
        if(root.left != null){
            getPath(root, sum, result, list);
            list.remove(list.size() - 1);
        }
        if(root.right != null){
            getPath(root, sum, result, list);
            list.remove(list.size() - 1);
        }
    }

    /**
     * LC39:判断给定的二叉树是否是平衡的
     * 在这个问题中，定义平衡二叉树为每个节点的左右两个子树高度差的绝对值不超过1的二叉树
     */
    private boolean isBalances = true;

    public boolean isBalanced (TreeNode root){
        if(root == null){
            return true;
        }
        getDepth(root);
        return isBalances;
    }

    private int getDepth(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        if(Math.abs(left - right) > 1){
            isBalances = false;
        }
        return Math.max(left, right) + 1;
    }

    /**
     * LC42:给定一个二叉树，返回该二叉树由底层到顶层的层序遍历，（从左向右，从叶子节点到根节点，一层一层的遍历）
     */
    public List<List<Integer>> levelOrderBottom (TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int len = queue.size();
            List<Integer> temp = new ArrayList<>();
            for(int i = 0; i < len; i++){
                TreeNode node = queue.poll();
                temp.add(node.val);
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
            result.add(0, temp);
        }
        return result;
    }

    /**
     * LC43:给出一棵树的中序遍历和后序遍历，请构造这颗二叉树
     * 注意：
     * 保证给出的树中不存在重复的节点
     */
    private HashMap<Integer, Integer> map;
    public TreeNode buildTree (int[] inOrder, int[] postOrder){
        if(inOrder == null || postOrder == null || inOrder.length == 0
                || inOrder.length != postOrder.length){
            return null;
        }
        int len = inOrder.length;
        map = new HashMap<>();
        for (int i = 0; i < len; i++){
            map.put(inOrder[i], i);
        }
        return build(inOrder, postOrder, 0, len - 1, 0, len - 1);
    }

    private TreeNode build(int[] inOrder, int[] postOrder, int inLeft, int inRight,
                           int postLeft, int postRight){
        if(inLeft > inRight){
            return null;
        }
        TreeNode root = new TreeNode(postOrder[postRight]);
        int i = map.get(root.val);
        int leftLen = i - inLeft;
        root.left = build(inOrder, postOrder, inLeft, i - 1, postLeft,
                postLeft + leftLen - 1);
        root.right = build(inOrder, postOrder, i + 1, inRight, postLeft + leftLen,
                postRight - 1);
        return root;
    }

    /**
     * LC44:给出一棵树的前序遍历和中序遍历，请构造这颗二叉树
     */
    public TreeNode buildTree2 (int[] preOrder, int[] inorder){
        if(preOrder == null || inorder == null || preOrder.length == 0
                || preOrder.length != inorder.length){
            return null;
        }
        int len = inorder.length;
        map = new HashMap<>();
        for (int i = 0; i < len; i++){
            map.put(inorder[i], i);
        }
        return build(preOrder, inorder, 0, len - 1, 0, len - 1);
    }

    private TreeNode build2(int[] preOrder, int[] inorder, int preLeft, int preRight,
                           int inLeft, int inRight){
        if(preLeft > preRight){
            return null;
        }
        TreeNode root = new TreeNode(preOrder[preLeft]);
        int i = map.get(root.val);
        int leftLen = i - inLeft;
        root.left = build(preOrder, inorder, preLeft + 1, preLeft + leftLen,
                inLeft, i - 1);
        root.right = build(preOrder, inorder, preLeft + leftLen + 1, preRight,
                i + 1, inRight);
        return root;
    }

    /**
     * LC45:求给定二叉树的最大深度，
     * 最大深度是指树的根结点到最远叶子结点的最长路径上结点的数量。
     */
    public int maxDepth (TreeNode root){
        if(root == null){
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * LC46:给定一个二叉树，返回该二叉树的之字形层序遍历，（从左向右，下一层从右向左，一直这样交替）
     */
    public List<List<Integer>> zigzagLevelOrder (TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        boolean isFromLeft = true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int len = queue.size();
            List<Integer> temp = new ArrayList<>();
            for(int i = 0; i < len; i++){
                TreeNode node = queue.poll();
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
                if (isFromLeft){
                    temp.add(node.val);
                } else {
                    temp.add(0, node.val);
                }
            }
            result.add(temp);
            isFromLeft = !isFromLeft;
        }
        return result;
    }

    /**
     * LC47:给定一个二叉树，返回该二叉树层序遍历的结果，（从左到右，一层一层地遍历）
     */
    public List<List<Integer>> levelOrder (TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int len = queue.size();
            List<Integer> temp = new ArrayList<>();
            for(int i = 0; i < len; i++){
                TreeNode node = queue.poll();
                temp.add(node.val);
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
            result.add(temp);
        }
        return result;
    }

    /**
     * LC48:给定一棵二叉树，判断琪是否是自身的镜像（即：是否对称）
     */
    public boolean isSymmetric (TreeNode root){
        if(root == null){
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode head1, TreeNode head2){
        if (head1 == null && head2 == null) {
            return true;
        }
        if(head1 == null || head2 == null){
            return false;
        }
        if (head1.val != head2.val) {
            return false;
        }
        return isSymmetric(head1.left, head2.right) && isSymmetric(head1.right, head2.left);
    }

    /**
     * LC49:给出两个二叉树，请写出一个判断两个二叉树是否相等的函数。
     * 判断两个二叉树相等的条件是：两个二叉树的结构相同，并且相同的节点上具有相同的值。
     */
    public boolean isSameTree (TreeNode p, TreeNode q){
        if(p == null && q == null){
            return true;
        }
        if(p == null || q == null || p.val != q.val){
            return false;
        }
        return isSymmetric(p.left, q.left) && isSymmetric(p.right, q.right);
    }
}

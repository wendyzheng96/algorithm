package com.zyf.algorithm.test.linkedlist06;

import java.util.Scanner;

/**
 * 基于单链表实现LRU缓存算法
 */
public class LRUBaseLinkedList<T> {

    /**
     * 默认链表容量
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 链表头结点
     */
    private SNode<T> headNode;

    /**
     * 链表长度
     */
    private int length;

    /**
     * 链表容量
     */
    private int capacity;

    public LRUBaseLinkedList() {
        this.headNode = new SNode<>();
        this.length = 0;
        this.capacity = DEFAULT_CAPACITY;
    }

    public LRUBaseLinkedList(int capacity) {
        this.headNode = new SNode<>();
        this.length = 0;
        this.capacity = capacity;
    }

    /**
     * 添加元素
     */
    public void add(T data) {
        SNode<T> preNode = findPreNode(data);

        //链表已存在，删除原数据，再添加到链表头部
        if (preNode != null) {
            deleteElem(preNode);
            insertElemAtBegin(data);
        } else {
            if (length >= capacity) {
                //链表已满，删除尾结点
                deleteElemAtEnd();
            }
            insertElemAtBegin(data);
        }
    }

    /**
     * 删除preNode结点的下一个结点
     */
    private void deleteElem(SNode<T> preNode) {
        preNode.setNext(preNode.getNext().getNext());
        length--;
    }

    /**
     * 删除尾结点
     */
    private void deleteElemAtEnd() {
        SNode<T> tmp = headNode;
        //空链表直接返回
        if (tmp.getNext() == null) {
            return;
        }
        while (tmp.getNext().getNext() != null) {
            tmp = tmp.getNext();
        }
        tmp.setNext(null);
        length--;
    }

    /**
     * 在链表头部插入元素
     */
    private void insertElemAtBegin(T data) {
        SNode<T> next = headNode.getNext();
        headNode.setNext(new SNode<>(data, next));
        length++;
    }

    /**
     * 获取查找到元素的前一个结点
     * @param data 查找元素
     */
    private SNode<T> findPreNode(T data) {
        SNode<T> node = headNode;
        while (node.getNext() != null) {
            if (data.equals(node.getNext().getElement())) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    private void printAll() {
        SNode<T> node = headNode.getNext();
        while (node != null) {
            System.out.print(node.getElement() + ", ");
            node = node.getNext();
        }
        System.out.println();
    }

    public static void main(String[] args){
        LRUBaseLinkedList<Integer> list = new LRUBaseLinkedList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            list.add(sc.nextInt());
            list.printAll();
        }
    }
}

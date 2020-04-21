package com.zyf.algorithm.test.linkedlist06;

/**
 * Created by zyf on 2019/3/23.
 */
public class SNode<T> {

    private T element;
    private SNode<T> next;

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public SNode<T> getNext() {
        return next;
    }

    public void setNext(SNode<T> next) {
        this.next = next;
    }

    public SNode(){

    }

    public SNode(T element, SNode<T> next) {
        this.element = element;
        this.next = next;
    }
}

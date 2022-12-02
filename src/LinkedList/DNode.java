/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkedList;

/**
 *
 * @author lephu
 */
public class DNode<T> {
    private T data;
    private DNode pre, next;

    public DNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public DNode getPre() {
        return pre;
    }

    public void setPre(DNode pre) {
        this.pre = pre;
    }

    public DNode getNext() {
        return next;
    }

    public void setNext(DNode next) {
        this.next = next;
    }
    
}

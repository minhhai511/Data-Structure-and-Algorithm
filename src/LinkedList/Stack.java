/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkedList;

/**
 *
 * @author win
 */
public class Stack<T> {

    private SingleLinkedList<T> innerList;

    public Stack() {
        innerList = new SingleLinkedList<>();
    }

    public SingleLinkedList<T> getInnerList() {
        return innerList;
    }

    public void setInnerList(SingleLinkedList<T> innerList) {
        this.innerList = innerList;
    }

    public boolean isEmpty() {
        return innerList.isEmpty();
    }

    public void clear() {
        innerList.Clear();
    }

    public void push(T data) {
        innerList.AddToHead(data);
    }


    public T pop() {
        if (innerList.isEmpty()) {
            return null;
        } else {
            T data = innerList.getHead().getData();
            innerList.DeleteHead();
            return data;
        }
    }

    public T top() {
        if (innerList.isEmpty()) {
            return null;
        }
        T data = innerList.getHead().getData();
        return data;
    }

}

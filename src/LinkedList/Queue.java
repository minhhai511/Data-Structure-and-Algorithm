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
public class Queue<T> {

    SingleLinkedList<T> innerList;

    public Queue() {
        innerList = new SingleLinkedList<>();
    }

    public boolean isEmpty() {
        return innerList.isEmpty();
    }

    public void Clear() {
        innerList.Clear();
    }

    public void Enqueue(T data) {//day data vao queue
        innerList.AddToTail(data);
    }

    public T Dequeue()//lay data ra khoi queue
    {
        if (innerList.isEmpty()) {
            return null;
        }
        T data = innerList.getHead().getData();
        innerList.DeleteHead();
        return data;
    }

}
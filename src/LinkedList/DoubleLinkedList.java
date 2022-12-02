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
public class DoubleLinkedList<T> {

    private DNode<T> head, tail;

    public DoubleLinkedList() {
    }

    public DNode<T> getHead() {
        return head;
    }

    public void setHead(DNode<T> head) {
        this.head = head;
    }

    public DNode<T> getTail() {
        return tail;
    }

    public void setTail(DNode<T> tail) {
        this.tail = tail;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void Clear() {
        head = tail = null;
    }

    public void AddToHead(T x) {
        DNode n = new DNode(x);
        if (isEmpty()) {
            head = tail = n;
        } else {
            n.setNext(head);
            head.setPre(n);
            head = n;
        }
    }

    public void AddAfterHead(T x) {
        DNode n = new DNode(x);
        if (isEmpty()) {
            System.out.println("Cannot add!");
        } else {
            if (head == tail) {
                head.setNext(n);
                n.setPre(head);
                tail = n;
            } else {
                DNode nextOfHead = head.getNext();
                n.setNext(nextOfHead);
                n.setPre(head);
                head.setNext(n);
                nextOfHead.setPre(n);
            }
        }
    }

    public void AddToTail(T x) {
        DNode n = new DNode(x);
        if (isEmpty()) {
            head = tail = n;
        } else {
            tail.setNext(n);
            n.setPre(tail);
            tail = n;
        }
    }

    public void TraverseFromHead() {
        System.out.println("Traverse from Head:");
        if (isEmpty()) {
            System.out.println("Empty");
            return;
        }
        DNode n = head;
        while (n != null) {
            System.out.print(n.getData() + " ");
            n = n.getNext();
        }
        System.out.println("");
    }

    public void TraverseFromTail() {
        System.out.println("Traverse from Tail:");
        if (isEmpty()) {
            System.out.println("Empty");
            return;
        }
        DNode n = tail;
        while (n != null) {
            System.out.println(n.getData());
            n = n.getPre();
        }
    }

    public void AddAfter(DNode p, T x) {
        DNode q = getNodeByData((T) p.getData());
        if (q != null) {
            DNode n = new DNode(x);
            if (q == head) {
                AddAfterHead(x);
            } else if (q == tail) {
                AddToTail(x);
            } else {
                DNode nexOfQ = q.getNext();
                n.setNext(nexOfQ);
                n.setPre(q);
                q.setNext(n);
                nexOfQ.setPre(n);
            }
        } else {
            System.out.println("The node doesn't exist!");
        }
    }

    public void AddAfter(T x, T y) {
        DNode q = getNodeByData(x);
        AddAfter(q, x);
    }

    public void DeleteNode(DNode p) {
        DNode q = getNodeByData((T) p.getData());
        if (q != null) {
            if (q == head) {
                DeleteHead();
            } else if (q == tail) {
                DeleteTail();
            } else {
                DNode preOfQ = q.getPre();
                DNode nextOfQ = q.getNext();
                preOfQ.setNext(nextOfQ);
                nextOfQ.setPre(preOfQ);
            }
        } else {
            System.out.println("The node doesn't exist!");
        }
    }

    public void DeleteNode(T x) {
        DNode q = getNodeByData(x);
        DeleteNode(q);
    }

    public void DeleteHead() {
        if (isEmpty()) {
            System.out.println("Empty");
            return;
        }
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.getNext();
            head.setPre(null);
        }
    }

    public void DeleteTail() {
        DNode a = head;
        if (isEmpty()) {
            System.out.println("Empty");
            return;
        }
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.getPre();
            tail.setNext(null);
        }
    }

    public DNode getNodeByData(T x) {
        if (isEmpty()) {
            return null;
        }
        DNode n = head;
        while (n != null) {
            if (n.getData().equals(x)) {
                return n;
            }
            n = n.getNext();
        }
        return null;
    }
}

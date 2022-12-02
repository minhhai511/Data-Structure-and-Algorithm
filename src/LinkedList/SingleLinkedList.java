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
public class SingleLinkedList<T> {

    private Node<T> head, tail;

    public SingleLinkedList() {
    }

    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public void setTail(Node<T> tail) {
        this.tail = tail;
    }

    Boolean isEmpty() {
        return head == null;
    }

    public void Clear() {
        head = tail = null;
    }

    public int size() {
        if (isEmpty()) {
            return 0;
        }
        int count = 1;
        Node<T> start = head;
        Node<T> end = tail;
        while (start != end) {
            start = start.getNext();
            count++;
        }
        return count;
    }

    public void AddToTail(T x) {
        Node<T> n = new Node(x);//1 - tao node moi
        if (isEmpty()) {
            head = tail = n;//neu ds dang rong thi them 1 node chinh la node duy nhat trong ds
        } else {
            tail.setNext(n); //2 - noi n vao sau tail
            tail = n;//3 - cap nhat lai tail
        }
    }

    public void AddToHead(T x) {
        Node<T> n = new Node(x);//1
        n.setNext(head);//2
        if (isEmpty()) {
            head = tail = n;
        } else {
            head = n;//3
        }
    }

    public void Traverse() {
        if (isEmpty()) {
            System.out.println("Empty");
            return;
        }
        Node<T> p = head;
        while (p != null) {
            System.out.println(p.getData() + ", ");
            p = p.getNext();//p nhay sang node tiep theo trong danh sach.
        }
    }

    public T GetDataByIndex(int index) {
        int i = 0;
        Node<T> n = head;
        while (true) {
            if (n == null) {
                return null;
            }
            if (i == index) {
                return n.getData();
            }
            n = n.getNext();
            i++;
        }
    }

    public void AddNodeAfter(Node p, T x) {

        if (p == null) {
            return;
        }
        Node<T> n = new Node<>(x);
        n.setNext(p.getNext());
        p.setNext(n);
        if (p == tail) {
            tail = n;
        }
    }

    //add node co du lieu x vao sau node co du lieu y
    public void AddNodeAfter(T x, T y) {
        Node n = head;
        while (n != null) {
            if (n.getData().equals(y)) {
                break;
            }
            n = n.getNext();
        }
        if (n != null)//truong hop tim dc node co du lieu la y
        {
            AddNodeAfter(n, x);
        }
    }

    public void DeleteHead() {
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.getNext();
        }
    }

    public void DeleteNode(Node p) {
        if (p == null) {
            return;
        }
        if (p == head) {
            DeleteHead();
            return;
        }
        Node<T> q = head;
        while (q != null && q.getNext() != p) {
            q = q.getNext();
        }
        if (q != null) {
            q.setNext(p.getNext());
            if (tail == p) {
                tail = q;
            }
        }
    }

    public void DeleteAllNodeByData(T x) {
        if (isEmpty()) {
            return;
        }
        Node n = head, pre = null;
        while (true) {
            if (n == null) {
                break;
            }
            //kiem tra day co phai node can xoa khong?
            if (n.getData().equals(x)) //can xoa
            {
                if (n == head) {
                    DeleteHead();
                    n = head; //day head moi, ko phai head ban nay
                } else {
                    pre.setNext(n.getNext());
                    if (n == tail) {
                        tail = pre;
                        break;
                    }
                    n = pre.getNext();//nhay node tiep theo
                }
            } else {
                pre = n;
                n = n.getNext();
            }
        }
    }

}

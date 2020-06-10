package ch01;

import java.util.Iterator;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/4/28
 *  \* Time: 8:52
 *  \* To change this template use File | Settings | File Templates.
 *  \* Description: 
 *  \
 */
public class Queue<Item> implements Iterable<Item>{
    private Node front;
    private Node last;
    private int N;

    private class Node {
        Item item;
        Node next;

        Node(Item item) {
            this.item = item;
            next = null;
        }
    }
    public Queue(){

    }
    // ex 1.3.41的答案，从q中构造一个Queue
    public Queue(Queue<Item> q){
        Queue<Item> r=new Queue<>();
        while (!q.isEmpty()) {
           r.enqueue(q.dequeue());
        }
        N=r.size();
        for (int i = 0; i < N; i++) {
            Item item=r.dequeue();
            q.enqueue(item);
            r.enqueue(item);
        }
    }

    public void enqueue(Item item) {
        Node n = new Node(item);
        if(isEmpty())
            front=n; //如果是队列，设置front为新结点
        else
            last.next = n;  //如果不是空队列，直接设置last的next指针为新结点
        last = n;   //更新last
        N++;
    }

    public Item dequeue() {
        Node first = front;
        front = front.next;
        if(isEmpty())
            last=null;  //出队后，如果是空队列，那么last变为null，front因为上一条语句也变为null
        N--;
        return first.item;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }
    public Iterator<Item> iterator(){
        return new QueueIterator();
    }
    private class QueueIterator implements Iterator<Item>{
        private Node cur=front;
        @Override
        public boolean hasNext() {
            return !(cur==null);
        }

        @Override
        public Item next() {
            Item item=cur.item;
            cur=cur.next;
            return item;
        }

        @Override
        public void remove() { }
    }
    public static void main(String[] args) {
        Queue<Integer> que=new Queue<>();
        for (int i = 0; i < 5; i++) {
            que.enqueue(i+1);
        }
//        que.dequeue();
//        System.out.println(que.dequeue());
//        System.out.println(que.size());
        for(int i : que){
            System.out.println(i);
        }
    }
}

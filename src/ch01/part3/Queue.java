package ch01.part3;

import java.util.Iterator;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/4/28
 *  \* Time: 8:52
 *  \* To change this template use File | Settings | File Templates.
 *  \* Description: answer for ex1.3.29, Queue implemented by linklist which has front and last pointer
 *  \
 */
public class Queue<Item> implements Iterable<Item> {
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

    public Queue() {

    }

//     answer for ex 1.3.41
    public Queue(Queue<Item> q) {
        N = q.size();
        front = new Node(null);
        last = front;
        for (Item item : q) {
            Node t = new Node(item);
            last.next = t;
            last = t;
        }
        front = front.next;
    }

    public void enqueue(Item item) {
        Node n = new Node(item);
        if (isEmpty())
            front = n; //如果是队列，设置front为新结点
        else
            last.next = n;  //如果不是空队列，直接设置last的next指针为新结点
        last = n;   //更新last
        N++;
    }

    public Item dequeue() {
        Node first = front;
        front = front.next;
        if (isEmpty())
            last = null;  //出队后，如果是空队列，那么last变为null，front因为上一条语句也变为null
        N--;
        return first.item;
    }

    public Item front() {
        Item item = null;
        try {
            item = getFront().item;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    private Node getFront() {
        return front;
    }

    private Node getLast() {
        Node t = front;
        while (t.next != null) {
            t = t.next;
        }
        return t;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
        private Node cur = front;

        @Override
        public boolean hasNext() {
            return !(cur == null);
        }

        @Override
        public Item next() {
            Item item = cur.item;
            cur = cur.next;
            return item;
        }

        @Override
        public void remove() {
        }
    }

    // ex 1.3.47
    // 将r里面的元素链接到队尾
    public void catenation(Queue<Item> r) {
        for (Item item : r) {
            this.enqueue(item);
        }
    }


    public static void main(String[] args) {
//        Queue<Integer> que = new Queue<>();
//        for (int i = 0; i < 5; i++) {
//            que.enqueue(i + 1);
//        }
//        System.out.println("q:");
//        que.forEach(System.out::println);
////        que.dequeue();
////        System.out.println(que.dequeue());
////        System.out.println(que.size());
//        Queue<Integer> r = new Queue<>(que);
//        System.out.println("r:");
//        r.forEach(System.out::println);
//        r.enqueue(42);
//        r.enqueue(3);
//
//        System.out.println("r:");
//        r.forEach(System.out::println);
//
//        System.out.println("q:");
//        que.forEach(System.out::println);

        // ex 1.3.47 test
//        Queue<Integer> q=new Queue<>();
//        Queue<Integer> r=new Queue<>();
//        q.enqueue(1);
//        q.enqueue(2);
//        r.enqueue(-1);
//        r.enqueue(-3);
//        q.catenation(r);
//        q.forEach(System.out::println);
    }
}

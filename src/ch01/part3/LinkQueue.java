package ch01.part3;

import java.util.Iterator;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/5/1
 *  \* Time: 11:28
 *  \* To change this template use File | Settings | File Templates.
 *  \* Description: 
 *  \
 */
public class LinkQueue<Item> implements Iterable<Item> {
    @Override
    public Iterator<Item> iterator() {
        return new LinkQueueIterator();
    }

    private class LinkQueueIterator implements Iterator<Item> {
        private int n = N;
        private Node f = last == null ? null : last.next;

        @Override
        public boolean hasNext() {
            return n != 0;
        }

        @Override
        public Item next() {
            Item item = f.item;
            f = f.next;
            n--;
            return item;
        }

        @Override
        public void remove() {

        }
    }

    private int N;
    private Node last;

    private class Node {
        private Item item;
        private Node next;

        Node(Item item) {
            this.item = item;
            this.next = null;
        }
    }

    LinkQueue() {
        last = null;
        N = 0;
    }

    LinkQueue(Item[] items) {
        // use array construct LinkQueue
        N = items.length;
        Node first = new Node(items[0]);
        Node t = first;
        for (int i = 1; i < items.length; i++) {
            Node n = new Node(items[i]);
            t.next = n;
            t = n;
        }
        last = t;
        last.next = first;
    }

    public boolean isEmpty() {
        return last == null;
    }

    public int size() {
        return N;
    }

    public void enqueue(Item item) {
        // 如果原始队列为空，则更新last结点
        Node t = new Node(item);
        if (N == 0) {
            last = t;
            last.next = last; //更新尾指针指向自己
        } else {
            // 如果队列不为空，则last指针指向的结点一定存在
            t.next = last.next;
            last.next=t;
            last=t;
        }
        N += 1;
    }

    public Item dequeue() {
        if (isEmpty())
            throw new Error("队列为空");
        Node t = last.next;
        Item item = t.item;
        last.next = t.next;
        N -= 1;
        if (N == 0) {
            last = null;  // 如果出队导致队列为空，那么更新last
        }
        return item;

    }

    public static void main(String[] args) {
        Integer[] list = {1, 2, 3, 4};
        LinkQueue<Integer> q = new LinkQueue<>(list);
        q.enqueue(5);
        for (Integer i : q)
            System.out.print(i + " ");
        int size = q.size();
        for (int i = 0; i < size; i++) {
            q.dequeue();
        }
        System.out.println(q.isEmpty());
        q.enqueue(1);
        q.enqueue(2);

        System.out.println(q.size());
        for (Integer i : q)
            System.out.print(i + " ");
    }


}

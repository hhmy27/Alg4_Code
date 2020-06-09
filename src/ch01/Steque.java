package ch01;

import java.util.Iterator;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/5/12
 *  \* Time: 14:20
 *  \* To change this template use File | Settings | File Templates.
 *  \* Description: This is answer for 1.3.32
 *  \
 */
public class Steque<Item> implements Iterable<Item> {
    private Node front; // front 是右边的 栈顶
    private Node last;  // last 是左边的 栈底和入队的地方
    private int N;

    Steque() {
        front = last = null;
        N = 0;
    }

    private class Node {
        private Item item;
        private Node next;
        private Node prev;

        Node(Item item) {
            this.item = item;
            next = prev = null;
        }
    }

    // first push
    private void pushFront(Item item) {
        Node t = new Node(item);
        front = last = t;
        N = 1;
    }

    // first enqueue
    private void enqueueFront(Item item) {
        Node t = new Node(item);
        front = last = t;
        N = 1;
    }

    // first pop
    private Item popFront() {
        Item item = front.item;
        front = last = null;
        N = 0;
        return item;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void enqueue(Item item) {
        if (isEmpty())
            enqueueFront(item);
        else {
            Node t = new Node(item);
            t.next = last;
            last.prev = t;
            last = t;
            N += 1;
        }
    }

    public void push(Item item) {
        if (isEmpty())
            pushFront(item);
        else {
            Node t = new Node(item);
            front.next = t;
            t.prev = front;
            front = t;
            N += 1;
        }
    }

    public Item pop() {
        if(isEmpty())
            throw new Error("This steque is empty");
        if (N == 1)
            return popFront();
        else {
            Node t = front;
            front = front.prev;
            Item item = t.item;
            front.next = null;
            N -= 1;
            return item;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new StequeIterator();
    }

    private class StequeIterator implements Iterator<Item> {
        private Node t = front;

        @Override
        public boolean hasNext() {
            return t != null;
        }

        @Override
        public Item next() {
            Item item = t.item;
            t = t.prev;
            return item;
        }

        @Override
        public void remove() {

        }
    }

    public static void main(String[] args) {
        Steque<Integer> steque=new Steque<>();
        steque.push(1);
        steque.push(2);
        steque.push(3);
        steque.enqueue(4);
        steque.enqueue(5);
        System.out.println(steque.pop());
        int size=steque.size();
        for (int i = 0; i < size; i++) {
            steque.pop();
        }
        steque.push(1);
        steque.enqueue(2);
        for(Integer i:steque)
            System.out.print(i+" ");
    }
}

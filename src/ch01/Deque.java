package ch01;

import java.util.Iterator;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/5/12
 *  \* Time: 15:07
 *  \* To change this template use File | Settings | File Templates.
 *  \* Description: This is answer for ex 1.3.33
 *  \  1       2      3      4       5
 * \  left                         right
 */
public class Deque<Item> implements Iterable<Item> {
    private Node right;
    private Node left;
    private int N;

    Deque() {
        right = left = null;
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

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void pushFirst(Item item) {
        Node t = new Node(item);
        right = left = t;
        N = 1;
    }

    private Item popFirst() {
        Item item = right.item;
        right = left = null;
        N = 0;
        return item;
    }

    public void pushLeft(Item item) {
        if (isEmpty())
            pushFirst(item);
        else {
            Node t = new Node(item);
            t.next = left;
            left.prev = t;
            left = t;
            N += 1;
        }
    }

    public void pushRight(Item item) {
        if (isEmpty())
            pushFirst(item);
        else {
            Node t = new Node(item);
            t.prev = right;
            right.next = t;
            right = t;
            N += 1;
        }
    }

    public Item popLeft() {
        if (isEmpty())
            throw new Error("this queue is empty");
        if (N == 1)
            return popFirst();
        else {
            Item item = left.item;
            left = left.next;
            left.prev = null;
            N -= 1;
            return item;
        }
    }

    public Item popRight() {
        if (isEmpty())
            throw new Error("this queue is empty");
        if (N == 1)
            return popFirst();
        else {
            Item item = right.item;
            right = right.prev;
            right.next = null;
            N -= 1;
            return item;
        }
    }


    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node t = right;

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
        Deque<Integer> deque = new Deque<>();
        deque.pushRight(1);
        deque.pushLeft(2);
        deque.pushRight(3);
        deque.pushRight(4);
        int size = deque.size();
        for (int i = 0; i < size; i++) {
            deque.popRight();
        }
        deque.pushRight(1);
        deque.pushLeft(2);
        deque.pushRight(3);
        deque.pushRight(4);
        for (int i : deque)
            System.out.print(i + " ");
    }
}

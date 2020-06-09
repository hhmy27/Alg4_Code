package ch01;

import java.util.Iterator;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/5/8
 *  \* Time: 20:28
 *  \* To change this template use File | Settings | File Templates.
 *  \* Description: 
 *  \
 */

public class DoubleLinklist<Item> implements Iterable<Item> {
    private DoubleNode first;
    private DoubleNode last;
    private int N;

    DoubleLinklist() {
        N = 0;
        first = null;
    }

    public void insertFront(Item item) {
        // if first node is null
        if (first == null) {
            first = last = new DoubleNode(item);
        } else {
            DoubleNode t = new DoubleNode(item);
            t.next = first;
            first.prev = t;
            first = t;
        }
        N += 1;
    }

    public void deleteFront() {
        if (isEmpty()) {
            throw new Error("This linklist is empty");
        }
        if (N == 1) {
            first = last = null;
        } else {
            first = first.next;
            first.prev.next = null;
            first.prev = null;
        }
        N -= 1;
    }

    public void insertLast(Item item) {
        DoubleNode t = new DoubleNode(item);
        last.next = t;
        t.prev = last;
        last = t;
        N += 1;
    }

    public void deleteLast() {
        if (N == 0) {
            throw new Error("This linklist is empty");
        }
        if (N == 1) {
            last = first = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        N -= 1;
    }

    public void insertNodeBack(Item item, int index) {
        if (!checkIndex(index))
            throw new Error("index is invaild");
        if (index == N) {
            insertLast(item);
        } else {
            int t = 1;
            DoubleNode tnode = new DoubleNode(item);
            DoubleNode tf = first;
            while (t < index) {
                t++;
                tf = tf.next;
            }
            tnode.next = tf.next;
            tnode.prev = tf;
            tf.next.prev = tnode;
            tf.next = tnode;
            N += 1;
        }

    }

    public void insertNodeFront(Item item, int index) {
        if (!checkIndex(index))
            throw new Error("index is invaild");
        if (index == 1) {
            insertFront(item);
        } else {
            int t = 1;
            DoubleNode tnode = new DoubleNode(item);
            DoubleNode tf = first;
            while (t < index) {
                t++;
                tf = tf.next;
            }
            tnode.next = tf;
            tnode.prev = tf.prev;
            if (tf.prev != null) tf.prev.next = tnode;
            tf.prev = tnode;
            N += 1;
        }

    }

    public void deleteNode(int index) {
        if (index < 0 || index > N)
            throw new Error("index is invaild");
        if (index == 1) {
            deleteFront();
        } else if (index == N) {
            deleteLast();
        } else {
            int t = 1;
            DoubleNode tf = first;
            while (t < index) {
                t++;
                tf = tf.next;
            }
            DoubleNode f = tf.prev;
            DoubleNode n = tf.next;
            f.next = tf.next;
            if (n != null) n.prev = f;
            N -= 1;
        }
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private boolean checkIndex(int index) {
        if (index <= 0 || index > N)
            return false;
        return true;
    }

    private class DoubleNode {
        private Item item;
        private DoubleNode prev;
        private DoubleNode next;

        DoubleNode(Item item) {
            this.item = item;
            prev = next = null;
        }
    }


    @Override
    public Iterator<Item> iterator() {
        return new DoubleLinklistIterator();
    }

    class DoubleLinklistIterator implements Iterator<Item> {
        private DoubleNode tfirst = first;

        @Override
        public boolean hasNext() {
            return tfirst.next != null;
        }

        @Override
        public Item next() {
            Item item = tfirst.item;
            tfirst = tfirst.next;
            return item;
        }

        @Override
        public void remove() {

        }
    }

    public void visit() {
        DoubleNode t = first;
        System.out.print("list: ");
        while (t != null) {
            System.out.print(t.item);
            if (t.next != null) {
                System.out.print("=>");
            }
            t = t.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DoubleLinklist<Integer> list = new DoubleLinklist<>();
        for (int i = 1; i < 6; i++) {
            list.insertFront(i);
        }
        list.insertLast(6);
        System.out.println(list.size());
        list.insertNodeBack(7, 6);
        list.deleteNode(1);
        list.deleteNode(3);
        System.out.println(list.size());
        list.insertNodeBack(3, 3);
        for (int i = 0; i < 5; i++) {
            list.deleteNode(1);
        }
        list.visit();
        System.out.println(list.size());
        list.insertNodeBack(1,1);
        list.visit();
        list.insertNodeFront(1,1);
        list.visit();
        list.deleteNode(2);
        list.visit();
        System.out.println(list.size());
    }
}



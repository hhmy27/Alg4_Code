package ch01;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/5/12
 *  \* Time: 15:37
 *  \* To change this template use File | Settings | File Templates.
 *  \* Description: 
 *  \
 */
public class RandomBag<Item> implements Iterable<Item> {
    private Node first;
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public void add(Item item) {
        Node t = first;
        first = new Node();
        first.item = item;
        first.next = t;
        N++;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private class ListIterator implements Iterator<Item> {
        private int ind;
        private Object[] list;

        ListIterator() {
            ind = 0;
            list = new Object[size()];
            Node t = first;
            int tind = 0;
            while (t != null){
                list[tind++] = t.item;
                t=t.next;
            }
            StdRandom.shuffle(list);
        }

        public boolean hasNext() {
            return ind != list.length;
        }

        public void remove() {
        }

        public Item next() {
            Item item = (Item) list[ind++];
            return item;
        }
    }

    public static void main(String[] args) {
        RandomBag<Integer> rb=new RandomBag<>();
        for (int i = 0; i < 10; i++) {
            rb.add(i+1);
        }
        for(int i : rb)
            System.out.print(i+" ");
    }
}

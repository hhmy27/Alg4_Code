package ch01;

import edu.princeton.cs.algs4.In;

import java.util.Iterator;
import java.util.ListIterator;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/4/28
 *  \* Time: 9:29
 *  \* To change this template use File | Settings | File Templates.
 *  \* Description: 
 *  \
 */
public class Bag<Item> implements Iterable<Item> {
    private Node first;
    private int N;
    private class Node {
        Item item;
        Node next;
    }

    public void add(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    public int size(){
        return N;
    }
    public boolean isEmpty(){
        return N==0;
    }
    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Bag<Integer> bag=new Bag<>();
        for (int i = 0; i < 5; i++) {
            bag.add(i+1);
        }
        System.out.println("size"+bag.size());
        for (int i : bag){
            System.out.println(i);
        }

    }
}

package ch01;

import edu.princeton.cs.algs4.In;

import java.util.Iterator;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/4/28
 *  \* Time: 8:36
 *  \* To change this template use File | Settings | File Templates.
 *  \* Description: 链栈的插入方式是在首结点插入，移动first 
 *  \
 */
public class Stack<Item> implements Iterable<Item> {
    private Node first;
    private int N;

    public Stack() {
        first = null;
        N = 0;
    }

    // 1.3.42的答案
    public Stack(Stack<Item> s) {
        N = s.size();
        first = null;
        Stack<Item> t = new Stack<>();
        for (Item item : s) {
            t.push(item);
        }

        for (Item item : t) {
            Node node = new Node(item);
            node.next = first;
            first = node;
        }

    }

    @Override
    public Iterator<Item> iterator() {
        return new StakeIterator();
    }

    private class StakeIterator implements Iterator<Item> {
        private Node cur = first;

        @Override
        public boolean hasNext() {
//            return cur.next!=null;
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

    private class Node {
        Item item;
        Node next;

        Node(Item item) {
            this.item = item;
            next = null;
        }
    }

    public void push(Item item) {
        Node n = new Node(item);
        n.next = first;
        first = n;
        N++;
    }

    public Item pop() {
        Node top = first;
        first = first.next;
        N--;
        return top.item;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public Item peek() {
        return first.item;
    }

    // 1.3.12的答案
    public static <Item> Stack<Item> copy(Stack<Item> s) {
        Stack<Item> result = new Stack<>();
        Stack<Item> temp = new Stack<>();
        Iterator<Item> it = s.iterator();
        while (it.hasNext())
            temp.push(it.next());
        it = temp.iterator();
        while (it.hasNext())
            result.push(it.next());
        return result;
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("are");
        stack.push("you");
        stack.push("ok?");
        Stack<String> temp = Stack.copy(stack);
        for (String s : temp)
            System.out.println(s);
        System.out.println("---");
        Stack<String> t=new Stack<>(stack);
        t.forEach(System.out::println);
    }
}

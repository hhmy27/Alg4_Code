package ch01.part3;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/4/28
 *  \* Time: 8:36
 *  \* To change this template use File | Settings | File Templates.
 *  \* Description: Single list implement Stack
 *  \
 */
public class Stack<Item> implements Iterable<Item> {
    private Node first;
    private int N;
    private int op;

    public Stack() {
        first = null;
        N = 0;
        op = 0;
    }

    // 1.3.42的答案
    // 从一个栈中构造另一个栈
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
        private int top = op;

        @Override
        public boolean hasNext() {
            if (top != op)
                throw new ConcurrentModificationException("栈被修改了");
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
        op++;
    }

    public Item pop() {
        Node top = first;
        first = first.next;
        N--;
        op--;
        return top.item;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public Item peek() {
        if (first == null)
            throw new IndexOutOfBoundsException("stack is empty");
        return first.item;
    }

    // ex 1.3.47
    // 在当前栈上面放置q栈中的元素
    public void catenation(Stack<Item> q) {
        Stack<Item> t = new Stack<>();
        for (Item item : q) {
            t.push(item);
        }
        while (!t.isEmpty()) {
            this.push(t.pop());
        }
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
//        Stack<String> stack = new Stack<>();
//        stack.push("are");
//        stack.push("you");
//        stack.push("ok?");
//        Stack<String> temp = Stack.copy(stack);
//        for (String s : temp)
//            System.out.println(s);
//        System.out.println("---");
//        Stack<String> t = new Stack<>(stack);
//        t.forEach(System.out::println);
        // ex 1.3.47
        Stack<Integer> s = new Stack<>();
        s.push(3);
        s.push(2);
        Stack<Integer> d = new Stack<>();
        d.push(5);
        d.push(-7);
        s.catenation(d);
        s.forEach(System.out::println);
        for (Integer integer : s) {
            if (integer < 3)
                s.pop();
            else
                System.out.println(integer);
        }
    }
}

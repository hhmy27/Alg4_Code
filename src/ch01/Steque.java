package ch01;

import ch01.part3.Stack;

import java.util.Iterator;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/5/12
 *  \* Time: 14:20
 *  \* To change this template use File | Settings | File Templates.
 *  \* Description: This is answer for 1.3.32
 * \* front是栈顶，last是栈底，last还是入队的地方，push操作是从front一端进入,enqueue是从last一端，也就是从栈底的位置进入
 *  \* push、pop 都是对队列同一端的操作，enqueue 和 push 对应，但操作的是队列的另一端。——译者注
 */
public class Steque<Item> implements Iterable<Item> {
    private Node front; // front 是右边的 栈顶
    private Node last;  // last 是左边的 栈底和入队的地方
    private int N;

    public Steque() {
        front = last = null;
        N = 0;
    }

    public Steque(Steque<Item> r) {
        Stack<Item> t = new Stack<>();
        for (Item item : r) {
            t.push(item);
        }
        N = t.size();
        front = last = null;

        for (Item item : t) {
            Node n = new Node(item);
            n.next = front;
            front = n;
            if (last == null)
                last = n;
        }
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
        if (isEmpty())
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

    // ex 1.3.47
    // 将r中的元素连接到当前steque中
    public void catenation(Steque<Item> r) {
        Steque<Item> t = new Steque<>();
        for (Item item : r) {
            t.push(item);
        }
        for (Item item : t) {
            this.push(item);
        }
    }


    public static void main(String[] args) {
        Steque<Integer> steque = new Steque<>();
        steque.push(1);
        steque.push(2);
        steque.push(3);
        steque.enqueue(4);
        steque.enqueue(5);
        System.out.println(steque.pop());
        int size = steque.size();
        for (int i = 0; i < size; i++) {
            steque.pop();
        }
        steque.push(1);
        steque.enqueue(2);
        for (Integer i : steque)
            System.out.print(i + " ");

        Steque<Integer> r = new Steque<>();
        r.push(8);
        r.push(9);
        r.catenation(steque);
        r.forEach(System.out::println);
    }
}

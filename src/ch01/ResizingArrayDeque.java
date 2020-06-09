package ch01;


import java.util.Iterator;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/5/13
 *  \* Time: 8:23
 *  \* To change this template use File | Settings | File Templates.
 *  \* Description:
 * \   1   2   3   4   5
 * \  front            last 
 *  \
 */
public class ResizingArrayDeque<Item> implements Iterable<Item> {
    private int left;
    private int right;
    private int N = 0;
    private Object[] list = new Object[2];

    ResizingArrayDeque() {
        left = right = 0;
    }

    public boolean isFull() {
        return N == list.length;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void pushFirst(Item item) {
        left = right = 0;
        list[left] = item;
        N = 1;
    }

    public void pushLeft(Item item) {
        if (isEmpty()) {
            pushFirst(item);
        } else {
            if (isFull()) {
                resize(2 * list.length);
            }
            left -= 1;
            if (left < 0)
                left = list.length - 1;
            list[left] = item;
            N += 1;
        }
    }

    public void pushRight(Item item) {
        if (isEmpty()) {
            pushFirst(item);
        } else {
            if (isFull()) {
                resize(2 * list.length);
            }
            right = (++right) % list.length;
            list[right] = item;
            N += 1;
        }
    }

    public Item popLeft() {
        if (isEmpty())
            throw new Error("queue is empty");
        Item item = (Item) list[left];
        list[left] = null;
        left = (++left) % list.length;
        N -= 1;
        if (N == list.length / 4) {
            resize(list.length / 2);
        }
        return item;
    }

    public Item popRight() {
        if (isEmpty())
            throw new Error("queue is empty");
        Item item = (Item) list[right];
        list[right] = null;
        right -= 1;
        if (right < 0)
            right = list.length;
        N -= 1;
        if (N == list.length / 4) {
            resize(list.length / 2);
        }
        return item;
    }

    private void resize(int max) {
        Object[] t = new Object[max];
        for (int i = 0; i < max; i++) {
            t[i] = list[(i + left) % list.length];
        }
        left = 0;
        right = N - 1;
        list = t;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ResizingArrayDequeIterator();
    }

    private class ResizingArrayDequeIterator implements Iterator<Item> {
        private int n = N;
        private int L = left;

        @Override
        public boolean hasNext() {
            return n != 0;
        }

        @Override
        public Item next() {
            Item item = (Item) list[L++];
            n -= 1;
            if (L == list.length) L = 0;
            return item;
        }

        @Override
        public void remove() {

        }
    }

    public static void main(String[] args) {
        ResizingArrayDeque<Integer> deque = new ResizingArrayDeque<>();
        deque.pushLeft(1);
        deque.pushRight(2);
        deque.pushLeft(3);
        deque.pushLeft(4);
        int size = deque.size();
        for (int i = 0; i < size; i++) {
            System.out.println(deque.popLeft());
        }
        deque.pushLeft(1);
        deque.pushLeft(2);
        deque.pushLeft(3);
        deque.pushLeft(4);
        deque.pushRight(5);
        deque.pushRight(6);
        for (int i : deque)
            System.out.print(i + " ");
    }
}


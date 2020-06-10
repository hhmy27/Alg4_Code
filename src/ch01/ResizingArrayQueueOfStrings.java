package ch01;

import java.util.Iterator;
import java.util.Objects;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/4/30
 *  \* Time: 10:59
 *  \* To change this template use File | Settings | File Templates.
 *  \* Description: ex_1.3.14 编写一个类 ResizingArrayQueueOfStrings，使用定长数组实现队列的抽象，然后扩展实现， 使用调整数组的方法突破大小的限制 
 *  \
 */
public class ResizingArrayQueueOfStrings<Item> implements Iterable<Item> {
    private Item[] list;
    private int front, last, N;

    ResizingArrayQueueOfStrings() {
        front = last = N = 0;
        list = (Item[]) new Object[2];
    }

    public void enqueue(Item s) {
        if (N == list.length)
            resize(2 * list.length);
        N++;
        list[last] = s;
        last = (++last) % list.length;
    }

    public Item dequeue() {
        // 出队
        if (isEmpty())
            throw new Error("队空");
        N--;
        Item s = list[front];
        list[front] = null;   // 手动置为空
        front = (++front) % list.length;
        if (N == (list.length / 4))
            resize(list.length / 2);
        return s;
    }

    public boolean isEmpty() {
        return N == 0;
    }


    private void resize(int size) {
        Item[] newlist = (Item[]) new Object[size];
        for (int i = 0; i < N; i++)
            newlist[i] = list[(i + front) % list.length];
        list = newlist;
        front = 0;
        last = N;
    }

    public int size() {
        return N;
    }

    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < N;
        }

        @Override
        public Item next() {
            Item item = list[(i + front) % list.length];
            i++;
            return item;
        }

        @Override
        public void remove() {

        }
    }

    public static void main(String[] args) {
        ResizingArrayQueueOfStrings<String> que = new ResizingArrayQueueOfStrings<>();
        que.enqueue("are");
        System.out.println(que.size());
        que.enqueue("you");
        System.out.println(que.size());
        que.enqueue("ok0?");
        System.out.println(que.size());

        que.enqueue("ok1?");
        System.out.println(que.size());

        que.enqueue("ok2?");
        System.out.println(que.size());

//        que.dequeue();
        que.dequeue();
//        que.dequeue();
//        que.dequeue();
        for (String s : que)
            System.out.println(s);
    }
}

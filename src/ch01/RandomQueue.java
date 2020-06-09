package ch01;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/**
 * @program: AlgorithmBook
 * @description: ex 1.3.35
 * @author: HMY777
 * @created: 2020/05/13 09:29
 */
public class RandomQueue<Item> implements Iterable<Item> {
    private Object[] list = new Object[2];
    private int N = 0;

    public boolean isEmpty() {
        return N == 0;
    }

    private void resize(int size) {
        Object[] t = new Object[size];
        for (int i = 0; i < N; i++) {
            t[i] = list[i];
        }
        list = t;
    }

    public void enqueue(Item item) {
        if (N == list.length) {
            resize(2 * list.length);
        }
        list[N++] = item;
    }

    public Item dequeue() {
        int ind = StdRandom.uniform(N);
        Item item = (Item) list[ind];
        list[ind] = list[--N];
        list[N] = null;
        if (N == list.length / 4)
            resize(list.length / 2);
        return item;
    }

    public Item sample() {
        int ind = StdRandom.uniform(N);
        return (Item) list[ind];
    }



    @Override
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }
    private class RandomQueueIterator implements Iterator<Item>{
        private Object[] tlist=list;
        private int tn=N;
        @Override
        public boolean hasNext() {
            return tn!=0;
        }

        @Override
        public Item next() {
            int ind = StdRandom.uniform(tn);
            Item item = (Item) tlist[ind];
            tlist[ind] = tlist[--tn];
            tlist[tn] = null;
            return item;
        }

        @Override
        public void remove() {

        }
    }
    public static void main(String[] args) {
        RandomQueue<Integer> rq = new RandomQueue<>();
        for (int i = 1; i < 14; i++) {
            rq.enqueue(i);
        }
        for(int i :rq)
            System.out.print(i+" ");
//        while(!rq.isEmpty())
//            System.out.println(rq.dequeue());
    }
}

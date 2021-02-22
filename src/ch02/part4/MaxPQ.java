package ch02.part4;

/**
 * @program: AlgorithmBook
 * @author: hhmy27
 * @created: 2021/02/22 15:46
 * @description:
 */
public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    MaxPQ() {
    }

    MaxPQ(int max) {
        pq = (Key[]) new Comparable[max + 1];
    }

    MaxPQ(Key[] a) {
        pq = a;
    }

    void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    public int size() {
        return N;
    }

    Key max() {
        return pq[1];
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);
        return max;
    }

    boolean isEmpty() {
        return N == 0;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

}

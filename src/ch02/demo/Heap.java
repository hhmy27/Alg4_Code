package ch02.demo;

/**
 * @program: AlgorithmBook
 * @author: hhmy27
 * @created: 2021/02/22 16:48
 * @description:
 */
public class Heap extends Example {
    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        for (int k = N / 2; k >= 1; k--)
            sink(a, k, N);
        while (N > 1) {
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }

    private void sink(Comparable[] a, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(a, k, j);
            k = j;
        }
    }

    public void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}

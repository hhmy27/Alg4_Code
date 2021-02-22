package ch02.demo;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * @program: AlgorithmBook
 * @author: hhmy27
 * @created: 2021/02/21 21:45
 * @description:
 */
public class Quick extends Example {

    @Override
    public void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo)
            return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v))
                if (i == hi)
                    break;
            while (less(v, a[--j]))
                if (j == lo)
                    break;
            if (i >= j)
                break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void main(String[] args) {
        Quick quick = new Quick();
        Integer[] a = {3, 5, 2, 1, 6, 7, 8};
        quick.sort(a);
        System.out.println(Arrays.toString(a));
    }
}

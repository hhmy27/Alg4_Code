package ch02.part2;

import ch02.demo.Example;
import ch02.demo.Insertion;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * @program: Alg4_Code
 * @author: hhmy27
 * @created: 2021/02/01 15:53
 * @description: use insertion when a.length < 15
 */
public class ex_2_2_11_1 extends Example {
    private static Comparable[] aux;
    private final int bound = 15;

    private void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }


    private void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        if (hi - lo <= bound) {
            insertionSort(a, lo, hi);
        } else {
            int mid = lo + (hi - lo) / 2;
            sort(a, lo, mid);
            sort(a, mid + 1, hi);
            merge(a, lo, mid, hi);
        }
    }

    private void merge(Comparable[] a, int lo, int mid, int hi) {
        for (int i = 0; i < a.length; i++)
            aux[i] = a[i];


        int i = lo, j = mid + 1;
        int ind = lo;
        while (i <= mid && j <= hi) {
            if (aux[i].compareTo(aux[j]) <= 0)
                a[ind++] = aux[i++];
            else
                a[ind++] = aux[j++];
        }
        while (i <= mid)
            a[ind++] = aux[i++];
        while (j <= hi)
            a[ind++] = aux[j++];
    }

    public static void main(String[] args) {
        ex_2_2_11_1 merge = new ex_2_2_11_1();
        final int N = (int) 500;
        Integer[] a = merge.generateList(N);
        merge.sort(a);
        System.out.println(merge.isSorted(a));
    }
}
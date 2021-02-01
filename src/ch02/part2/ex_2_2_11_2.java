package ch02.part2;

import ch02.demo.Example;
import ch02.demo.Merge;

/**
 * @program: Alg4_Code
 * @author: hhmy27
 * @created: 2021/02/01 16:24
 * @description:
 */
public class ex_2_2_11_2 extends Example {
    private static Comparable[] aux;

    public void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    private void merge(Comparable[] a, int lo, int mid, int hi) {
        // only difference
        if (a[mid].compareTo(a[mid + 1]) <= 0)
            return;

        for (int i = lo; i <= hi; i++)
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
        ex_2_2_11_2 merge = new ex_2_2_11_2();
        Integer[] a = merge.generateList(100);
        merge.sort(a);
        System.out.println(merge.isSorted(a));
    }
}
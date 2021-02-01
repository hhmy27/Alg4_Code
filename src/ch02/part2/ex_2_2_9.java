package ch02.part2;

import ch02.demo.Example;
import ch02.demo.Merge;

/**
 * @program: Alg4_Code
 * @author: hhmy27
 * @created: 2021/02/01 15:39
 * @description:
 */
public class ex_2_2_9 extends Example {

    public void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, 0, a.length - 1, aux);
    }

    private void sort(Comparable[] a, int lo, int hi, Comparable[] aux) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid, aux);
        sort(a, mid + 1, hi, aux);
        merge(a, lo, mid, hi, aux);
    }

    private void merge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux) {
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
        ex_2_2_9 merge = new ex_2_2_9();
//        Integer[] a = new Integer[]{3, 1, 2, 4, 5};
        Integer[] a = merge.generateList(100);
        merge.sort(a);
        System.out.println(merge.isSorted(a));
    }
}
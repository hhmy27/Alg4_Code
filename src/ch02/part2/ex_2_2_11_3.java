package ch02.part2;

import ch02.demo.Example;
import ch02.demo.Merge;

/**
 * @program: Alg4_Code
 * @author: hhmy27
 * @created: 2021/02/01 16:28
 * @description: it is very interesting trick, but I think it improve is limited and it's slightly difficult to debug...
 */
public class ex_2_2_11_3 extends Example {
    private static Comparable[] aux;

    public void sort(Comparable[] a) {
        aux = a.clone();
        sort(aux, a, 0, a.length - 1);
    }

    private void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(aux, a, lo, mid);
        sort(aux, a, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        int ind = lo;
        while (i <= mid && j <= hi) {
            if (a[i].compareTo(a[j]) <= 0)
                aux[ind++] = a[i++];
            else
                aux[ind++] = a[j++];
        }
        while (i <= mid)
            aux[ind++] = a[i++];
        while (j <= hi)
            aux[ind++] = a[j++];
    }

    public static void main(String[] args) {
        ex_2_2_11_3 merge = new ex_2_2_11_3();
        Integer[] a = merge.generateList(100);
        merge.sort(a);
        System.out.println(merge.isSorted(a));
    }
}
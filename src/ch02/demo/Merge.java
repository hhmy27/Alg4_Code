package ch02.demo;


/**
 * @program: Alg4_Code
 * @author: hhmy27
 * @created: 2021/02/01 14:25
 * @description: Algorithm 2.4
 */
public class Merge extends Example {
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
        Merge merge = new Merge();
        Comparable[] a = merge.generateList(100);
//        Integer[] a = {5, 4, 3, 2, 1};
//        merge.show(a);
        merge.sort(a);
//        merge.show(a);
        System.out.println(merge.isSorted(a));
    }
}
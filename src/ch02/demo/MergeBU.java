package ch02.demo;

/**
 * @program: Alg4_Code
 * @author: hhmy27
 * @created: 2021/02/01 15:11
 * @description:
 */
public class MergeBU extends Example {
    private static Comparable[] aux;

    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        // stride 1, 2, 4, ...
        for (int sz = 1; sz < N; sz += sz)
            // 0~N-sz, every time completed merge adjoin array,lo go to next range to keep merge
            for (int lo = 0; lo < N - sz; lo += sz + sz)
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));

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
        MergeBU mergeBU = new MergeBU();
//        Integer[] a = new Integer[]{3, 1, 2, 4, 5};
        Integer[] a = mergeBU.generateList(100);
        mergeBU.sort(a);
        System.out.println(mergeBU.isSorted(a));
    }
}

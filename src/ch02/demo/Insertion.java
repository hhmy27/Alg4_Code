package ch02.demo;

/**
 * @program: Alg4_Code
 * @author: hhmy27
 * @created: 2021/01/30 20:25
 * @description: very fast if a is close sorted.
 */
public class Insertion extends Example {
    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        Insertion insertion = new Insertion();
        Integer[] a = {3, 2, 1};
        insertion.sort(a);
        insertion.show(a);
    }
}
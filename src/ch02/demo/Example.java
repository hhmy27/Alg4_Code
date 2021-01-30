package ch02.demo;

/**
 * @program: Alg4_Code
 * @author: hhmy27
 * @created: 2021/01/30 20:02
 * @description:
 */
public abstract class Example {
    public abstract void sort(Comparable[] a);

    public boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public void show(Comparable[] a) {
        for (Comparable comparable : a) {
            System.out.print(comparable + " ");
        }
        System.out.println();
    }

    public boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1]))
                return false;
        }
        return true;
    }
}
package ch02.demo;

/**
 * @program: Alg4_Code
 * @author: hhmy27
 * @created: 2021/02/01 11:39
 * @description:
 */
public class Shell extends Example {
    @Override
    public void sort(Comparable[] a) {
        int n = a.length;

        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
        int h = 1;
        while (h < n / 3) h = 3 * h + 1;

        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h /= 3;
        }
        assert isSorted(a);
    }


    public static void main(String[] args) {
        Shell shell = new Shell();
        Integer[] a = {3, 2, 1};
        shell.sort(a);
        assert shell.isSorted(a);
        shell.show(a);
    }
}
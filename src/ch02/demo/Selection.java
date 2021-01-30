package ch02.demo;

import java.net.ServerSocket;

/**
 * @program: Alg4_Code
 * @author: hhmy27
 * @created: 2021/01/30 19:59
 * @description: O(n ^ 2), time of run is independent of input
 * minimal number of element moves
 */
public class Selection extends Example {

    public void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++)
                if (less(a[j], a[min])) min = j;
            exch(a, i, min);
        }
    }

    public static void main(String[] args) {
        Selection selection = new Selection();
        Integer[] a = {3, 2, 1};
        selection.sort(a);
        selection.show(a);

    }


}

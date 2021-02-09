package ch02.part2;

import ch02.demo.Example;
import ch02.demo.Merge;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.concurrent.ConcurrentMap;

/**
 * @program: AlgorithmBook
 * @author: hhmy27
 * @created: 2021/02/09 10:39
 * @description:
 */
public class ex_2_2_16 {

    public static void naturalMergeSort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];

        int low = 0;
        int middle;
        int high;
        // we need find two adjoin sub array in a
        // range is [low,middle], [middle+1,high]
        while (true) {
            middle = findSortedSubArrayIndex(a, low);
            // if first sorted sub array range is [low,a.length-1]
            if (middle == a.length - 1 && low == 0) {
                break;  // already sorted
            }

            high = findSortedSubArrayIndex(a, middle + 1);
            merge(a, aux, low, middle, high);
            // if high == a.length - 1, we need let low = 0 to continue merge sorted sub array
            low = (high == a.length - 1) ? 0 : high + 1;

        }
    }

    private static void merge(Comparable[] a, Comparable[] aux, int low, int middle, int high) {
        for (int i = low; i <= high; i++) {
            aux[i] = a[i];
        }

        int i = low, j = middle + 1;
        int ind = low;

        while (i <= middle && j <= high) {
            if (aux[i].compareTo(aux[j]) <= 0) {
                a[ind++] = aux[i++];
            } else
                a[ind++] = aux[j++];
        }
        while (i <= middle) {
            a[ind++] = aux[i++];
        }
        while (j <= high) {
            a[ind++] = aux[j++];
        }
    }

    private static int findSortedSubArrayIndex(Comparable[] a, int low) {
        for (int i = low + 1; i < a.length; i++) {
            if (a[i].compareTo(a[i - 1]) < 0)
                return i - 1; // [low,i-1] is sorted, i-1 minimal is low, means [low,low]
        }
        return a.length - 1;
    }

    public static Comparable[] generateList(int N) {
        Comparable[] a = new Comparable[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(N);
        }
        return a;
    }

    public static void main(String[] args) {
        Comparable[] a = generateList(10);
        naturalMergeSort(a);
        System.out.println(Arrays.asList(a));
    }


}

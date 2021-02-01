package ch02.part1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import javafx.scene.paint.Stop;

import javax.swing.text.DefaultEditorKit;

/**
 * @program: Alg4_Code
 * @author: hhmy27
 * @created: 2021/02/01 13:21
 * @description: ref: https://github.com/reneargento/algorithms-sedgewick-wayne/blob/master/src/chapter2/section1/Exercise24_InsertionSortWithSentinel.java
 */
public class ex_2_1_24 {
    private enum InsertionSortType {
        DEFAULT, SENTINEL;
    }

    public static void main(String[] args) {
        sortCompare();
    }

    private static void insertionSort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && a[j].compareTo(a[j - 1]) < 0; j--) {
                Comparable temp = a[j - 1];
                a[j - 1] = a[j];
                a[j] = temp;
            }
        }
    }

    private static void insertionSortSentinel(Comparable[] a) {
        // first, find minimal number and exchange it to left position.
        Comparable min = Double.MAX_VALUE;
        int ind = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i].compareTo(min) < 0) {
                min = a[i];
                ind = i;
            }
        }

        Comparable temp = a[0];
        a[0] = a[ind];
        a[ind] = temp;

        for (int i = 1; i < a.length; i++) {
            for (int j = i; a[j].compareTo(a[j - 1]) < 0; j--) {
                temp = a[j - 1];
                a[j - 1] = a[j];
                a[j] = temp;
            }
        }
    }

    private static void sortCompare() {
        int arrayLength = (int) 10e5;
        int numberOfExperiments = 3;
        double timeInsertionSortDefault = timeRandomInput(InsertionSortType.DEFAULT, arrayLength, numberOfExperiments);
        double timeInsertionSortSentinel = timeRandomInput(InsertionSortType.SENTINEL, arrayLength, numberOfExperiments);

        StdOut.printf("For %d random doubles\n Insertion Sort default is", arrayLength);
        StdOut.printf(" %.1f times faster than Insertion Sort with a sentinel", timeInsertionSortSentinel / timeInsertionSortDefault);

    }

    private static double timeRandomInput(InsertionSortType insertionSortType, int length, int numberOfExperiments) {
        double total = 0;
        Comparable[] a = new Comparable[length];
        for (int i = 0; i < numberOfExperiments; i++) {
            for (int j = 0; j < length; j++) {
                a[j] = StdRandom.uniform();
            }
            total += time(insertionSortType, a);
        }
        return total;
    }

    private static double time(InsertionSortType insertionSortType, Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        if (insertionSortType == InsertionSortType.DEFAULT) {
            insertionSort(a);
        } else if (insertionSortType == InsertionSortType.SENTINEL) {
            insertionSortSentinel(a);
        }
        return timer.elapsedTime();
    }
}
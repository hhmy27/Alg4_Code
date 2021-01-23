package ch01.part4;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/**
 * @program: AlgorithmBook
 * @author: hhmy27
 * @created: 2020/06/22 15:52
 * @description: 4-sum algorithm, ex 1_4_14
 */
public class FourSum {
    // O(N^4)
    public static void func1(int[] a) {
        int cnt = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                for (int k = j + 1; k < a.length; k++) {
                    for (int l = k + 1; l < a.length; l++) {
                        if (a[i] + a[j] + a[k] + a[l] == 0) {
//                            System.out.printf("%d %d %d %d\n", a[i], a[j], a[k], a[l]);
                            cnt++;
                        }
                    }
                }
            }
        }
        System.out.println(cnt);
    }

    // O(N^3logN)
    public static void func2(int[] a) {
        int cnt = 0;
        Arrays.sort(a);
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] == a[i + 1])
                throw new Error("数组中有重复数字");
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                for (int k = j + 1; k < a.length; k++) {
                    int t = a[i] + a[j] + a[k];
                    if (BinarySearch.indexOf(a, -t) > k)
                        cnt++;
                }
            }
        }
        System.out.println(cnt);
    }


    public static void main(String[] args) {
        int[] a = In.readInts("src/data/1Kints.txt");
        // O(N^4) 大约 78 second, O(N^3logN) 大约 7 second
        Stopwatch timer = new Stopwatch();
        func1(a);
        double time = timer.elapsedTime();
        System.out.println(time + " seconds");
        Stopwatch timer2 = new Stopwatch();
        func2(a);
        time = timer2.elapsedTime();
        System.out.println(time + " seconds");
    }
}

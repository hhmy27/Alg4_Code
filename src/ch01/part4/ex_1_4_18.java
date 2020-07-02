package ch01.part4;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * @program: AlgorithmBook
 * @author: hhmy27
 * @created: 2020/06/26 09:06
 * @description:
 */
public class ex_1_4_18 {

    // find i meet a[i]<a[i-1] and a[i]<a[i+1]
    // brute force method
    public static int func(int[] a) {
        if (a.length == 0)
            return -1;
        if (a.length == 1)
            return 0;

        for (int i = 1; i < a.length - 1; i++) {
            if (a[i] < a[i - 1] && a[i] < a[i + 1])
                return i;
        }

        if (a[0] < a[1])
            return 0;
        else if (a[a.length - 1] > a[a.length - 1 - 1])
            return a.length - 1;
        return -1;
    }

    // 蛮力法的基础上面改进了一点点
    public static int func2(int[] a) {
        if (a.length == 0)
            return -1;
        if (a.length == 1)
            return 0;

        for (int i = 1; i < a.length - 1; i++) {
            if (a[i] < a[i - 1] && a[i] < a[i + 1])
                return i;
            else if (a[i] < a[i + 1])
                i++;
        }
        return -1;
    }

    public static int func3(int[] a) {
        if (a.length == 0)
            return -1;

        if (a.length == 1)
            return 0;

        if (a.length == 2)
            if (a[0] < a[1])
                return 0;
            else return 1;

        int lo = 0, hi = a.length - 1;
        int N = (lo + hi) / 2;
        while (N != 0 && N != a.length - 1) {
            if (a[N] < a[N - 1] && a[N] < a[N + 1])
                return N;
            else if (a[N - 1] < a[N + 1])
                hi = N - 1;
            else
                lo = N + 1;

            N = (lo + hi) / 2;
        }
        return N;
    }

    public static void main(String[] args) {
        int[] a = {-7, -4, -2, 1, 0, 4, 7, 8, 11, 9};
        StdRandom.shuffle(a);
        System.out.println(Arrays.toString(a));
        System.out.println(func(a));
        System.out.println(func2(a));
        System.out.println(func3(a));
    }

}

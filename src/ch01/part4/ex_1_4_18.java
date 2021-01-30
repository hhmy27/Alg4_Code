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
    public static int func1(int[] a) {
        if (a.length == 0)
            return -1;
        if (a.length == 1)
            return 0;

        if (a[0] < a[1])
            return 0;
        else if (a[a.length - 1] > a[a.length - 1 - 1])
            return a.length - 1;

        for (int i = 1; i < a.length - 1; i++) {
            if (a[i] < a[i - 1] && a[i] < a[i + 1])
                return i;
        }

        return -1;
    }


    /*
     * 二分法解决数组中peak finding 问题，要求数组必须是distinct的
     * 如果当前元素不是peak，那么就往较小的相邻元素走，若干次二分后始终会碰到一个满足条件的peak元素
     * */
    public static int func2(int[] a) {
        if (a.length == 0)
            return -1;

        if (a.length == 1)
            return 0;

        if (a.length == 2)
            if (a[0] < a[1])
                return 0;
            else return 1;

        int lo = 0, hi = a.length - 1;
        int mid = (lo + hi) / 2;
        while (mid != 0 && mid != a.length - 1) {
            if (a[mid] < a[mid - 1] && a[mid] < a[mid + 1])
                return mid;
            else if (a[mid - 1] < a[mid + 1])
                hi = mid - 1;
            else
                lo = mid + 1;

            mid = (lo + hi) / 2;
        }
        return mid;
    }

    public static void main(String[] args) {
        int[] a = {-7, -4, -2, 1, 0, 4, 7, 8, 11, 9};
        StdRandom.shuffle(a);
        System.out.println(Arrays.toString(a));
        System.out.println(func1(a));
        System.out.println(func2(a));
    }

}

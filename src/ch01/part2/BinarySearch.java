package ch01.part2;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/4/14
 *  \* Time: 19:55
 *  \* To change this template use File | Settings | File Templates.
 *  \* Description: 
 *  \
 */
public class BinarySearch {
    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid])
                hi = mid - 1;
            else if (key > a[mid])
                lo = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    public static int rank_recursion(int key, int[] a, int lo, int hi, int d) {
        if (lo > hi) {
            System.out.printf("can't find %d\n", key);
            return -1;
        }
        int mid = lo + (hi - lo) / 2;
        System.out.printf("%d %d %d\n", lo, hi, d);
        if (a[mid] == key) {
            return mid;
        } else if (a[mid] > key) {
            return rank_recursion(key, a, lo, mid - 1, d + 1);
        } else {
            return rank_recursion(key, a, mid + 1, hi, d + 1);
        }
    }

    public static int gcd(int p, int q) {
        System.out.printf("%d %d\n", p, q);
        if (q == 0)
            return p;
        else
            return gcd(q, p % q);
    }

    public static int lower_bound(int key, int[] a) {
        //返回第一个大于等于key的位置
        int lo = 0, hi = a.length;
        int mid = 0;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            if (a[mid] >= key) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    public static int upper_bound(int key, int[] a) {
        //返回第一个大于key的位置
        int lo = 0, hi = a.length;
        int mid = 0;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            if (a[mid] <= key)
                lo = mid + 1;
            else
                hi = mid;
        }
        return lo;
    }

    public static void main(String[] args) {
//        int[] a = {1, 2, 3, 4, 4, 4, 7, 8, 11, 13};
//        int key = 4;
//        System.out.println(lower_bound(key, a));
//        System.out.println(upper_bound(key, a));
//        int i = lower_bound(key, a);
//        int j = upper_bound(key, a);
//        System.out.printf("a中小于 %d 的数量为 %d ", key, i);
//        System.out.printf("a中等于 %d 的数量为 %d ", key, j - i);

    }

}


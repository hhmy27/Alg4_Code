package ch01.part4;

import java.util.Arrays;

/**
 * @program: AlgorithmBook
 * @author: hhmy27
 * @created: 2020/06/15 21:23
 * @description: ex 1.4.11
 */
public class StaticSETofInts {
    private int[] a;

    public StaticSETofInts(int[] keys) {
        a = new int[keys.length];
        for (int i = 0; i < keys.length; i++) {
            a[i] = keys[i];
        }
        Arrays.sort(a);
    }

    boolean contains(int key) {
        return rank(key) != -1;
    }

    private int rank(int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid])
                lo = mid + 1;
            else if (key > a[mid])
                hi = mid - 1;
            else
                return mid;
        }
        return -1;
    }

    public int howMany(int key) {
        int lo = 0;
        int hi = a.length - 1;
        int mid = 0;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            if (key <= a[mid])
                hi = mid;
            else
                lo = mid + 1;
        }
        if (a[lo] != key)
            return 0;
        int sum = 0;
        while (a[lo++] == key) {
            sum += 1;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] t = {1, 1, 2, 2, 2, 3, 5, 5, 6, 8, 9, 10, 10, 11, 13};
        StaticSETofInts s = new StaticSETofInts(t);
        System.out.println(s.howMany(5));
    }
}

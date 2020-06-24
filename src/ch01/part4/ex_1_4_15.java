package ch01.part4;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @program: AlgorithmBook
 * @author: hhmy27
 * @created: 2020/06/23 09:02
 * @description: ex_1_4_15 answer
 */
public class ex_1_4_15 {

    // O(N^2)
    public static void TwoSum_ini(int[] a) {
        int cnt = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] + a[j] == 0)
                    cnt++;
            }
        }
        System.out.println(cnt);
    }

    // O(NlogN)版本 也是书上的版本,不适用于数组中出现重复数的情况
    public static void TwoSum_NlogN(int[] a) {
        int cnt = 0;
        for (int i = 0; i < a.length; i++) {
            if (BinarySearch.indexOf(a, -a[i]) > i)
                cnt += 1;
        }
        System.out.println(cnt);
    }

    // O(N),使用hash表存储
    public static void TwoSumFaster(int[] a) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int cnt = 0;
        // a中元素以及它出现的次数
        for (Integer i : a) {
            int count = 0;
            if (map.containsKey(i))
                count = map.get(i);
            map.put(i, count + 1);
        }
        // 注意是遍历a而不是map
        for (Integer v : a) {
            // 如果存在-v使得两数之和为0
            if (map.containsKey(-v)) {
                if (v != 0)
                    cnt += map.get(-v);
            }
        }
        cnt /= 2;
        // 特殊处理0
        if (map.containsKey(0) && map.get(0) > 1)
            cnt += cmn(map.get(0), 2);
        System.out.println(cnt);
    }

    // O(N),不需要额外的空间开销，特点是依据a已经排序的前提,和 two point 的思想
    // 我们知道，如果能在一个有序数组中找到了两个数之和为零，那么它们一定是在该数组的两端（相对而言）
    // 使用i从左到右，使用j从右到左，取得a[i],a[j]，根据a[i]+a[j]的结果来移动指针，特别要注意的是数组中有重复数和有0元素的情况
    public static void TwoSumFaster_2(int[] a) {
        int i = 0, j = a.length - 1;
        int cnt = 0;
        //任何一个指针碰到0就退出了
        while (i < j && a[i] != 0 && a[j] != 0) {
            if (a[i] + a[j] > 0)
                j -= 1;
            else if (a[i] + a[j] < 0)
                i += 1;
            else {
                // 此时a[i]+a[j]=0，做进一步处理

                // 记录重复个数
                int di = 1, dj = 1;
                int ti = i + 1, tj = j - 1;
                while (a[ti] == a[i]) {
                    di += 1;
                    ti++;
                }
                while (a[tj] == a[j]) {
                    dj += 1;
                    tj--;
                }
                // 如果没有重复数
                if (di == 1 && dj == 1) {
                    i++;
                    j--;
                    cnt++;
                } else {
                    // 组合数
                    cnt += cmn(di, 1) * cmn(dj, 1);
                    i += di;
                    j -= dj;
                }
            }
        }
        int zn = 0;
        if (a[i] == 0) {
            while (a[i] == 0) {
                i++;
                zn++;
            }
        } else if (a[j] == 0) {
            while (a[j] == 0) {
                j--;
                zn++;
            }
        }
        if (zn > 1)
            cnt += cmn(zn, 2); //n个0里面选2个来形成组合数
        System.out.println(cnt);
    }

    //要求输入n不能大于m，否则会返回-1，这是无意义的
    private static long cmn(int m, int n) {
        if (n == 0)
            return 1;
        if (n == 1)
            return m;
        if (n > m / 2)
            return cmn(m, m - n);
        if (n > 1)
            return cmn(m - 1, n - 1) + cmn(m - 1, n);
        return -1; //通过编译用
    }

    // O(N^2)
    public static void ThreeSumFaster() {

    }

    // 同样是two point的思想
    public static void ThreeSumFaster_2() {

    }

    public static void main(String[] args) {
//        int[] a = {-2, -1, 0, 0, 0, 1, 1, 2, 3};
        int[] a = In.readInts("src/data/4Kints.txt");
        Arrays.sort(a);
//        TwoSum_ini(a);
//        TwoSumFaster(a);
//        TwoSumFaster_2(a);
    }
}

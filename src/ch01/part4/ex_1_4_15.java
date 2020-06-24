package ch01.part4;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;

import java.util.*;

/**
 * @program: AlgorithmBook
 * @author: hhmy27
 * @created: 2020/06/23 09:02
 * @description: ex_1_4_15 answer
 */
public class ex_1_4_15 {

    // O(N^2)
    public static int TwoSum_ini(int[] a) {
        int cnt = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] + a[j] == 0)
                    cnt++;
            }
        }
        return cnt;
    }

    // O(NlogN)版本 也是书上的版本,不适用于数组中出现重复数的情况
    public static int TwoSum_NlogN(int[] a) {
        int cnt = 0;
        for (int i = 0; i < a.length; i++) {
            if (BinarySearch.indexOf(a, -a[i]) > i)
                cnt += 1;
        }
        return cnt;
    }

    // O(NlogN)版本 也是书上的版本,不适用于数组中出现重复数的情况
    public static int TwoSum_NlogN_p(int[] a) {
        int cnt = 0;
        for (int i = 0; i < a.length; i++) {
            int j = lower_bound(a, -a[i]);
            if (j > i && a[j] + a[i] == 0) {
                int r = 1;
                while (a[++j] == -a[i])
                    r++;
                cnt += r;
            }
        }
        int zn = (int) Arrays.stream(a).filter(v -> v == 0).count();
        if (zn > 1)
            cnt += cmn(zn, 2);
        return cnt;
    }

    private static int lower_bound(int[] a, int key) {
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

    // O(N),使用hash表存储,对于0特殊处理
    public static int TwoSumFaster(int[] a) {
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
            if (v != 0)
                // 如果存在-v使得两数之和为0
                if (map.containsKey(-v))
                    cnt += map.get(-v);

        }
        cnt /= 2;

        // 特殊处理0
        if (map.containsKey(0) && map.get(0) > 1)
            cnt += cmn(map.get(0), 2);
//        System.out.println(cnt);
        return cnt;
    }

    // O(N),不需要额外的空间开销，特点是依据a已经排序的前提,和 two point 的思想
    // 我们知道，如果能在一个有序数组中找到了两个数之和为零，那么它们一定是在该数组的两端（相对而言）
    // 使用i从左到右，使用j从右到左，取得a[i],a[j]，根据a[i]+a[j]的结果来移动指针，特别要注意的是数组中有重复数和有0元素的情况
    public static int TwoSumFaster_2(int[] a) {
        int i = 0, j = a.length - 1;
        int cnt = 0;
        // 如果没有全为正或者全为负就退出
        if (a[i] > 0 || a[j] < 0)
            return 0;
        // 在two sum里面，是求两个数之和，所以任何一个指针碰到0就退出了
        while (i < j && a[i] != 0 && a[j] != 0) {
            if (a[i] + a[j] > 0)
                j -= 1;
            else if (a[i] + a[j] < 0)
                i += 1;
            else {
                // 此时a[i]+a[j]=0，做进一步处理
                // 记录重复个数
                int di = 1, dj = 1;
                while (i + di < j && a[i + di] == a[i]) {
                    di += 1;
                }
                while (j - dj > i && a[j - dj] == a[j]) {
                    dj += 1;
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
        // 记录0的个数 特殊处理0和0的组合
        int zn = (int) Arrays.stream(a).filter(v -> v == 0).count();
        if (zn > 1)
            cnt += cmn(zn, 2);
        return cnt;
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

    // 时间复杂度大于等于 O(N^2)
    // 同样借助hash表,不同的是hash表里面存放的是这个元素的下标，目的是为了不重复计算组合
    public static int ThreeSumFaster(int[] a) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        // 记录答案
        int cnt = 0;
        // 记录下标
        for (int i = 0; i < a.length; i++) {
            if (!map.containsKey(a[i]))
                map.put(a[i], new ArrayList<>());
            map.get(a[i]).add(i);
        }

        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                int t = a[i] + a[j];
                if (map.containsKey(-t)) {
                    int finalJ = j;
                    cnt += map.get(-t).stream().filter(v -> v.compareTo(finalJ) > 0).count();
                }
            }
        }
//        System.out.println(cnt);
        return cnt;
    }

    // 同样按照two point 的思想，由于是求三数之和，我们外面再套一个for
    public static int ThreeSumFaster_2(int[] a) {
        int start = 0, end = a.length - 1;
        int cnt = 0;
        // 如果没有全为正或者全为负就退出
        if (a[start] > 0 || a[end] < 0)
            return 0;

        for (int i = 0; i < a.length; i++) {
            start = i + 1;
            end = a.length - 1;

            while (start < end) {
                // 现在的判断条件变为三数之和
                int t = a[i] + a[start] + a[end];
                if (t > 0)
                    end -= 1;
                else if (t < 0)
                    start += 1;
                else {
                    // 此时a[i]+a[j]=0，做进一步处理

                    // 跳过为0的情况，0的组合单独计算
                    if (a[start] == 0 && a[end] == 0) {
                        start++;
                        end--;
                        continue;
                    }
                    // 记录重复个数
                    int di = 1, dj = 1;

                    while (start + di < end && a[start + di] == a[start]) {
                        di += 1;
                    }

                    while (end - dj > start && a[end - dj] == a[end]) {
                        dj += 1;
                    }

                    // 如果没有重复数
                    if (di == 1 && dj == 1) {
                        start++;
                        end--;
                        cnt++;
                    } else {
                        // 组合数
                        cnt += cmn(di, 1) * cmn(dj, 1);
                        start += di;
                        end -= dj;
                    }
                }
            }
        }
        // 记录0的个数 特殊处理0和0的组合
        int zn = (int) Arrays.stream(a).filter(value -> value == 0).count();
        if (zn > 2)
            cnt += cmn(zn, 3);
        return cnt;
    }


    public static void main(String[] args) {
        int[] a = {-2, -1, 0, 0, 0, 1, 1, 2, 3};
        int[] b = In.readInts("src/data/2Kints.txt");
        Arrays.sort(b);
        int[] c = In.readInts("src/data/4Kints.txt");
        Arrays.sort(c);

        System.out.println("three sum 12, a");
        System.out.println(ThreeSumFaster(a));
        System.out.println(ThreeSumFaster_2(a));

        System.out.println("three sum 528, b");
        System.out.println(ThreeSumFaster(b));
        System.out.println(ThreeSumFaster_2(b));

        System.out.println("three sum 4039, c");
        System.out.println(ThreeSumFaster(c));
        System.out.println(ThreeSumFaster_2(c));

        System.out.println("two sum 6, a");
        System.out.println(TwoSum_ini(a));
        System.out.println(TwoSum_NlogN_p(a));
        System.out.println(TwoSumFaster(a));
        System.out.println(TwoSumFaster_2(a));

        System.out.println("two sum 2, b");
        System.out.println(TwoSum_ini(b));
        System.out.println(TwoSum_NlogN_p(b));
        System.out.println(TwoSumFaster(b));
        System.out.println(TwoSumFaster_2(b));

        System.out.println("two sum 3, c");
        System.out.println(TwoSum_ini(c));
        System.out.println(TwoSum_NlogN_p(c));
        System.out.println(TwoSumFaster(c));
        System.out.println(TwoSumFaster_2(c));
    }
}

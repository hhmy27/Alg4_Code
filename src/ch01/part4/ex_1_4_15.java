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

    // O(NlogN) two sum, effective only when a does NOT contain duplicates number
    public static int TwoSum_NlogN(int[] a) {
        int cnt = 0;
        for (int i = 0; i < a.length; i++) {
            if (BinarySearch.indexOf(a, -a[i]) > i)
                cnt += 1;
        }
        return cnt;
    }

    // check ex_1_4_10
    private static int lower_bound(int[] a, int key) {
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

    // O(NlogN), use lower_bound, see at ex_1_4_10
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
        // 0 + 0 = 0, O(n)
        int zn = (int) Arrays.stream(a).filter(v -> v == 0).count();
        if (zn > 1)
            cnt += cmn(zn, 2);
        return cnt;
    }


    // time complexity: O(N)
    // use hash map
    // space complexity: O(N)
    public static int TwoSumFaster(int[] a) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int cnt = 0;
        // counter a[i] and its times
        for (Integer i : a) {
            int count = 0;
            if (map.containsKey(i))
                count = map.get(i);
            map.put(i, count + 1);
        }

        for (Integer v : a) {
            if (v != 0)
                // if find -v
                if (map.containsKey(-v))
                    cnt += map.get(-v);
        }

        // remove duplication
        cnt /= 2;

        // process 0 number
        if (map.containsKey(0) && map.get(0) > 1)
            cnt += cmn(map.get(0), 2);
        return cnt;
    }

    // time complexity: O(N)
    // space complexity: O(1)
    // a is sorted, we use two point to iterate
    public static int TwoSumFaster_p(int[] a) {
        int i = 0, j = a.length - 1;
        int cnt = 0;
        // cant make up any pair
        if (a[i] > 0 || a[j] < 0)
            return 0;

        // if a[i] or a[j] == 0, end loop.
        while (i < j && a[i] != 0 && a[j] != 0) {
            if (a[i] + a[j] > 0)
                j -= 1;
            else if (a[i] + a[j] < 0)
                i += 1;
            else {
                // record duplicated number
                int di = 1, dj = 1;
                while (i + di < j && a[i + di] == a[i]) {
                    di += 1;
                }
                while (j - dj > i && a[j - dj] == a[j]) {
                    dj += 1;
                }
                // if not duplicated number
                if (di == 1 && dj == 1) {
                    i++;
                    j--;
                    cnt++;
                } else {
                    // calculate pair
                    cnt += cmn(di, 1) * cmn(dj, 1);
                    i += di;
                    j -= dj;
                }
            }
        }

        // process zero
        int zn = (int) Arrays.stream(a).filter(v -> v == 0).count();
        if (zn > 1)
            cnt += cmn(zn, 2);
        return cnt;
    }

    // n <= m
    private static long cmn(int m, int n) {
        if (n == 0)
            return 1;
        if (n == 1)
            return m;
        if (n > m / 2)
            return cmn(m, m - n);
        if (n > 1)
            return cmn(m - 1, n - 1) + cmn(m - 1, n);
        return -1; // useless, through compile
    }

    // use hash map
    public static int ThreeSumFaster(int[] a) {
        // key: value
        // value: index of value, a[index] = value
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int cnt = 0;
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

        return cnt;
    }

    // use two point also
    public static int ThreeSumFaster_p(int[] a) {
        int start = 0, end = a.length - 1;
        int cnt = 0;
        // cant make up any pair
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

                    if (a[start] == 0 && a[end] == 0) {
                        start++;
                        end--;
                        continue;
                    }

                    int di = 1, dj = 1;

                    while (start + di < end && a[start + di] == a[start]) {
                        di += 1;
                    }

                    while (end - dj > start && a[end - dj] == a[end]) {
                        dj += 1;
                    }

                    if (di == 1 && dj == 1) {
                        start++;
                        end--;
                        cnt++;
                    } else {
                        cnt += cmn(di, 1) * cmn(dj, 1);
                        start += di;
                        end -= dj;
                    }
                }
            }
        }

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
        System.out.println(ThreeSumFaster_p(a));

        System.out.println("three sum 528, b");
        System.out.println(ThreeSumFaster(b));
        System.out.println(ThreeSumFaster_p(b));

        System.out.println("three sum 4039, c");
        System.out.println(ThreeSumFaster(c));
        System.out.println(ThreeSumFaster_p(c));

        System.out.println("two sum 6, a");
        System.out.println(TwoSum_ini(a));
        System.out.println(TwoSum_NlogN_p(a));
        System.out.println(TwoSumFaster(a));
        System.out.println(TwoSumFaster_p(a));

        System.out.println("two sum 2, b");
        System.out.println(TwoSum_ini(b));
        System.out.println(TwoSum_NlogN_p(b));
        System.out.println(TwoSumFaster(b));
        System.out.println(TwoSumFaster_p(b));

        System.out.println("two sum 3, c");
        System.out.println(TwoSum_ini(c));
        System.out.println(TwoSum_NlogN_p(c));
        System.out.println(TwoSumFaster(c));
        System.out.println(TwoSumFaster_p(c));
    }
}

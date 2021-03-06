package ch01.part4;

import java.util.Arrays;

import static java.lang.Math.abs;
import static java.util.Arrays.sort;

/**
 * @program: AlgorithmBook
 * @author: hhmy27
 * @created: 2020/06/26 08:46
 * @description:
 */
public class ex_1_4_16 {

    // O(NlogN)
    public static double[] func(double[] a) {
        if (a.length == 0) {
            throw new Error("empty list");
        }

        sort(a);
        double[] ans = new double[2];
        // min is most closer to 0, not most minimal, because exercise require most closer
        double min = a[0];
        for (int i = 0; i < a.length - 1; i++) {
            if (abs(a[i] - a[i + 1]) * abs(a[i] - a[i + 1]) < min * min) {
                min = abs(a[i] - a[i + 1]);
                ans[0] = a[i];
                ans[1] = a[i + 1];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        double[] a = {1.1, 2.3, -1.2, -3.1, 2.7, 1.8, 0.4, 0.9, -0.3, -1.8, -2};
        System.out.println(Arrays.toString(func(a)));
    }
}

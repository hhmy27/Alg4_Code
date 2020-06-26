package ch01.part4;

import java.util.Arrays;

/**
 * @program: AlgorithmBook
 * @author: hhmy27
 * @created: 2020/06/26 09:01
 * @description:
 */
public class ex_1_4_17 {
    // O(N) mean cant sort a[]
    public static double[] func(double[] a) {
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        for (double v : a) {
            if (v < min)
                min = v;
            if (v > max)
                max = v;
        }
        return new double[]{min, max};
    }

    public static void main(String[] args) {
        double[] a = {1.1, 2.3, -1.2, -3.1, 2.7, 1.8, 0.4, 0.9, -0.3, -1.8, -2};
        System.out.println(Arrays.toString(func(a)));
    }
}

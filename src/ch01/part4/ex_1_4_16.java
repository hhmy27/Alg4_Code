package ch01.part4;

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
    public static double func(double[] a) {
        if (a.length == 0) {
            throw new Error("数组为空");
        }

        sort(a);
        // min应该是最接近0的数，而不是最小的数
        double min = a[0];
        for (int i = 0; i < a.length - 1; i++) {
            // 使用平方公式算和0的距离
            if (abs(a[i] - a[i + 1]) * abs(a[i] - a[i + 1]) < min * min) {
                System.out.println(a[i] + " " + a[i + 1]);
                min = abs(a[i] - a[i + 1]);
            }
        }
        return min;
    }

    public static void main(String[] args) {
        double[] a = {1.1, 2.3, -1.2, -3.1, 2.7, 1.8, 0.4, 0.9, -0.3, -1.8, -2};
        System.out.println(func(a));
    }
}

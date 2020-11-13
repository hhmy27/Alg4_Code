package ch01.part1;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/4/26
 *  \* Time: 9:24
 *  \* To change this template use File | Settings | File Templates.
 *  \* Description: Matrix is answer of ex 1.1.33, Practice contains
 *  \
 */
public class Practice {
    static int[] arr = new int[100000];

    public static int F(int N) {
        if (N <= 1)
            return arr[N];
        else {
            if (arr[N] != 0)
                return arr[N];
            else {
                arr[N] = F(N - 1) + F(N - 2);
                return arr[N];
            }
        }
    }

    public static long F2(int N) {
        int[] arr = new int[100000];
        arr[0] = arr[1] = arr[2] = 1;
        for (int i = 3; i <= N; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[N];
    }

    public static int mystery(int a, int b) {
        if (b == 0) return 1;
        if (b % 2 == 0) return mystery(a * a, b / 2);
        return mystery(a * a, b / 2) * a;
    }

    public static void numToBin(int num) {
        String s = "";
        while (num != 0) {
            s = num % 2 + s;
            num /= 2;
        }
        System.out.println(s);
    }

    public static void lg(int num) {
        double precision = 0.0001;
        double lo = 0;
        double hi = num;
        double mid = hi;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            double value = Math.pow(2, mid) - num;
            if (value < precision && value > -precision) {
                System.out.println(mid);
                return;
            } else if (value > precision) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        System.out.println("can't find!");
    }

    public static String exR1(int n) {
        if (n <= 0) return "";
        return exR1(n - 3) + n + exR1(n - 2) + n;
    }

    public static double log(int num, int n) {
        double precision = 0.0001;
        double lo = 0;
        double hi = num;
        double mid = hi;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            double value = Math.pow(n, mid) - num;
            if (value < precision && value > -precision) {
                return mid;
            } else if (value > precision) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        // -1 表示不存在
        return -1;
    }

    public static double ln(int N) {
        if (N == 0 || N == 1) return 0;
        return ln(N - 1) + Math.log(N);
    }

    public static void main(String[] args) {
//        double[][] a = {{1, 0, 2}, {-1, 3, 1}};
//        double[][] b = {{3, 1}, {2, 1}, {1, 0}};
//        double[] x = {1, 2, 3};
//        double[] y = {2, -1, 2};
//        System.out.println(Matrix.dot(x, y));
//        System.out.println(Arrays.deepToString(Matrix.mult(a, b)));
//        System.out.println(Arrays.deepToString(Matrix.transpose(a)));
//        System.out.println(Arrays.toString(Matrix.mult(a,x)));
//        System.out.println(Arrays.toString(Matrix.mult(x,b)));
//        int []a={1,2,3};
//        int []b={3,2,1};
//        func(a,b);
//        System.out.println(Arrays.toString(a));
//        System.out.println(Arrays.toString(b));
//        System.out.println(Arrays.toString(a));
//        String s = "ACTGACG";
//        String t = "TGACGAC";
//        System.out.println(judgeStr(s, t));
//        String s="abcd";
//        System.out.println(mystery(s));
        int[] arr=new int[5];
        arr[0]=arr[1]=1;
        System.out.println(arr.length);
    }




    public static boolean judgeStr(String s, String t) {
        return s.concat(s).contains(t);
    }

    public static String mystery(String s) {
        int N = s.length();
        if (N <= 1) return s;
        String a = s.substring(0, N / 2);
        String b = s.substring(N / 2, N);
        return mystery(b) + mystery(a);
    }

    public static void func(int[] a) {
        a[1] = -a[1];
    }

    public static void func(int[] a, int[] b) {
        int[] t = a;
        a = b;
        b = t;
    }
}

class Matrix {
    public static double dot(double[] x, double[] y) {
        if (x.length != y.length)
            throw new Error();
        double ans = 0;
        for (int i = 0; i < x.length; i++) {
            ans += x[i] * y[i];
        }
        return ans;
    }

    public static double[][] mult(double[][] a, double[][] b) {
        int ai = a.length, aj = a[0].length;
        int bi = b.length, bj = b[0].length;
        if (aj != bi)
            throw new Error();
        double[][] ans = new double[ai][bj];
        for (int i = 0; i < ai; i++) {
            for (int j = 0; j < bj; j++) {
                double[] at = a[i];
                double[] bt = new double[bi];
                for (int k = 0; k < bi; k++) {
                    bt[k] = b[k][j];
                }
                ans[i][j] = dot(at, bt);
            }
        }
        return ans;
    }

    static double[][] transpose(double[][] a) {
        int ai = a.length, aj = a[0].length;
        double[][] ans = new double[aj][ai];
        for (int j = 0; j < aj; j++) {
            double[] t = new double[ai];
            for (int i = 0; i < ai; i++) {
                t[i] = a[i][j];
            }
            ans[j] = t;
        }
        return ans;
    }

    static double[] mult(double[][] a, double[] x) {
        int ai = a.length, aj = a[0].length;
        int xi = x.length;
        if (aj != xi)
            throw new Error("长度不相等");
        double[] ans = new double[ai];
        for (int i = 0; i < ai; i++) {
            ans[i] = dot(a[i], x);
        }
        return ans;
    }

    static double[] mult(double[] y, double[][] a) {
        int ai = a.length, aj = a[0].length;
        int yi = y.length;
        if (yi != ai)
            throw new Error("长度不相等");
        double[] ans = new double[aj];
        for (int i = 0; i < aj; i++) {
            double[] t = new double[ai];
            for (int k = 0; k < ai; k++) {
                t[k] = a[k][i];
            }
            ans[i] = dot(y, t);
        }
        return ans;
    }
}

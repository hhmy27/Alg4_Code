package ch01.part4;

/**
 * @program: Alg4_Code
 * @author: hhmy27
 * @created: 2020/10/06 20:32
 * @description: answer for 1.4.20
 */
public class ex_1_4_20 {

    public static boolean func(int[] A, int x, int L, int R) {
        if (L > R)
            return false;
        int M = (L + R) / 2;
        if (A[M] == x)
            return true;
        else {
            if (M != A.length - 1) {
                // 降序序列
                if (A[M] > A[M + 1]) {
                    if (A[M] > x) {
                        return func(A, x, L, M - 1) || func(A, x, M + 1, R);
                    } else {
                        return func(A, x, L, M - 1);
                    }
                } else {
                    if (A[M] > x) {
                        return func(A, x, L, M - 1) || func(A, x, M + 1, R);
                    } else {
                        return func(A, x, M + 1, R);
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 4, 7, 8, 6, 3};
        int[] B = {2, 5, 6, 3, 1};
        int[] C = {3, 7, 8, 11, 1};
        System.out.println(func(A, 3, 0, A.length - 1));
        System.out.println(func(B, 5, 0, B.length - 1));
        System.out.println(func(C, 9, 0, C.length - 1));
    }
}
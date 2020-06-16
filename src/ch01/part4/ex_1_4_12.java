package ch01.part4;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: AlgorithmBook
 * @author: hhmy27
 * @created: 2020/06/16 15:41
 * @description: answer for ex 1.4.12
 */
public class ex_1_4_12 {
    // a,b 有序数组
    // 打印出a,b的公共元素，要求运行时间和N成正比
    public static void print(int[] a, int[] b) {
        int i = 0, j = 0;
        while (i < a.length && j < b.length) {
            if (a[i] == b[j]) {
                System.out.println(a[i]);
                i++;
                j++;
            } else if (a[i] < b[j])
                i++;
            else
                j++;
        }

    }

    public static void print2(int[] a, int[] b) {
        Set<Integer> sa=new HashSet<>();
        Set<Integer> sb=new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            sa.add(a[i]);
        }
        for(int j=0;j<b.length;j++){
            sb.add(b[j]);
        }
        sa.retainAll(sb);
        System.out.println(sa);
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6,6};
        int[] b = {3, 5, 6, 7, 8, 8};
        print2(a, b);
    }
}

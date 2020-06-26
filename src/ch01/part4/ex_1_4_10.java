package ch01.part4;

/**
 * @program: AlgorithmBook
 * @author: hhmy27
 * @created: 2020/06/15 21:10
 * @description: answer for  1.4.10
 */
public class ex_1_4_10 {

    public static void find(int[] list, int k) {
        int L = 0, R = list.length - 1;
        int mid = 0;
        // [L,R], L==R退出，此时只有一个元素,要么停留在索引最小的key值处，要么key值不存在
        while (L < R) {
            mid = (R + L) / 2;
            // 尝试寻找更小的索引
            if (list[mid] >= k)
                R = mid;
            else
                L = mid + 1;
        }
        if (list[L] != k)
            System.out.println("can't find " + k);
        else
            System.out.println("find k at " + L);
    }

    public static void main(String[] args) {
        int[] list = {1, 1, 2, 2, 2, 3, 5, 5, 6, 8, 9, 10, 10, 11, 13};
        find(list, 3);
    }
}
package ch01.part3;

import org.junit.Test;

/**
 * @program: Alg4_Code
 * @author: hhmy27
 * @created: 2020/11/12 16:03
 * @description: thread like ex_1_3_3
 */
public class ex_1_3_13 {
    public boolean func(String input) {
        Queue<Integer> que = new Queue<>();
        int ind = 0;
        for (int i = 0; i < 9; i++) {
            que.enqueue(i);
            while (!que.isEmpty() && que.front().equals(input.charAt(ind) - '0')) {
                que.dequeue();
                ind++;
            }
        }
        return que.isEmpty();
    }

    @Test
    public void test1() {
        String input = "0123456789";
        System.out.println(func(input));

    }

    @Test
    public void test2() {
        String input = "4687532901";
        System.out.println(func(input));
    }

    @Test
    public void test3() {
        String input = "2567489310";
        System.out.println(func(input));
    }

    @Test
    public void test4() {
        String input = "4321056789";
        System.out.println(func(input));

    }
}

package ch02.part2;


import java.util.*;

/**
 * @program: AlgorithmBook
 * @author: hhmy27
 * @created: 2021/02/08 20:47
 * @description:
 */
public class ex_2_2_14 {
    public static Queue<Integer> func(Queue<Integer> q1, Queue<Integer> q2) {
        Queue<Integer> que = new LinkedList<Integer>();
        while (!q1.isEmpty() && !q2.isEmpty()) {
            Integer p1 = q1.peek();
            Integer p2 = q2.peek();
            if (p1 <= p2) {
                q1.poll();
                que.add(p1);
            } else {
                q2.poll();
                que.add(p2);
            }
        }
        while (!q1.isEmpty()) {
            que.add(q1.poll());
        }
        while (!q2.isEmpty()) {
            que.add(q2.poll());
        }
        return que;
    }

    public static void main(String[] args) {
        Queue<Integer> q1 = new LinkedList<Integer>(new ArrayList<Integer>(Arrays.asList(1, 2, 5, 6, 7)));
        Queue<Integer> q2 = new LinkedList<Integer>(new ArrayList<Integer>(Arrays.asList(3, 4, 8)));
        Queue<Integer> ans = func(q1, q2);
        System.out.println(Arrays.asList(ans));

    }
}

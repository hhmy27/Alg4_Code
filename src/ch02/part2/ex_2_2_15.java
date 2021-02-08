package ch02.part2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.*;

/**
 * @program: AlgorithmBook
 * @author: hhmy27
 * @created: 2021/02/08 21:24
 * @description:
 */
public class ex_2_2_15 {
    public static Queue<Queue<Integer>> func(List<Integer> list) {
        int N = list.size();
        Queue<Queue<Integer>> QUE = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            QUE.add(new LinkedList<Integer>(Collections.singleton(list.get(i))));
        }
        while (QUE.size() != 1) {
            Queue<Integer> p1 = QUE.poll();
            Queue<Integer> p2 = QUE.poll();
            Queue<Integer> np = ex_2_2_14.func(p1, p2);
            QUE.add(np);
        }
        return QUE;
    }

    public static void main(String[] args) {
        int N = 16;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(StdRandom.uniform(50));
        }
        System.out.println(list);
        Queue<Queue<Integer>> que = func(list);
        System.out.println(que);

    }
}

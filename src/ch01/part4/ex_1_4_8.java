package ch01.part4;

import edu.princeton.cs.algs4.In;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @program: AlgorithmBook
 * @author: hhmy27
 * @created: 2020/06/15 19:33
 * @description: answer for ex 1.4.8
 */
public class ex_1_4_8 {
    public static ArrayList<Integer> read() {
        ArrayList<Integer> list = new ArrayList<>();
        File file = new File("src/ch01/part4/ex_1_4_8_input");
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(reader);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                list.add(Integer.valueOf(lineTxt));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void N2() {
        ArrayList<Integer> list = read();
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            int q = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                if (q == list.get(j))
                    sum += 1;
            }
        }
        System.out.println(sum);
    }

    public static void NlogN() {
        ArrayList<Integer> list = read();
        list.sort(Integer::compareTo);
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            int q = list.get(i);
            int j = i + 1;
            // get all equal number
            while (j < list.size() && list.get(j) == q)
                j += 1;
            // k is equal number
            int k = j - i;
            if (k == 1)
                continue;
            int d = k - 2;
            sum += factorial(k) / factorial(d) / 2;
            i = j - 1;
        }
        System.out.println(sum);
    }

    public static void N() {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> list = read();
        int sum = 0;
        for (Integer i : list) {
            int count = 0;
            if (map.containsKey(i))
                count = map.get(i);
            map.put(i, count + 1);
        }
        for (Integer value : map.keySet()) {
            int k = map.get(value);
            if (k > 1)
                sum += factorial(k) / factorial(k - 2) / 2;
        }
        System.out.println(sum);
    }

    public static int factorial(int num) {
        if (num <= 1)
            return 1;
        return num * factorial(num - 1);
    }

    public static void main(String[] args) {
        N2();
        NlogN();
        N();
    }
}

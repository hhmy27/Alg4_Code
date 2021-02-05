package ch02.demo;

import ch02.part1.ex_2_1_24;
import ch02.part2.ex_2_2_11_1;
import ch02.part2.ex_2_2_11_2;
import ch02.part2.ex_2_2_11_3;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @program: Alg4_Code
 * @author: hhmy27
 * @created: 2021/02/01 16:42
 * @description: get two sort class and compare their performance.
 * ref: https://stackoverflow.com/questions/17387888/java-reflection-to-invoke-a-method-that-takes-a-comparable-argument
 */
public class CompareSort {
    private Object A;
    private Object B;
    // sort name
    private String nameA;
    private String nameB;
    // sort method
    private Method methodA;
    private Method methodB;

    CompareSort(Object A, Object B) throws NoSuchMethodException {
        this.A = A;
        this.B = B;
        String methodName = "sort";
        Class<?> aClass = A.getClass();
        Class<?> bClass = B.getClass();
        nameA = aClass.getSimpleName();
        nameB = bClass.getSimpleName();

        // 拿到方法
        methodA = aClass.getDeclaredMethod(methodName, Comparable[].class);
        methodB = bClass.getDeclaredMethod(methodName, Comparable[].class);

    }

    private void sortCompare() throws InvocationTargetException, IllegalAccessException {
        int arrayLength = (int) 50e4;
        int numberOfExperiments = 5;
        double timeA = timeRandomInput(A, methodA, arrayLength, numberOfExperiments);
        double timeB = timeRandomInput(B, methodB, arrayLength, numberOfExperiments);


        StdOut.printf("For %d random doubles\n", arrayLength);
        StdOut.printf("time ratio %s / %s is %.2f ", nameA, nameB, timeA / timeB);

    }

    private double timeRandomInput(Object object, Method method, int length, int numberOfExperiments) throws InvocationTargetException, IllegalAccessException {
        double total = 0;
        Comparable[] a = new Comparable[length];
        for (int i = 0; i < numberOfExperiments; i++) {
            for (int j = 0; j < length; j++) {
                a[j] = StdRandom.uniform();
            }
            total += time(object, method, a);
        }
        return total;
    }

    private double time(Object object, Method method, Comparable[] a) throws InvocationTargetException, IllegalAccessException {
        Stopwatch timer = new Stopwatch();
        // careful that, check ref to avoid IllegalArgumentException error
        method.invoke(object, (Object) a);

        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        CompareSort compareSort = null;
        try {
            compareSort = new CompareSort(new Merge(), new MergeBU());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            assert compareSort != null;
            compareSort.sortCompare();
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
package ch01.part5;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @program: Alg4_Code
 * @author: hhmy27
 * @created: 2021/01/24 18:38
 * @description:
 */
public abstract class UF {
    public int[] id;
    public int count;

    public UF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    // many way to implement it
    // QuickFindUF
    // QuickUnionUF
    // WeightedQuickUnionUF

    abstract void union(int p, int q);

    abstract int find(int p);


    boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    int count() {
        return count;
    }

    public static void main(String[] args) {
//       int[] a = In.readInts("src/data/tinyUF.txt");
//
//
//        int N = a[0];
//        UF uf = new UF(N);
//
//        for (int i = 1; i < 2 * N; i++) {
//            int p = a[i];
//            int q = a[i + 1];
//            if (uf.connected(p, q)) continue;
//            uf.union(p, q);
//            StdOut.println(p + " " + q);
//        }
//
//        StdOut.println(uf.count() + " components");
    }
}
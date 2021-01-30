package ch01.part5;

import edu.princeton.cs.algs4.In;

/**
 * @program: Alg4_Code
 * @author: hhmy27
 * @created: 2021/01/25 14:11
 * @description:
 */
public class ex_1_5_12 extends QuickUnionUF {
    public ex_1_5_12(int N) {
        super(N);
    }

    @Override
    void union(int p, int q) {
        super.union(p, q);
    }

    @Override
    int find(int p) {
        int root = p;
        while (root != id[root]) root = id[root];

        while (p != id[p]) {
            int t = p;
            p = id[p];
            id[t] = root;
        }

        return root;
    }

    public static void main(String[] args) {
        int[] a = In.readInts("src/data/tinyUF.txt");


        int N = a[0];
        UF uf = new QuickUnionUF(N);

        for (int i = 1; i < 2 * N; i++) {
            int p = a[i];
            int q = a[i + 1];
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            System.out.println(p + " " + q);
        }

        System.out.println(uf.count() + " components");
    }
}
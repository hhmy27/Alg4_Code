package ch01.part5;

import edu.princeton.cs.algs4.In;

/**
 * @program: Alg4_Code
 * @author: hhmy27
 * @created: 2021/01/25 12:47
 * @description:
 */
public class QuickUnionUF extends UF {

    public QuickUnionUF(int N) {
        super(N);
    }

    @Override
    void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;
        id[pRoot] = qRoot;
        count--;
    }

    @Override
    int find(int p) {
        while (p != id[p]) p = id[p];
        return p;
    }

    public static void main(String[] args) {
        int[] a = In.readInts("src/data/largeUF.txt");


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
package ch01.part5;

import edu.princeton.cs.algs4.In;

/**
 * @program: Alg4_Code
 * @author: hhmy27
 * @created: 2021/01/25 13:15
 * @description:
 */
public class WeightedQuickUnionUF extends UF {
    // 各个根节点所对应的分量的大小
    private int[] sz;

    public WeightedQuickUnionUF(int N) {
        super(N);
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            sz[i] = 1;
        }
    }

    @Override
    void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) return;
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
        count -= 1;
    }

    @Override
    int find(int p) {
        while (p != id[p]) p = id[p];
        return p;
    }

    public static void main(String[] args) {
        int[] a = In.readInts("src/data/largeUF.txt");


        int N = a[0];
        UF uf = new WeightedQuickUnionUF(N);

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
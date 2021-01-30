package ch01.part5;

import edu.princeton.cs.algs4.In;

/**
 * @program: Alg4_Code
 * @author: hhmy27
 * @created: 2021/01/25 12:44
 * @description:
 */
public class QuickFindUF extends UF {
    public QuickFindUF(int N) {
        super(N);
    }

    @Override
    void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        if (pID == qID) return;
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID)
                id[i] = qID;
        }
        count -= 1;
    }

    @Override
    int find(int p) {
        return id[p];
    }

    public static void main(String[] args) {
        int[] a = In.readInts("src/data/tinyUF.txt");


        int N = a[0];
        UF uf = new QuickFindUF(N);

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
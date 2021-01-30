package ch01.part4;

/**
 * @program: AlgorithmBook
 * @author: hhmy27
 * @created: 2020/08/16 16:34
 * @description: 在矩阵中找到一个局部最小元素
 */
public class ex_1_4_19 {

    // brute force
    public static int func1(int a[][]) {
        // direction
        int dx[] = {0, -1, 0, 1};
        int dy[] = {-1, 0, 1, 0};

        // iterate matrix to find a local minimal number
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                boolean flag = true;
                for (int k = 0; k < 4; k++) {
                    int tx = i + dx[k];
                    int ty = j + dy[k];
                    if (tx < 0 || ty < 0 || tx >= a.length || ty >= a.length) {
                        continue;
                    }
                    if (a[tx][ty] <= a[i][j]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) return a[i][j];
            }
        }
        // just for pass compile, never execute it
        return -1;
    }

    public static int func2(int a[][]) {
        if (a == null || a.length == 0 || (a.length == 1 && a[0].length == 0))
            throw new Error("矩阵不能为空");

        int N = a.length;
        int lo = 0, hi = N * N - 1;

        int dx[] = {0, -1, 0, 1};
        int dy[] = {-1, 0, 1, 0};

        while (true) {
            int mi = lo + (hi - lo) / 2;
            // list[mid] corresponding matrix[x][y]
            int x = mi / N, y = mi % N;
            int min = a[x][y];
            int nx = x, ny = y;
            boolean flag = true;

            // judge it meet or not
            for (int k = 0; k < 4; k++) {
                int tx = x + dx[k];
                int ty = y + dy[k];
                if (tx < 0 || ty < 0 || tx >= a.length || ty >= a.length) {
                    continue;
                }
                if (a[tx][ty] < a[x][y]) {
                    flag = false;
                }
                // record minimal number in list[mid] around
                if (a[tx][ty] < min) {
                    min = a[ty][ty];
                    nx = tx;
                    ny = ty;
                }
            }
            // if list[mid] is local minimal number
            if (flag) return a[x][y];
            // search in smaller side
            int min_ind = nx * N + ny;

            if (min_ind > mi)
                lo = min_ind;
            else
                hi = min_ind;
        }
    }

    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;
        if (nums.length == 1) {
            return 0;
        }
        if (nums.length == 2) {
            return nums[0] > nums[1] ? 0 : 1;
        }
        int lo = 0, hi = nums.length - 1;
        int mi = lo + (hi - lo) / 2;
        // 碰到边界情况了
        while (mi != 0 && mi != nums.length - 1) {
            if (nums[mi] > nums[mi + 1] && nums[mi] > nums[mi - 1]) {
                return mi;
            } else {
                if (nums[mi + 1] > nums[mi - 1])
                    lo = mi + 1;
                else
                    hi = mi - 1;
            }
            mi = lo + (hi - lo) / 2;
        }
        // 边界情况特殊判断
        if (mi == 0) {
            return nums[mi] > nums[mi + 1] ? mi : mi + 1;
        } else {
            return nums[mi] > nums[mi - 1] ? mi : mi - 1;
        }
    }

    public static int func3(int a[][]) {
        int N = a.length;
        int y = N / 2;
        while (true) {
            int min = a[0][y];
            int x = 0;
            // 寻找该列最大元素
            for (int i = 0; i < a.length; i++) {
                if (a[i][y] < min) {
                    x = i;
                    min = a[i][y];
                }
            }
            if (y > 0 && a[x][y] > a[x][y - 1])
                y = y - 1;
            else if (y < a.length - 1 && a[x][y] > a[x][y + 1])
                y = y + 1;
            else
                return a[x][y];
        }
    }

    public static void main(String[] args) {
        int A[][] = {{5, 7, 3}, {8, 4, 1}, {2, 9, 10}};
        int B[][] = {{15, 4, 7, 9}, {6, 13, 8, 99}, {14, 21, 3, 17}, {16, 24, 2, 11}};
        int C[][] = {{}};
        int D[][] = {{1}};
        System.out.println(func1(A));
        System.out.println(func1(B));
        System.out.println(func1(C));
        System.out.println(func1(D));
        System.out.println("===");
        System.out.println(func2(A));
        System.out.println(func2(B));
//        System.out.println(func2(C));
        System.out.println(func2(D));
        System.out.println("===");
        System.out.println(func3(A));
        System.out.println(func3(B));
//        System.out.println(func3(C));
        System.out.println(func3(D));
    }
}

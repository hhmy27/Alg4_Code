package ch01;


/**
 * @program: AlgorithmBook
 * @description: ex 1.3.38 的数组实现
 * @author: HMY777
 * @created: 2020/05/14 08:24
 */
public class GeneralizedArrayQueue<Item> {
    private int right;
    private int N = 0;
    private Object[] list = new Object[2];

    GeneralizedArrayQueue() {
        right = 0;
    }

    public boolean isFull() {
        return N == list.length;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void pushFirst(Item item) {
        right = 0;
        list[right] = item;
        N = 1;
    }


    public void insert(Item item) {
        if (isEmpty()) {
            pushFirst(item);
        } else {
            if (isFull()) {
                resize(2 * list.length);
            }
//            right = (++right) % list.length;
            right++;
            list[right] = item;
            N += 1;
        }
    }

    public Item delete(int k) {
        if (k <= 0 || k > N)
            throw new Error("k error");
        k -= 1;
        Item item = (Item) list[k];
        // 移位
        resetArray(k);
        N -= 1;
        if (N == list.length / 4) {
            resize(list.length / 2);
        }
        return item;
    }

    private void resetArray(int k) {
        for (int i = k; i < N - 1; i++) {
            list[i] = list[i + 1];
        }
        list[N - 1] = null;
    }

    private void resize(int max) {
        Object[] t = new Object[max];
        for (int i = 0; i < N; i++) {
            t[i] = list[i];
        }
        right = N - 1;
        list = t;
    }

    public static void main(String[] args) {
        GeneralizedArrayQueue<Integer> queue = new GeneralizedArrayQueue<>();
        for (int i = 0; i < 5; i++) {
            queue.insert(i);
        }
        System.out.println(queue.delete(3));
        System.out.println(queue.delete(3));
        System.out.println(queue.delete(3));
        System.out.println(queue.delete(2));
        System.out.println(queue.delete(1));
        for (int i = 0; i < 3; i++) {
            queue.insert(i + 3);
        }
        System.out.println(queue.delete(2));
    }
}

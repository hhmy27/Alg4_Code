package ch01.part3;

/**
 * @program: AlgorithmBook
 * @author: hhmy27
 * @created: 2020/06/14 16:34
 * @description: answer for ex 1.3.48
 */
public class TwoStack<Item> {

    private Deque<Item> deque;

    public TwoStack() {
        deque = new Deque<>();
    }

    public void pushL(Item item) {
        deque.pushLeft(item);
    }

    public void pushR(Item item) {
        deque.pushRight(item);
    }

    public Item popL() {
        return deque.popLeft();
    }

    public Item popR() {
        return deque.popRight();
    }

    public boolean isEmpty() {
        return deque.isEmpty();
    }

    public static void main(String[] args) {
        TwoStack<Integer> ts = new TwoStack<>();
        ts.pushL(1);
        ts.pushR(2);
        ts.pushL(3);
        while (!ts.isEmpty()) {
            System.out.println(ts.popL());
        }
    }
}

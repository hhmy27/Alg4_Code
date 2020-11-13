package ch01.part3;

/**
 * @program: AlgorithmBook
 * @author: hhmy27
 * @created: 2020/06/14 16:44
 * @description: ex 1.3.49
 * API: enqueue,dequeue,isEmpty,size,front
 */
public class StackImplementQueue<Item> {
    private Stack<Item> A;
    private Stack<Item> B;

    public StackImplementQueue() {
        A = new Stack<>();
        B = new Stack<>();
    }

    public void enqueue(Item item) {
        A.push(item);
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new Error("队列为空");
        }
        // 栈不为空，则一定有一个元素会弹出
        // B不为空，弹出B的栈顶
        if (!B.isEmpty())
            return B.pop();
        // B为空，判断A
        while (A.size() != 1) {
            B.push(A.pop());
        }
        return A.pop();
    }

    public int size() {
        return A.size() + B.size();
    }

    public boolean isEmpty() {
        return A.isEmpty() && B.isEmpty();
    }

    public Item front() {
        if (A.size() == 1)
            return A.peek();
        else
            return B.peek();
    }

    public static void main(String[] args) {
        StackImplementQueue<Integer> queue = new StackImplementQueue<>();
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i + 1);
        }
        System.out.println(queue.dequeue());
        queue.enqueue(6);
        queue.enqueue(7);
        System.out.println(queue.size());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.enqueue(1);
        queue.enqueue(2);
//        System.out.println(queue.size());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }
}

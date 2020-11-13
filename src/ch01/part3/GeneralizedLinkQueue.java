package ch01.part3;

/**
 * @program: AlgorithmBook
 * @description: ex 1.3.38 linklist implement
 * @author: HMY777
 * @created: 2020/05/14 08:24
 */
public class GeneralizedLinkQueue<Item> {
    private Node front;
    private Node last;
    private int size;

    private class Node {
        private Item item;
        private Node next;

        Node(Item item) {
            this.item = item;
            next = null;
        }

    }

    GeneralizedLinkQueue() {
        front = last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void insertFirst(Item item) {
        Node t = new Node(item);
        front = last = t;
        size = 1;
    }

    private Item deleteLast() {
        Item item = front.item;
        front = last = null;
        size = 0;
        return item;
    }

    public void insert(Item x) {
        if (size == 0)
            insertFirst(x);
        else {
            Node t = new Node(x);
            last.next = t;
            last = last.next;
            size += 1;
        }
    }

    public Item delete(int k) {
        if (k <= 0 || k > size)
            throw new Error("k error");
        if (size == 1)
            return deleteLast();
        else if(k==1){
            Item item=front.item;
            front=front.next;
            size-=1;
            return item;
        }else{
            int t = 1;
            Node prev = front;
            while (t < k-1) {
                prev = prev.next;
                t += 1;
            }
            Node n=prev.next;
            Item item=n.item;
            prev.next=n.next;
            n.next=null;
            size-=1;
            return item;
        }
    }

    public static void main(String[] args) {
        GeneralizedLinkQueue<Integer> queue=new GeneralizedLinkQueue();
        for (int i = 0; i < 5; i++) {
            queue.insert(i);
        }
        System.out.println(queue.delete(3));
    }
}

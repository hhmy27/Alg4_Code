package ch01;

import java.util.Iterator;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/5/1
 *  \* Time: 8:39
 *  \* To change this template use File | Settings | File Templates.
 *  \* Description: A linklist which has front and last pointer, and hasn't head node 
 * LinkList contains answers follwing:
 * 1.3.19
 * 1.3.20
 * 1.3.21
 * 1.3.24
 * 1.3.25
 * 1.3.26
 * 1.3.27
 * 1.3.28
 * 1.3.30
 *  \
 */
public class LinkList<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    // N is list node number
    private int N;

    public void visitList() {
        Node t = first;
        if (t == null) return;
        while (t.next != null) {
            System.out.print(t.item + " => ");
            t = t.next;
        }
        System.out.print(t.item);
        System.out.println();
    }

    private void reduceN() {
        N--;
        if (N == 1) {
            last = first;
        } else if (N == 0) {
            first = last = null;
        }
    }

    private void reduceN(int i) {
        N -= i;
        if (N == 1) {
            last = first;
        } else if (N == 0) {
            first = last = null;
        }
    }

    private void addN() {
        N++;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkIterator();
    }

    private class LinkIterator implements Iterator<Item> {
        private Node t = getFirst();

        @Override
        public boolean hasNext() {
            return t != null;
        }

        @Override
        public Item next() {
            Item item = t.item;
            t = t.next;
            return item;
        }

        @Override
        public void remove() {
            throw new Error("未定义的remove");
        }
    }

    private class Node {
        private Item item;
        private Node next;

        Node(Item item) {
            this.item = item;
            next = null;
        }
    }

    LinkList() {
        first = null;
        last = null;
    }

    LinkList(Item[] items) {
        first = new Node(items[0]);
        Node t = first;
        for (int i = 1; i < items.length; i++) {
            Node n = new Node(items[i]);
            t.next = n;
            t = n;
        }
        last = t;
        N = items.length;
    }

    public Node getFirst() {
        return first;
    }

    public boolean isEmpty() {
        return first == null;
    }

    // add new node in last of linklist
    public void append(Item item) {

        Node t = new Node(item);
        // if linklist is null list
        if (first == null) {
            first = last = t;
        } else {
            last.next = t;
            last = t;
        }

        addN();
    }

    // add new node before front of linklist
    public void preAppend(Item item) {
        Node t = new Node(item);
        t.next = first;
        first = t;
        addN();
    }

    // answer for ex1.3.19
    // remove last node
    public void removeLast() {
        Node t = getFirst();
        while (t.next.next != null)
            t = t.next;
        t.next = null;
        last = t;
        reduceN();
    }

    // remove fist node
    public void removeFirst() {
        if (isEmpty())
            throw new Error("链表为空");
        first = first.next;
        reduceN();
    }

    // answer for ex1.3.20
    // delete k-th node in linklist
    public Item delete(int k) {
        if (k <= 0) {
            throw new Error("k must bigger than 0");
        }
        // remove head node
        if (k == 1) {
            Item item = first.item;
            removeFirst();
            return item;
        }
        // find k-1 th node
        k -= 1;
        Node tf = first;
        // tf is k-1 th node
        while (tf != null && k > 1) {
            k--;
            tf = tf.next;
        }

        if (tf == null) {
            throw new Error("k bigger that the length of linklist");
        }
        Item item = tf.next.item;
        tf.next = tf.next.next;
        reduceN();
        return item;
    }

    // 在第k个结点前插入
    public void insert(Item item, int k) {
        if (k <= 0) {
            throw new Error("k必须大于0");
        }
        if (k == 1) {
            preAppend(item);
        } else {
            k -= 1;   // 删除第k个结点，需要找到第k-1个结点
            Node tf = first;
            // 由于没有空的头结点，所以终止条件是k大于1，例如传入k=2,那么while时k=1，则first就是要插入结点的前一个结点
            while (tf != null && k > 1) {
                k--;
                tf = tf.next;
            }
            if (tf == null) {
                throw new Error("超过链表长度");
            }
            Node t = new Node(item);
            t.next = tf.next;
            tf.next = t;
            addN();
        }
    }

    // answer for ex1.3.21
    public boolean find(Item key) {
        for (Item item : this)
            if (item == key)
                return true;
        return false;
    }

    // answer for ex1.3.24
    public void removeAfter(Item item) {
        Node t = first;
        while (t != null && t.item != item)
            t = t.next;
        // 如果找到了这样的结点，并且t的后续节点不为空，则删除
        if (t != null && t.next != null) {
            t.next = t.next.next;
            reduceN();
        }

    }

    // answer for ex1.3.25
    public void insertAfter(Item a, Item b) {
        if (a == null && b == null)
            return;
        Node t = first;
        while (t != null && !t.item.equals(a))
            t = t.next;
        if (t == null)
            return;
        Node p = new Node(b);
        p.next = t.next;
        t.next = p;
        addN();
    }

    // answer for ex1.3.26
    // delete all node which item == key
    public void remove(Item key) {
        // 接上一个空的结点，从H开始遍历
        // join a head node, iterate linklist from h
        Node H = new Node(null);
        H.next = first;
        Node t = H;
        int num = 0;
        while (t != null) {
            if (t.next != null && t.next.item.equals(key)) {
                t.next = t.next.next;
                num += 1;
            } else
                t = t.next;
        }
        //删除完之后，更新first和last两个指针
        // update front and last pointer after iterate linklist
        first = H.next;
        last = first;
        while (last != null && last.next != null)
            last = last.next;
        reduceN(num);
    }

    // answer for ex1.3.27
    public int max_iterate() {
        Node t = first;
        int max = (int) t.item;
        while (t != null && t != null) {
            if ((int) t.item > max)
                max = (int) t.item;
            t = t.next;
        }
        return max;
    }

    // answer for ex1.3.28
    public Item max() {
        Node t = first;
        return max(t, first.item);
    }

    // answer for ex1.3.28
    private Item max(Node t, Item max_num) {
        if (t == null)
            return max_num;
        else {
            max_num = (Item) (Integer) Math.max((Integer) max_num, (Integer) t.item);
            return max(t.next, max_num);
        }
    }

    // iterate way to reverse linklist
    public void reverse_iteration() {
        Node H = new Node(null);
        Node t = first;
        while (t != null) {
            Node n = t.next;
            t.next = H.next;
            H.next = t;
            t = n;
        }
        // 逆置后，last变为原先的first,first变为H.next
        // transform last and first pointer
        last = first;
        first = H.next;
        H.next = null;
        H = null;
    }

    // answer for ex1.3.28
    public Node reverse_recursion(Node node) {
        // 递归终止条件,如果当前结点为空，或者当前结点是尾结点
        if (node == null || node.next == null)
            return node;
        // 获得当前节点的next结点逆置的结果
        Node first = reverse_recursion(node.next);
        node.next.next = node;
        node.next = null;
        return first;
    }

    public int size() {
        return N;
    }

    LinkList(Node node) {
        int num = 0;
        first = node;
        Node t = node;
        while (t.next != null) {
            t = t.next;
            num += 1;
        }
        last = node;
        N = num;
    }

    public static void main(String[] args) {
        Integer[] list = {1, 2, 3, 4};
        LinkList<Integer> L = new LinkList<>(list);
        System.out.println(L.max_iterate());
        System.out.println(L.max());
//        LinkList<Integer> tL = new LinkList<>(L.reverse_recursion(L.first));
//        tL.visitList();
//        L.delete(4);
        L.visitList();
        L.reverse_iteration();
        L.visitList();
        L.first = L.reverse_recursion(L.first);
        L.visitList();
    }
}

package ch01;

import java.util.Iterator;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/5/1
 *  \* Time: 8:39
 *  \* To change this template use File | Settings | File Templates.
 *  \* Description: 带有首尾指针的单向链表，其中N是链表的长度，并且链表没有额外的头结点 
 *  \
 */
public class LinkList<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
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
        // 构造头结点
        first = new Node(items[0]);
        // 用于构造链表
        Node t = first;
        for (int i = 1; i < items.length; i++) {
            // 新建结点
            Node n = new Node(items[i]);
            // 建立链接并更新
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

    public void append(Item item) {
        //在末尾更新结点
        Node t = new Node(item);
        //如果链表一开始是空的,此时last也是空的
        if (first == null) {
            first = last = t;
        } else {
            last.next = t;
            last = t;
        }

        addN();
    }

    public void preAppend(Item item) {
        // 在首部插入结点
        Node t = new Node(item);
        t.next = first;
        first = t;
        addN();
    }

    public void removeLast() {
        // 移除尾结点
        Node t = getFirst();
        while (t.next.next != null)
            t = t.next;
        t.next = null;
        last = t;
        reduceN();
    }

    public void removeFirst() {
        // 移除首结点
        if (isEmpty())
            throw new Error("链表为空");
        first = first.next;
        reduceN();
    }

    public Item delete(int k) {
        // 删除第k个结点
        if (k <= 0) {
            throw new Error("k必须大于0");
        }
        if (k == 1) {
            Item item = first.item;
            // 删除头结点，特殊操作
            removeFirst();
            return item;
        }
        k -= 1;   // 删除第k个结点，需要找到第k-1个结点
        Node tf = first;
        // 由于没有空的头结点，所以终止条件是k大于1，例如传入k=2,那么while时k=1，则first就是要删除结点的前一个结点
        while (tf != null && k > 1) {
            k--;
            tf = tf.next;
        }
        if (tf == null) {
            throw new Error("超过链表长度");
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

    public boolean find(Item key) {
        for (Item item : this)
            if (item == key)
                return true;
        return false;
    }

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

    public void remove(Item key) {
        // 删除所有item==key的node
        // 接上一个空的结点，从H开始遍历
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
        first = H.next;
        last = first;
        while (last != null && last.next != null)
            last = last.next;
        reduceN(num);
    }

    public Item max() {
        Node t = first;
        return max(t, (Item) new Integer(0));
    }

    private Item max(Node t, Item max_num) {
        if (t == null)
            return max_num;
        else {
            max_num = (Item) (Integer) Math.max((Integer) max_num, (Integer) t.item);
            return max(t.next, max_num);
        }
    }

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
        last = first;
        first = H.next;
    }

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
        LinkList<Integer> tL = new LinkList<>(L.reverse_recursion(L.first));
        tL.visitList();
    }
}

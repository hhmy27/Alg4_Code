package ch02.part2;


import java.util.*;

/**
 * @program: AlgorithmBook
 * @author: hhmy27
 * @created: 2021/02/09 11:47
 * @description:
 */
public class ex_2_2_17<Item extends Comparable> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;

        Node() {
            next = null;
        }

        Node(Item item) {
            this.item = item;
            next = null;
        }
    }

    public Node first;
    public Node last;
    public int size;

    public ex_2_2_17() {
        first = last = null;
        size = 0;
    }

    public void add(Item item) {
        Node t = new Node(item);
        if (first == null) {
            first = t;
            last = t;
        } else {
            last.next = t;
            last = last.next;
        }
        size += 1;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        Node first = this.first;

        @Override
        public boolean hasNext() {
            return first != null;
        }

        @Override
        public Item next() {
            Item item = first.item;
            first = first.next;
            return item;
        }
    }

    public void LinkedListNaturalMergeSort() {
        Node nfirst = mergeSort();
        first = nfirst;
        last = first;
        while (last.next != null) {
            last = last.next;
        }
    }

    public Node mergeSort() {
        Node left = first;
        Node middle;
        Node right;

        while (true) {
            middle = findSortedSubListNode(left);

            // already sorted linked list
            // error! first node will change!
            if (middle == last && left == first) {
                break;
            }
            right = findSortedSubListNode(middle.next);
            left = merge(left, middle, right);
            first = left;
        }
        return left;
    }

    private Node findSortedSubListNode(Node left) {
        while (left != null && left.next != null) {
            if (left.item.compareTo(left.next.item) > 0)
                return left;
            left = left.next;
        }
        return last;
    }

    // merge two sub list
    private Node merge(Node low, Node middle, Node right) {
        Node head;
        Node l;
        Node i = low;
        Node j = middle.next;
        if (i.item.compareTo(j.item) <= 0) {
            l = i;
            head = i;
            i = i.next;
        } else {
            l = j;
            head = j;
            j = j.next;
        }
        Node right_next = right.next;

        while (i != middle.next && j != right.next) {
            if (i.item.compareTo(j.item) <= 0) {
                l.next = i;
                i = i.next;
            } else {
                l.next = j;
                j = j.next;
            }
            l = l.next;
        }

        if (i == middle.next) {
            l.next = j;
        }
        if (j == right.next) {
            l.next = i;
            middle.next = right_next;
        }
        return head;
    }

    public static void main(String[] args) {

        ex_2_2_17<Integer> list = new ex_2_2_17<>();

        list.add(3);
        list.add(5);
        list.add(2);
        list.add(7);
        list.add(1);
        list.add(0);

        ex_2_2_17.Node first = list.first;
        while (first != null) {
            System.out.printf("%d ", first.item);
            first = first.next;
        }
        System.out.println();

        list.LinkedListNaturalMergeSort();


        first = list.first;
        while (first != null) {
            System.out.printf("%d ", first.item);
            first = first.next;
        }


    }

}


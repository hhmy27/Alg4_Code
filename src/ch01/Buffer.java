package ch01;

/**
 * @program: AlgorithmBook
 * @author: hhmy27
 * @created: 2020/06/13 17:54
 * @description: ex 1.3.13 answer
 * insert
 * delete
 * left
 * right
 * size
 * 思路：两个栈A,B，当左移的时候，A往B入栈，当右移的时候，B往A入栈
 * 同时记录当前总共的字符数和当前光标指向的字符位置
 * 约定：
 * delete是向左删除，当删除完最后一个字符的时候再删除就出错
 * insert是在光标指向的字符之后插入
 * 当尝试左右移动的时候，如果越界则移动到第一个字符或最后一个字符
 */
public class Buffer {
    // 两个链栈
    private Stack<Character> A;
    private Stack<Character> B;
    private int ind;
    private int size;

    public Buffer() {
        A = new Stack<>();
        B = new Stack<>();
        ind = size = 0;
    }

    public Buffer(String str) {
        A = new Stack<>();
        B = new Stack<>();
        ind = size = str.length();
        for (int i = 0; i < str.length(); i++) {
            A.push(str.charAt(i));
        }
    }

    public void insert(char c) {
        A.push(c);
        addInd(1);
        addSize(1);
    }

    public char delete() {
        char ch = A.pop();
        reduceInd(1);
        reduceSize(1);
        return ch;
    }

    private void addInd(int i) {
        ind += i;
    }

    private void reduceInd(int i) {
        if (ind - i < 0) {
            throw new Error("ind不能小于0");
        }
        ind -= i;
    }

    private void addSize(int i) {
        size += i;
    }

    private void reduceSize(int i) {
        if (size - i < 0) {
            throw new Error("size不能小于0");
        }
        size -= i;

    }

    private boolean judge(int tind) {
        if (tind > size || tind < 1)
            return false;
        return true;
    }

    public void left(int k) {
        if (!judge(ind - k)) {
            // 如果越界了，则移动光标到第一个字符
            int n = A.size() - 1;
            for (int i = 0; i < n; i++) {
                B.push(A.pop());
            }
            reduceInd(ind - 1);
        } else {
            for (int i = 0; i < k; i++) {
                B.push(A.pop());
            }
            reduceInd(k);
        }

    }

    public void right(int k) {
        if (!judge(ind + k)) {
            int n = B.size() - 1;
            for (int i = 0; i < n; i++) {
                A.push(B.pop());
            }
            reduceInd(ind - 1);
        } else {
            for (int i = 0; i < k; i++) {
                A.push(B.pop());
            }
            addInd(k);
        }

    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        Buffer buf = new Buffer("asdfgh");
        System.out.println(buf.delete());
        System.out.println(buf.delete());
        buf.left(3);
        System.out.println(buf.delete());
        buf.right(2);
        System.out.println(buf.delete());

    }
}

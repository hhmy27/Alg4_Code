package ch01.part3;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/4/27
 *  \* Time: 16:51
 *  \* Description: ex1.3.1 add isFull()
 *  \
 */
public class FixedCapacityStackOfStrings {
    private String[] stack;
    private int index;

    FixedCapacityStackOfStrings(int cap) {
        stack = (String[]) new Object[cap];
        index = 0;
    }

    public void push(String item) {
        if (index == stack.length)
            resize(2 * stack.length);
        stack[index++] = item;
    }

    public String pop() {
        if (index == 0)
            throw new Error("empty stack can not pop");
        String item = stack[--index];
        stack[index] = null;  // manual set null
        if (index > 0 && index == stack.length / 2) resize(stack.length / 2);
        return item;
    }

    public boolean isEmpty() {
        return index == 0;
    }

    public int size() {
        return index;
    }

    public void resize(int max) {
        String[] temp = (String[]) new Object[max];
        for (int i = 0; i < index; i++) {
            temp[i] = stack[i];
        }
        stack = temp;
    }

    // ex1.3.1
    public boolean isFull() {
        return index == stack.length;
    }

}

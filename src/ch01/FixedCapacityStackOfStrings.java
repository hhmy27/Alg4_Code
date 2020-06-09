package ch01;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/4/27
 *  \* Time: 16:51
 *  \* Description: 
 *  \
 */
public class FixedCapacityStackOfStrings<Item> {
    private Item[] stack;
    private int index;

    FixedCapacityStackOfStrings(int cap) {
        stack = (Item[]) new Object[cap];
        index = 0;
    }

    public void push(Item item) {
        if (index == stack.length)
            resize(2 * stack.length);
        stack[index++] = item;
    }

    public Item pop() {
        if (index == 0)
            throw new Error("栈空");
        Item item=stack[--index];
        stack[index] = null;  //避免对象游离
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
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < index; i++) {
            temp[i] = stack[i];
        }
        stack = temp;
    }

    public boolean isFull(){
        return index==stack.length;
    }

}

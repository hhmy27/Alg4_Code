package ch01;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/5/1
 *  \* Time: 8:59
 *  \* To change this template use File | Settings | File Templates.
 *  \* Description: 
 *  \
 */
public class ex_1_3_19 {
    public static void main(String[] args) {
        Integer[] nums={1,2,3,4,5};
        Linklist<Integer> L=new Linklist<>(nums);
        L.reverse_iteration();
        L.visitList();
        int size=L.size();
        for (int i = 0; i < size; i++) {
            L.removeFirst();
        }
        L.visitList();
        L.append(2);
        L.append(3);
        L.visitList();
        L.reverse_iteration();
        L.visitList();
    }
}

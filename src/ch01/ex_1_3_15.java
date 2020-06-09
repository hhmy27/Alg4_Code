package ch01;

/**
 *  \* Created with IntelliJ IDEA.
 *  \* User: hmy
 *  \* Date: 2020/5/1
 *  \* Time: 8:29
 *  \* To change this template use File | Settings | File Templates.
 *  \* Description: 
 *  \
 */
public class ex_1_3_15 {
    public static void main(String[] args) {
        String[] slist={"are","You","Thank","youe?","Ha?"};
        int k=2;
        Queue<String> q=new Queue<>();
        for(String s:slist)
            q.enqueue(s);
        int num=q.size()-k;
        while(num!=0)
        {
            q.dequeue();
            num--;
        }
        System.out.println(q.dequeue());
    }
}

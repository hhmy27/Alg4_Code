package ch01;

/**
 * @program: AlgorithmBook
 * @description: ex 1.3.37
 * @author: HMY777
 * @created: 2020/05/13 10:05
 */
public class Josephus {
    private Queue queue;
    private int M;
    Josephus(int N,int M){
        queue=new Queue();
        this.M=M;
        for (int i = 0; i < N; i++) {
           queue.enqueue(i);
        }
    }
    public void solution(){
       while(queue.size()!=1){
           for (int i = 0; i < M-1; i++) {
                queue.enqueue(queue.dequeue());
           }
           System.out.print(queue.dequeue()+" ");
       }
        System.out.print("winner is :"+ queue.dequeue());
    }
    public static void main(String[] args) {
        int N=7;
        int M=2;
        Josephus J=new Josephus(N,M);
        J.solution();
    }
}

package ch01.part3;

/**
 * @program: AlgorithmBook
 * @author: HMY777
 * @created: 2020/05/14 09:34
 * @description: ex 1.3.39 的代码，使用了线程表示消费者和生产者
 * 1 2 3 4 5
 * L     R
 */
public class RingBuffer<Item> {
    private Object[] buffer;
    private int N;
    private int L;
    private int R;

    RingBuffer(int N) {
        buffer = new Object[N];
        N = 0;
        L = R = 0;
    }

    private void addN(int i) {
        N += i;
    }

    private void reduceN(int i) {
        N -= i;
    }

    public synchronized void write(Item item) {
        while (N >= buffer.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 写入缓冲区
        buffer[R++] = item;
        R = R % buffer.length;
        addN(1);
        notify();
    }

    public synchronized Item read() {
        while (N <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 读取缓冲区
        Item item = (Item) buffer[L++];
        L = L % buffer.length;
        reduceN(1);
        notify();
        return item;
    }

    public static void main(String[] args) {
        RingBuffer<Integer> rb = new RingBuffer<>(10);
        Consumer con = new Consumer(rb);
        Producter pro = new Producter(rb);
        con.start();
        pro.start();
    }
}

// 简单起见，Consumer和Productor都是用Integer
class Consumer extends Thread {
    private final RingBuffer<Integer> rb;

    Consumer(RingBuffer<Integer> rb) {
        this.rb = rb;
    }

    @Override
    public void run() {
        while (true) {
            rb.write((int) (Math.random() * (101)));
        }
    }
}

class Producter extends Thread {
    private final RingBuffer<Integer> rb;

    Producter(RingBuffer<Integer> rb) {
        this.rb = rb;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(rb.read());
        }
    }
}


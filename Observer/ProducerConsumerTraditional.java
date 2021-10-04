package aPatternsCode;
import java.util.concurrent.ThreadLocalRandom;

class Common {
    public static int COUNT = 100;
    private volatile int[] buffer = new int[COUNT];
    volatile int readP, writeP, size;
    public synchronized int get() {
        while (size == 0) {
            try {
                wait();
            } catch (InterruptedException ignoreInterruption) {
            }
        }
        int r = buffer[readP];
        readP = (readP + 1) % COUNT;
        size--;
        notify();
        return r;
    }
    public synchronized void put(int n) {
        while (size == COUNT) {
            try {
                wait();
            } catch (InterruptedException ignoreInterruption) {
            }
        }
        buffer[writeP] = n;
        writeP = (writeP + 1) % COUNT;
        size++;
        notify();
    }
}

public class ProducerConsumerTraditional {
    static final int NUMBERS = 100000;
    private static void producer(Common c) {
        for (int i = 0; i < NUMBERS; i++)
            c.put(ThreadLocalRandom.current().nextInt());
    }
    private static void consumer(Common c) {
        int odds = 0;
        int evens = 0;
        for (int i = 0; i < NUMBERS; i++)
            if ((c.get() % 2) == 0)
                evens++;
            else
                odds++;
        System.out.printf("Evens=%d Odds=%d%n", evens, odds);
    }
    public static void main(String[] args) {
        final Common c = new Common();
        new Thread(new Runnable() {
            @Override
            public void run() {
                producer(c);
            }
        }).start();
        consumer(c);
    }
}

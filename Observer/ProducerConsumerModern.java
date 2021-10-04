package aPatternsCode;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class ProducerConsumerModern {
    static final int NUMBERS = 100000;
    private static void producer(ArrayBlockingQueue<Integer> buffer)
            throws InterruptedException {
        for (int i = 0; i < NUMBERS; i++)
            buffer.put(ThreadLocalRandom.current().nextInt());
    }
    private static void consumer(ArrayBlockingQueue<Integer> buffer)
            throws InterruptedException {
        int odds = 0;
        int evens = 0;
        for (int i = 0; i < NUMBERS; i++)
            if ((buffer.take() % 2) == 0)
                evens++;
            else
                odds++;
        System.out.printf("Evens=%d Odds=%d%n", evens, odds);
    }
    public static void main(String[] args) {
        int CAPACITY = 100;
        final ArrayBlockingQueue<Integer> buffer = new ArrayBlockingQueue<Integer>(
                CAPACITY);
        ExecutorService p = Executors.newSingleThreadExecutor();
        p.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    producer(buffer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            consumer(buffer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        p.shutdown();
    }
}

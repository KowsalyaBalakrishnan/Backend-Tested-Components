package exceptions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ExceptionMessagesHandling {

    public static void main(String[] args) throws InterruptedException {

        AtomicInteger count = new AtomicInteger(5);
        ExceptionMessagesHandling obj = new ExceptionMessagesHandling();

        /*ExecutorService service = Executors.newFixedThreadPool(2);
        //for (int i = 0; i < 2; i++) {
            service.submit(() -> obj.increment(count));
        //}*/

        Thread t1 = new Thread(() -> {
            obj.increment(count);
        });
        Thread t2 = new Thread(() -> {
            obj.increment(count);
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(count);


    }

    private void increment(AtomicInteger count) {
        count.getAndAdd(5);
    }
}

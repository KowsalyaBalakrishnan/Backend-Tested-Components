package Concurrency;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CallableFutureImpl {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);

        Callable<String> callableTask1 = () -> {
            Thread.sleep(5000);
            System.out.println("G - " + Thread.currentThread().getName());
            return "Gomathi";
        };
        Callable<String> callableTask2 = () -> {
            Thread.sleep(6000);
            System.out.println("K - " + Thread.currentThread().getName());
            return "Kowsalya";
        };
        Callable<String> callableTask3 = () -> {
            Thread.sleep(12000);
            System.out.println("B - " + Thread.currentThread().getName());
            return "Balakrishnan";
        };
        Callable<String> callableTask4 = () -> {
            Thread.sleep(20000);
            System.out.println("D - " + Thread.currentThread().getName());
            return "Dhandapani";
        };
        Callable<String> callableTask5 = () -> {
            Thread.sleep(4000);
            System.out.println("C - " + Thread.currentThread().getName());
            return "Chandru";
        };


        try {
            List<Future<String>> submit = service.invokeAll(Arrays.asList(callableTask1, callableTask3, callableTask4, callableTask5, callableTask2));
            StringBuilder family = new StringBuilder();
            for (Future<String> stringFuture : submit) {
                String s = stringFuture.get(5, TimeUnit.SECONDS);
                family.append(s).append(" ");
            }
            System.out.println("Family --> " + family);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Main Thread");

        service.shutdown();
    }

}

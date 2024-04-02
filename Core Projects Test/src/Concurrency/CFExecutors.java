package Concurrency;

import java.util.concurrent.*;

public class CFExecutors {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(3);

        // runAsync
        CompletableFuture<Void> runAsync1 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Thread Name:: Run -> " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, service);

        CompletableFuture<Void> runAsync2 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Thread Name:: Run -> " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, service);


        System.out.println("-------------" + Thread.currentThread().getName() + "-------------------");

        // Supply Async
        CompletableFuture<String> supplyAsync1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Thread Name:: Supply -> " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello " + Thread.currentThread().getName();
        }, service);

        CompletableFuture<String> supplyAsync2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(8);
                System.out.println("Thread Name:: Supply -> " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello " + Thread.currentThread().getName();
        }, service);


        System.out.println("Some Other Task");
        System.out.println("Supply Async 1 :: " + supplyAsync1.get() + "|| Current statement executed by :: " + Thread.currentThread().getName());
        System.out.println("Supply Async 2 :: " + supplyAsync2.get() + "|| Current statement executed by :: " + Thread.currentThread().getName());

        System.out.println("Runtime Processors: " + Runtime.getRuntime().availableProcessors());

        if (!service.isShutdown()) {
            service.shutdown();
        }

    }
}

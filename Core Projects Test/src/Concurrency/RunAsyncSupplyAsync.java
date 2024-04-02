package Concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RunAsyncSupplyAsync {
    public static void main(String[] args) {

        // No return value - Similar to runnable - runAsync
        CompletableFuture<Void> nothingToReturn = CompletableFuture.runAsync(() -> {
            System.out.println("RunAsync - " + Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        try {
            nothingToReturn.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println("===Blocked===");


        // Get return value - Similar to callable - supplyAsync
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println("SupplyAsync - " + Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Supply Async Result";
        });
        //boolean hello = supplyAsync.complete("Hello");
        try {
            System.out.println(supplyAsync.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}

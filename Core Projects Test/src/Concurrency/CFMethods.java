package Concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CFMethods {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //thenApply() -> take a function and return a value
        System.out.println("===thenApply()===");

        // Example 1: Full Name
        CompletableFuture<String> firstName = CompletableFuture.supplyAsync(() -> "Kowsalya");
        CompletableFuture<String> lastName = firstName.thenApply((input) -> input + " Balakrishnan");
        System.out.println("Full Name:: " + lastName.get());

        // Example 2: Factorial
        CompletableFuture<Integer> fourFactorial = CompletableFuture.supplyAsync(() -> 1)
                .thenApply(input -> input * 2)
                .thenApply(input -> input * 3)
                .thenApply(input -> input * 4);
        System.out.println("fourFactorial:: " + fourFactorial.get());

        /*
        thenAccept() -> take a consumer -> takes input but doesn't return anything -> Probably the last callback function in the chain
        thenRun() -> takes a runnable -> it doesn't get hold of the previous results from chain, however there is a runnable where we might delegate the request
         */
        System.out.println("===thenAccept()===");

        // Four Factorial
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> 1)
                .thenApply(input -> input * 2 * 3 * 4)
                .thenAccept(System.out::println);
        voidCompletableFuture.get();

        CompletableFuture.supplyAsync(() -> 1)
                .thenApply(data -> data * 2 * 3 * 4)
                .thenRun(() -> System.out.println("Result set isn't caputured"));
    }
}

package thread.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureEx2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> greet = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> time = CompletableFuture.supplyAsync(() -> "World!");
        CompletableFuture<String> finalResult = greet.thenCombine(time, (greetData, timeData) -> greetData + " " + timeData);
        System.out.println(finalResult.get());

    }
}

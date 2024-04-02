package thread.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureEx3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> result =
                CompletableFuture
                        .supplyAsync(() -> 10 / 2)
                        .exceptionally(ex -> 0);
        System.out.println(result.get());
    }
}

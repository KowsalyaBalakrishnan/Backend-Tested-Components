package Concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CFCombinedFuturesImpl {
    public static void main(String[] args) throws Exception{
        CompletableFuture<String> amma = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Gomathi";
        });
        CompletableFuture<String> appa = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Balakrishnan";
        });
        CompletableFuture<String> mama = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Dhandapani";
        });
        CompletableFuture<String> bf = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(9);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Chandru";
        });
        CompletableFuture<String> myself = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(12);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Kowsalya";
        });


        CompletableFuture<Void> futures = CompletableFuture.allOf(mama, bf, amma, appa, myself);
        futures.get();
        // How to get the combined results? there is an option!

    }
}

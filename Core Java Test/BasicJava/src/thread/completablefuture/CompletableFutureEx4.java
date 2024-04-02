package thread.completablefuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureEx4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Current Thread hello => " + Thread.currentThread().getName());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello";
        });

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Current Thread world => " + Thread.currentThread().getName());
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "World";
        });

        CompletableFuture<Void> combinedFutures = CompletableFuture.allOf(hello, world);

        System.out.println("IsHelloDone => " + hello.isDone());
        System.out.println("IsWorldDone => " + world.isDone());

        combinedFutures.get();

        if (hello.isDone() && world.isDone()) {
            List<String> strings = Stream.of(hello, world)
                    .map(CompletableFuture::join)
                    .collect(Collectors.toList());
            String joinedResult = String.join("**", strings);
            System.out.println(joinedResult);
        }

        System.out.println("Main Thread!");
    }
}

package thread.completablefuture;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureEx5 {

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        CompletableFuture<Map<String, Integer>> future1 = CompletableFuture.supplyAsync(() -> {
            Map<String, Integer> result = new HashMap<>();
            for (int i = 1; i <= 30000; i++) {
                result.put("Line => " + i, i);
            }
            try {
                System.out.println("Current Thread 1 => " + Thread.currentThread().getName());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return result;
        }, executorService);

        CompletableFuture<Map<String, Integer>> future2 = CompletableFuture.supplyAsync(() -> {
            Map<String, Integer> result = new HashMap<>();
            for (int i = 30001; i <= 50000; i++) {
                result.put("Line => " + i, i);
            }
            try {
                System.out.println("Current Thread 2 => " + Thread.currentThread().getName());
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return result;
        }, executorService);


        CompletableFuture<Void> finalCombinedResult = CompletableFuture.allOf(future1, future2);
        finalCombinedResult.get();

        if (future1.isDone() && future2.isDone()) {
            Map<String, Integer> result = Stream.of(future1, future2)
                    .map(CompletableFuture::join)
                    .flatMap(map -> map.entrySet().parallelStream())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            System.out.println("Count -> " + result.size());
        }

        System.out.println("Main Thread!");
        String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss"));
        System.out.println(formattedDateTime);
        executorService.shutdown();
    }
}

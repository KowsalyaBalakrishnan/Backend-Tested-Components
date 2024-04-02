package thread;

import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureExample {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        List<String> result = new ArrayList<>();
        ExecutorService customExecutor = Executors.newFixedThreadPool(10);

        List<String> linesToProcess = generateLinesToProcess();
        System.out.println("Total Lines Generated => " + linesToProcess.size());

        for (String line : linesToProcess) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> processLine(line, result), customExecutor)
                    .exceptionally(throwable -> {
                        // Handle exceptions if needed
                        throwable.printStackTrace();
                        return null;
                    });
            futures.add(future);
        }

        // Wait for all CompletableFuture to complete
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        try {
            allOf.get(); // This blocks until all tasks are completed
            System.out.println("All lines processed successfully!");
            System.out.println("Number of List : " + result.size());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }

        customExecutor.shutdown();

        long end = System.currentTimeMillis();
        System.out.println("Total Time => " + ProcessTimeUtil.getTimeConsumed(end - start));
    }

    private static void processLine(String line, List<String> result) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        result.add(line);
        System.out.println(Thread.currentThread().getName() + " is executing => " + line);
    }

    private static List<String> generateLinesToProcess() {
        List<String> lines = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            lines.add("Line " + i);
        }
        return lines;
    }
}

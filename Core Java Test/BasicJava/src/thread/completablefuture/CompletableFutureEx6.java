package thread.completablefuture;

import org.apache.commons.collections4.ListUtils;
import thread.ProcessTimeUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class CompletableFutureEx6 {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        List<String> lines = generateLines();

        Map<String, String> result = validateTheLines(lines);

        System.out.println("Validated Counts => " + result.size());

        System.out.println("Main Thread Continues!");

        long end = System.currentTimeMillis();
        System.out.println("Total Time => " + ProcessTimeUtil.getTimeConsumed(end - start));

    }

    private static Map<String, String> validateTheLines(List<String> lines) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<CompletableFuture<Map<String, String>>> futureObjects = new ArrayList<>();

        List<List<String>> partitionedList = ListUtils.partition(lines, 1000);
        int partitionedListSize = partitionedList.size();

        for (int i = 0; i < partitionedListSize; i++) {
            int finalI = i;
            CompletableFuture<Map<String, String>> futureObject =
                    CompletableFuture.supplyAsync(() -> appendString(partitionedList.get(finalI)), executorService);

            futureObjects.add(futureObject);
        }

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futureObjects.toArray(new CompletableFuture[0]));
        try {
            allOf.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        // Join the future result
        Map<String, String> resultantMap = futureObjects.stream()
                .map(CompletableFuture::join)
                .flatMap(map -> map.entrySet().parallelStream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        executorService.shutdown();
        return resultantMap;
    }

    public static Map<String, String> appendString(List<String> lines) {
        Map<String, String> newResult = new HashMap<>();
        System.out.println("AppendString method executed by => " + Thread.currentThread().getName());
        for (String line : lines) {
            String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss"));
            newResult.put(line, formattedDateTime);

            // Simulate delay
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Inside => " + line);
        }
        return newResult;
    }

    private static List<String> generateLines() {
        List<String> generatedLines = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            generatedLines.add("Line => " + i);
        }
        return generatedLines;
    }
}

package thread;

import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class WaitExecutorService {

    private static final int NUM_THREADS = 4;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Set<Integer> finalList = new HashSet<>();
        // callMeFuture();
        callMeAwait(finalList);

        System.out.println("Main thread continues...");
        System.out.println("FinalSize Size => " + finalList.size());
        //finalList.forEach(System.out::println);
    }

    private static void callMeAwait(Set<Integer> finalList) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            integers.add(i);
        }

        List<List<Integer>> partitionedList = ListUtils.partition(integers, 1000);

        for (List<Integer> integerList : partitionedList) {
            executorService.submit(new ProcessClass(integerList, finalList));
        }

        /*for (int i = 0; i < 10000; i++) {
            executorService.submit(new ProcessClass(i));
        }*/

        executorService.shutdown();

        try {
            boolean b = executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            System.out.println("Await Termination");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("After Await()");

    }

    private static void callMeFuture() throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            Future<?> submit = executor.submit(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " is executing a task");
                } catch (InterruptedException e) {
                    System.out.println("Interrupted Exception");
                }
            });
            futures.add(submit);
        }

        for (Future obj : futures) {
            obj.get();
        }

        System.out.println("After Future get()");

    }
}

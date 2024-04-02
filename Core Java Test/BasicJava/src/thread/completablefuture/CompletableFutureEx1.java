package thread.completablefuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureEx1 {

    public static void main(String[] args) throws Exception{

        CompletableFuture<List<Integer>> futuredList = CompletableFuture.supplyAsync(() -> {
            List<Integer> numbers = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName());
                numbers.add(i);
            }
            return numbers;
        });

        futuredList.get().forEach(System.out::println);

    }
}

package PracticalUseCase.Performance.CompletableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Client {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        // Sequential Flow
        CustomerTransaction transaction1 = new CustomerTransaction(1, "Trans-1");
        CustomerTransaction transaction2 = new CustomerTransaction(2, "Trans-2");
        CustomerTransaction transaction3 = new CustomerTransaction(3, "Trans-3");
        CustomerTransaction transaction4 = new CustomerTransaction(4, "Trans-1");
        CustomerTransaction transaction5 = new CustomerTransaction(5, "Trans-2");
        CustomerTransaction transaction6 = new CustomerTransaction(6, "Trans-3");
        CustomerTransaction transaction7 = new CustomerTransaction(7, "Trans-1");
        CustomerTransaction transaction8 = new CustomerTransaction(8, "Trans-2");
        CustomerTransaction transaction9 = new CustomerTransaction(9, "Trans-3");
        CustomerTransaction transaction10 = new CustomerTransaction(10, "Trans-1");
        CustomerTransaction transaction11 = new CustomerTransaction(11, "Trans-2");
        CustomerTransaction transaction12 = new CustomerTransaction(12, "Trans-3");
        CustomerTransaction transaction13 = new CustomerTransaction(13, "Trans-1");
        CustomerTransaction transaction14 = new CustomerTransaction(14, "Trans-2");
        CustomerTransaction transaction15 = new CustomerTransaction(15, "Trans-3");
        CustomerTransaction transaction16 = new CustomerTransaction(16, "Trans-1");
        CustomerTransaction transaction17 = new CustomerTransaction(17, "Trans-2");
        CustomerTransaction transaction18 = new CustomerTransaction(18, "Trans-3");
        CustomerTransaction transaction19 = new CustomerTransaction(19, "Trans-3");
        CustomerTransaction transaction20 = new CustomerTransaction(20, "Trans-3");


        // Sequential Stream
        /*List<Category> categories = Stream.of(transaction1, transaction2, transaction3, transaction4, transaction5,
                        transaction6, transaction7, transaction8, transaction9, transaction10,
                        transaction11, transaction12, transaction13, transaction14, transaction15,
                        transaction16, transaction17, transaction18, transaction19, transaction20)
                .map(CategorizationService::getCategory)
                .collect(Collectors.toList());*/

        // Parallel Stream
        /*List<Category> categories = Stream.of(transaction1, transaction2, transaction3, transaction4, transaction5,
                        transaction6, transaction7, transaction8, transaction9, transaction10,
                        transaction11, transaction12, transaction13, transaction14, transaction15,
                        transaction16, transaction17, transaction18, transaction19, transaction20)
                .parallel()
                .map(CategorizationService::getCategory)
                .collect(Collectors.toList());*/

        Executor executor = Executors.newFixedThreadPool(11);
        List<CompletableFuture<Category>> futureCategoriesList = Stream.of(transaction1, transaction2, transaction3, transaction4, transaction5,
                        transaction6, transaction7, transaction8, transaction9, transaction10,
                        transaction11, transaction12, transaction13, transaction14, transaction15,
                        transaction16, transaction17, transaction18, transaction19, transaction20)
                .map(transaction -> CompletableFuture.supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getName() + " processes " + transaction.getTransactionId());
                    return CategorizationService.getCategory(transaction);
                }, executor))
                .collect(Collectors.toList());

        List<Category> categories = futureCategoriesList.stream().map(CompletableFuture::join).collect(Collectors.toList());
        //categories.forEach(System.out::println);

        long end = System.currentTimeMillis();
        System.out.println("Time Taken for Processing:: " + (end - start));

    }
}

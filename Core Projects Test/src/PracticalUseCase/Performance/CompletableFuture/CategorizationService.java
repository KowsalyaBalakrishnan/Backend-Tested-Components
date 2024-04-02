package PracticalUseCase.Performance.CompletableFuture;

import java.util.concurrent.TimeUnit;

public class CategorizationService {

    public static Category getCategory(CustomerTransaction transaction) {
        customDelay();
        Category category = new Category();
        category.setCategoryName("CustomTransaction - " + transaction.getTransactionId());
        return category;
    }

    private static void customDelay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

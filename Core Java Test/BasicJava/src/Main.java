import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Example usage
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 5000; i++) {
            numbers.add(i);
        }

        int partitionSize = 2500;
        List<List<Integer>> result = ListUtils.partition(numbers, partitionSize);

        // Print the partitioned lists
        for (List<Integer> partition : result) {
            System.out.println(partition.size());
        }
    }
}

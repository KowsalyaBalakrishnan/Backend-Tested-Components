import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ELabels {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        List<String> equalList = new ArrayList<>(Arrays.asList("a", "c", "b", "f", "g", "d", "p", "n", "a"));

        equalList.parallelStream().distinct().sorted().collect(Collectors.toList()).forEach(System.out::println);

    }
}

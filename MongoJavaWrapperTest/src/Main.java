import com.apple.internal.App;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Wrapper Class");

        /*String data = "{}";
        String updateCriteria = "{\"address.street\" : \"6th street\"}";
        List<String> results = App.processData(data, "books", "UPDATE", updateCriteria);
        for (String result : results) {
            System.out.println(result);
        }*/

        /*String data = "{\"genres\" : \"fantasy\"}";
        List<String> results = App.processData(data, "books", "FIND", "");
        for (String result : results) {
            System.out.println(result);
        }*/

        /*String deleteCriteria = "{\"rating\" : 7}";
        List<String> results = App.processData("", "books", "REMOVE", deleteCriteria);
        for (String result : results) {
            System.out.println(result);
        }*/

        /*String data = "{\n" +
                "  \"key1\": \"value1\",\n" +
                "  \"key2\": {\n" +
                "    \"nested_key1\": \"nested_value1\",\n" +
                "    \"nested_key2\": [\"array_value1\", \"array_value2\"]\n" +
                "  },\n" +
                "  \"key3\": [\n" +
                "    {\"array_obj_key1\": \"array_obj_value1\"},\n" +
                "    {\"array_obj_key2\": \"array_obj_value2\"}\n" +
                "  ]\n" +
                "}";
        List<String> results = App.processData(data, "books", "INSERT", "", "", "", "");
        for (String result : results) {
            System.out.println(result);
        }*/

        List<String> results = App.processData("", "SRC_ENVELOPE", "COUNT", "", "", "", "");
        for (String result : results) {
            System.out.println(result);
        }
    }
}
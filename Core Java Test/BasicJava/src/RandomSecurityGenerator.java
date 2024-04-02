import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class RandomSecurityGenerator {

    public static void main(String[] args) {
        int length = 15;
        List<String> randomString = generateRandomString(length);
        createFile(randomString);
    }

    public static List<String> generateRandomString(int length) {
        String alphanumeric = "0123456789";
        List<String> lineItem = new ArrayList<>();

        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < 25; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < length; j++) {
                int randomIndex = secureRandom.nextInt(alphanumeric.length());
                line.append(alphanumeric.charAt(randomIndex));
            }
            lineItem.add(line.append(",").toString());
        }

        return lineItem;
    }

    private static void createFile(List<String> randomString) {
        try {
            File file = new File("/Users/c2220081/Documents/ELabelFileSystem/SFTPFiles/consumed.txt");
            file.createNewFile();

            List<String> lineItems = new ArrayList<>();
            lineItems.add("Consumed,00-8573-229567,06-08-2025,A2570,FATP,12-22-2023,HHCD");

            lineItems.addAll(randomString);

            Path targetPath = file.toPath();
            Files.write(targetPath, lineItems, StandardCharsets.UTF_8);

        } catch (IOException exception) {
            System.out.println("IOException => " + exception);
        }
    }
}

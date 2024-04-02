package excel;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileWriterUpload {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/c2220081/Desktop/ELabel_Upload_Files/PN202308245042-A3092-300k.txt");

        List<ELabelUpload> list = new ArrayList<>();

        try (BufferedReader br =
                     new BufferedReader(
                             new InputStreamReader(
                                     new FileInputStream(file)))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                ELabelUpload upload = new ELabelUpload(split[0],split[1]);
                list.add(upload);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Path filePath = Paths.get("/Users/c2220081/Desktop/ELabel_Upload_Files/Dummy_Write.txt");
        Files.deleteIfExists(filePath);
        Files.createFile(filePath);
        for (ELabelUpload str : list) {
            Files.writeString(filePath, str.getLabel() + System.lineSeparator(),
                    StandardOpenOption.APPEND);
        }

    }
}

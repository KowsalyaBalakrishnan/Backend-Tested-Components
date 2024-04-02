package thread;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class ExeService {

    public static void main(String[] args) throws Exception {

        String originalFilePath = "/Users/c2220081/Desktop/ELabel_Upload_Files/DuplicatesRemoval/PN202308245041-A3092-300k.txt";
        String[] originalFileSplit = originalFilePath.split(".txt");

        String bsNumbers = "/Users/c2220081/Desktop/ELabel_Upload_Files/DuplicatesRemoval/bs-filtered.txt";
        String labelNumbers = "/Users/c2220081/Desktop/ELabel_Upload_Files/DuplicatesRemoval/label-filtered.txt";
        String finalFilePath = "/Users/c2220081/Desktop/ELabel_Upload_Files/DuplicatesRemoval/final.txt";

        String bsRemovalCommand = "awk -F ',' 'NR==FNR{a[$1]++; next} a[$1] == 1' " + originalFilePath + " "
                + originalFilePath + " > " + bsNumbers + " | sort -t',' -k1,1 -c " + bsNumbers
                + " && sort -t',' -k1,1 -o " + bsNumbers + " " + bsNumbers;

        String labelIdRemovalCommand = "awk -F ',' 'NR==FNR{a[$1]++; next} a[$1] == 1' " + originalFilePath + " "
                + originalFilePath + " > " + labelNumbers + " | sort -t',' -k1,1 -c " + labelNumbers
                + " && sort -t',' -k1,1 -o " + labelNumbers + " " + labelNumbers;

        String finalText = "comm -12 " + bsNumbers + " " + labelNumbers + " > " + finalFilePath;

        Runtime rt = Runtime.getRuntime ();

        try {
            String[] bsRemoval = new String[] {"/bin/sh", "-c", bsRemovalCommand};
            rt.exec(bsRemoval);

            String[] labelRemoval = new String[] {"/bin/sh", "-c", labelIdRemovalCommand};
            rt.exec(labelRemoval);

            Thread.sleep(1000);

            String[] commands = new String[] {"/bin/sh", "-c", finalText};
            rt.exec(commands);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally{
            System.out.println("Finally");
        }

    }

}

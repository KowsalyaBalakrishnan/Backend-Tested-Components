package ELabelDir;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestFileData {
    public static void main(String[] args) throws IOException {
        String myTextFile = "/Users/c2220081/Documents/2. E-Label/DummyFiles/Test.txt";
        String outputFile = "/Users/c2220081/Documents/2. E-Label/DummyFiles/";

        Hello obj1 = new Hello("AA", "aa");
        Hello obj2 = new Hello("BB", "bb");
        Hello obj3 = new Hello("BB", "cc");
        Hello obj4 = new Hello("DD", "dd");
        Hello obj5 = new Hello("EE", "ee");
        Hello obj6 = new Hello("EE", "ff");

        List<Hello> helloList = Arrays.asList(obj1, obj2, obj3, obj4, obj5, obj6);


        BufferedWriter br = new BufferedWriter(new FileWriter(outputFile + "OUT_ERROR_" + new Date().getTime() + ".txt"));
        for (Hello str : helloList) {
            br.write(str.getMsg1() + "," + str.getMsg2() + System.lineSeparator());
        }
        br.close();


    }
}

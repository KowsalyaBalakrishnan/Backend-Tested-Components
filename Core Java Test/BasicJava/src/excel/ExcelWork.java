package excel;

import java.io.File;
import java.io.IOException;

public class ExcelWork {
    public static void main(String[] args) throws IOException {
        String fileName = "ELABEL_HHZZ_IPHONE_A1001_ALLOCATE_2_10172023080343.txt";

        if (fileName.contains("ALLOCATE") && !fileName.contains("ALLOCATERECV")) {
            System.out.println("Allocate");
        }

        if (fileName.contains("ALLOCATERECV")) {
            System.out.println("Allocate RECEIVED");
        }

    }
}

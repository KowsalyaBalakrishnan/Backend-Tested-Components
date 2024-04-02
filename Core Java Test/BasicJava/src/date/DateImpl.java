package date;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateImpl {
    public static void main(String[] args) throws ParseException {
        String fileName = "ELABEL_HHLH_IPAD_A0110_ALLOCATERECV_3_07292023155036.txt";
        String fileDateTime = fileName.split("_")[6].split("\\.")[0];
        StringBuilder fileDate = new StringBuilder(fileDateTime.substring(0, 8));
        fileDate.insert(2, "-").insert(5, "-");

        Date parse = new SimpleDateFormat("MM-dd-yyyy").parse(fileDate.toString());
        System.out.println(parse);
    }
}

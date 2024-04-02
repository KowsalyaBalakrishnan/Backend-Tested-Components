import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileIntegrityCheck {

    public static String calculateMD5(String filePath) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("MD5");

        try (FileInputStream fis = new FileInputStream(filePath);
             DigestInputStream dis = new DigestInputStream(fis, md)) {

            // Read the file and update the message digest simultaneously
            while (dis.read() != -1) {
                // Read file contents, but we're not using it directly
            }

            // Get the MD5 hash value
            byte[] hash = md.digest();

            // Convert the byte array to a hexadecimal string
            StringBuilder result = new StringBuilder();
            for (byte b : hash) {
                result.append(String.format("%02x", b));
            }

            return result.toString();
        }
    }

    public static void main(String[] args) {
        try {
            String filePath = "/Users/c2220081/Documents/ELabelFileSystem/SFTPFiles/AllocateReceived/data/active/ELABEL_KOCH_IPAD_A7070_ALLOCATERECV_5_12172023152635_R0019_ERROR_FIX.txt";
            String expectedMD5 = "4cc809bf00d910e7437b98852df8d9c5"; // Replace with the expected MD5 hash

            String calculatedMD5 = calculateMD5(filePath);
            System.out.println(calculatedMD5);

            if (calculatedMD5.equals(expectedMD5)) {
                System.out.println("Duplicate");
            } else {
                System.out.println("Modified");
            }
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }
}

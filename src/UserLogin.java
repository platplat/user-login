import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class UserLogin {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);

        // Get user input
        System.out.print("Enter username: ");
        String usernameInput = sc.nextLine();
        System.out.print("Enter password: ");
        String passwordInput = sc.nextLine();

        // Login validation
        if (isValidCredentials(usernameInput, passwordInput)) {
            System.out.println("Successfully logged in.");
        } else {
            System.out.println("Incorrect username/password.");
        }

        // Close scanner
        sc.close();
    }


    public static boolean isValidCredentials(String username, String password) throws NoSuchAlgorithmException {
        // Starting with hardcoded user details
        Map<String,String> users = new HashMap<>();
        users.put("user1",getHash("password123"));
        users.put("user2", getHash("password321"));

        // Check if username and password are valid credentials
        if (users.containsKey(username) && users.get(username).equals(getHash(password))) {
            return true;
        } else {
            return false;
        }
    }


    public static String getHash(String password) throws NoSuchAlgorithmException {
        // Get SHA-256 hashing tool
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        
        // Convert password to byte array
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);

        // Hash password bytes
        byte[] passwordHash = md.digest(passwordBytes);

        // Create StringBuilder object to store output String
        StringBuilder hexString = new StringBuilder();

        // Iterate through the bytes of the hashed password, conver to hexadecimal and append to string
        for (byte b: passwordHash) {
            hexString.append(String.format("%02x", b));
        }

        // Convert StringBuilder object to a String and return
        return hexString.toString();
    }
}

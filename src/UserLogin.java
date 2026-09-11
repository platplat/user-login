import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
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
        List<User> users = new ArrayList<>();
        users.add(new User("user1", getHash("password123")));
        users.add(new User("user2", getHash("password321")));
        
        // Create a new User with the input username and password
        User inputUser = new User(username, getHash(password));
        
        // Check if username and password are valid credentials
        if (users.contains(inputUser)) {
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

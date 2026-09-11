import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class UserLogin {
    public static void main(String[] args) {
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

    public static boolean isValidCredentials(String username, String password) {
        // Starting with hardcoded user details
        Map<String,String> users = new HashMap<>();
        users.put("user1","password123");
        users.put("user2", "password321");

        // Check if username and password are valid credentials
        if (users.containsKey(username) && password.equals(users.get(username))) {
            return true;
        } else {
            return false;
        }
    }
}

import java.util.Scanner;

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
        String username1 = "user1";
        String password1 = "password123";

        // Check if username and password are valid credentials
        if (username.equals(username1) && password.equals(password1)) {
            return true;
        } else {
            return false;
        }
    }
}

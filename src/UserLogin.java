import java.util.Scanner;

public class UserLogin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Starting with hardcoded user details
        String username = "user1";
        String password = "password123";

        // Get user input
        System.out.print("Enter username: ");
        String usernameInput = sc.nextLine();
        System.out.print("Enter password: ");
        String passwordInput = sc.nextLine();

        // Login validation
        if (username.equals(usernameInput) && password.equals(passwordInput)) {
            System.out.println("Successfully logged in.");
        } else {
            System.out.println("Incorrect username/password.");
        }

        // Close scanner
        sc.close();
    }
}

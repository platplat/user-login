import java.util.Scanner;

public class UserLogin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        UserManagement userManagement = new UserManagement("src/users.json");

        while (true) {
            // Get user input
            System.out.print("Enter username: ");
            String usernameInput = sc.nextLine();
            System.out.print("Enter password: ");
            String passwordInput = sc.nextLine();

            // Login validation
            if (userManagement.isValidCredentials(usernameInput, passwordInput)) {
                System.out.println("Successfully logged in.");
                break;
            } else {
                System.out.println("Incorrect username/password. Please try again.");
            }
        }

        // Close scanner
        sc.close();
    }
}

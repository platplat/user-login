import java.util.Scanner;

public class UserLogin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // JSON file in which users are stored
        final String USERS_FILE = "src/users.json";

        UserManagement userManagement = new UserManagement(USERS_FILE);

        while (true) {
            System.out.println("Do you want to:");
            System.out.println("[1] Login (existing users)");
            System.out.println("[2] Register (new users)");
            System.out.println("[3] Exit");
            String userChoice = sc.nextLine().strip().toLowerCase();

            if (userChoice.equals("1") || userChoice.equals("login")) {
                startLogin(sc, userManagement);
                break;
            } else if (userChoice.equals("2") || userChoice.equals("register")) {
                startRegistration(sc, userManagement);
            } else if (userChoice.equals("3") || userChoice.equals("exit")) {
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        // Close scanner
        sc.close();
    }

    public static void startLogin(Scanner sc, UserManagement userManagement) {
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
    }

    public static void startRegistration(Scanner sc, UserManagement userManagement) {
        while (true) {
            // Get user input
            System.out.print("Enter username: ");
            String usernameInput = sc.nextLine();
            System.out.print("Enter password: ");
            String passwordInput = sc.nextLine();
            System.out.print("Confirm password: ");
            String passwordConfirm = sc.nextLine();

            while ( !(passwordInput.equals(passwordConfirm)) ) {
                System.out.println("Passwords do not match. Please try again.");
                System.out.print("Enter password: ");
                passwordInput = sc.nextLine();
                System.out.print("Confirm password: ");
                passwordConfirm = sc.nextLine();
            }

            // Register user
            if (userManagement.addUser(usernameInput, passwordInput)) {
                System.out.println("User registration successful.");
                break;
            } else {
                System.out.println("Username already exists. Please try again.");
            }
        }
    }
}

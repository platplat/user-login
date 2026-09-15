import java.util.Scanner;

public class UserInterface {
    private Scanner sc;
    private UserManagement userManagement;

    public UserInterface(Scanner sc, UserManagement userManagement) {
        this.sc = sc;
        this.userManagement = userManagement;
    }

    public void start() {

        while (true) {
            System.out.println("Do you want to:");
            System.out.println("[1] Login (existing users)");
            System.out.println("[2] Register (new users)");
            System.out.println("[3] Exit");
            String userChoice = sc.nextLine().strip().toLowerCase();

            if (userChoice.equals("1") || userChoice.equals("login")) {
                startLogin();
                break;
            } else if (userChoice.equals("2") || userChoice.equals("register")) {
                startRegistration();
            } else if (userChoice.equals("3") || userChoice.equals("exit")) {
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void startLogin() {
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

    public void startRegistration() {
        while (true) {
            // Get username from user
            System.out.print("Enter username: ");
            String usernameInput = sc.nextLine();

            // Validate username
            while (usernameInput.contains("\"") || usernameInput.contains(",")) {
                System.out.println("Invalid username. Please try again.");
                System.out.print("Enter username: ");
                usernameInput = sc.nextLine();
            }
                
            // Get password from user
            System.out.print("Enter password: ");
            String passwordInput = sc.nextLine();
            while (passwordInput.length() < 8) {
                System.out.println("Password too short. Please try again.");
                System.out.print("Enter password: ");
                passwordInput = sc.nextLine();
            }
            System.out.print("Confirm password: ");
            String passwordConfirm = sc.nextLine();

            // Confirm password 
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
import java.util.Scanner;

public class UserLogin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // JSON file in which users are stored
        final String USERS_FILE = "src/users.json";

        UserManagement userManagement = new UserManagement(USERS_FILE);

        UserInterface ui = new UserInterface(sc, userManagement);
        ui.start();
    }
}

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class UserLogin {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);

        while (true) {
            // Get user input
            System.out.print("Enter username: ");
            String usernameInput = sc.nextLine();
            System.out.print("Enter password: ");
            String passwordInput = sc.nextLine();

            // Login validation
            if (isValidCredentials(usernameInput, passwordInput)) {
                System.out.println("Successfully logged in.");
                break;
            } else {
                System.out.println("Incorrect username/password. Please try again.");
            }
        }

        // Close scanner
        sc.close();
    }


    public static boolean isValidCredentials(String username, String password) throws NoSuchAlgorithmException {
        // Create list of users and load data from csv file
        List<User> users = new ArrayList<>();
        users = readFromJson("src/users.json");
        
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


    public static List<User> readFromCsv(String file) {
        // Create empty list of users to return
        List<User> users = new ArrayList<>();
        
        // Try catch block to manage exception
        try (Scanner sc = new Scanner(Paths.get(file))) {
            
            // Check file has next line
            while (sc.hasNextLine()) {
                String user = sc.nextLine();

                // if line is empty or incomplete, continue
                if (user.equals("") || !(user.contains(",")) ) {
                    continue;
                }
                // Extract username and hashed password and add new user to list
                String[] userArray = user.split(",");
                users.add(new User(userArray[0], userArray[1]));
            }

        } catch (Exception e) {
            System.out.println("\nERROR: There was a problem reading the file.");
            System.out.println(e);
            return new ArrayList<>();
        }

        return users;
    }


    public static List<User> readFromJson(String file) {
        // Number of property for each user in JSON file
        final int NUMBER_OF_PROPERTIES = 2;

        List<User> users = new ArrayList<>();
        try {
            Scanner sc = new Scanner(Paths.get(file));
            List<String> lines = new ArrayList<>();
            
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
            sc.close();

            for (int i = 2; i < lines.size()-2; i+=NUMBER_OF_PROPERTIES+2) {

                // Extract and clean username
                String username = lines.get(i).split(":")[1];
                username = username.replace("\"", "").replace(",", "").strip();
                //System.out.println(username);

                // Extract and clean password hash
                String passwordHash = lines.get(i+1).split(":")[1];
                passwordHash = passwordHash.replace("\"", "").replace(",", "").strip();
                //System.out.println(passwordHash);

                // Add user to users list
                users.add(new User(username, passwordHash));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return users;
    }
}

import java.util.List;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;

public class UserManagement {
    private List<User> users;

    public UserManagement(String file) {
        if (file.endsWith("csv")) {
            this.users = readFromJson(file);
        } else if (file.endsWith("json")) {
            this.users = readFromJson(file);
        } else {
            this.users = new ArrayList<>();
        }
    }

    public List<User> getUsers() {
        return this.users;
    }

    public boolean isValidCredentials(String username, String password) {
        // Create a new User with the input username and password
        User inputUser = new User(username, getHash(password));
        
        // Check if username and password are valid credentials
        if (this.users.contains(inputUser)) {
            return true;
        } else {
            return false;
        }
    }

    // Static methods
    public static List<User> readFromCsv(String file) {
        // Create empty list of users to return
        List<User> users = new ArrayList<>();
        
        // Try catch block to manage IOException exception
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


    public static String getHash(String password) {
        // try catch block to catch NoSuchAlgorithmException
        try {
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

        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            return null;
        }
    }
}

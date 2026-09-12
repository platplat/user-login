import java.util.List;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class UserManagement {
    private Map<String,User> users;
    private String file;

    public UserManagement(String file) {
        this.file = file;
        this.users = this.readFromJson();
    }

    public Map<String,User> getUsers() {
        //return this.users;
        return this.users;
    }

    public boolean addUser(String username, String password) {
        User userToAdd = new User(username, getHash(password));
        if (this.users.containsKey(username)) {
            return false;
        }
        this.users.put(username, userToAdd);
        this.writeToJson(users);
        return true;
    }

    public boolean isValidCredentials(String username, String password) {
        // Create a new User with the input username and password
        User inputUser = new User(username, getHash(password));
        
        // Verify user exists
        if ( !(this.users.containsKey(username)) ) {
            return false;
        }

        // Check if username and password are valid credentials
        if (this.users.get(username).equals(inputUser)) {
            return true;
        } else {
            return false;
        }
    }

    public void writeToJson(Map<String,User> users) {
        StringBuilder jsonString = new StringBuilder();
        jsonString.append("[\n");
        for (String username: users.keySet()) {
            jsonString.append("  {\n");
            jsonString.append("    \"username\": \"" + username + "\",\n");
            jsonString.append("    \"passwordHash\": \"" + users.get(username).getPasswordHash() + "\"\n");
            jsonString.append("  },\n");
        }
        jsonString.deleteCharAt(jsonString.length()-2);
        jsonString.append("]");

        Path filePath = Paths.get(this.file);
        try {
            Files.writeString(filePath, jsonString.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Map<String, User> readFromJson() {
        // Number of property for each user in JSON file
        final int NUMBER_OF_PROPERTIES = 2;

        Map<String,User> users = new HashMap<>();
        try {
            Scanner sc = new Scanner(Paths.get(this.file));
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
                users.put(username, new User(username, passwordHash));
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

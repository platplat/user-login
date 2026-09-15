public class User {
    private String username;
    private String passwordHash;

    public User(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public boolean equals(Object compared) {
        if (this == compared) {
            return true;
        }
        if ( !(compared.getClass() == this.getClass()) ) {
            return false;
        }

        User comparedUser = (User) compared;

        if (comparedUser.getUsername().equals(this.getUsername()) 
            && comparedUser.getPasswordHash().equals(this.getPasswordHash())) {
            return true;
        }
        return false;
    }
}

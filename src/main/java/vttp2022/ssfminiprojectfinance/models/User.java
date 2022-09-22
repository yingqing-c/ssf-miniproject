package vttp2022.ssfminiprojectfinance.models;

public class User {
    private String fullName;
    private String email;
    private boolean loggedIn;

    public User() {
    }

    public User(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }
    
    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User [email=" + email + ", fullName=" + fullName + ", loggedIn=" + loggedIn + "]";
    }
}

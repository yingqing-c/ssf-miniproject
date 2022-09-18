package vttp2022.ssfminiprojectfinance.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private String fullName;
    private String email;
    private String password;
    private String dateOfBirth;
    private boolean loggedIn;

    public boolean isLoggedIn() {
        return loggedIn;
    }
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Date getDateOfBirth() {
        try {
            return dateFormat.parse(this.dateOfBirth);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    @Override
    public String toString() {
        return "User [dateFormat=" + dateFormat + ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", fullName="
                + fullName + ", password=" + password + "]";
    }

}

    
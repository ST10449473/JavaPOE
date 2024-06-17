/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PROG5121P0E;

/**
 *
 * @author Dzivhani Precious Rosiiwa
 */

import java.util.regex.Pattern;

public class SignUp {
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^\\w{1,5}_?$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={};':\"\\\\|,.<>\\/?])(?=\\S+$).{8,}$");

    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public SignUp() {
        // Default constructor
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean checkUserName() {
        if (username != null && USERNAME_PATTERN.matcher(username).matches()) {
            System.out.println("Username successfully captured");
            return true;
        } else {
            System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.");
            return false;
        }
    }

    public boolean checkPasswordComplexity() {
        if (password != null && PASSWORD_PATTERN.matcher(password).matches()) {
            System.out.println("Password successfully captured");
            return true;
        } else {
            System.out.println("Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.");
            return false;
        }
    }

    public String registerUser() {
        if (checkUserName() && checkPasswordComplexity()) {
            return "User registered successfully";
        } else {
            return "Registration failed";
        }
    }
}



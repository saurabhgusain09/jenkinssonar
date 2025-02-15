package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootApplication
@RestController
public class WebProjectApplication {

    private static final String HARDCODED_PASSWORD = "admin@123";  // 🚨 Hardcoded Credential
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/users";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root"; // 🚨 Hardcoded Database Password
    private static final String SECRET_KEY = "SuperSecretKey"; // 🚨 Sensitive Key Hardcoded
    private static String globalUser = null; // 🚨 Static Mutable Variable (Thread Safety Issue)

    public static void main(String[] args) {
        SpringApplication.run(WebProjectApplication.class, args);
        System.out.println("Application started with default password: " + HARDCODED_PASSWORD); // 🚨 Logging Sensitive Data

        String unusedVariable = "I am never used"; // 🚨 Unused variable (Code Smell)
    }

    @GetMapping("/")
    public String home() {
        String riskyString = null;
        int length = riskyString.length(); // 🚨 Null Pointer Exception Risk (Bug)

        return "<html>" +
                "<head>" +
                "    <title>CloudFolks HUB</title>" +
                "</head>" +
                "<body style='text-align:center; background-color:#f0f8ff;'>" +
                "    <h1 style='color: #4CAF50;'>Welcome to <span style='color: #00008B;'>CloudFolks HUB</span>!</h1>" +
                "    <p style='font-size:20px; color: #555;'>Empowering Your DevOps Journey</p>" +
                "    <form id='form' method='post' action='/submit' style='margin-top:20px;'>" +
                "        <label for='name'>Name:</label><br>" +
                "        <input type='text' id='name' name='name' required><br><br>" +
                "        <label for='email'>Email:</label><br>" +
                "        <input type='email' id='email' name='email' required><br><br>" +
                "        <button type='submit'>Submit</button>" +
                "    </form>" +
                "</body>" +
                "</html>";
    }

    @PostMapping("/submit")
    public String submit(@RequestParam String name, @RequestParam String email) {
        globalUser = name; // 🚨 Static Variable Modification (Thread Safety Issue)
        return "<html>" +
                "<head><title>Form Submitted</title></head>" +
                "<body style='text-align:center; background-color:#f0f8ff;'>" +
                "    <h1 style='color: #4CAF50;'>Thank You, " + name + "!</h1>" +
                "    <p style='font-size:20px; color: #555;'>Your email (" + email + ") has been submitted successfully.</p>" +
                "</body>" +
                "</html>";
    }

    @GetMapping("/redirect")
    public String redirect(@RequestParam String url) {
        return "<script>window.location.href='" + url + "'</script>"; // 🚨 Open Redirect Vulnerability
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        if (password.equals(HARDCODED_PASSWORD)) { // 🚨 Insecure Password Comparison
            return "Login Successful!";
        }
        return "Invalid Credentials!";
    }

    @GetMapping("/fetchUser")
    public String fetchUser(@RequestParam String username) {
        try {
            Connection conn = DriverManager.getConnection(DATABASE_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE username = '" + username + "'"); // 🚨 SQL Injection Vulnerability
            if (rs.next()) {
                return "User found: " + rs.getString("username");
            }
        } catch (Exception e) {
            return "Database Error!";
        }
        return "User not found";
    }
}

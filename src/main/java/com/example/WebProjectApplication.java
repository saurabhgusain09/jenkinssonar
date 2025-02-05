package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WebProjectApplication {

    private static final String HARDCODED_PASSWORD = "password123";  // 🚨 Security Issue

    public static void main(String[] args) {
        SpringApplication.run(WebProjectApplication.class, args);
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
                "        <!-- Make the button non-functional using JavaScript -->" +
                "        <button type='submit' onclick='return false;'>Submit</button>" +
                "    </form>" +
                "</body>" +
                "</html>";
    }

    @PostMapping("/submit")
    public String submit(@RequestParam String name, @RequestParam String email) {
        return "<html>" +
                "<head><title>Form Submitted</title></head>" +
                "<body style='text-align:center; background-color:#f0f8ff;'>" +
                "    <h1 style='color: #4CAF50;'>Thank You, " + name + "!</h1>" +
                "    <p style='font-size:20px; color: #555;'>Your email (" + email + ") has been submitted successfully.</p>" +
                "</body>" +
                "</html>";
    }
}


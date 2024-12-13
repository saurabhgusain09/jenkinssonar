package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WebProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebProjectApplication.class, args);
    }

    @GetMapping("/")
    public String home() {
        // Return HTML content with branding and colorful text
        return "<html>" +
               "<head>" +
               "<title>CloudFolks HUB</title>" +
               "</head>" +
               "<body style='text-align:center; background-color:#f0f8ff;'>" +
               "<h1 style='color: #4CAF50;'>Welcome to <span style='color: #00008B;'>CloudFolks HUB</span>!</h1>" +
               "<p style='font-size:20px; color: #555;'>Empowering Your DevOps Journey</p>" +
               "</body>" +
               "</html>";
    }
}


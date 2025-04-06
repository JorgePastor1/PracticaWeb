package com.example.torneos.Controller;

// Import necessary Spring MVC annotations
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Marks this class as a Spring MVC Controller
public class WebController {

    // Handles GET requests to the root URL "/"
    @GetMapping("/")
    public String home() {
        return "index"; // Returns the view named "index" (usually index.html under /templates)
    }
}

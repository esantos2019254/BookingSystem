package com.elmer.Booking.controller.health;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    public String checkApi() {
        return "<h1>The API is working ok!</h1>";
    }
}

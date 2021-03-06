package com.park.spring.security.mongodb.securitymongodb.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "Home Public";
    }

    @Secured({"USER", "ADMIN"})
    @GetMapping("/profile")
    public String profile() {
        return "Profile Secured";
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/setting")
    public String setting() {
        return "Setting Secured";
    }

    @Secured("USER")
    @GetMapping("/user")
    public String user() {
        return "User Secured";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "Admin Secured";
    }

}

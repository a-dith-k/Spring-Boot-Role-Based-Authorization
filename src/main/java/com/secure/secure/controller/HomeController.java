package com.secure.secure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("")
public class HomeController {

    @GetMapping()
    public String getHome() {

        return "This is Home";
    }

    @GetMapping("/user/dashboard")
    public ResponseEntity<String> normalUser(){

        return  ResponseEntity.ok("This is  User Dashboard ");
    }


    @GetMapping("/admin/dashboard")
    public ResponseEntity<String> adminUser(){
        return  ResponseEntity.ok("This is admin dashboard");
    }

}

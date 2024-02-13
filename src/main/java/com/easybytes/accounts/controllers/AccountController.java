package com.easybytes.accounts.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping(path = "/")
    public String greeting() {
        return "Hello World!";
    }
}

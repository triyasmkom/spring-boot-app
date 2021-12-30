
package com.domain.restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// alamat url client
@RequestMapping("/api/welcome")
public class WelcomeController {
    
    @GetMapping
    public String wecome(){
        return "welcom";
    }
}

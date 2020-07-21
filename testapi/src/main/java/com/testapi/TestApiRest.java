package com.testapi;  

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class TestApiRest {
	

	@GetMapping("/name")
    public String getName(@RequestParam String Name){
        
        return Name;
    }

}

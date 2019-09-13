package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
	
	@GetMapping("/home")
	public String hello() {
		return "Hi app-3 is up and working";
	}
	
}

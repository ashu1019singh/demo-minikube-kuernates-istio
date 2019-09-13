package com.example.demo;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AppController {

	private RestTemplate template;

	private String url = "http://localhost:8094/home";

	@GetMapping("/home")
	public String hello() {
		return "Hi app-2 is up and working";
	}

	@GetMapping("/app-3")
	public String app3() {
		template = new RestTemplate();
		ResponseEntity<String> res = template.exchange(url, HttpMethod.GET, null, String.class);
		System.out.println("res is " + res);
		return res.getBody().toString();
	}

}

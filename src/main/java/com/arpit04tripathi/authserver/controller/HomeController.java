package com.arpit04tripathi.authserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping(value= "/")
	public String home() {
		return "I am an OAuth2 Server";
	}
}

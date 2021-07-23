package ru.otus.spring.security.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSecurityController {

	@GetMapping("/")
	public String helloSecurity() {
		return "Hello Security";
	}	

	@GetMapping("/public")
	public String helloPublic() {
		return "Hello Public";
	}
	
	

}

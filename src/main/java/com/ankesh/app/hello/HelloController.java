package com.ankesh.app.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/ankesh")
public class HelloController {
	
		 @RequestMapping("/hi")
		    public String index() {
		        return "Greetings from Spring Boot !";
		    }
		 

		 @RequestMapping("/hello")
		    public String index1() {
		        return "Greetings from Spring Boot Ankesh!";
		    }


}

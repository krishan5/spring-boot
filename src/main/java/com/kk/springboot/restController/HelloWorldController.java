package com.kk.springboot.restController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	/**
	 * If we do not mention any path (uri) here as well as at class level
	 * then this could be access through >> http://localhost:8080
	 */
	//@RequestMapping(method = RequestMethod.GET)
	
	/**
	 * Now we have mentioned the path here or if we mentioned at class level using @RequestMapping("/hello-world")
	 * then this gonna access through >> http://localhost:8080/hello-world
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	public String helloWorld() {
		return "Hello World !";
	}
	
}

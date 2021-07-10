package com.kk.springboot.restController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kk.springboot.entity.HelloWorld;

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
	//@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	
	/**
	 * This is the alternative way of using @RequestMapping and most recommendable way too.
	 */
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World !";
	}
	
	@GetMapping(path = "/hello-world-2")
	public HelloWorld helloWorld2() {
		return new HelloWorld("Hello World 2 !");
	}
	
	@GetMapping(path = "/hello-world/{pathVariable}")
	public HelloWorld helloWorld3(@PathVariable(name = "pathVariable") String pathVariable) {
		return new HelloWorld(pathVariable);
	}
}

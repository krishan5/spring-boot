package com.kk.springboot.restController;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.kk.springboot.entity.HelloWorld;

@RestController
public class HelloWorldController {
	
	@Autowired
	MessageSource messageSource;
	
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
	
	/**
	 * INTERNATIONALISATION
	 * 
	 * In this approach, the problem is we have to write code for locale parameter for each and every method
	 * which will be a overhead for method signature.
	 * To resolve this, we can simply use LocaleContextHolder instead of locale.
	 */
	@GetMapping(path = "/hello-world-i18n")
	public String helloWorldI18N(@RequestHeader(name = "Accept-language", required = false) Locale locale) {
		//good.morning.message is coded in all messages properties files. Which language it need to fetch depends
		//upon the "accept-language" header value. If client send request with this header with value = "fr"
		//then messages_fr.properties will be used by MessageSource and it will search for "good.morning.message"
		//value from that file.
		return messageSource.getMessage("good.morning.message", null, "DEFAULT MESSAGE", locale);
	}
	
	@GetMapping(path = "/hello-world-i18n-2")
	public String helloWorldI18N_UsingLocaleContextHolder() {
		return messageSource.getMessage("good.morning.message", null, "DEFAULT MESSAGE",
				LocaleContextHolder.getLocale());
	}
}

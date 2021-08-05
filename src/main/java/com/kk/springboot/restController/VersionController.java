package com.kk.springboot.restController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kk.springboot.entity.PersonV1;
import com.kk.springboot.entity.PersonV2;

/**
 * Making version is easy but deciding which way of versioning is to use for versioning is not easy.
 * Following are different ways of versioning :
 * 1. URI based versioning
 * 2. Request param based versioning
 * 3. Custom Header based versioning
 * 4. Media type versioning
 * 
 * Following are factors using them to decide which way to use when :
 * 1. Easy of use and making documentation.
 * 2. URI pollution.
 * 3. HTTP Header misuse.
 * 4. Caching.
 * 5. Can we execute the request on browser ?
 * 6. API documentation.
 * 
 * In URI and Request param based versioning, we are actually polluting the url but these are easy to build
 * and can be used by browsers url directly and also easy to documented.
 * Header based and media type versioning are never made for versioning. We are just misusing them here.
 * We can't cache the request in case of Header based and Media type versioing because they have same url.
 * Caching will become very for them. but in case of URI and Request params based versioning, it is cache-able.
 * 
 * Note: There is no perfect solution. Simple, analyze the usage of them.
 */
@RestController
public class VersionController {
	
	/*** URI based versioning ***/
	
	@GetMapping("/v1/person")
	public PersonV1 getPersonV1() {
		return new PersonV1("Krishan Kumar");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getPersonV2() {
		return new PersonV2("Krishan", "Kumar");
	}
	
	
	/*** Request param based versioning ***/
	
	@GetMapping(value = "/person/param", params = "version=1")
	public PersonV1 getPersonV1BasedOnParam() {
		return new PersonV1("Krishan Kumar");
	}
	
	@GetMapping(value = "/person/param", params = "version=2")
	public PersonV2 getPersonV2BasedOnParam() {
		return new PersonV2("Krishan", "Kumar");
	}
	
	
	/*** Custom Header based versioning ***/ // Here custom header is "X-API-VERSION"
	
	@GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 getPersonV1BasedOnHeader() {
		return new PersonV1("Krishan Kumar");
	}
	
	@GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 getPersonV2BasedOnHeader() {
		return new PersonV2("Krishan", "Kumar");
	}
	
	
	/*** Media type versioning ***/ // It is similar to customer header way, just use "Accept" header instead of custom header like "X-API-VERSION"
	
	@GetMapping(value = "/person/produces", produces = "application/kk.company.app-v1+json")
	public PersonV1 getPersonV1BasedOnProduces() {
		return new PersonV1("Krishan Kumar");
	}
	
	@GetMapping(value = "/person/produces", produces = "application/kk.company.app-v2+json")
	public PersonV2 getPersonV2BasedOnProduces() {
		return new PersonV2("Krishan", "Kumar");
	}

}

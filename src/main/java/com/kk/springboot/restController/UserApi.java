package com.kk.springboot.restController;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kk.springboot.entity.User;
import com.kk.springboot.exception.UserNotFoundException;
import com.kk.springboot.service.UserDaoService;

@RestController
@RequestMapping("/users")
public class UserApi {
	
	@Autowired
	UserDaoService userDaoService;
	
	@GetMapping
	public List<User> getAllUsers() {
		return userDaoService.findAll();
	}
	
	@GetMapping("/{id}")
	public EntityModel<User> getUser(@PathVariable("id") int id) {
		User user = userDaoService.findById(id);
		if(Objects.isNull(user))
			throw new UserNotFoundException("id="+id);
		
		/***HATEOAS***/
		
		/**
		 * "_links" is attached in response along with values of User object :
		 * 
		 * {
		 *   "id": 1,
         *   "name": "Recker",
         *   "birthDate": "1994-09-28",
		 *   "_links": {
         *     "all-users": {
         *       "href": "http://localhost:8080/users"
         *     }
         *   }
         * }
		 */
		
		EntityModel<User> entityModel = EntityModel.of(user);
		
		//Instead of hard coding the link to entityModel, what we are saying we want hard coded link related 
		//to this specific method i.e. getAllUsers() method and build the proper link to access it.
		WebMvcLinkBuilder linkToUsers = WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
		//This is easy way to do above WebMvcLinkBuilder implementation importing static methods like :
		// import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
		//Then it will be accessed like this :
		// WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).getAllUsers());
		
		//Adding that link to entityModel. Also making relation of prepared link. Given value here ("all-users")
		//will be name of that link and added into response.
		entityModel.add(linkToUsers.withRel("all-users"));
		
		return entityModel;
	}
	
	/**
	 * To make use of validation, just put @Valid annotation on object you want to validate
	 * and define validation type in that class (i.e. in User class) like @Size, @Past, @NotNull
	 */
	@PostMapping
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
		User createdUser = userDaoService.save(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(createdUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = userDaoService.deleteUserById(id);
		if(Objects.isNull(user))
			throw new UserNotFoundException("id="+id);
	}
	
}

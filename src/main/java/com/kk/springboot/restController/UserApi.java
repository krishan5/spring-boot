package com.kk.springboot.restController;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public User getUser(@PathVariable("id") int id) {
		User user = userDaoService.findById(id);
		if(Objects.isNull(user))
			throw new UserNotFoundException("id="+id);
		return user;
	}
	
	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User createdUser = userDaoService.save(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(createdUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
}

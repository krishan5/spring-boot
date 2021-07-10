package com.kk.springboot.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.kk.springboot.entity.User;

@Component
public class UserDaoService {
	private static List<User> userData = new ArrayList<>();
	private static int userCount;
	
	static {
		userData.add( new User(1, "Recker", LocalDate.of(1994, Month.SEPTEMBER, 28)) );
		userData.add( new User(2, "Stark", LocalDate.of(1986, Month.MAY, 15)) );
		userData.add( new User(3, "Michael", LocalDate.of(1990, Month.FEBRUARY, 4)) );
		userCount = 3;
	}
	
	public List<User> findAll() {
		return userData;
	}
	
	public User save(User user) {
		if(user.getId() == 0) {
			user.setId(++userCount);
		}
		userData.add(user);
		return user;
	}
	
	public User findById(int id) {
		for(User user : userData) {
			if(user.getId() == id)
				return user;
		}
		return null;
	}
}

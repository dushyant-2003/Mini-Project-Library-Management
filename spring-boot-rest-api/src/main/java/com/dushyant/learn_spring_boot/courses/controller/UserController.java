package com.dushyant.learn_spring_boot.courses.controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dushyant.learn_spring_boot.courses.bean.Users;
import com.dushyant.learn_spring_boot.courses.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository repository;

	// URL - http://localhost:8080/users
	@GetMapping("/users")
	public List<Users> getAllUsers() {

		return repository.findAll();
	}

	@GetMapping("/users/{id}")
	@Cacheable(value = "users")
	public ResponseEntity<Users> getUserById(@PathVariable long id) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Optional<Users> user = repository.findById(id);
		if (user.isEmpty()) {
			throw new RuntimeException("User not found with id " + id);
		}
		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS)).body(user.get());

	}

	@PostMapping("/users")
	public ResponseEntity<Users> createUser(@RequestBody Users user) {
		repository.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<Users> updateUser(@RequestBody Users user) {
		repository.save(user);
		return ResponseEntity.ok(user);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<Users> deleteUser(@PathVariable long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}

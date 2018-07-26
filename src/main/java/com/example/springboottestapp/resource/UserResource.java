package com.example.springboottestapp.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.springboottestapp.exception.RestResourceNotFoundException;
import com.example.springboottestapp.model.User;
import com.example.springboottestapp.service.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User> findUsers() {
		return userService.findUsers();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") Integer id) {
		User removedUser = userService.deleteUser(id);
		
		if (removedUser == null) {
			throw new RestResourceNotFoundException("User of id " + id + " not found!");
		}
		
		return ResponseEntity.ok().build();
		
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user){
		userService.createUser(user);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(user.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable Integer id) {
		User user = userService.getUser(id);
		
		if (user == null) {
			throw new RestResourceNotFoundException("User of id " + id + " not found!");
		}
		
		return user;
	}
	
}

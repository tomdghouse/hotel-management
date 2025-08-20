package com.nexted.UserService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexted.UserService.entity.Rating;
import com.nexted.UserService.entity.User;
import com.nexted.UserService.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping()
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getSingleUser(@PathVariable String id) {
		User user = userService.getUser(id);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/all-users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> allUsers = userService.getAllUsers();
		return ResponseEntity.ok(allUsers);
	}

	@PutMapping("/update-user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
		User userDb = userService.updateUser(id, user);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDb);
	}

	@DeleteMapping("/delete-user/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable String id) {
		userService.deleteUserById(id);
		return ResponseEntity.ok("User with Id " + id + " deleted Successfully...");
	}

	@DeleteMapping("/delete-user-rating-by-ratingId/{ratingId}/{userId}")
	public ResponseEntity<String> deleteRatingById(@PathVariable String ratingId, @PathVariable String userId) {
		userService.deleteRatingById(ratingId, userId);
		return ResponseEntity.status(HttpStatus.OK)
				.body("User rating with id " + ratingId + " is deleted successfully");
	}

	@DeleteMapping("/delete-all-rating-by-user/{userId}")
	public ResponseEntity<String> deleteAllRatingByUser(@PathVariable String userId) {
		userService.deleteAllRatingByUser(userId);
		return ResponseEntity.status(HttpStatus.OK).body("Ratings deleted successfully for userId " + userId);
	}

}

package com.nexted.Ratingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexted.Ratingservice.entity.Rating;
import com.nexted.Ratingservice.service.impl.RatingServiceImpl;

@RestController
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	private RatingServiceImpl ratingServiceImpl;

	@PostMapping("/create-rating")
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
		Rating ratingDb = ratingServiceImpl.createRating(rating);
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingDb);
	}

	@GetMapping("/get-rating/{ratingId}")
	public ResponseEntity<Rating> getRating(@PathVariable String ratingId) {
		Rating rating = ratingServiceImpl.getRating(ratingId);
		return ResponseEntity.status(HttpStatus.OK).body(rating);
	}

	@GetMapping("/get-all-ratings")
	public ResponseEntity<List<Rating>> getAllRatings() {
		List<Rating> ratingDb = ratingServiceImpl.getAllRatings();
		return ResponseEntity.status(HttpStatus.OK).body(ratingDb);
	}

	@GetMapping("/get-rating-by-user-id/{id}")
	public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String id) {
		List<Rating> ratingDb = ratingServiceImpl.getRatingsByUserId(id);
		return ResponseEntity.status(HttpStatus.OK).body(ratingDb);
	}

	@GetMapping("/get-rating-by-hotel-id/{id}")
	public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String id) {
		List<Rating> ratingDb = ratingServiceImpl.getRatingsByHotelId(id);
		return ResponseEntity.status(HttpStatus.OK).body(ratingDb);
	}

	@PutMapping("/update-rating/{id}")
	public ResponseEntity<Rating> updateRating(@PathVariable String id, Rating rating) {
		Rating ratingDb = ratingServiceImpl.updateRating(id, rating);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(ratingDb);
	}

	@DeleteMapping("/delete-by-id/{id}")
	public ResponseEntity<String> deleteRatingById(@PathVariable String id) {
		ratingServiceImpl.deleteRatingById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Rating with id " + id + " successfully deleted..");
	}

	@DeleteMapping("/delete-all-rating-by-user/{userId}")
	public ResponseEntity<String> deleteAllRatingsByUser(@PathVariable String userId) {
		ratingServiceImpl.deleteAllRatingsByUser(userId);
		return ResponseEntity.status(HttpStatus.OK)
				.body("All the ratings of user with id " + userId + " deleted successfully");
	}

}

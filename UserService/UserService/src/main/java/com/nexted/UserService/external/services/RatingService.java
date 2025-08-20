package com.nexted.UserService.external.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.nexted.UserService.entity.Rating;

@FeignClient(name = "RATINGSERVICE")
public interface RatingService {

	@GetMapping("/rating/get-all-ratings")
	List<Rating> getAllRatings();
	
	@GetMapping("/rating/get-rating/{ratingId}")
	Rating getRating(@PathVariable String ratingId);

	@GetMapping("/rating/get-rating-by-user-id/{id}")
	List<Rating> getAllRating(@PathVariable String id);

	@PutMapping("/rating/update-rating/{id}")
	ResponseEntity<Rating> updateRating(@PathVariable String id, Rating rating);

	@DeleteMapping("/rating/delete-by-id/{id}")
	public ResponseEntity<String> deleteRatingById(@PathVariable String id);

	@DeleteMapping("/rating/delete-all-rating-by-user/{userId}")
	public ResponseEntity<String> deleteAllRatingsByUser(@PathVariable String userId);
}

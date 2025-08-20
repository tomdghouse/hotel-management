package com.nexted.Ratingservice.service;

import java.util.List;

import com.nexted.Ratingservice.entity.Rating;


public interface RatingService {
	
	 Rating createRating(Rating rating);
	 
	 Rating getRating(String ratingId);
	 
	 List<Rating> getAllRatings(); 
	 
	 List<Rating> getRatingsByUserId(String id); 
	 
	 List<Rating> getRatingsByHotelId(String id);
	 
	 public Rating updateRating(String id, Rating rating);
	 
	 void deleteRatingById(String id);
	 
	 void deleteAllRatingsByUser(String userId);

}

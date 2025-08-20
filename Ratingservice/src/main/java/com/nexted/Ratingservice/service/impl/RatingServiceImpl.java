package com.nexted.Ratingservice.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexted.Ratingservice.entity.Rating;
import com.nexted.Ratingservice.exception.ResourceNotFoundException;
import com.nexted.Ratingservice.repository.RatingRepository;
import com.nexted.Ratingservice.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public Rating createRating(Rating rating) {
		String randomId = UUID.randomUUID().toString();
		rating.setRatingId(randomId);
		return ratingRepository.save(rating);
	}

	@Override
	public Rating getRating(String ratingId) {
		Rating rating = ratingRepository.findById(ratingId)
				.orElseThrow(() -> new ResourceNotFoundException("Rating with id " + ratingId + " not found.."));
		return rating;
	}

	@Override
	public List<Rating> getAllRatings() {
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getRatingsByUserId(String id) {
		return ratingRepository.findByUserId(id);
	}

	@Override
	public List<Rating> getRatingsByHotelId(String id) {
		return ratingRepository.findByHotelId(id);
	}

	@Override
	public Rating updateRating(String id, Rating rating) {
		Rating ratingDb = ratingRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Rating with id " + id + " not found.."));
		if (rating.getFeedback() != null) {
			ratingDb.setFeedback(rating.getFeedback());
		}
		if (rating.getRating() != ratingDb.getRating()) {
			ratingDb.setRating(rating.getRating());
		}
		return ratingRepository.save(ratingDb);
	}

	@Override
	public void deleteRatingById(String id) {
		Rating rating = ratingRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Rating with id " + id + " not found.."));
		ratingRepository.deleteById(id);

	}

	@Override
	public void deleteAllRatingsByUser(String userId) {
		List<Rating> ratingList = ratingRepository.findByUserId(userId);
		if (ratingList.isEmpty()) {
			throw new ResourceNotFoundException("No ratings found for the userId " + userId);
		}
		ratingRepository.deleteAll(ratingList);
	}

}

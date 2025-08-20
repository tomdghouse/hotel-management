package com.nexted.UserService.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nexted.UserService.entity.Hotel;
import com.nexted.UserService.entity.Rating;
import com.nexted.UserService.entity.User;
import com.nexted.UserService.exception.ResourceNotFoundException;
import com.nexted.UserService.external.services.HotelService;
import com.nexted.UserService.external.services.RatingService;
import com.nexted.UserService.repositories.UserRepository;
import com.nexted.UserService.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repository;
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private RatingService ratingService;

	@Override
	public User saveUser(User user) {

		String randomUserId = UUID.randomUUID().toString();
		user.setId(randomUserId);
		return repository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		List<User> userList = repository.findAll();
		if (userList.isEmpty()) {
			throw new ResourceNotFoundException("No Users found");
		}
		List<User> newUserList = userList.stream().map(user -> {
			List<Rating> ratingList = ratingService.getAllRating(user.getId());
			List<Rating> newRatingList =  ratingList.stream().map(rating -> {
				Hotel hotel = hotelService.getHotel(rating.getHotelId());
				rating.setHotel(hotel);
				return rating;
			}).collect(Collectors.toList());
			user.setRatings(newRatingList);
			return user;
		}).collect(Collectors.toList());

		return newUserList;
	}

	@Override
	public User getUser(String id) {
		User user = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with the given id : " + id + " not found"));

//		Rating[] ratingArr = restTemplate
//				.getForObject("http://RATINGSERVICE/rating/get-rating-by-user-id/" + user.getId(), Rating[].class);

		List<Rating> userRatings = ratingService.getAllRating(user.getId());
//		List<Rating> userRatings = Arrays.stream(ratingArr).toList();
		userRatings.stream().map(rating -> {
//			System.out.println(rating.getHotelId());
//			ResponseEntity<Hotel> forObject = restTemplate
//					.getForEntity("http://HOTELSERVICE/hotels/get-hotel-by-id/" + rating.getHotelId(), Hotel.class);
			hotelService.getHotel(rating.getHotelId());
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			rating.setHotel(hotel);
			return new Rating();
		}).collect(Collectors.toList());

		user.setRatings(userRatings);
		return user;
	}

	@Override
	public void deleteUserById(String id) {
		Optional<User> user = repository.findById(id);
		if (user.isPresent()) {
			repository.deleteById(id);
		} else {
			throw new ResourceNotFoundException("User with the given id : " + id + " not found..");
		}

	}

	@Override
	public User updateUser(String id, User user) {
		User userDb = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with the given id : " + id + " not found.."));

		if (user.getName() != null) {
			userDb.setName(user.getName());
		}
		if (user.getEmail() != null) {
			userDb.setEmail(user.getEmail());
		}
		if (user.getAbout() != null) {
			userDb.setAbout(user.getAbout());
		}

		return repository.save(userDb);
	}

	@Override
	public void deleteRatingById(String ratingId, String userId) {
		User user = repository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User with the given id : " + userId + " not found.."));

		List<Rating> ratingList = user.getRatings();
		Rating rating = ratingService.getRating(ratingId);

		if (rating != null) {
			if (ratingList.contains(rating)) {
				ratingList.remove(rating);
			}
		} else {
			throw new ResourceNotFoundException("Rating with the given id : " + ratingId + " not found..");
		}

		user.setRatings(ratingList);
		ratingService.deleteRatingById(ratingId);
		repository.save(user);
	}

	@Override
	public void deleteAllRatingByUser(String userId) {
		User user = repository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User with the given id : " + userId + " not found.."));
		List<Rating> ratingList = user.getRatings();
		user.setRatings(null);
		ratingService.deleteAllRatingsByUser(userId);
		repository.save(user);

	}

}

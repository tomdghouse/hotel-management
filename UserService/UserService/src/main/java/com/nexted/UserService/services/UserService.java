package com.nexted.UserService.services;

import java.util.List;

import com.nexted.UserService.entity.User;

public interface UserService {

	User saveUser(User user);

	List<User> getAllUsers();

	User getUser(String id);

	// TODO: delete

	void deleteUserById(String id);

	// TODO: update

	User updateUser(String id, User user);

	void deleteRatingById(String ratingId, String userId);

	void deleteAllRatingByUser(String userId);

}

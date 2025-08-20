package com.nexted.Hotelservice.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.nexted.Hotelservice.entity.Hotel;
import com.nexted.Hotelservice.exception.ResourceNotFoundException;
import com.nexted.Hotelservice.repository.HotelRepository;
import com.nexted.Hotelservice.service.HotelService;

@Repository
public class HotelServiceImpl implements HotelService {
	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Hotel createHotel(Hotel hotel) {
		String randomId  = UUID.randomUUID().toString();
		hotel.setId(randomId);
		Hotel hoteldb = hotelRepository.save(hotel);
		return hoteldb;
	}

	@Override
	public List<Hotel> getAllHotels() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel getHotel(String id) {
		return hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel with given id not found "+id));
	}

}

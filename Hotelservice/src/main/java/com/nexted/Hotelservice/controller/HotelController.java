package com.nexted.Hotelservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexted.Hotelservice.entity.Hotel;
import com.nexted.Hotelservice.service.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;

	@PostMapping("/create-hotel")
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
		Hotel hoteldb = hotelService.createHotel(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(hoteldb);
	}

	@GetMapping("/get-hotel-by-id/{id}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable String id) {
		Hotel hotel = hotelService.getHotel(id);
		return ResponseEntity.status(HttpStatus.OK).body(hotel);
	}
	
	@GetMapping("/get-all-hotels")
	public ResponseEntity<List<Hotel>> getAllHotels() {
		List<Hotel> hotelList = hotelService.getAllHotels();
		return ResponseEntity.status(HttpStatus.OK).body(hotelList);
	}

}

package com.nexted.Hotelservice.service;

import java.util.List;

import com.nexted.Hotelservice.entity.Hotel;

public interface HotelService {
	
	Hotel createHotel(Hotel hotel);
	
	List<Hotel> getAllHotels();
	
	Hotel getHotel(String id);

}

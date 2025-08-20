package com.nexted.Hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexted.Hotelservice.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String>{

}

package com.nexted.UserService.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nexted.UserService.entity.Hotel;

@FeignClient(name = "HOTELSERVICE")
public interface HotelService {

	@GetMapping("/hotels/get-hotel-by-id/{hotelId}")
	Hotel getHotel(@PathVariable String hotelId);

}

package com.nexted.Hotelservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hotels")
@Entity
public class Hotel {
	
	@Id
	private String id;
	private String name;
	private String location;
	private String about;

}

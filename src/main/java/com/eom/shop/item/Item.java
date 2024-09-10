package com.eom.shop.item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Setter
@Getter
public class Item {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	private String title;
	private Integer price;
	private String imageUrl;


}



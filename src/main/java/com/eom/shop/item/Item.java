package com.eom.shop.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@ToString
@Setter
@Getter
public class Item {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	private String title;
	private Integer price;

}



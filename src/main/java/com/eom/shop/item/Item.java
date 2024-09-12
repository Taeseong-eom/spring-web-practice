package com.eom.shop.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Setter
@Getter
@Table(indexes = @Index(columnList = "title",name = "titleIndex"))
public class Item {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	private String title;
	private Integer price;
	private String imageUrl;


}



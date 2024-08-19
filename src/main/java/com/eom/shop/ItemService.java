package com.eom.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepository itemRepository;
	public void saveItem(String title, Integer price){
		Item item = new Item();
		item.setTitle(title);
		item.setPrice(price);
		itemRepository.save(item);
	}

	public List findItem(){
		List<Item> result = itemRepository.findAll();
		return result;
	}

	public Optional<Item> findById(Long id){
		Optional<Item> result = itemRepository.findById(id);
		return result;
	}
}

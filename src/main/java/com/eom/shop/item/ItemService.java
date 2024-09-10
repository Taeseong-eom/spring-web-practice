package com.eom.shop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepository itemRepository;
	public void saveItem(String title, Integer price, String imageUrl){
		Item item = new Item();
		item.setTitle(title);
		item.setPrice(price);
		item.setImageUrl(imageUrl);
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

	public void editItem(Item item){
		if(100 <= item.getTitle().length()){
			throw new IllegalArgumentException("제목이 100자 이상임. 다시 적으셈");
		}
		itemRepository.save(item);
	}
}

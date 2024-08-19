package com.eom.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {
	private final ItemRepository itemRepository;
	private final ItemService itemService;

	@GetMapping("/list")
	String list(Model model) {

		List<Item> result = itemService.findItem();

		model.addAttribute("items", result);
		var a = new Item();
		System.out.println(a.toString());
		return "list.html";
	}

	@GetMapping("/write")
	String write() {
		return "write.html";
	}

	@PostMapping("/add")
	String addPost(@RequestParam String title,
	               @RequestParam Integer price) {
		itemService.saveItem(title, price);
		return "redirect:/list";
	}

	@GetMapping("/detail/{id}")
	String detail(@PathVariable Long id, Model model) {

		Optional<Item> result = itemService.findById(id);

		if (result.isPresent()) {
			Item item = result.get();
			model.addAttribute("item", item);
			return "detail.html";
		} else {
			return "redirect:/list";
		}
	}


}

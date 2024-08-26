package com.eom.shop.item;

import lombok.RequiredArgsConstructor;
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

		List<Item> result = itemService.findItem(); // DB에서 데이터 가져옴

		model.addAttribute("items", result); // 가져온 정보 전달
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

	@GetMapping("/edit/{id}")
	String edit(@PathVariable Long id, Model model) {

		Optional<Item> result = itemService.findById(id);

		if (result.isPresent()) {
			model.addAttribute("item", result.get());
			return "edit.html";
		} else {
			return "redirect:/list";
		}
	}

	@PostMapping("/edit/save/{id}")
	String editItem(@PathVariable Long id, @ModelAttribute Item item){
		itemService.editItem(item);
		return "redirect:/list";
	}

	@GetMapping("/test1")
	String test1(@RequestParam String name){
		System.out.println(name);
		return "redirect:/list";
	}

	@DeleteMapping("/item")
	ResponseEntity<String> delateItem(@RequestParam Long id){
		itemRepository.deleteById(id);
		return ResponseEntity.status(200).body("삭제완료");
	}
}

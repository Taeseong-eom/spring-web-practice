package com.eom.shop.item;

import com.eom.shop.comment.Comment;
import com.eom.shop.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	private final S3Service s3Service;
	private final CommentRepository commentRepository;

	@GetMapping("/list")
	String list(Model model) {
		return "redirect:/list/page/1";
	}

	@GetMapping("/write")
	String write() {
		return "write.html";
	}

	@PostMapping("/add")
	String addPost(@RequestParam String title,
	               @RequestParam Integer price,
	               @RequestParam String imageUrl) {
		itemService.saveItem(title, price, imageUrl);
		return "redirect:/list";
	}

	@GetMapping("/detail/{id}")
	String detail(@PathVariable Long id, Model model) {

		List<Comment> res = commentRepository.findAllByParentId(id);
		Optional<Item> result = itemService.findById(id);

		if (result.isPresent()) {
			Item item = result.get();
			model.addAttribute("item", item);
			model.addAttribute("comments", res);
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
	ResponseEntity<String> deleteItem(@RequestParam Long id){
		itemRepository.deleteById(id);
		return ResponseEntity.status(200).body("삭제완료");
	}

	@GetMapping("/list/page/{num}")
	String getListPage(Model model, @PathVariable Integer num) {

		Page<Item> result = itemRepository.findPageBy(PageRequest.of(num-1, 5));
		System.out.println(result.getTotalPages());
		model.addAttribute("items", result); // 가져온 정보 전달
		model.addAttribute("currentPage", num); // 현재 페이지 번호 전달
		model.addAttribute("totalPages", result.getTotalPages()); // 전체 페이지 수 전달
		return "list.html";
	}

	@GetMapping("/presigned-url")
	@ResponseBody
	String getURL(@RequestParam String filename){
		var result = s3Service.createPresignedUrl("test/" + filename);
		return result;
	}
	@PostMapping("/search")
	@ResponseBody
	String postSearch(@RequestParam String searchText){
		var result = itemRepository.findAllByTitleContains(searchText);
		System.out.println(result);
		return "list.html";
	}
}

package com.eom.shop.notice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class noticeController {
	private final noticeRepository noticeRepository;
	@GetMapping("/notice")
	String list(Model model){
		List<notice> result = noticeRepository.findAll();
		model.addAttribute("notice", result);
		return "notice.html";
	}
}

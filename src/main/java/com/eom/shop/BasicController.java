package com.eom.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

import static java.time.LocalDate.now;

@Controller
public class BasicController {
	@GetMapping("/")
	String hello(){
		return "redirect:/index.html";
	}

	@GetMapping("/about")
	@ResponseBody
	String about(){
		return "엄";
	}

	@GetMapping("/mypage")
	@ResponseBody
	String mypage(){
		return "엄태성";
	}

	@GetMapping("/date")
	@ResponseBody
	String  date(){
		return LocalDate.now().toString();
	}

}

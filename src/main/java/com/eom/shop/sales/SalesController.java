package com.eom.shop.sales;

import com.eom.shop.member.CustomUser;
import com.eom.shop.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SalesController {
	private final SalesRepository salesRepository;

	@PostMapping("/order")
	String postOrder(@RequestParam String title,
	                 @RequestParam Integer price,
	                 @RequestParam Integer count,
	                 Authentication auth) {
		Sales sales = new Sales();
		sales.setCount(count);
		sales.setPrice(price);
		sales.setItemName(title);
		CustomUser user = (CustomUser) auth.getPrincipal();
		var member = new Member();
		member.setId(user.id);
		sales.setMember(member);

		return "list.html";
	}

	@GetMapping("/order/all")
	String getOrderAll(){
		List<Sales> result = salesRepository.customerFindAll();
		System.out.println(result);

		return "list.html";
	}
}

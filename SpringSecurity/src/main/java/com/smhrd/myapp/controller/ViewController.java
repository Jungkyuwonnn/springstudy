package com.smhrd.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smhrd.myapp.dto.MemberDTO;
import com.smhrd.myapp.entity.Member;

@Controller
// @RequestMapping("/view") --> class 단위로 url mapping 부여 가능
public class ViewController {

	@GetMapping("/")
	public String login() {
		return "login";
	}
	
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@GetMapping("/admin")
	// @PreAuthorize("hasAnyRole('admin')") -> 이런 형태로도 할 수 있다....
	public String admin() {
		return "admin";
	}
	
	@GetMapping("/user")
	public String user(MemberDTO vo) {
		return "user";
	}
	@GetMapping("/dashboard")
	public String dashboard() {
		return "dashboard";
	}
	
}

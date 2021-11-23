package com.care.root.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {
	@GetMapping("/")
	public String rootTest() {
		return "Hello Boot";
	}
}

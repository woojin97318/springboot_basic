package com.care.root.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommonController {
	@Autowired HttpSession session;
	
	@GetMapping("/")
	public String index(Model model) {
		session.setAttribute("user", "session value");
		model.addAttribute("user", "model value");
		return "index";
	}
	@GetMapping("object")
	public String object(Model model) {
		TestDTO dto01 = new TestDTO();
		TestDTO dto02 = new TestDTO();
		dto01.setName("홍길동-01"); dto01.setAddr("산골짜기-01");
		dto02.setName("홍길동-02"); dto02.setAddr("산골짜기-02");
		
		session.setAttribute("user", dto01);
		model.addAttribute("user", dto02);
		
		String message = "<script>alert('문제발생');</script>";
		message += "<h1>코드 추가</h1>";
		model.addAttribute("msg", message);
		
		return "object";
	}
	@RequestMapping("operator/{num}")
	public ModelAndView operator(@PathVariable int num, ModelAndView mav) {
	   mav.setViewName("operator");
	   List<String> arr = new ArrayList<String>();
	   arr.add("홍길동");
	   arr.add("김개똥");
	   arr.add("고길동");
	   mav.addObject("arr",arr);
	   mav.addObject("num",num);
	   mav.addObject("name","김말이");
	   return mav;
	}
}

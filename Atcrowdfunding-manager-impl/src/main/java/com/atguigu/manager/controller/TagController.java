package com.atguigu.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tag")
public class TagController {

	@RequestMapping("/index")
	public String param() {
		return "tag/index";
	}	  
}

package com.atguigu.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/type")
public class TypeController {

	@RequestMapping("/index")
	public String param() {
		return "type/index";
	}	  
}

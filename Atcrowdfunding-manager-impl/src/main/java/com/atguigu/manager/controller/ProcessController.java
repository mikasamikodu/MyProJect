package com.atguigu.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/process")
public class ProcessController {

	@RequestMapping("/index")
	public String param() {
		return "process/index";
	}	  
}

package com.cafe24.jblog2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//pathvariable 정규 표현식
// -> 핸들러 매핑 x, 클래스 매핑
@Controller
public class MainController {

	@RequestMapping({"/","/main"})
	public String main() {
		return "/main/index";
	}
}

package com.cafe24.jblog2.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.jblog2.service.UserService;
import com.cafe24.mysite.dto.JsonResult;

@Controller("userAPIController")
@RequestMapping("/user/api")
public class UserController {

	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/checkId")
	public JsonResult checkEmail(
			@RequestParam(value="id",required=true, defaultValue="") String id) {
		
		Boolean exist = userService.existId(id);
		
		return JsonResult.success(exist);
	}
}

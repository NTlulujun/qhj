package com.project.dms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.dms.entiy.User;
import com.project.dms.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/index")
	@ResponseBody
    public String index() {
        return "Hello World";
    }
	
	@RequestMapping("/user/getName")  
	@ResponseBody
    public String get(User user) {  
		String id="001";
		 User u=userService.getNameById(id);
		 
        return u.getName();     
    }
	
	@RequestMapping("/user/list")
    public String  listUser(Model model) {
        List<User> userList = new ArrayList<User>();
        userList=userService.getUserList();
        
        model.addAttribute("users", userList);
        return "user/list";
    }
}

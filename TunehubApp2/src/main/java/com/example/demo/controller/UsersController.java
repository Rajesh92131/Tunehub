package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Songs;
import com.example.demo.entities.Users;
import com.example.demo.services.SongsService;
import com.example.demo.services.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {
	
	@Autowired
	SongsService sserv;
	
	@Autowired
	UsersService userv;
	
	
	
	@PostMapping("/register")
	public String addUser(@ModelAttribute Users user) {
		
		boolean userStatus=userv.emailExists(user.getEmail());
				if(userStatus==false) {
					userv.addUser(user);
					System.out.println("User added");
					return "registersuccess";
				}
				else 
					System.out.println("User already exist");
		
		         return "registerfail";
	}
	
	@PostMapping("/login")
	public String validateUser(@RequestParam String email,@RequestParam String password
			                    , HttpSession session)
	{
		boolean loginStatus=userv.validateUser(email, password);
		String role=userv.findRole(email);
		if(loginStatus==true) {
			
			session.setAttribute("email", email);
			
			if(role.equals("admin"))
			return "adminhome";
			else
		    return "customerhome";
		}
		else
		return "loginfail";
	}
	
	@GetMapping("/viewallmembersdetails")
	public String viewAllMembersDetails(Model model) {
		
		
		List<Users> users=userv.fetchAllUsers();
		model.addAttribute("users",users);
		return "displayallmembersdetails";
		
		
	}
	
	@GetMapping("/exploresongs")
	public String exploreSongs(HttpSession session,Model model) {
		
		String email=(String) session.getAttribute("email");

		Users user=userv.getUser(email);
		
		boolean userStatus=user.isPremium();
		
		if(userStatus==true) {
			

			List<Songs> song=sserv.fetchAllSongs();
			model.addAttribute("songs",song);
			return "displaysongs";
			
		}
		else
		return "payment";
	}
	

}

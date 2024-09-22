package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {


	@GetMapping("/map-register")
	public String registerUser() {
		
		return "register";
	}
	

	@GetMapping("/map-login")
	public String loginUser() {
		
		return "login";
	}
	@GetMapping("/map-songs")
	public String mapSongs() {
		
		return "addsongs";
	}
	@GetMapping("/paymentstatus")
	public String paymentstatus() {
		
		return "paymentstatus";
	}
	@GetMapping("/samplepayment")
	public String samplePayment() {
		
		return "samplepayment";
	}
	
}

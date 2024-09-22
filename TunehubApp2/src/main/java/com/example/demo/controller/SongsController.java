package com.example.demo.controller;

import java.awt.Dialog.ModalExclusionType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.entities.Songs;
import com.example.demo.services.SongsService;

@Controller
public class SongsController {

	@Autowired
	SongsService sserv;
	
	@PostMapping("/addsongs")
	public String addSongs(@ModelAttribute Songs song) {
		
		Boolean songStatus=sserv.songExists(song.getName());
		if(songStatus==false) {
			
			sserv.addSongs(song);	
			return "songsuccess";
		}
		else
			return "songfail";
	}
	
	@GetMapping("/viewsongs")
	public String viewSongs(Model model) {
		
		List<Songs> song=sserv.fetchAllSongs();
		model.addAttribute("songs",song);
		return "displaysongs";
	}
	@GetMapping("/viewcustomersongs")
	public String viewcustomersongs(Model model) {
		
		boolean primeStatus=true;
		if(primeStatus==true) {
			
			List<Songs> song=sserv.fetchAllSongs();
			model.addAttribute("songs",song);
			return "displaysongs";
		}
		else
		{
			return "makepayment";
		}
	}
	

}

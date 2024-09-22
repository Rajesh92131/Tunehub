package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Playlist;
import com.example.demo.entities.Songs;
import com.example.demo.services.PlaylistService;
import com.example.demo.services.SongsService;

@Controller
public class PlaylistController {
	@Autowired
	PlaylistService pserv;
	
	@Autowired
	SongsService sserv;
	
	@GetMapping("/createplaylist")
	public String createPlaylist(Model model) {
		
		//fetching all songs using son service
		List<Songs> songslist=sserv.fetchAllSongs();
		
		//adding songs to model
		model.addAttribute("songslist", songslist);
		
		//sending createplaylists
		return "createplaylist";
		
	}

	@PostMapping("/addplaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist){
		
		pserv.addPlaylist(playlist);
		
		List<Songs> songsList=playlist.getSongs();
		for(Songs song:songsList)
		{
			song.getPlaylist().add(playlist);
			sserv.updateSong(song);
		}
		
		return "playlistsuccess";
	}
	
	@GetMapping("/viewplaylist")
	public String viewPlaylist(Model model) {
		
		List<Playlist> plist=pserv.fetchPlaylist();
		
		model.addAttribute("plist",plist);
		return "viewplaylist";
	}
	
}

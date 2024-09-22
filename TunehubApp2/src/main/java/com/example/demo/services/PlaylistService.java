package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Playlist;
import com.example.demo.entities.Songs;

public interface PlaylistService {

	void addPlaylist(Playlist playlist);

	List<Playlist> fetchPlaylist();
	
//	public List<Songs> createPlaylist(Songs song);

}

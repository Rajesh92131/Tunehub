package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Songs;
import com.example.demo.entities.Users;
import com.example.demo.repositories.UsersRepository;

@Service
public class UsersServiceImplementation implements UsersService{
    
	@Autowired
	UsersRepository urepo;
	
	@Override
	public String addUser(Users user) {
		
		urepo.save(user);
		return "User is created and saved";
	}

	@Override
	public boolean emailExists(String email) {
       
		if(urepo.findByEmail(email)==null) {
			return false;
		}
		else
		return true;
	}

	@Override
	public boolean validateUser(String email, String password) {
		
		 Users  user=urepo.findByEmail(email);
		 String db_password=user.getPassword();
		 if(db_password.equals(password)) {
			 return true;
		 }
		 else
		     return false;
	}

	@Override
	public String findRole(String email) {
		Users  user=urepo.findByEmail(email);
		 String role=user.getRole();
		return role;
	}

	@Override
	public List<Users> fetchAllUsers() {
		List<Users> users=urepo.findAll();
		return users;
	}

	@Override
	public Users getUser(String email) {
		
		return urepo.findByEmail(email);
	}

	@Override
	public void updateUser(Users user) {
	
		 urepo.save(user);
		
	}

}

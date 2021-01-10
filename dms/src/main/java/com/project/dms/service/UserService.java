package com.project.dms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.dms.entiy.User;

@Service
public interface  UserService {

	public User getNameById(String id);
	 
	public List<User> getUserList();
}

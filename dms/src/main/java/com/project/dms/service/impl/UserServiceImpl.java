package com.project.dms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dms.dao.UserMapper;
import com.project.dms.entiy.User;
import com.project.dms.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public User getNameById(String id) {
		// TODO Auto-generated method stub
		User user=userMapper.selectByPrimaryKey(id);
		return user;
	}

	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		List<User> users=new ArrayList<User>();
		users=userMapper.selectAll();
		return users;
	}

	
}

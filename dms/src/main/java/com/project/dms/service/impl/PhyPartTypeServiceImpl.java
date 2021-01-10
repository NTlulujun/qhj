package com.project.dms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dms.dao.PhyPartTypeMapper;
import com.project.dms.entiy.PhyPartType;
import com.project.dms.service.PhyPartTypeService;

@Service
public class PhyPartTypeServiceImpl implements PhyPartTypeService {
	@Autowired
	private PhyPartTypeMapper phyPartTypeMapper;

	@Override
	public PhyPartType getPhyPartTypeById(Integer id) {
		// TODO Auto-generated method stub
		PhyPartType user=phyPartTypeMapper.selectByPrimaryKey(id);
		return user;
	}

	@Override
	public List<PhyPartType> getPhyPartTypeList() {
		// TODO Auto-generated method stub
		List<PhyPartType> users=new ArrayList<PhyPartType>();
		users=phyPartTypeMapper.selectAll();
		return users;
	}

	
}

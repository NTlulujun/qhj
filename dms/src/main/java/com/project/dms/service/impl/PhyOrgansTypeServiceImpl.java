package com.project.dms.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dms.dao.PhyOrgansTypeMapper;
import com.project.dms.entiy.PhyOrgansType;
import com.project.dms.service.PhyOrgansTypeService;

@Service
public class PhyOrgansTypeServiceImpl implements PhyOrgansTypeService {
	@Autowired
	private PhyOrgansTypeMapper phyOrgansTypeMapper;

	@Override
	public List<PhyOrgansType> getPhyOrgansTypeList() {
		// TODO Auto-generated method stub
		List<PhyOrgansType> phyOrgansTypeList=phyOrgansTypeMapper.selectAll();
		return phyOrgansTypeList;
	}

	
	
}

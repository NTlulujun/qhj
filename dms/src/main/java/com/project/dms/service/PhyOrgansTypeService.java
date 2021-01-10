package com.project.dms.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.project.dms.entiy.PhyOrgansType;


@Service
public interface  PhyOrgansTypeService {
	
	public List<PhyOrgansType> getPhyOrgansTypeList();
}

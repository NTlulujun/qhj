package com.project.dms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.dms.entiy.PhyPartType;

@Service
public interface  PhyPartTypeService {

	public PhyPartType getPhyPartTypeById(Integer id);
	 
	public List<PhyPartType> getPhyPartTypeList();
}

package com.project.dms.service;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.project.dms.entiy.PhySymptomsType;


@Service
public interface  PhySymptomsTypeService {
	
	public List<Map<String,Object>> getPhySymptomsTypeList(String keyWord);
	
	public List<Map<String,Object>> getCheckedPhySymptomsTypeList(String ids);
	
	public PhySymptomsType getPhySymptomsTypeById(int id);
}

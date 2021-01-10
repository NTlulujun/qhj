package com.project.dms.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dms.dao.PhySymptomsTypeMapper;
import com.project.dms.entiy.PhySymptomsType;
import com.project.dms.service.PhySymptomsTypeService;

@Service
public class PhySymptomsTypeServiceImpl implements PhySymptomsTypeService {
	@Autowired
	private PhySymptomsTypeMapper phySymptomsTypeMapper;

	@Override
	public List<Map<String,Object>> getPhySymptomsTypeList(String keyWord) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> phySymptomsTypes = phySymptomsTypeMapper.selectByKeyWord(keyWord);
		return phySymptomsTypes;
	}

	@Override
	public PhySymptomsType getPhySymptomsTypeById(int id) {
		// TODO Auto-generated method stub
		PhySymptomsType phySymptomsType=phySymptomsTypeMapper.selectByPrimaryKey(id);
		return phySymptomsType;
	}

	@Override
	public List<Map<String, Object>> getCheckedPhySymptomsTypeList(String ids) {
		// TODO Auto-generated method stub
		return this.phySymptomsTypeMapper.selectByIds(ids);
	}

	
}

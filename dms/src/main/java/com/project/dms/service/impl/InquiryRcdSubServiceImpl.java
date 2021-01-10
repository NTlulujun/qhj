package com.project.dms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dms.dao.InquiryRcdSubMapper;
import com.project.dms.entiy.InquiryRcdSub;
import com.project.dms.service.InquiryRcdSubService;

@Service
public class InquiryRcdSubServiceImpl implements InquiryRcdSubService {
	@Autowired
	private InquiryRcdSubMapper inquiryRcdSubMapper;

	@Override
	public InquiryRcdSub getInquiryRcdSubById(Integer id) {
		// TODO Auto-generated method stub
		InquiryRcdSub user=inquiryRcdSubMapper.selectByPrimaryKey(id);
		return user;
	}

	@Override
	public List<InquiryRcdSub> getInquiryRcdSubList() {
		// TODO Auto-generated method stub
		List<InquiryRcdSub> users=new ArrayList<InquiryRcdSub>();
		users=inquiryRcdSubMapper.selectAll();
		return users;
	}

	@Override
	public int saveInquiryRcdSub(InquiryRcdSub InquiryRcdSub) {
		// TODO Auto-generated method stub
		inquiryRcdSubMapper.insert(InquiryRcdSub);
		int conditionId=InquiryRcdSub.getConditionId();
		return conditionId;
	}

	@Override
	public List<InquiryRcdSub> getInquiryRcdSubListByInquiryId(Integer inquiryId) {
		// TODO Auto-generated method stub
		List<InquiryRcdSub> inquiryRcdSubs=inquiryRcdSubMapper.selectByInquiryId(inquiryId);
		return inquiryRcdSubs;
	}

	@Override
	public InquiryRcdSub getInquiryRcdSubByMainidAndSymptomsSeq(Integer inquiryId,
			Integer symptomsSeq) {
		// TODO Auto-generated method stub
		List<InquiryRcdSub> inquiryRcdSubs=inquiryRcdSubMapper.selectInquiryRcdSubByMainidAndSymptomsSeq(inquiryId, symptomsSeq);
		InquiryRcdSub inquiryRcdSub=new InquiryRcdSub();
		if(inquiryRcdSubs!=null&&inquiryRcdSubs.size()>0){
			inquiryRcdSub=inquiryRcdSubs.get(0);
		}
		return inquiryRcdSub;
	}

	@Override
	public int updateInquiryRcdSub(InquiryRcdSub InquiryRcdSub) {
		// TODO Auto-generated method stub
		int result=inquiryRcdSubMapper.updateByPrimaryKey(InquiryRcdSub);
		return result;
	}

	
}

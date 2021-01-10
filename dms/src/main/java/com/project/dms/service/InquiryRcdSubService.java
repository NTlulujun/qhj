package com.project.dms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.dms.entiy.InquiryRcdSub;

@Service
public interface  InquiryRcdSubService {

	public InquiryRcdSub getInquiryRcdSubById(Integer id);
	 
	public List<InquiryRcdSub> getInquiryRcdSubList();
	
	public int saveInquiryRcdSub(InquiryRcdSub InquiryRcdSub);
	
	public List<InquiryRcdSub> getInquiryRcdSubListByInquiryId(Integer inquiryId);
	
	/**
	 * 根据客户问诊记录主表id和身体症状类别id查询客户问诊子记录
	 * @param mainId
	 * @param symptomsSeq
	 * @return
	 */
	public InquiryRcdSub getInquiryRcdSubByMainidAndSymptomsSeq(Integer inquiryId,Integer symptomsSeq);
	
	public int updateInquiryRcdSub(InquiryRcdSub InquiryRcdSub);
}

package com.project.dms.service;



import org.springframework.stereotype.Service;

import com.project.dms.entiy.InquiryRcdCondition;
import com.project.dms.entiy.InquiryRcdMain;
import com.project.dms.entiy.PerSysSub;
import com.project.dms.entiy.PhyConditionDrug;
import com.project.dms.entiy.PhyConditionList;
import com.project.dms.entiy.PhyConditionType;


@Service
public interface  SaveDataService {

	/**
	 * 保存客户问诊记录主表
	 * @return
	 */
	public int saveInquiryRcdMain(InquiryRcdMain inquiryRcdMain);
	
	/**
	 * 保存客户问诊记录调理子表
	 * @param inquiryRcdCondition
	 * @return
	 */
	public int saveInquiryRcdCondition(InquiryRcdCondition inquiryRcdCondition);
	
	/**
	 * 保存调理套餐主表
	 * @param phyConditionType
	 * @return
	 */
	public int savePhyConditionType(PhyConditionType phyConditionType);
	
	/**
	 * 保存调理套餐子表
	 * @param phyConditionList
	 * @return
	 */
	public int savePhyConditionList(PhyConditionList phyConditionList);
	/**
	 * 保存问诊药方明细表
	 * @param phyConditionList
	 * @return
	 */
	public int savePhyConditionDrug(PhyConditionDrug phyConditionDrug);
	
	public void editPhyConditionType(PhyConditionType phyConditionType);
	/**
	 * 保存会员
	 * @param perSysSub
	 * @return
	 */
	public int savePerSysSub(PerSysSub perSysSub);
}

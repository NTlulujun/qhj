package com.project.dms.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dms.dao.InquiryRcdConditionMapper;
import com.project.dms.dao.InquiryRcdMainMapper;
import com.project.dms.dao.PerSysSubMapper;
import com.project.dms.dao.PhyConditionDrugMapper;
import com.project.dms.dao.PhyConditionListMapper;
import com.project.dms.dao.PhyConditionTypeMapper;
import com.project.dms.dao.PhyPartTypeMapper;
import com.project.dms.entiy.InquiryRcdCondition;
import com.project.dms.entiy.InquiryRcdMain;
import com.project.dms.entiy.PerSysSub;
import com.project.dms.entiy.PhyConditionDrug;
import com.project.dms.entiy.PhyConditionList;
import com.project.dms.entiy.PhyConditionType;
import com.project.dms.service.SaveDataService;

@Service
public class SaveDataServiceImpl implements SaveDataService {
	@Autowired
	private InquiryRcdMainMapper inquiryRcdMainMapper;
	@Autowired
	private InquiryRcdConditionMapper inquiryRcdConditionMapper;
	@Autowired
	private PhyConditionTypeMapper phyConditionTypeMapper;
	@Autowired
	private PhyConditionListMapper phyConditionListMapper;
	@Autowired
	private PhyConditionDrugMapper phyConditionDrugMapper;
	@Autowired
	private PerSysSubMapper perSysSubMapper;
	
	@Override
	public int saveInquiryRcdMain(InquiryRcdMain inquiryRcdMain) {
		// TODO Auto-generated method stub
		int row=inquiryRcdMainMapper.insert(inquiryRcdMain);
		System.out.println("saveInquiryRcdMain-row:"+row);
		int InquiryId=inquiryRcdMain.getInquiryId();
		System.out.println("saveInquiryRcdMain-InquiryId:"+InquiryId);
		return InquiryId;
	}

	@Override
	public int saveInquiryRcdCondition(InquiryRcdCondition inquiryRcdCondition) {
		// TODO Auto-generated method stub
		int row=inquiryRcdConditionMapper.insert(inquiryRcdCondition);
		
		int conditionSubId=inquiryRcdCondition.getConditionSubId();
		
		return conditionSubId;
	}

	@Override
	public int savePhyConditionType(PhyConditionType phyConditionType) {
		// TODO Auto-generated method stub
		int row=phyConditionTypeMapper.insert(phyConditionType);
		int conditionIdx=phyConditionType.getConditionIdx();
		return conditionIdx;
	}

	@Override
	public int savePhyConditionList(PhyConditionList phyConditionList) {
		// TODO Auto-generated method stub
		int row=phyConditionListMapper.insert(phyConditionList);
		int conditionSubIdx=phyConditionList.getConditionSubIdx();
		return conditionSubIdx;
	}

	@Override
	public int savePhyConditionDrug(PhyConditionDrug phyConditionDrug) {
		// TODO Auto-generated method stub
		int row=phyConditionDrugMapper.insert(phyConditionDrug);
		int drugSeqId=phyConditionDrug.getDrugSeqId();
		return drugSeqId;
	}

	@Override
	public void editPhyConditionType(PhyConditionType phyConditionType) {
		// TODO Auto-generated method stub
		this.phyConditionTypeMapper.updateByPrimaryKey(phyConditionType);
	}

	@Override
	public int savePerSysSub(PerSysSub perSysSub) {
		// TODO Auto-generated method stub
		return this.perSysSubMapper.insert(perSysSub);
	}
	

	
}

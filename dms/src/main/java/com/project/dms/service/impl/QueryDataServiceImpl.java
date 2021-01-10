package com.project.dms.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dms.dao.DrugDefinedMainMapper;
import com.project.dms.dao.InquiryRcdConditionMapper;
import com.project.dms.dao.InquiryRcdMainMapper;
import com.project.dms.dao.PerSysListMapper;
import com.project.dms.dao.PerSysSubMapper;
import com.project.dms.dao.PhyConditionTypeMapper;
import com.project.dms.dao.PhyOrgansTypeMapper;
import com.project.dms.dao.PhyPartTypeMapper;
import com.project.dms.dao.PhySymptomsTypeMapper;
import com.project.dms.dao.PhyTongueTypeMapper;
import com.project.dms.entiy.DrugConditionDef;
import com.project.dms.entiy.DrugConditionList;
import com.project.dms.entiy.DrugDefinedMain;
import com.project.dms.entiy.InquiryRcdCondition;
import com.project.dms.entiy.InquiryRcdMain;
import com.project.dms.entiy.InquiryRcdSub;
import com.project.dms.entiy.PerSysList;
import com.project.dms.entiy.PerSysSub;
import com.project.dms.entiy.PhyConditionList;
import com.project.dms.entiy.PhyConditionType;
import com.project.dms.entiy.PhyOrgansType;
import com.project.dms.entiy.PhyPartType;
import com.project.dms.entiy.PhyTongueType;
import com.project.dms.service.QueryDataService;

@Service
public class QueryDataServiceImpl implements QueryDataService {
	@Autowired
	private PhyTongueTypeMapper phyTongueTypeMapper;
	@Autowired
	private PhyOrgansTypeMapper phyOrgansTypeMapper;
	@Autowired
	private PerSysListMapper perSysListMapper;
	@Autowired
	private PerSysSubMapper perSysSubMapper;
	@Autowired
	private InquiryRcdConditionMapper inquiryRcdConditionMapper;
	@Autowired
	private InquiryRcdMainMapper inquiryRcdMainMapper;
	@Autowired
	private PhyPartTypeMapper phyPartTypeMapper;
	@Autowired
	private DrugDefinedMainMapper drugDefinedMainMapper;
	@Autowired
	private PhyConditionTypeMapper phyConditionTypeMapper;
	
	
	@Override
	public List<PhyTongueType> queryPhyTongueTypes() {
		// TODO Auto-generated method stub
		List<PhyTongueType> phyTongueTypes=phyTongueTypeMapper.selectAll();
		return phyTongueTypes;
	}

	@Override
	public List<PhyOrgansType> queryPhyOrgansTypes() {
		// TODO Auto-generated method stub
		List<PhyOrgansType> phyOrgansTypes=phyOrgansTypeMapper.selectAll();
		return phyOrgansTypes;
	}

	@Override
	public PerSysList queryPerSysListByPerid(String per_id) {
		// TODO Auto-generated method stub
		PerSysList perSysList=null;
		List<PerSysList> list=perSysListMapper.selectByPerid(per_id);
		if(list!=null&&list.size()>0){
			perSysList=list.get(0);
		}
		return perSysList;
	}

	@Override
	public InquiryRcdCondition queryInquiryRcdConditionById(int conditionSubId) {
		// TODO Auto-generated method stub
		InquiryRcdCondition inquiryRcdCondition=new InquiryRcdCondition();
		inquiryRcdCondition = inquiryRcdConditionMapper.selectByPrimaryKey(conditionSubId);
		return inquiryRcdCondition;
	}

	@Override
	public InquiryRcdMain queryInquiryRcdMainById(int inquiryId) {
		// TODO Auto-generated method stub
		InquiryRcdMain inquiryRcdMain=new InquiryRcdMain();
		inquiryRcdMain = inquiryRcdMainMapper.selectByPrimaryKey(inquiryId);
		return inquiryRcdMain;
	}


	@Override
	public List<PhyPartType> queryPhyPartTypeAll() {
		// TODO Auto-generated method stub
		List<PhyPartType> phyPartTypes=phyPartTypeMapper.selectAll();
		return phyPartTypes;
	}

	@Override
	public List<PhyPartType> queryPhyPartTypeByPhyseqs(String phySeqs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DrugConditionDef queryDrugConditionDefByConditionList(
			PhyConditionList phyConditionList) {
		// TODO Auto-generated method stub
		DrugConditionDef drugConditionDef=new DrugConditionDef();
		return null;
	}

	@Override
	public List<DrugDefinedMain> queryDrugDefinedMainsByModel(
			DrugDefinedMain drugDefinedMain) {
		// TODO Auto-generated method stub
		List<DrugDefinedMain> drugDefinedMains=drugDefinedMainMapper.selectByModel(drugDefinedMain);
		return drugDefinedMains;
	}

	@Override
	public List<InquiryRcdMain> queryInquiryRcdMainListByUser(String key) {
		// TODO Auto-generated method stub
		List<InquiryRcdMain> inquiryRcdMains=inquiryRcdMainMapper.selectByKey(key);
		return inquiryRcdMains;
	}

	@Override
	public PhyConditionType queryPhyConditionTypeById(int conditionIdx) {
		// TODO Auto-generated method stub
		PhyConditionType phyConditionType=phyConditionTypeMapper.selectByPrimaryKey(conditionIdx);
		return phyConditionType;
	}

	@Override
	public List<Map<String, Object>> queryYaofangByPart(int symptomsSeq,
			int organsSeq, int tongueSeq, int seasonSeq, int yearType,
			int timeType, String parts) {
		// TODO Auto-generated method stub
		return drugDefinedMainMapper.selectYaofangByParts(symptomsSeq, organsSeq, tongueSeq, seasonSeq, yearType, timeType, parts);
	}
	@Override
	public List<Map<String, Object>> queryYaofangByPart2(int symptomsSeq,
			int organsSeq, int tongueSeq, int seasonSeq, int yearType,
			int timeType, String parts) {
		// TODO Auto-generated method stub
		return drugDefinedMainMapper.selectYaofangByParts2(symptomsSeq, organsSeq, tongueSeq, seasonSeq, yearType, timeType, parts);
	}

	@Override
	public PhyTongueType queryPhyTongueTypeById(int id) {
		// TODO Auto-generated method stub
		return phyTongueTypeMapper.selectByPrimaryKey(id);
	}

	@Override
	public PhyOrgansType queryPhyOrgansTypeById(int id) {
		// TODO Auto-generated method stub
		return phyOrgansTypeMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<PhyConditionType> getPhyConditionTypeRecordListByInquiryId(
			Integer inquiryId) {
		// TODO Auto-generated method stub
		List<PhyConditionType> phyConditionTypes=phyConditionTypeMapper.selectRecordByInquiryId(inquiryId);
		return phyConditionTypes;
	}

	@Override
	public List<Map<String, Object>> getRecordDetailByConditionIdx(
			int conditionIdx) {
		// TODO Auto-generated method stub
		return phyConditionTypeMapper.selectRecordDetailByConditionIdx(conditionIdx);
	}

	@Override
	public List<Map<String, Object>> getDrugDetailByConditionIdx(
			int conditionIdx) {
		// TODO Auto-generated method stub
		return phyConditionTypeMapper.selectDrugDetailByConditionIdx(conditionIdx);
	}

	@Override
	public PerSysSub queryPerSysSubByPerid(String per_id) {
		// TODO Auto-generated method stub
		PerSysSub perSysSub=null;
		List<PerSysSub> list=perSysSubMapper.selectByPerid(per_id);
		if(list!=null&&list.size()>0){
			perSysSub=list.get(0);
		}
		return perSysSub;
	}

	@Override
	public String lastPerId(String key) {
		// TODO Auto-generated method stub
		String lastPerId="";
		List<PerSysSub> list=perSysSubMapper.selectByKey(key);
		if(list!=null&&list.size()>0){
			PerSysSub lastPerSysSub=list.get(0);
			lastPerId=lastPerSysSub.getPerId();
		}
		return lastPerId;
	}

	@Override
	public PerSysSub queryPerSysSubByPeridOrPhone(String key) {
		// TODO Auto-generated method stub
		PerSysSub perSysSub=null;
		List<PerSysSub> list=perSysSubMapper.selectByPeridOrPhone(key);
		if(list!=null&&list.size()>0){
			perSysSub=list.get(0);
		}
		return perSysSub;
	}

	@Override
	public List<Map<String, Object>> getPicsByConditionIdx(int conditionIdx) {
		// TODO Auto-generated method stub
		return phyConditionTypeMapper.selectPicsByConditionIdx(conditionIdx);
	}

	
}

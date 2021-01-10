package com.project.dms.service;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.project.dms.entiy.DrugConditionDef;
import com.project.dms.entiy.DrugConditionList;
import com.project.dms.entiy.DrugDefinedMain;
import com.project.dms.entiy.InquiryRcdCondition;
import com.project.dms.entiy.InquiryRcdMain;
import com.project.dms.entiy.PerSysList;
import com.project.dms.entiy.PerSysSub;
import com.project.dms.entiy.PhyConditionList;
import com.project.dms.entiy.PhyConditionType;
import com.project.dms.entiy.PhyOrgansType;
import com.project.dms.entiy.PhyPartType;
import com.project.dms.entiy.PhyTongueType;


@Service
public interface  QueryDataService {

	/**
	 * 查询舌苔选项
	 * @return
	 */
	public List<PhyTongueType> queryPhyTongueTypes();
	
	public PhyTongueType queryPhyTongueTypeById(int id);
	
	/**
	 * 查询脏项选项
	 * @return
	 */
	public List<PhyOrgansType> queryPhyOrgansTypes();
	
	public PhyOrgansType queryPhyOrgansTypeById(int id);
	
	/**
	 * 根据员工号查询员工信息
	 * @return
	 */
	public PerSysList queryPerSysListByPerid(String per_id);
	/**
	 * 根据会员号查询会员信息
	 * @return
	 */
	public PerSysSub queryPerSysSubByPerid(String per_id);
	/**
	 * 根据会员号/手机号查询会员信息
	 * @return
	 */
	public PerSysSub queryPerSysSubByPeridOrPhone(String key);
	
	public String lastPerId(String key);
	
	public InquiryRcdCondition queryInquiryRcdConditionById(int conditionSubId);
	
	public InquiryRcdMain queryInquiryRcdMainById(int inquiryId);
	
	public DrugConditionDef queryDrugConditionDefByConditionList(PhyConditionList phyConditionList);
	
	public List<PhyPartType> queryPhyPartTypeAll();
	public List<PhyPartType> queryPhyPartTypeByPhyseqs(String phySeqs);
	
	public List<DrugDefinedMain> queryDrugDefinedMainsByModel(DrugDefinedMain drugDefinedMain);
	
	public List<InquiryRcdMain> queryInquiryRcdMainListByUser(String userid);
	
	public PhyConditionType queryPhyConditionTypeById(int conditionIdx);
	/**
	 * 根据勾选的部位查询药方（合并）
	 * @return
	 */
	List<Map<String,Object>> queryYaofangByPart(int symptomsSeq,int organsSeq,int tongueSeq,int seasonSeq,int yearType,int timeType,String parts);
	/**
	 * 根据勾选的部位查询药方（不合并）
	 * @return
	 */
	List<Map<String,Object>> queryYaofangByPart2(int symptomsSeq,int organsSeq,int tongueSeq,int seasonSeq,int yearType,int timeType,String parts);
	
	public List<PhyConditionType> getPhyConditionTypeRecordListByInquiryId(Integer inquiryId);
	
	List<Map<String,Object>> getRecordDetailByConditionIdx(int conditionIdx);
	
	List<Map<String,Object>> getDrugDetailByConditionIdx(int conditionIdx);
}

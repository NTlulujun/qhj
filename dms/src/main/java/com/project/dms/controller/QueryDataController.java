package com.project.dms.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.dms.entiy.DrugConditionList;
import com.project.dms.entiy.InquiryRcdCondition;
import com.project.dms.entiy.InquiryRcdMain;
import com.project.dms.entiy.InquiryRcdSub;
import com.project.dms.entiy.PerSysList;
import com.project.dms.entiy.PerSysSub;
import com.project.dms.entiy.PhyConditionType;
import com.project.dms.entiy.PhyOrgansType;
import com.project.dms.entiy.PhySymptomsType;
import com.project.dms.entiy.PhyTongueType;
import com.project.dms.service.InquiryRcdSubService;
import com.project.dms.service.PhySymptomsTypeService;
import com.project.dms.service.QueryDataService;
@Controller
public class QueryDataController {
	@Autowired
	private PhySymptomsTypeService phySymptomsTypeService;
	@Autowired
	private QueryDataService queryDataService;
	@Autowired
	private InquiryRcdSubService inquiryRcdSubService;
	
	
	/**
	 * 根据keyWord查询身体状况选项
	 * @param keyWord
	 * @return
	 */
	@RequestMapping("/queryData/queryUser")
	@ResponseBody
    public Map<String,Object>  queryUser(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> result=new HashMap<String, Object>();
		String keyWord=request.getParameter("keyWord");
		PerSysSub perSysSub=new PerSysSub();
		perSysSub=queryDataService.queryPerSysSubByPeridOrPhone(keyWord);
		result.put("perSysSub", perSysSub);
		return result;
	}
	/**
	 * 根据keyWord查询身体状况选项
	 * @param keyWord
	 * @return
	 */
	@RequestMapping("/phySymptomsType/list")
	@ResponseBody
    public Map<String,Object>  listPhySymptomsType(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> result=new HashMap<String, Object>();
		String keyWord=request.getParameter("keyWord");
		System.out.println("keyWord:"+keyWord);
		String checkedIds = request.getParameter("checkedIds");
		System.out.println("checkedIds:"+checkedIds);
		String ids="";
		   if(!StringUtils.isEmpty(checkedIds)){
			   String[] idsArr = checkedIds.split(",");
			   
			   for(int i=0;i<idsArr.length;i++){
				   String id=idsArr[i];
				   ids=ids+",'"+id+"'";
			   }
		   }
		   if(ids.length()>0){
			   ids=ids.substring(1);
		   }else{
			   ids="''";
		   }
		
		
        List<Map<String,Object>> phySymptomsTypeList = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> checkedPhySymptomsTypeList = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> phySymptomsTypeList_last = new ArrayList<Map<String,Object>>();
        phySymptomsTypeList=phySymptomsTypeService.getPhySymptomsTypeList(keyWord);
        checkedPhySymptomsTypeList=phySymptomsTypeService.getCheckedPhySymptomsTypeList(ids);
        if(phySymptomsTypeList!=null&&phySymptomsTypeList.size()>0){//选项不为空
        	if(checkedPhySymptomsTypeList!=null&&checkedPhySymptomsTypeList.size()>0){//选中的也不为空
        		for(int i=0;i<phySymptomsTypeList.size();i++){
            		Map<String,Object> phySymptomsTypeMap=phySymptomsTypeList.get(i);
            		String phySymptomsTypeId=phySymptomsTypeMap.get("symptomsSeq").toString();
            		boolean allHasChecked=false;//选项中是否存在选中的值（默认没有：false）

            		for(int k=0;k<checkedPhySymptomsTypeList.size();k++){
                		Map<String,Object> checkedPhySymptomsTypeMap=checkedPhySymptomsTypeList.get(k);
                		String checkPhySymptomsTypeId=checkedPhySymptomsTypeMap.get("symptomsSeq").toString();
                		if(phySymptomsTypeId.equals(checkPhySymptomsTypeId)){//选项中存在选中的值
                			phySymptomsTypeList_last.add(checkedPhySymptomsTypeMap);
                			allHasChecked=true;//选项中是否存在选中的值（是：true）
                			break;
                		}
                		           		
                	}
            		if(!allHasChecked){
            			phySymptomsTypeList_last.add(phySymptomsTypeMap);
            		}
            	}
        		
        		for(int i=0;i<checkedPhySymptomsTypeList.size();i++){
            		Map<String,Object> checkedPhySymptomsTypeMap=checkedPhySymptomsTypeList.get(i);
            		String checkPhySymptomsTypeId=checkedPhySymptomsTypeMap.get("symptomsSeq").toString();
            		boolean checkedInAll=false;//选中的值是否在选项中（默认没有：false）

            		for(int k=0;k<phySymptomsTypeList.size();k++){
                		Map<String,Object> phySymptomsTypeMap=phySymptomsTypeList.get(k);
                		String phySymptomsTypeId=phySymptomsTypeMap.get("symptomsSeq").toString();
                		if(checkPhySymptomsTypeId.equals(phySymptomsTypeId)){//选中的值在选项中
                			checkedInAll=true;//选中的值是否在选项中（是：true）
                			break;
                		}
                		           		
                	}
            		if(!checkedInAll){
            			phySymptomsTypeList_last.add(checkedPhySymptomsTypeMap);
            		}
            	}
        	}else{//选中的为空
        		phySymptomsTypeList_last=phySymptomsTypeList;
        	}
        	
        	
        }else{//选项为空
        	if(checkedPhySymptomsTypeList!=null&&checkedPhySymptomsTypeList.size()>0){//选中的不为空
        		phySymptomsTypeList_last=checkedPhySymptomsTypeList;
        	}else{//选中的也为空
        		
        	}
        	
        }
      
        
        
        result.put("phySymptomsTypeList", phySymptomsTypeList_last);
        return result;
    }
	
	/**
	 * 根据问诊主记录主键跟会员号查询症状选项、脏项选项、舌苔情况选项、人员信息
	 * @return
	 */
	@RequestMapping("/queryData/getRadioDatas")
	@ResponseBody
    public Map<String,Object>  getRadioDatas(String inquiry_id,String per_id) {
		Map<String,Object> result=new HashMap<String, Object>();
		
		//症状选项
		List<InquiryRcdSub> inquiryRcdSubList = new ArrayList<InquiryRcdSub>();
		int inquiryId=Integer.parseInt(inquiry_id);
		inquiryRcdSubList=inquiryRcdSubService.getInquiryRcdSubListByInquiryId(inquiryId);
		String symptomsNames="";
		if(inquiryRcdSubList!=null&&inquiryRcdSubList.size()>0){
			for(int i=0;i<inquiryRcdSubList.size();i++){
				InquiryRcdSub inquiryRcdSub=inquiryRcdSubList.get(i);
				symptomsNames=symptomsNames+","+inquiryRcdSub.getSymptomsName();
			}
			if(symptomsNames.length()>0){
				symptomsNames=symptomsNames.substring(1);
			}
		}
		 
		//舌苔情况选项
        List<PhyTongueType> phyTongueTypeList = new ArrayList<PhyTongueType>();
        phyTongueTypeList = queryDataService.queryPhyTongueTypes();
        //脏项选项
        List<PhyOrgansType> phyOrgansTypeList = new ArrayList<PhyOrgansType>();
        phyOrgansTypeList = queryDataService.queryPhyOrgansTypes();
//        //人员信息
//        PerSysList perSysList=queryDataService.queryPerSysListByPerid(per_id);
      //人员信息
        PerSysSub perSysSub=queryDataService.queryPerSysSubByPerid(per_id);
        
        String season=getSeason();
        
        result.put("symptomsNames", symptomsNames);
        result.put("inquiryRcdSubList", inquiryRcdSubList);
        result.put("phyTongueTypeList", phyTongueTypeList);
        result.put("phyOrgansTypeList", phyOrgansTypeList);
        result.put("perSysSub", perSysSub);
        result.put("season", season);
        
        return result;
    }
	
	/**
	 * 根据问诊主记录id查询需调理的病症选项
	 * @param inquiryIdStr
	 * @return
	 */
	@RequestMapping("/queryData/getInquiryRcdSubsByInquiryId")
	@ResponseBody
    public List<InquiryRcdSub>  getInquiryRcdSubsByInquiryId(String inquiryIdStr) {
		int inquiryId=Integer.parseInt(inquiryIdStr);
        List<InquiryRcdSub> inquiryRcdSubs = new ArrayList<InquiryRcdSub>();
        inquiryRcdSubs=inquiryRcdSubService.getInquiryRcdSubListByInquiryId(inquiryId);
        
        return inquiryRcdSubs;
    }
	
	
	@RequestMapping("/queryData/getInquiryRcdSub")
	@ResponseBody
    public InquiryRcdSub  getInquiryRcdSub(String idStr) {
		int id=Integer.parseInt(idStr);
		InquiryRcdSub inquiryRcdSub=inquiryRcdSubService.getInquiryRcdSubById(id);
        return inquiryRcdSub;
    }
	
	/**
	 * tiaoli界面初始化数据
	 * @param conditionSubId
	 * @return
	 */
	@RequestMapping("/queryData/getTiaoliDatas")
	@ResponseBody
    public Map<String,Object>  getTiaoliDatas(HttpServletRequest request, HttpServletResponse response) {
		String conditionSubId=request.getParameter("conditionSubId");
		
		Map<String,Object> result=new HashMap<String, Object>();
		InquiryRcdCondition inquiryRcdCondition=queryDataService.queryInquiryRcdConditionById(Integer.parseInt(conditionSubId));//客户问诊记录调理子表
		int conditionId=inquiryRcdCondition.getConditionId();
		InquiryRcdSub inquiryRcdSub=inquiryRcdSubService.getInquiryRcdSubById(conditionId); //客户问诊记录子表
		
		int inquiryId=inquiryRcdSub.getInquiryId();
		InquiryRcdMain inquiryRcdMain=queryDataService.queryInquiryRcdMainById(inquiryId);
		
		
        result.put("inquiryRcdCondition", inquiryRcdCondition);
        result.put("inquiryRcdSub", inquiryRcdSub);
        result.put("inquiryRcdMain", inquiryRcdMain);
        result.put("perid", inquiryRcdMain.getPerId());
        
        return result;
    }
	
	@RequestMapping("/queryData/getYaofang")
	@ResponseBody
    public Map<String,Object>  getYaofang(HttpServletRequest request, HttpServletResponse response) {
		//String condition_type=request.getParameter("condition_type");
		String conditionSubIdx_Str=request.getParameter("conditionSubIdx_Str");
		
		
		Map<String,Object> result=new HashMap<String, Object>();
		
		List<DrugConditionList> drugConditionLists=new ArrayList<DrugConditionList>();
		
		
        
        return result;
    }
	
	/**
	 * 根据用户名查询问诊主记录
	 * @param keyWord
	 * @return
	 */
	@RequestMapping("/inquiryRcdMain/getListByUser")
	@ResponseBody
    public Map<String,Object>  getListByUser(String queryStr) {
		Map<String,Object> result=new HashMap<String, Object>();
		System.out.println("queryStr:"+queryStr);
        List<InquiryRcdMain> inquiryRcdMainList = new ArrayList<InquiryRcdMain>();
       
        inquiryRcdMainList=queryDataService.queryInquiryRcdMainListByUser(queryStr);
        
        result.put("inquiryRcdMainList", inquiryRcdMainList);
        return result;
    }
	/**
	 * 加载药方页面初始数据
	 * @param keyWord
	 * @return
	 */
	@RequestMapping("/queryData/getYaofangPageData")
	@ResponseBody
	public Map<String,Object> getYaofangPageData(HttpServletRequest request, HttpServletResponse response) throws Exception {
	   Map<String,Object> result=new HashMap<String, Object>();
	    
	   String conditionIdx_str = request.getParameter("conditionIdx");//PHY_CONDITION_TYPE(调理套餐主表)的主键
	   
	   
	   int conditionIdx=Integer.parseInt(conditionIdx_str);
	   PhyConditionType phyConditionType=queryDataService.queryPhyConditionTypeById(conditionIdx);
	   
	   int symptomsSeq=phyConditionType.getSymptomsSeq();//症状序列号
	   String symptomsName=phyConditionType.getConditionName();//症状名称
	   
	   int conditionSubId=phyConditionType.getConditionSubId();//调理序号(INQUIRY_RCD_CONDITION表的主键)
	   InquiryRcdCondition inquiryRcdCondition=queryDataService.queryInquiryRcdConditionById(conditionSubId);//客户问诊记录调理子表
	   int conditionId=inquiryRcdCondition.getConditionId();
	   InquiryRcdSub inquiryRcdSub=inquiryRcdSubService.getInquiryRcdSubById(conditionId); //客户问诊记录子表
	   int organsSeq=inquiryRcdSub.getOrgansSeq();//脏象类别 
	   PhyOrgansType phyOrgansType=queryDataService.queryPhyOrgansTypeById(organsSeq);
	   int tongueSeq=inquiryRcdSub.getTongueSeq();//舌苔类别
	   PhyTongueType phyTongueType=queryDataService.queryPhyTongueTypeById(tongueSeq);
	    
	   int seasonSeq=getSeasonSeq();//季节
	   int yearType=1;//年龄区间
	   int timeType=1;//调理次数 
	   result.put("symptomsName", symptomsName); //症状名称
	   result.put("organsName", phyOrgansType.getOrgansName()); //脏象类别 
	   result.put("tongueName", phyTongueType.getTongueColor()); //舌苔类别
	   
	   return result;
	}
	/**
	 * 加载药方页面初始数据(基础/全身)
	 * @param keyWord
	 * @return
	 */
	@RequestMapping("/queryData/getYaofangPageDataB")
	@ResponseBody
	public Map<String,Object> getYaofangPageDataB(HttpServletRequest request, HttpServletResponse response) throws Exception {
	   Map<String,Object> result=new HashMap<String, Object>();
	    
	   String conditionIdx_str = request.getParameter("conditionIdx");//PHY_CONDITION_TYPE(调理套餐主表)的主键
	   String parts_str=request.getParameter("parts");//部位
	   String parts="";
	   if(!StringUtils.isEmpty(parts_str)){
		   String[] partsArr = parts_str.split(",");
		   
		   for(int i=0;i<partsArr.length;i++){
			   String part=partsArr[i];
			   parts=parts+",'"+part+"'";
		   }
	   }
	   if(parts.length()>0){
		   parts=parts.substring(1);
	   }
	   
	   int conditionIdx=Integer.parseInt(conditionIdx_str);
	   PhyConditionType phyConditionType=queryDataService.queryPhyConditionTypeById(conditionIdx);
	   
	   int symptomsSeq=phyConditionType.getSymptomsSeq();//症状序列号
	   String symptomsName=phyConditionType.getConditionName();//症状名称
	   
	   int conditionSubId=phyConditionType.getConditionSubId();//调理序号(INQUIRY_RCD_CONDITION表的主键)
	   InquiryRcdCondition inquiryRcdCondition=queryDataService.queryInquiryRcdConditionById(conditionSubId);//客户问诊记录调理子表
	   int conditionId=inquiryRcdCondition.getConditionId();
	   InquiryRcdSub inquiryRcdSub=inquiryRcdSubService.getInquiryRcdSubById(conditionId); //客户问诊记录子表
	   int organsSeq=inquiryRcdSub.getOrgansSeq();//脏象类别 
	   PhyOrgansType phyOrgansType=queryDataService.queryPhyOrgansTypeById(organsSeq);
	   int tongueSeq=inquiryRcdSub.getTongueSeq();//舌苔类别
	   PhyTongueType phyTongueType=queryDataService.queryPhyTongueTypeById(tongueSeq);
	    
	   int seasonSeq=getSeasonSeq();//季节
	   int yearType=1;//年龄区间
	   int timeType=1;//调理次数 
	   result.put("symptomsName", symptomsName); //症状名称
	   result.put("organsName", phyOrgansType.getOrgansName()); //脏象类别 
	   result.put("tongueName", phyTongueType.getTongueColor()); //舌苔类别
	   
	   List<Map<String,Object>> yaofangList=queryDataService.queryYaofangByPart2(symptomsSeq, organsSeq, tongueSeq, seasonSeq, yearType, timeType, parts);
	   String amount="";
	   BigDecimal amount_totale=new BigDecimal("0");
	   if(yaofangList!=null&&yaofangList.size()>0){
		   for(Map<String,Object> yaofangMap:yaofangList){
			   BigDecimal  p=new BigDecimal(yaofangMap.get("PRICE").toString());
			   BigDecimal  c=new BigDecimal(yaofangMap.get("COUNT").toString());
			   BigDecimal a=p.multiply(c);
			   amount_totale=amount_totale.add(a);
		   }
		   
	   }
	   amount_totale=amount_totale.setScale(2, RoundingMode.HALF_UP);
	   amount=String.valueOf(amount_totale.doubleValue());
	   
	   result.put("yaofangList", yaofangList); //药方
	   result.put("amount", amount); //金额
	   
	   return result;
	}
	
	/**
	 * 根据部位查询问诊药方
	 * @param keyWord
	 * @return
	 */
	@RequestMapping("/queryData/getDrugByPart")
	@ResponseBody
	public Map<String,Object> savePhyConditionType(HttpServletRequest request, HttpServletResponse response) throws Exception {
	   Map<String,Object> result=new HashMap<String, Object>();
	    
	   String conditionIdx_str = request.getParameter("conditionIdx");//PHY_CONDITION_TYPE(调理套餐主表)的主键
	   String parts_str=request.getParameter("parts");//选择的部位
	   String parts="";
	   if(!StringUtils.isEmpty(parts_str)){
		   String[] partsArr = parts_str.split(",");
		   
		   for(int i=0;i<partsArr.length;i++){
			   String part=partsArr[i];
			   parts=parts+",'"+part+"'";
		   }
	   }
	   if(parts.length()>0){
		   parts=parts.substring(1);
	   }else{
		   parts="''";
	   }
	   
	   int conditionIdx=Integer.parseInt(conditionIdx_str);
	   PhyConditionType phyConditionType=queryDataService.queryPhyConditionTypeById(conditionIdx);
	   
	   int symptomsSeq=phyConditionType.getSymptomsSeq();//症状序列号
	   int conditionSubId=phyConditionType.getConditionSubId();//调理序号(INQUIRY_RCD_CONDITION表的主键)
	   InquiryRcdCondition inquiryRcdCondition=queryDataService.queryInquiryRcdConditionById(conditionSubId);//客户问诊记录调理子表
	   int conditionId=inquiryRcdCondition.getConditionId();
	   InquiryRcdSub inquiryRcdSub=inquiryRcdSubService.getInquiryRcdSubById(conditionId); //客户问诊记录子表
	   int organsSeq=inquiryRcdSub.getOrgansSeq();//脏象类别
	   int tongueSeq=inquiryRcdSub.getTongueSeq();//舌苔类别
	   
	   int seasonSeq=getSeasonSeq();//季节
	   int yearType=1;//年龄区间
	   int timeType=1;//调理次数
	   
	   List<Map<String,Object>> yaofangList=queryDataService.queryYaofangByPart2(symptomsSeq, organsSeq, tongueSeq, seasonSeq, yearType, timeType, parts);
	   result.put("conditionIdx", conditionIdx);
	   result.put("yaofangList", yaofangList);
	   
	   String amount="";
	   BigDecimal amount_totale=new BigDecimal("0");
	   if(yaofangList!=null&&yaofangList.size()>0){
		   for(Map<String,Object> yaofangMap:yaofangList){
			   BigDecimal  p=new BigDecimal(yaofangMap.get("PRICE").toString());
			   BigDecimal  c=new BigDecimal(yaofangMap.get("COUNT").toString());
			   BigDecimal a=p.multiply(c);
			   amount_totale=amount_totale.add(a);
		   }
		   
	   }
	   amount_totale=amount_totale.setScale(2, RoundingMode.HALF_UP);
	   amount=String.valueOf(amount_totale.doubleValue());
	   result.put("amount", amount); //金额
	   return result;
	}
	
	/**
	 * 根据问诊主记录主键跟会员号查询已调理的症状选项、脏项选项、舌苔情况选项、人员信息
	 * @return
	 */
	@RequestMapping("/queryData/getRecordPageData")
	@ResponseBody
    public Map<String,Object>  getRecordPageData(String inquiryId,String perId) {
		Map<String,Object> result=new HashMap<String, Object>();
		
		int inquiryId_int=Integer.parseInt(inquiryId);
		
		InquiryRcdMain inquiryRcdMain=queryDataService.queryInquiryRcdMainById(inquiryId_int);//问诊主记录
		//症状选项
		List<InquiryRcdSub> inquiryRcdSubList = new ArrayList<InquiryRcdSub>();
		inquiryRcdSubList=inquiryRcdSubService.getInquiryRcdSubListByInquiryId(inquiryId_int);
		String symptomsNames="";
		if(inquiryRcdSubList!=null&&inquiryRcdSubList.size()>0){
			for(int i=0;i<inquiryRcdSubList.size();i++){
				InquiryRcdSub inquiryRcdSub=inquiryRcdSubList.get(i);
				symptomsNames=symptomsNames+","+inquiryRcdSub.getSymptomsName();
			}
			if(symptomsNames.length()>0){
				symptomsNames=symptomsNames.substring(1);
			}
		}
		
		//调理的症状记录
		List<PhyConditionType> phyConditionTypeRecordList = new ArrayList<PhyConditionType>();
		phyConditionTypeRecordList=queryDataService.getPhyConditionTypeRecordListByInquiryId(inquiryId_int);
		
		 
		//舌苔情况选项
        List<PhyTongueType> phyTongueTypeList = new ArrayList<PhyTongueType>();
        phyTongueTypeList = queryDataService.queryPhyTongueTypes();
        //脏项选项
        List<PhyOrgansType> phyOrgansTypeList = new ArrayList<PhyOrgansType>();
        phyOrgansTypeList = queryDataService.queryPhyOrgansTypes();
//        //人员信息
//        PerSysList perSysList=queryDataService.queryPerSysListByPerid(perId);
      //人员信息
        PerSysSub perSysSub=queryDataService.queryPerSysSubByPerid(perId);
        
        String season=getSeason();
        
        result.put("inquiryRcdMain", inquiryRcdMain);//问诊主记录
        result.put("symptomsNames", symptomsNames);//问诊选择的所有症状
        result.put("phyConditionTypeRecordList", phyConditionTypeRecordList);//调理的症状记录
        result.put("PerSysSub", perSysSub);
        result.put("season", season);
        
        return result;
    }
	
	/**
	 * 根据已调理的症状记录主键查询已调理的用药清单
	 * @return
	 */
	@RequestMapping("/queryData/getRecordDetailPageData")
	@ResponseBody
    public Map<String,Object>  getRecordDetailPageData(String conditionIdx) {
		String ipcUrl="https://api.qihuangjie.com/record_images/";
		Map<String,Object> result=new HashMap<String, Object>();
		
		int conditionIdx_int=Integer.parseInt(conditionIdx); 
		 
		List<Map<String,Object>> recordDetailList=queryDataService.getRecordDetailByConditionIdx(conditionIdx_int);
		Map<String,Object> recordDetail= new HashMap<String,Object>();
		if(recordDetailList!=null&&recordDetailList.size()>0){
			recordDetail=recordDetailList.get(0);
		}
		
		List<Map<String,Object>> picRecords=queryDataService.getPicsByConditionIdx(conditionIdx_int);
		Map<String,Object> picRecord= new HashMap<String,Object>();
		List<String> pics=new ArrayList<String>();
		if(picRecords!=null&&picRecords.size()>0){
			picRecord=picRecords.get(0);
			if(picRecord.get("p1")!=null){
				if(!(picRecord.get("p1").toString().equals(" "))){
					pics.add(ipcUrl+picRecord.get("p1").toString());
				}
				
			}
			if(picRecord.get("p2")!=null){
				if(!(picRecord.get("p2").toString().equals(" "))){
					pics.add(ipcUrl+picRecord.get("p2").toString());
				}
				
			}
			if(picRecord.get("p3")!=null){
				if(!(picRecord.get("p3").toString().equals(" "))){
					pics.add(ipcUrl+picRecord.get("p3").toString());
				}
				
			}
			if(picRecord.get("p4")!=null){
				if(!(picRecord.get("p4").toString().equals(" "))){
					pics.add(ipcUrl+picRecord.get("p4").toString());
				}
				
			}
			if(picRecord.get("p5")!=null){
				if(!(picRecord.get("p5").toString().equals(" "))){
					pics.add(ipcUrl+picRecord.get("p5").toString());
				}
				
			}
		}
		
		List<Map<String,Object>> drugDetailList=queryDataService.getDrugDetailByConditionIdx(conditionIdx_int);
        
        result.put("recordDetail", recordDetail);//
        
        result.put("drugDetailList", drugDetailList);//
        
        result.put("pics", pics);//
        
        return result;
    }
	
	public  String getSeason(){
	    int seasonNumber = Calendar.getInstance().get(Calendar.MONTH);
	    return seasonNumber>=1&&seasonNumber<=3?"春":seasonNumber>=4&&seasonNumber<=6?"夏":seasonNumber>=7&&seasonNumber<=9?"秋":seasonNumber>=10?"冬":"冬";
	}
	
	public  int getSeasonSeq(){
	    int seasonNumber = Calendar.getInstance().get(Calendar.MONTH);
	    //return seasonNumber>=1&&seasonNumber<=3?"春":seasonNumber>=4&&seasonNumber<=6?"夏":seasonNumber>=7&&seasonNumber<=9?"秋":seasonNumber>=10?"冬":"冬";
	    return seasonNumber>=1&&seasonNumber<=3?1:seasonNumber>=4&&seasonNumber<=6?2:seasonNumber>=7&&seasonNumber<=9?3:seasonNumber>=10?4:4;
		
	}
	
	@RequestMapping("/queryData/getNewPerId")
	@ResponseBody
    public Map<String,Object>  getNewPerId(HttpServletRequest request, HttpServletResponse response) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
		String key=formatter.format(new Date());
		String lastPerId=queryDataService.lastPerId(key);
		String newPerId="";
		if(StringUtils.isEmpty(lastPerId)){
			newPerId=key+"0001";
		}else{
			int lastPerId_int=Integer.parseInt(lastPerId);
			int newPerId_int=lastPerId_int+1;
			newPerId=String.valueOf(newPerId_int);
		}
		 
		
		
		Map<String,Object> result=new HashMap<String, Object>();
		result.put("newPerId", newPerId);
        
        return result;
    }
	
}

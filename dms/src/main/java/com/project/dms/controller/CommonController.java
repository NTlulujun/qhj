package com.project.dms.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.project.dms.entiy.DrugDefinedMain;
import com.project.dms.entiy.InquiryRcdCondition;
import com.project.dms.entiy.InquiryRcdMain;
import com.project.dms.entiy.InquiryRcdSub;
import com.project.dms.entiy.PerSysList;
import com.project.dms.entiy.PerSysSub;
import com.project.dms.entiy.PhyConditionDrug;
import com.project.dms.entiy.PhyConditionList;
import com.project.dms.entiy.PhyConditionType;
import com.project.dms.entiy.PhyPartType;
import com.project.dms.entiy.PhySymptomsType;
import com.project.dms.service.InquiryRcdSubService;
import com.project.dms.service.PhySymptomsTypeService;
import com.project.dms.service.QueryDataService;
import com.project.dms.service.SaveDataService;

@Controller
public class CommonController {
	@Autowired
	private SaveDataService saveDataService;
	@Autowired
	private QueryDataService queryDataService;
	@Autowired
	private PhySymptomsTypeService phySymptomsTypeService;
	@Autowired
	private InquiryRcdSubService inquiryRcdSubService;
	
	
	
	public String dates(){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
   }
   @RequestMapping("common/wx_upload")
   public ModelAndView  uploadPicture(HttpServletRequest request, HttpServletResponse response,PrintWriter writer) throws Exception {
	   MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
	   String pic_no = request.getParameter("pic_no");
	   System.out.println("pic_no：" + pic_no);
       //对应前端的upload的name参数"file"
       MultipartFile multipartFile = req.getFile("file");

       //realPath填写电脑文件夹路径
       //String realPath = "C:\\Users\\Admin\\Pictures\\WeChatPic";
       String realPath = "C:\\file";
       
       //格式化时间戳
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
       String nowTime = sdf.format(new Date().getTime());

       //裁剪用户id
       String originalFirstName = multipartFile.getOriginalFilename();
       String picFirstName = originalFirstName.substring(0, originalFirstName.indexOf("."));

       //取得图片的格式后缀
       String originalLastName = multipartFile.getOriginalFilename();
       String picLastName = originalLastName.substring(originalLastName.lastIndexOf("."));

       //拼接：名字+时间戳+后缀
       String picName =nowTime +"_"+pic_no + "_" + picFirstName + picLastName;
       try {
           File dir = new File(realPath);
           //如果文件目录不存在，创建文件目录
           if (!dir.exists()) {
               dir.mkdir();
               System.out.println("创建文件目录成功：" + realPath);
           }
           File file = new File(realPath, picName);
           multipartFile.transferTo(file);
           System.out.println("添加图片成功！文件名称为："+picName);
           String conditionIdStr = request.getParameter("conditionId");//客户问诊记录子表主键
           int conditionId=Integer.parseInt(conditionIdStr);
           InquiryRcdSub  inquiryRcdSub =inquiryRcdSubService.getInquiryRcdSubById(conditionId);
           if(pic_no.equals("0")){
        	   inquiryRcdSub.setTonguePicId1(picName);
           }else if(pic_no.equals("1")){
        	   inquiryRcdSub.setTonguePicId2(picName);
           }else if(pic_no.equals("2")){
        	   inquiryRcdSub.setTonguePicId3(picName);
           }else if(pic_no.equals("3")){
        	   inquiryRcdSub.setTonguePicId4(picName);
           }else if(pic_no.equals("4")){
        	   inquiryRcdSub.setTonguePicId5(picName);
           }
           inquiryRcdSubService.updateInquiryRcdSub(inquiryRcdSub);
       } catch (IOException e) {
           e.printStackTrace();
       } catch (IllegalStateException e) {
           e.printStackTrace();
       }
       return null;
   }
   
   /**
	 * 保存问诊主表跟对应的病症子表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	*/
   @RequestMapping("common/saveInquiryRcdMain")
   @ResponseBody
   public Map<String,Object>  saveInquiryRcdMain(HttpServletRequest request, HttpServletResponse response) throws Exception {
	   Map<String,Object> result=new HashMap<String, Object>();
	   
	   String checkedTtips = request.getParameter("checkedTtips");
	   String vipnumber = request.getParameter("vipnumber");
	   String typeIndex = request.getParameter("typeIndex");
	   String remark = request.getParameter("remark");
	  // System.out.println("checkedTtips:"+checkedTtips);
	   //System.out.println("vipnumber:"+vipnumber);
	  // System.out.println("typeIndex:"+typeIndex);
	  // System.out.println("remark:"+remark);
	   
	   PerSysSub perSysSub=queryDataService.queryPerSysSubByPerid(vipnumber);
	   if(perSysSub==null){
		   System.out.println("用户编号："+vipnumber+"不存在！");
		   result.put("code", "2");
		   result.put("msg", "用户编号："+vipnumber+"不存在！");
		   return result;
	   }
	   HttpSession session = request.getSession();
	   session.setAttribute("perSysSub", perSysSub);
	   
	   InquiryRcdMain inquiryRcdMain=new InquiryRcdMain();
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String dateString = formatter.format(new Date());
	   inquiryRcdMain.setInquiryTime(dateString);
	   inquiryRcdMain.setInquiryContent(remark);
	   inquiryRcdMain.setPerId(vipnumber);
	   inquiryRcdMain.setPerName(perSysSub.getPerName());
	   inquiryRcdMain.setUseFlag(typeIndex);
	   inquiryRcdMain.setRecordClose("N");	   
	   inquiryRcdMain.setPerNameIn("wx_user");
	   
	   int inquiry_id=saveDataService.saveInquiryRcdMain(inquiryRcdMain);
	   System.out.println("INQUIRY_ID:"+inquiry_id);
	   result.put("inquiry_id", inquiry_id);//客户问诊记录主表id
	   result.put("vipnumber", vipnumber);//会员号
	   
	   if(!StringUtils.isEmpty(checkedTtips)){
		   String[] checkedTtipsArr = checkedTtips.split(",");
		   System.out.println("checkedTtipsarr:"+checkedTtipsArr.length);
		   
		   for(int i=0;i<checkedTtipsArr.length;i++){
			   InquiryRcdSub inquiryRcdSub=new InquiryRcdSub();
			   
			   String idStr=checkedTtipsArr[i];
			   int id=Integer.parseInt(idStr);
			   PhySymptomsType phySymptomsType = phySymptomsTypeService.getPhySymptomsTypeById(id);
			   
			   String symptoms_name=phySymptomsType.getSymptomsName();
			   String symptoms_type=phySymptomsType.getSymptomsType();
			   int symptoms_pri=phySymptomsType.getSymptomsPri();
			   int symptoms_seq=phySymptomsType.getSymptomsSeq();
			   int phyBelong=phySymptomsType.getPhyBelong();
			   
			   inquiryRcdSub.setInquiryId(inquiry_id);//问诊号
			   inquiryRcdSub.setSymptomsSeq(symptoms_seq);
			   inquiryRcdSub.setSymptomsName(symptoms_name);
			   inquiryRcdSub.setSymptomsType(symptoms_type);
			   inquiryRcdSub.setSymptomsPri(symptoms_pri);//症状优先级
			   inquiryRcdSub.setConditionTime(new Date());//调理时间
			   inquiryRcdSub.setPhyBelong(phyBelong);//所属身体部位
			   inquiryRcdSub.setConditionIf("0");//是否已调理
			   inquiryRcdSub.setRecordClose("0");//是否关闭
			   inquiryRcdSubService.saveInquiryRcdSub(inquiryRcdSub);
			   
		   }
	   }
	  
	   result.put("code", "1");
	   
	   
	   return result;
   }
   
   @RequestMapping("common/updateInquiryRcdSub")
   @ResponseBody
   public Map<String,Object>  updateInquiryRcdSub(HttpServletRequest request, HttpServletResponse response) throws Exception {
	   Map<String,Object> result=new HashMap<String, Object>();
	   int code=0;//返回状态编码 0成功 1异常
	   
	   String inquiryIdStr = request.getParameter("inquiryId");//问诊记录主键
	   String conditionIdStr = request.getParameter("conditionId");//客户问诊记录子表主键
	   String dirtyItemVal = request.getParameter("dirtyItemVal");//选择的脏项主键
	   String tongueItemVal = request.getParameter("tongueItemVal");//选择的舌苔主键
	   
	   
	   
	   int inquiryId=Integer.parseInt(inquiryIdStr);
	   int conditionId=Integer.parseInt(conditionIdStr);
	   int dirtyItem=Integer.parseInt(dirtyItemVal);
	   int tongueItem=Integer.parseInt(tongueItemVal);
	   
	   InquiryRcdSub  inquiryRcdSub =inquiryRcdSubService.getInquiryRcdSubById(conditionId);
	   if(inquiryRcdSub!=null){
		   PhySymptomsType phySymptomsType=phySymptomsTypeService.getPhySymptomsTypeById(inquiryRcdSub.getSymptomsSeq());
		   int belong=phySymptomsType.getPhyBelong();
		   
		   inquiryRcdSub.setOrgansSeq(dirtyItem);//脏象序列号
		   inquiryRcdSub.setTongueSeq(tongueItem);//舌苔序列号
		   inquiryRcdSub.setPhyBelong(belong);//所属身体部位
		   
		   inquiryRcdSubService.updateInquiryRcdSub(inquiryRcdSub);
		   
		   int symptomsSeq=inquiryRcdSub.getSymptomsSeq();
		   int conditionSubId=saveInquiryRcdCondition(conditionId,inquiryId,symptomsSeq);
		   result.put("conditionSubId", conditionSubId);
		   
	   }
	  
	   result.put("code", 0);
	   result.put("msg", "提交成功");
	   
	   
	   return result;
   }
   
   /**
	 * 保存客户问诊记录调理子表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
   */
   public int  saveInquiryRcdCondition(int conditionId,int inquiryId,int symptomsSeq) throws Exception {
	   InquiryRcdCondition inquiryRcdCondition=new InquiryRcdCondition();
	   
	   inquiryRcdCondition.setSymptomsSeq(symptomsSeq);
	   inquiryRcdCondition.setConditionId(conditionId);
	   inquiryRcdCondition.setInquiryId(inquiryId);
	   inquiryRcdCondition.setCreateTime(new Date());
	   
	   int conditionSubId=saveDataService.saveInquiryRcdCondition(inquiryRcdCondition);
	   
	   return conditionSubId;
   }
   
   
   
   
   /**
	 * 保存调理套餐主表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("common/savePhyConditionType")
	@ResponseBody
	public Map<String,Object> savePhyConditionType(HttpServletRequest request, HttpServletResponse response) throws Exception {
	   Map<String,Object> result=new HashMap<String, Object>();
	   
	   PhyConditionType phyConditionType=new PhyConditionType();
	   String condition_type_str = request.getParameter("conditionType");//套餐类别
	   String condition_sub_id_str = request.getParameter("conditionSubId");//客户问诊记录调理子表主键
	   
	   int conditionSubId=Integer.parseInt(condition_sub_id_str);
	   
	   InquiryRcdCondition inquiryRcdCondition=queryDataService.queryInquiryRcdConditionById(conditionSubId);//客户问诊记录调理子表
	   int conditionId=inquiryRcdCondition.getConditionId();
	   InquiryRcdSub inquiryRcdSub=inquiryRcdSubService.getInquiryRcdSubById(conditionId); //客户问诊记录子表
	   int inquiryId=inquiryRcdSub.getInquiryId();
	   InquiryRcdMain inquiryRcdMain=queryDataService.queryInquiryRcdMainById(inquiryId);//客户问诊记录主表
	   
	   phyConditionType.setConditionType(condition_type_str);//套餐类别
	   phyConditionType.setConditionSubId(conditionSubId);//调理序号
	   phyConditionType.setConditionId(conditionId);//问诊序号
	   phyConditionType.setSymptomsSeq(inquiryRcdCondition.getSymptomsSeq());//症状序列号
	   phyConditionType.setConditionName(inquiryRcdSub.getSymptomsName());//症状名称
	   phyConditionType.setCreatTime(new Date());//调理时间
	   phyConditionType.setPerId(inquiryRcdMain.getPerId());//顾客工号
	   phyConditionType.setInquiryId(inquiryId);//问诊号
	   phyConditionType.setPerNameIn("wx");
	   
	   //保存调理套餐主表
	   int conditionIdx=saveDataService.savePhyConditionType(phyConditionType);
	   
	 /*  List<PhyPartType> phyPartTypes=new ArrayList<PhyPartType>();
	   if(condition_type_str.equals("0")){//全局
		   phyPartTypes=queryDataService.queryPhyPartTypeAll();
	   }else if(condition_type_str.equals("1")){//基础
			String physeqs="1,2,3,4";
			phyPartTypes=queryDataService.queryPhyPartTypeByPhyseqs(physeqs);
	   }else {//局部
		   String physeqs="1,2,3,4";
			phyPartTypes=queryDataService.queryPhyPartTypeByPhyseqs(physeqs);
	   }
	   
	   //保存调理套餐子表
	   if(phyPartTypes!=null&&phyPartTypes.size()>0){
		   for(PhyPartType phyPartType:phyPartTypes){
			   PhyConditionList phyConditionList=new PhyConditionList();
			   phyConditionList.setConditionType(condition_sub_id_str);
			   phyConditionList.setConditionPart(phyPartType.getPhyPart());
			   phyConditionList.setConditionIdx(conditionIdx);
			   
			   int conditionSubIdx=saveDataService.savePhyConditionList(phyConditionList);
			   
			   int symptomsSeq=phyConditionType.getSymptomsSeq();//病症类别
			   int organsSeq=inquiryRcdSub.getOrgansSeq();//脏象类别
			   int tongueSeq=inquiryRcdSub.getTongueSeq();//舌苔类别
			   int seasonSeq=getSeason();//季节
			   int yearType=0;//年龄区间
			   int timeType=0;//调理次数
			   int phySeq=phyPartType.getPhySeq();//调理部位
			 
			   
			   DrugDefinedMain drugDefinedMain_m=new DrugDefinedMain();
			   drugDefinedMain_m.setSymptomsSeq(symptomsSeq);
			   drugDefinedMain_m.setOrgansSeq(organsSeq);
			   drugDefinedMain_m.setTongueSeq(tongueSeq);
			   drugDefinedMain_m.setSeasonSeq(seasonSeq);
			   drugDefinedMain_m.setYearSeq(yearType);
			   drugDefinedMain_m.setTimeSeq(timeType);
			   drugDefinedMain_m.setPhySeq(phySeq);
			   drugDefinedMain_m.setCreateTime(new Date());
			   
			   
			   
		   }
	   }*/
	   
	   result.put("conditionIdx", conditionIdx);
	   result.put("conditionType", condition_type_str);
	   return result;
	}
	 /**
		 * 保存药方
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
	 @RequestMapping("common/saveDrug")
	 @ResponseBody
	 public Map<String,Object>  saveDrug(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 Map<String,Object> result=new HashMap<String, Object>();
		 
		   String conditionIdx_str = request.getParameter("conditionIdx");//PHY_CONDITION_TYPE(调理套餐主表)的主键
		   String parts_str=request.getParameter("parts");//选择的部位
		   String amount_str=request.getParameter("amount");//金额
		   String[] partsArr = null;
		   if(!StringUtils.isEmpty(parts_str)){
			    partsArr = parts_str.split(",");
			    
		   }
		  
		   
		   int conditionIdx=Integer.parseInt(conditionIdx_str);
		   PhyConditionType phyConditionType=queryDataService.queryPhyConditionTypeById(conditionIdx);
		   
		   phyConditionType.setConditionPrice(new BigDecimal(amount_str));
		   
		   
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
		   if(partsArr!=null){
			   for(int i=0;i<partsArr.length;i++){ 
				   PhyConditionList phyConditionList=new PhyConditionList();
				   phyConditionList.setConditionType(phyConditionType.getConditionType());//套餐类别
				   phyConditionList.setConditionPart(partsArr[i]);//调理部位
				   phyConditionList.setConditionIdx(conditionIdx);//调理套餐主表(PHY_CONDITION_TYPE)id
				   
				   int conditionSubIdx=saveDataService.savePhyConditionList(phyConditionList);
				   
				   List<Map<String,Object>> yaofangList=queryDataService.queryYaofangByPart(symptomsSeq, organsSeq, tongueSeq, seasonSeq, yearType, timeType, "'"+partsArr[i]+"'");
				   
				   if(yaofangList!=null&&yaofangList.size()>0){
					   for(Map<String,Object> yaofangMap:yaofangList){
						   PhyConditionDrug phyConditionDrug=new PhyConditionDrug();
						   
						   phyConditionDrug.setConditionPart(partsArr[i]);//调理部位
						   phyConditionDrug.setConditionType(phyConditionType.getConditionType());//调理类别
						   String drugIdStr=yaofangMap.get("DRUGID").toString();
						   phyConditionDrug.setDrugId(Integer.parseInt(drugIdStr));//药品序号
						   String type=yaofangMap.get("TYPE").toString();
						   phyConditionDrug.setDrugType(type);//药品类别
						   String countStr=yaofangMap.get("COUNT").toString();
						   BigDecimal count=new BigDecimal(countStr);
						   phyConditionDrug.setUnitQty(count);//药品用量
						   phyConditionDrug.setConditionSubIdx(conditionSubIdx);//套餐序号
						   
						   int drugSeqId=saveDataService.savePhyConditionDrug(phyConditionDrug);
					   }
				   }
			   }
		   }
		   saveDataService.editPhyConditionType(phyConditionType);
		   
		   result.put("code", 0);
		   result.put("msg", "提交成功");
		 return result;
	 }
	 
	 /**
		 * 保存会员
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
	 @RequestMapping("common/saveUser")
	 @ResponseBody
	 public Map<String,Object>  saveUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 Map<String,Object> result=new HashMap<String, Object>();
	    
		 String perId_str = request.getParameter("perId");//vip号
		 String perName_str = request.getParameter("perName");//用户名
		 String perAge_str = request.getParameter("perAge");//年龄
		 String sex_str = request.getParameter("sex");//性别
		 String perTelNum_str = request.getParameter("perTelNum");//手机号
		 String perNum_str = request.getParameter("perNum");//身份证号
		 String perAdd_str = request.getParameter("perAdd");//地址
		 
		 PerSysSub perSysSub=new PerSysSub();
		 perSysSub.setPerId(perId_str);
		 perSysSub.setPerName(perName_str);
		 if(!StringUtils.isEmpty(perAge_str)){
			 perSysSub.setPerAge(Integer.parseInt(perAge_str));
		 }
		 perSysSub.setPerType("D");
		 perSysSub.setPerSex(sex_str);
		 perSysSub.setPerTelNum(perTelNum_str);
		 perSysSub.setPerNum(perNum_str);
		 perSysSub.setPerAdd(perAdd_str);
		 perSysSub.setCreateBy("wx");
		 perSysSub.setCreateTime(new Date());
		 
		 int perSysSub_id=saveDataService.savePerSysSub(perSysSub);
		 int perId_int=Integer.parseInt(perId_str);
		 int nextPerId_int=perId_int+1;
		 
		 result.put("nextPerId", String.valueOf(nextPerId_int));
		 result.put("code", 0);
		 result.put("msg", "提交成功");
		 return result;
	 }
	
	public  int getSeason(){
	    int seasonNumber = Calendar.getInstance().get(Calendar.MONTH);
	    //return seasonNumber>=1&&seasonNumber<=3?"春":seasonNumber>=4&&seasonNumber<=6?"夏":seasonNumber>=7&&seasonNumber<=9?"秋":seasonNumber>=10?"冬":"冬";
	    return seasonNumber>=1&&seasonNumber<=3?1:seasonNumber>=4&&seasonNumber<=6?2:seasonNumber>=7&&seasonNumber<=9?3:seasonNumber>=10?4:4;
		
	}
	public  int getSeasonSeq(){
	    int seasonNumber = Calendar.getInstance().get(Calendar.MONTH);
	    //return seasonNumber>=1&&seasonNumber<=3?"春":seasonNumber>=4&&seasonNumber<=6?"夏":seasonNumber>=7&&seasonNumber<=9?"秋":seasonNumber>=10?"冬":"冬";
	    return seasonNumber>=1&&seasonNumber<=3?1:seasonNumber>=4&&seasonNumber<=6?2:seasonNumber>=7&&seasonNumber<=9?3:seasonNumber>=10?4:4;
		
	}
}

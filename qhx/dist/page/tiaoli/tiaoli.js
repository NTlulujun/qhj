Page({
  data: {
    symptomsName: "",//本次调理
    vipnumber: "",//会员号
    userType: "",//用户类型(成人、中童、小童)
    organs: "",//脏项
    tongue: "",//舌苔
    bodyTtips: [],//身体部位
    conditionTypes: ["全身调理", "基础调理", "局部调理"],//调理类别
    conditionTypesCode: ["C", "B", "A"],//调理类别编号
    conditionType:"",//选择的调理类别
    conditionTypeValue:"",
    conditionSubId: "",//客户问诊记录调理子表主键
    inquiryId: ""//问诊主键
  },
  onLoad: function (option) {
    console.log('加载tiaoli' );
    // console.log('tipsText:', option.tabsText);
    var _this = this;
    var baseUrl=getApp().globalData.baseUrl;
    wx.request({
      url: baseUrl+'/queryData/getTiaoliDatas',
      data: {
        "conditionSubId": option.conditionSubId
      },
      method: 'post',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        var organs_seq = res.data.inquiryRcdSub.organsSeq;
        var organs_text=""
        if (organs_seq=="1"){
          organs_text ="有 高血压 糖尿病 失眠 更年期综合征  甲亢";
        } else if (organs_seq == "2") {
          organs_text = "无 高血压 糖尿病 失眠 更年期综合征  甲亢";
        }

        var tongue_seq = res.data.inquiryRcdSub.tongueSeq;
        var tongue_text = ""
        if (tongue_seq == "1") {
          tongue_text = "舌苔白";
        } else if (organs_seq == "2") {
          tongue_text = "舌苔黄";
        }

        var user_flag = res.data.inquiryRcdMain.userFlag;
        var user_flag_text = "成人"
        if (user_flag == "1") {
          user_flag_text = "中童";
        } else if (user_flag == "2") {
          user_flag_text = "小童";
        }

        _this.setData({
          // tips: res.data.symptomsNames.split(","),
          conditionSubId: option.conditionSubId,
          symptomsName: res.data.inquiryRcdSub.symptomsName,
          vipnumber: res.data.perid,
          organs: organs_text,
          tongue: tongue_text,
          userType: user_flag_text
        })
      },
      fail: function (res) {
        console.log("--------fail--------");
      }
    })


  },
  bindConditionTypesChange: function (e) {
    console.log('携带值为', e.detail.value);
    var conditionTypesCode=this.data.conditionTypesCode;
    this.setData({
      conditionType: e.detail.value,
      conditionTypeValue: conditionTypesCode[e.detail.value]
    })
  },
  submit2next: function () {
    var _this = this;
    var baseUrl=getApp().globalData.baseUrl;
    var userType=_this.data.userType;
    wx.request({
      url: baseUrl+'/common/savePhyConditionType',
      data: {
        "conditionType": _this.data.conditionTypeValue,
        "conditionSubId": _this.data.conditionSubId
      },
      method: 'post',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        var conditionIdx = res.data.conditionIdx;
        var conditionType = res.data.conditionType;
        console.log('conditionType为', conditionType);
        var yaofangUrl="";

        if (conditionType=="A"){//局部
          yaofangUrl = '../yaofangA/yaofangA?conditionIdx=' + conditionIdx+'&userType='+userType
        } else if (conditionType=="B"){//基础
          yaofangUrl = '../yaofangB/yaofangB?conditionIdx=' + conditionIdx+'&userType='+userType
        } else if (conditionType == "C") {//全身
          yaofangUrl = '../yaofangC/yaofangC?conditionIdx=' + conditionIdx+'&userType='+userType
        }
        console.log('yaofangUrl为', yaofangUrl);
        wx.navigateTo({
          url: yaofangUrl
        })
      },
      fail: function (res) {
        console.log("--------fail--------");
      }
    })
  }
})

Page({
  data: {
    time: "2020-06-22",//问诊时间
    userType: "成人",// 
    symptomsNames: "",// 
    remark: "备注信息",// 
    contextShowed:false,
    tipsShowed:true,
    phyConditionTypeRecordList: []
  },
  onLoad: function (option) {
    console.log('加载tiaoli' );
    // console.log('tipsText:', option.tabsText);
    var _this = this;
    var baseUrl=getApp().globalData.baseUrl;
    wx.request({
      url: baseUrl+'/queryData/getRecordPageData',
      data: {
        "inquiryId": option.inquiryId,
        "perId": option.perid
      },
      method: 'post',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        

        _this.setData({
          time: res.data.inquiryRcdMain.inquiryTime,
          userType: res.data.inquiryRcdMain.useFlag,
          symptomsNames: res.data.symptomsNames,
          remark: res.data.inquiryRcdMain.inquiryContent,
          phyConditionTypeRecordList:res.data.phyConditionTypeRecordList
        })
        if(res.data.phyConditionTypeRecordList==''){
          _this.setData({
            contextShowed: true,
            tipsShowed:false,
          })
        }else{
          _this.setData({
            contextShowed: false,
            tipsShowed:true,
          })
        }
      },
      fail: function (res) {
        console.log("--------fail--------");
      }
    })


  }
})

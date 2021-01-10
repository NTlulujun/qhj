Page({

  data: {
    tiaoliTime:'',
    symptomsName:'',
    parts:'',
    organsName:'',
    tongueColor:'',
    amount:'',
    tiaoliType: "",
    drugDetailList:[]
  },
  onLoad: function (option) {
    var _this = this;
    var baseUrl=getApp().globalData.baseUrl;
    wx.request({
      url: baseUrl+'/queryData/getRecordDetailPageData',
      data: {
        "conditionIdx": option.conditionIdx
      },
      method: 'post',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        

        _this.setData({
          symptomsName:option.conditionName,
          tiaoliTime: option.time,
          parts: res.data.recordDetail.parts,
          organsName:res.data.recordDetail.organsName,
          tongueColor:res.data.recordDetail.tongueColor,
          amount:res.data.recordDetail.amount,
          tiaoliType:res.data.recordDetail.conditionType,
          drugDetailList:res.data.drugDetailList
        })
      },
      fail: function (res) {
        console.log("--------fail--------");
      }
    })
    
  },
  submit2last: function (option) {
    
    wx.navigateBack({ changed: true });//返回上一页
  }
  
})

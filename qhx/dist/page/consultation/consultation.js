var base64 = require("../../images/base64");

Page({
  onLoad: function () {
    this.setData({
      tips: ["肩颈 肩周炎","舌质淡","手臂痛"],
      icon20: base64.icon20,
      icon60: base64.icon60
    });
  },
  data: {
    contentShowed: true,
    tipsShowed:true,
    inputVal: "",
    recodes:[]
  },
  doQuery: function () {
    var _this = this;
    var baseUrl=getApp().globalData.baseUrl;
    var queryStr=_this.data.inputVal;
    if(queryStr==''){
      _this.setData({
        contentShowed: true,
        tipsShowed: false
      });
      return;
    }
    wx.request({
      url: baseUrl+'/inquiryRcdMain/getListByUser',
      data: {
        "queryStr": queryStr
      },
      method: 'post',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        console.log('inquiryRcdMainList:' + res.data.inquiryRcdMainList);
        if(res.data.inquiryRcdMainList==''){
          _this.setData({
            contentShowed: true,
            tipsShowed: false
          });
        }else{
          _this.setData({
            contentShowed: false,
            tipsShowed: true
          });
        }
        _this.setData({
          recodes: res.data.inquiryRcdMainList
        })
      },
      fail: function (res) {
        console.log("--------fail--------");
      }
    })


    
  },
  inputTyping: function (e) {
    this.setData({
      inputVal: e.detail.value
    });
  }
});

Page({
  data: {
    perId:"",//vip号
    perName: "",//用户名
    perAge: "",//年龄
    perTelNum:"",//手机号
    perNum:"",//身份证号
    perAdd:"",//地址
    sex: ["男", "女"],
    sexIndex: 0
  },
  onShow: function () {
    console.log("onShow执行");
    var _this = this
    _this.setData({
      perName: "",
      perAge: "",
      sexIndex: 0,
      perTelNum: "",
      perNum: "",
      perAdd: "",
    })
  },
  onLoad: function () {
    var _this = this
    var baseUrl=getApp().globalData.baseUrl;
    wx.request({
      url: baseUrl+'/queryData/getNewPerId',
      data: {
        keyWord: ""
      },
      method: 'post',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        console.log('res.data:' + res.data.newPerId);
        _this.setData({
          perId: res.data.newPerId
        })
      },
      fail: function (res) {
        console.log("--------fail--------");
      }
    })


  },
  perNameChange: function (e) {
    this.setData({
      perName: e.detail.value
    })
  },
  perAgeChange: function (e) {
    this.setData({
      perAge: e.detail.value
    })
  }, 
  bindSexChange: function (e) {
    this.setData({
      sexIndex: e.detail.value
    })
  },
  perTelNumChange: function (e) {
    this.setData({
      perTelNum: e.detail.value
    })
  },
  perNumChange: function (e) {
    this.setData({
      perNum: e.detail.value
    })
  },
  
  perAddChange: function (e) {
    this.setData({
      perAdd: e.detail.value
    })
  },
  submit2save: function () {
    var _this = this;
    var perName=_this.data.perName;
    console.log('perName:' + perName);
    if (perName == '') {
      wx.showToast({
        title: "用户名不能为空！",
        icon: "none",
        duration: 1500
      })
      return false;
    }
    var perTelNumReg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
    var perTelNum=_this.data.perTelNum;
    console.log('perTelNum:' + perTelNum);
	if (perTelNum == '') {
  	wx.showToast({
    	title: '手机号不能为空！',
   		icon: 'none',
    	duration: 1500
 	 })
 	 return false;
  } else if (perTelNum.length != 11) {
	  wx.showToast({
	    title: '手机号长度有误！',
	    icon: 'none',
 	    duration: 1500
	  })
    return false;
  } else if (!perTelNumReg.test(perTelNum)) {
    wx.showToast({
      title: '手机号有误！',
	    icon: 'none',
  	  duration: 1500
    })
    return false;
  }

  var perNum=_this.data.perNum;
  console.log('perNum:' + perNum);
  if (perNum == '') {
    wx.showToast({
      title: '出生日期不能为空！',
      icon: 'none',
      duration: 1500
    })
    return false;
  }

    var baseUrl=getApp().globalData.baseUrl;  
    wx.request({
      url: baseUrl+'/common/saveUser',
      data: {
        "perId": this.data.perId,//vip号
        "perName": this.data.perName,//用户名
        "perAge": this.data.perAge,//年龄
        "sex": this.data.sexIndex,//性别
        "perTelNum": perTelNum,//手机号
        "perNum": perNum,//出生日期
        "perAdd": this.data.perAdd//地址
      }, 
      method: 'post',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        _this.setData({
          perId:res.data.nextPerId,
          perName: "",
          perAge: "",
          sexIndex: 0,
          perTelNum: "",
          perNum: "",
          perAdd: "",
        }),
        wx.showToast({
          title: '保存成功！',
          icon: 'none',
          duration: 1500
        })
        
      },
      fail: function (res) {
        console.log("--------fail--------");
      }
    })


  }
});
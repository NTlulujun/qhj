
Page({
  data: {
    showTopTips: false,
    keyWord:"",//会员号/手机号
    vipnumber:"",//会员号
    phoneNo:"",//手机号
    userName:"",//用户名
    remark: "",
    radioItems: [],
    bodyTtips: [],
    checkedTtips: [],//选中的身体情况
    checkedTtipsText: [],//选中的身体情况文本
    types: ["成人", "中童", "小童"],
    typeIndex: 0,
    showDialog: false,
    isAgree: false
  },
  showTopTips: function () {
    var that = this;
    this.setData({
      showTopTips: true
    });
    setTimeout(function () {
      that.setData({
        showTopTips: false
      });
    }, 3000);
  },
  onLoad: function () {
    var _this = this
    var baseUrl=getApp().globalData.baseUrl;
    wx.request({
      url: baseUrl+'/phySymptomsType/list',
      data: {
        keyWord: ""
      },
      method: 'post',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        console.log('res.data:' + res.data.phySymptomsTypeList);
        _this.setData({
          bodyTtips: res.data.phySymptomsTypeList
        })
      },
      fail: function (res) {
        console.log("--------fail--------");
      }
    })


  },
  inputChange: function (e) {
    var _this = this;
    var baseUrl=getApp().globalData.baseUrl;
    // console.log('input发生change事件，携带value值为：', e.detail.value)
    wx.request({
      url: baseUrl+'/phySymptomsType/list',
      data: {
        "keyWord": e.detail.value,
        "checkedIds":_this.data.checkedTtips
      },
      method: 'post',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        console.log('res.data:' + res.data.phySymptomsTypeList);
        _this.setData({
          bodyTtips: res.data.phySymptomsTypeList
        })
      },
      fail: function (res) {
        console.log("--------fail--------");
      }
    })

  },
  keyWordChange: function (e) {
    
    var _this = this
    _this.setData({
      keyWord: e.detail.value
    })
  },
  remarkChange: function (e) {

    var _this = this
    _this.setData({
      remark: e.detail.value
    })
  },
  checkboxChange: function (e) {
    // console.log('checkbox发生change事件，携带value值为：', e.detail.value);
    
    var bodyTtips = this.data.bodyTtips, values = e.detail.value;
    var checkedText=[];
    for (var i = 0, lenI = bodyTtips.length; i < lenI; ++i) {
      bodyTtips[i].checked = false;

      for (var j = 0, lenJ = values.length; j < lenJ; ++j) {
        if (bodyTtips[i].symptomsSeq == values[j]) {
          bodyTtips[i].checked = true;
          // console.log('选中的texte值为：', bodyTtips[i].symptomsName);
          checkedText.push(bodyTtips[i].symptomsName);
          break;
        }
      }
    }
    // console.log('checkbox发生change事件，携带text值为：', checkedText);
    this.setData({
      bodyTtips: bodyTtips,
      checkedTtips: e.detail.value,
      checkedTtipsText: checkedText
    });
  },
  bindDateChange: function (e) {
    this.setData({
      date: e.detail.value
    })
  },
  bindTimeChange: function (e) {
    this.setData({
      time: e.detail.value
    })
  },
  bindCountryCodeChange: function (e) {
    
    this.setData({
      countryCodeIndex: e.detail.value
    })
  },
  bindTypesChange: function (e) {
   
    this.setData({
      typeIndex: e.detail.value
    })
  },
  bindAccountChange: function (e) {
    
    this.setData({
      accountIndex: e.detail.value
    })
  },
  bindAgreeChange: function (e) {
    this.setData({
      isAgree: !!e.detail.value.length
    });
  },
  openDialog: function () {
    var _this = this
    var baseUrl=getApp().globalData.baseUrl;
    var keyWord=_this.data.keyWord;
    if(keyWord==''){
      wx.showModal({
        title: '提示',
        content: '请输入会员号或手机号',
        showCancel: false,
        success (res) {
          if (res.confirm) {
            console.log('用户点击确定')
          } 
        }
      }) 
      return;
    }
    wx.request({
      url: baseUrl+'/queryData/queryUser',
      data: {
        keyWord: _this.data.keyWord
      },
      method: 'post',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) { 
        if(res.data.perSysSub==null){
          wx.showModal({
                title: '提示信息',
                content: '用户不存在',
                cancelText: "取消",
                success: function (res) {
                  console.log(res);
                  if (res.confirm) {
                    console.log('用户点击确定')
                  } else {
                    console.log('用户点击取消')
                  }
                }
              });
        }else{
          _this.setData({
            vipnumber:res.data.perSysSub.perId,//会员号
            phoneNo:res.data.perSysSub.perTelNum,//手机号
            userName:res.data.perSysSub.perName,//用户名
            istrue: true
          })
        }
        
      },
      fail: function (res) {
        console.log("--------fail--------");
      }
    })
    
    // if (conditionId == "") {
    //   wx.showModal({
    //     title: '提示信息',
    //     content: '请选择本次调理症状',
    //     cancelText: "取消",
    //     success: function (res) {
    //       console.log(res);
    //       if (res.confirm) {
    //         console.log('用户点击确定')
    //       } else {
    //         console.log('用户点击取消')
    //       }
    //     }
    //   });
    // } else if (dirtyItemVal == ""){
    //   wx.showModal({
    //     title: '提示信息',
    //     content: '请选择脏项信息',
    //     cancelText: "取消",
    //     success: function (res) {
    //       console.log(res);
    //       if (res.confirm) {
    //         console.log('用户点击确定')
    //       } else {
    //         console.log('用户点击取消')
    //       }
    //     }
    //   });
    // } else if (tongueItemVal == "") {
    //   wx.showModal({
    //     title: '提示信息',
    //     content: '请选择舌苔信息',
    //     cancelText: "取消",
    //     success: function (res) {
    //       console.log(res);
    //       if (res.confirm) {
    //         console.log('用户点击确定')
    //       } else {
    //         console.log('用户点击取消')
    //       }
    //     }
    //   });
    // }else{
    //   this.setData({
    //     istrue: true
    //   })
    // }

    
  },
  closeDialog: function () {
    var _this = this
    _this.setData({
      istrue: false
    })
  },
  submit2next: function () {
    console.log('checkedTtips:' + this.data.checkedTtips);//选中的身体情况
    console.log('typeIndex:' + this.data.typeIndex);//成人0/中童1/小童2
    console.log('vipnumber:' + this.data.vipnumber);//vip账号
    console.log('remark:' + this.data.remark)//备注
    var _this = this
    var baseUrl=getApp().globalData.baseUrl;
    // var inquiryId = "9";
    // var perid = _this.data.vipnumber;
    // var typeIndex = _this.data.typeIndex;
    // wx.navigateTo({
    //   url: '../question/question?inquiryId=' + inquiryId + '&perid=' + perid + '&userType=' + typeIndex
    // })

    
    wx.request({
      url: baseUrl+'/common/saveInquiryRcdMain',
      data: {
        "checkedTtips": this.data.checkedTtips,
        "vipnumber": this.data.vipnumber,
        "typeIndex": this.data.typeIndex,
        "remark": this.data.remark
      },
      method: 'post',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        console.log('res:' ,res);
        var code=res.data.code;
        console.log('code:' ,code);
        if(code=='2'){
          wx.showModal({
            title: '提示',
            content: res.data.msg,
            showCancel: false,
            success (res) {
              if (res.confirm) {
                console.log('用户点击确定')
              } 
            }
          }) 
          
        }else{
          var inquiryId=res.data.inquiry_id;
          var perid = _this.data.vipnumber;
          var typeIndex = _this.data.typeIndex;
          wx.navigateTo({
            url: '../question/question?inquiryId=' + inquiryId + '&perid=' + perid+ '&userType=' + typeIndex
          })
        }
        
      },
      fail: function (res) {
        console.log("--------fail--------");
      }
    })


  }
});
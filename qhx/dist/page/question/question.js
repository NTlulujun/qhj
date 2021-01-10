var adds = {};
Page({
  data: {
    showtab:0,  //顶部选项卡索引
    showtabtype:'', //选中类型
    showfootertab:0,  //底部标签页索引
    tabnav:{},  //顶部选项卡数据
    questionsall:[],  //所有问题
    questions:[], //问题列表
    dirtyItems: [],//脏项
    tongueItems: [],//舌苔
    radioItems: [
            {name: 'cell standard', value: '0'},
            {name: 'cell standard', value: '1', checked: true}
        ],
    tips: [],//症状选项
    typeIndex: "",//选择的症状选项
    conditionId: "",//选择的客户问诊记录子表主键
    conditionSubId: "",//客户问诊记录调理子表主键
    showquestionindex:null, //查看问题索引,
    uploadimgs:[], //上传图片列表
    editable: true, //是否可编辑
    personAge:"",//会员年龄
    vipnumber: "",//会员号
    season:"",//当前季节
    showDialog: false,
    dirtyItemVal:"",
    dirtyItemText:"",
    tongueItemVal:"",
    tongueItemText:"",
    userType:"",//用户类型（成人0、中童1、小童2）
    inquiryId:""//问诊主键
  },
  onLoad: function (option) {
    // console.log('tips:' , option.tabs);
    // console.log('tipsText:', option.tabsText);

    var _this = this;
    var baseUrl=getApp().globalData.baseUrl;
    wx.request({
      url: baseUrl+'/queryData/getRadioDatas',
      data: {
        "inquiry_id": option.inquiryId,
        "per_id": option.perid
      },
      method: 'post',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      }, 
      success: function (res) {
         console.log('当前季节：', res.data.season);
        _this.setData({
          // tips: res.data.symptomsNames.split(","),
          tips: res.data.inquiryRcdSubList,
          tongueItems: res.data.phyTongueTypeList,
          dirtyItems: res.data.phyOrgansTypeList,
          inquiryId: option.inquiryId,
          vipnumber: option.perid,
          personAge: res.data.perSysSub.perAge,
          season: res.data.season,
          userType: option.userType
        })
      },
      fail: function (res) {
        console.log("--------fail--------");
      }
    })

    
  },
  openDialog: function () {
    var _this = this
    var conditionId = _this.data.conditionId;
    var dirtyItemVal = _this.data.dirtyItemVal;
    var tongueItemVal = _this.data.tongueItemVal;

    if (conditionId == "") {
      wx.showModal({
        title: '提示信息',
        content: '请选择本次调理症状',
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
    } else if (dirtyItemVal == ""){
      wx.showModal({
        title: '提示信息',
        content: '请选择脏项信息',
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
    } else if (tongueItemVal == "") {
      wx.showModal({
        title: '提示信息',
        content: '请选择舌苔信息',
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
      this.setData({
        istrue: true
      })
    }

    
  },
  closeDialog: function () {
    var _this = this
    _this.setData({
      istrue: false
    })
  },
  subData: function () {
    var _this = this
    var baseUrl=getApp().globalData.baseUrl;
    var typeIndex = _this.data.typeIndex;
    var dirtyItemVal = _this.data.dirtyItemVal;
    var tongueItemVal = _this.data.tongueItemVal;
    var conditionId = _this.data.conditionId;

    wx.request({
      url: baseUrl+'/common/updateInquiryRcdSub',
      data: {
        "inquiryId": _this.data.inquiryId,
        "conditionId": conditionId,
        "dirtyItemVal": dirtyItemVal,
        "tongueItemVal": tongueItemVal
      },
      method: 'post',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        //  console.log('res.data:' + res.data);
        // _this.setData({
        //   istrue: false
        // }),
        var conditionSubId = res.data.conditionSubId;
        console.log('开始跳转upload');
        _this.upload(conditionSubId);
        console.log('结束跳转upload');

        

        
      },
      fail: function (res) {
        console.log("--------fail--------");
      }
    })
    // _this.setData({
    //   istrue: false
    // }),
    // wx.navigateTo({
    //   url: '../input/input'
    // })
  },
  bindtipsTextChange: function (e) {
    console.log('本次调理发生选择改变，携带值(index)为', e.detail.value);
    var conditionId = this.data.tips[e.detail.value].conditionId;

    this.setData({
      typeIndex: e.detail.value,
      conditionId: conditionId
    })
   
  },

  radioChange: function (e) {
    console.log('发生change事件，携带value值为：', e.detail.value);

    var radioItems = this.data.radioItems;
    for (var i = 0, len = radioItems.length; i < len; ++i) {
      radioItems[i].checked = radioItems[i].value == e.detail.value;
    }

    this.setData({
      radioItems: radioItems
    });
  },
  dirtyItemsChange: function (e) {
    console.log('脏项radio发生change事件，携带value值为：', e.detail.value);

    var dirtyItems = this.data.dirtyItems;
    var choseItemText="";
    for (var i = 0, len = dirtyItems.length; i < len; ++i) {
      dirtyItems[i].checked = dirtyItems[i].organsSeq == e.detail.value;
      if(dirtyItems[i].organsSeq == e.detail.value){
        choseItemText = dirtyItems[i].organsName;
      }
    }
    console.log('choseItemText值为：', choseItemText)
    this.setData({
      dirtyItems: dirtyItems,
      dirtyItemVal: e.detail.value,
      dirtyItemText: choseItemText
    });
  },
  tongueItemsChange: function (e) {
    console.log('舌苔radio发生change事件，携带value值为：', e.detail.value)

    var tongueItems = this.data.tongueItems;
    var choseItemText = "";
    for (var i = 0, len = tongueItems.length; i < len; ++i) {
      tongueItems[i].checked = tongueItems[i].tongueSeq == e.detail.value;
      if(tongueItems[i].tongueSeq == e.detail.value) {
        choseItemText = tongueItems[i].tongueColor;
      }
    }
    console.log('choseItemText值为：', choseItemText)
    this.setData({
      tongueItems: tongueItems,
      tongueItemVal: e.detail.value,
      tongueItemText: choseItemText
    });
  },
  chooseImage:function() {
    let _this = this;
    wx.showActionSheet({
      itemList: ['从相册中选择', '拍照'],
      itemColor: "#f7982a",
      success: function(res) {
        if (!res.cancel) {
          if(res.tapIndex == 0){
            _this.chooseWxImage('album')
          }else if(res.tapIndex == 1){
            _this.chooseWxImage('camera')
          }
        }
      }
    })
  },
  // chooseWxImage:function(type){
  //   let _this = this;
  //   wx.chooseImage({
  //     sizeType: ['original', 'compressed'],
  //     sourceType: [type],
  //     success: function (res) {
  //       _this.setData({
  //         uploadimgs: _this.data.uploadimgs.concat(res.tempFilePaths)
  //       })
  //     }
  //   })
  // },

  chooseWxImage: function (type) {
    var that = this;
    if (this.data.uploadimgs.length < 3) {
      wx.chooseImage({
        sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
        sourceType: [type], // 可以指定来源是相册还是相机，默认二者都有
        success: function (res) {
          that.setData({
            uploadimgs: that.data.uploadimgs.concat(res.tempFilePaths)
          });
        }
      })
    } else {
      wx.showToast({
        title: '最多上传三张图片',
        icon: 'loading',
        duration: 3000
      });
    }
  },

  editImage:function(){
    this.setData({
      editable: !this.data.editable
    })
  },
  deleteImg:function(e){
    console.log(e.currentTarget.dataset.index);
    this.data.uploadimgs.splice(e.currentTarget.dataset.index, 1)
    this.setData({
      uploadimgs: this.data.uploadimgs
    })
  },
  questionSubmit:function(){
    console.log(this.data.uploadimgs);
    
  },
  upload: function (conditionSubId) {
    var _this = this;
    var baseUrl=getApp().globalData.baseUrl;
    var conditionId = _this.data.conditionId;
    var userType=_this.data.userType;
    var imgfile;
    for (var i = 0; i < _this.data.uploadimgs.length; i++) {//循环遍历图片 
      console.log("uploadimgs[" + i + "]:" + _this.data.uploadimgs[i])
      wx.uploadFile({
        url: baseUrl+'/common/wx_upload',//自己的接口地址
        filePath: _this.data.uploadimgs[i],
        name: 'file',
        header: {
          "Content-Type": "multipart/form-data",
          'accept': 'application/json',
          'Authorization': 'okgoodit'//若有token，此处换上你的token，没有的话省略
        },
        formData: ({//上传图片所要携带的参数
          "pic_no": i,
          "conditionId": conditionId
        }),
        success: function (res) {
          console.log(res)
          if (res) {
            console.log("返回的参数信息" + res.data)
            wx.showToast({
              title: '图片上传成功！',
              duration: 2000
            });
          }
        }
      })
    }
    // this.setData({
    //   username: 'CMOS180404',
    //   password: 'ecb01ff6-2e5c-11e8-b467-0ed5f89f718b'
    // })

    console.log('开始跳转tiaoli,conditionSubId为：',conditionSubId);
        wx.navigateTo({
          url: '../tiaoli/tiaoli?conditionSubId=' + conditionSubId+'&userType='+userType
        })
  },

})

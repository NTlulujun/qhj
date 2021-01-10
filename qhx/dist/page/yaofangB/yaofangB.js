Page({

  data: {
    bodys: [
      { value: '1', text: '肩' },
      { value: '2', text: '腰' },
      { value: '3', text: '背' },
      { value: '4', text: '腹' },
      { value: '5', text: '胸' },
      { value: '7', text: '两侧' },
      { value: '8', text: '脖' },
      { value: '9', text: '手' },
      { value: '10', text: '腿' },
      { value: '11', text: '脸' },
      { value: '12', text: '眼' },
      { value: '13', text: '鼻' },
      { value: '14', text: '臀' }
    ], 
    conditionIdx:'',
    symptomsName:'',//症状名称
    userType:'',//用户类型（成人、中童、小童）
    organsName:'',//脏象类别 
    tongueName:'',//舌苔类别
    checkedConditionType:['1','2','3','4'],
    yaofang: [],
    amount: '',
    inquiryId: ""//问诊主键
  },
  onLoad: function (option) {
    var _this = this;
    var baseUrl=getApp().globalData.baseUrl;
    console.log('加载yaofangA' );
    console.log('conditionIdx:', option.conditionIdx);
    wx.request({
      url: baseUrl+'/queryData/getYaofangPageDataB',
      data: {
        "conditionIdx":option.conditionIdx,
        "parts":_this.data.checkedConditionType
      },
      method: 'post',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        // console.log('res.data:' + res.data.yaofangList);
        
        _this.setData({
          conditionIdx:option.conditionIdx,
          userType:option.userType,
          symptomsName:res.data.symptomsName,
          organsName:res.data.organsName,
          tongueName:res.data.tongueName,
          amount:res.data.amount,
          yaofang:res.data.yaofangList
        })
      },
      fail: function (res) {
        console.log("--------fail--------");
      }
    })


  },
  amountChange: function (e) {
    var _this = this;
    _this.setData({
      amount:  e.detail.value
    })
    
  },
  submit2over:function (option) {
    var _this = this;
    var baseUrl=getApp().globalData.baseUrl;
    wx.request({
      url: baseUrl+'/common/saveDrug',
      data: {
        "conditionIdx":_this.data.conditionIdx,
        "parts":_this.data.checkedConditionType,
        "amount":_this.data.amount
      },
      method: 'post',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        wx.showModal({
          title: '提示',
          content: '保存成功',
          showCancel: false,
          success (res) {
            wx.switchTab({
              url: '/page/input/input'
            })
          }
        }) 
      },
      fail: function (res) {
        console.log("--------fail--------");
      }
    })
    
  }
  
})

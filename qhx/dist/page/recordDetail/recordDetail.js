Page({

  data: {
    picUrl:'',
    tiaoliTime:'',
    symptomsName:'',
    parts:'',
    organsName:'',
    tongueColor:'',
    amount:'',
    tiaoliType: "",
    drugDetailList:[],
    imgList: [
      // "https://api.qihuangjie.com/record_images/2020-07-01-23-25-00_0_tmp_151ab648034ece757f2d146c3c9ed2c0c82fedaab5d61479.jpg",
      // "https://api.qihuangjie.com/record_images/2020-07-02-22-38-43_0_tmp_18325c09d577c2b0d49894a9e80caae0106b2f2b216b2ca8.jpg",
      // "https://api.qihuangjie.com/record_images/2020-07-01-23-25-00_0_tmp_151ab648034ece757f2d146c3c9ed2c0c82fedaab5d61479.jpg",
      // "https://api.qihuangjie.com/record_images/2020-07-02-22-38-43_0_tmp_18325c09d577c2b0d49894a9e80caae0106b2f2b216b2ca8.jpg",
      // "https://api.qihuangjie.com/record_images/2020-07-03-15-24-21_0_tmp_a6f4d0f310ada2341786285fe8825c91e3a1b2bd073d0346.jpg"
    ]
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
          drugDetailList:res.data.drugDetailList,
          imgList:res.data.pics
        })
      },
      fail: function (res) {
        console.log("--------fail--------");
      }
    })
    
  },
  submit2last: function (option) {
    
    wx.navigateBack({ changed: true });//返回上一页
  },
  preview(event) {
    console.log(event.currentTarget.dataset.src)
    let currentUrl = event.currentTarget.dataset.src
    wx.previewImage({
      current: currentUrl, // 当前显示图片的http链接
      urls: this.data.imgList // 需要预览的图片http链接列表
    })
  }
  
})

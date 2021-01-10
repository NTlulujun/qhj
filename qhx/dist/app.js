App({
    onLaunch: function () {
        console.log('App Launch')
    },
    onShow: function () {
        console.log('App Show')
    },
    onHide: function () {
        console.log('App Hide')
    },
    globalData: {
        hasLogin: false,
        baseUrl:"https://api.qihuangjie.com"
        // baseUrl: "http://127.0.0.1:8080/qhj"
    }
});
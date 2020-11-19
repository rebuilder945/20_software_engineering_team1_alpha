// pages/juben/juben.js
Page({
  data: {
    comfirm1:0,
    comfirm2:0
  },
  comfirm1:function(e){
    wx.showModal({
      cancelColor: 'cancelColor',
      title:"设为主要剧情",
      content:"是否设为主要剧情",
      cancelText:"知道了",
      confirmText:"设置",
      success: function (res) {
        console.log(res)
        if (res.cancel) {
           //点击取消,默认隐藏弹框
        } else {
           //点击确定
          
          //console.log(this.comfirm1);
           
        }
     }
    })

  },
  comfirm2:function(e){
    wx.showModal({
      cancelColor: 'cancelColor',
      title:"设为主要剧情",
      content:"是否设为主要剧情",
      cancelText:"知道了",
      confirmText:"设置",
      success: function (res) {
        if (res.cancel) {
           //点击取消,默认隐藏弹框
        } else {
           //点击确定
            this.comfirm2=1;
            console.log(this.comfirm2);
           
        }
     }
    })

  }
})
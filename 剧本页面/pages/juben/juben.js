// pages/juben/juben.js
Page({
  data: {
    comfirm1:0,
    comfirm2:0
  },
  comfirm1:function(e){
      var com1=this.data.comfirm1
      var com2=this.data.comfirm2
       var self=this
      wx.showModal({
      cancelColor: 'cancelColor',
      title:"设为主要剧情",
      content:"是否设为主要剧情",
      cancelText:"查看剧情",
      confirmText:"设置",
      success: function (suc) {
        if (suc.cancel) {
          wx.navigateTo({
            url: '../juben1/juben1',
          })
           //点击取消,默认隐藏弹框
        } else {
           //点击确定
           com1=1
           com2=0
           self.setData({
             comfirm:com1,
            comfirm2:com2
          })
          wx.request({
            url: 'https://localhost:8443/xiaochengxu/addBill.do',
            data:self.data.comfirm1,
            method:"post",
            header: {'content-type': 'application/x-www-form-urlencoded'},
            success:function(res) {
              console.log('submit success');

          },
          fail:function(res){
              console.log('submit fail');
          },
          complete:function(res){
              console.log('submit complete');
          }
          })
        }
     }
    })

  },
  comfirm2:function(e){
    var com1=this.data.comfirm1
    var com2=this.data.comfirm2
    var self=this
    wx.showModal({
      cancelColor: 'cancelColor',
      title:"设为主要剧情",
      content:"是否设为主要剧情",
      cancelText:"查看剧情",
      confirmText:"设置",
      success: function (suc) {
        if (suc.cancel) {
          wx.navigateTo({
            url: '../juben2/juben2',
          })
           //点击取消,默认隐藏弹框
        } else {
           //点击确定
           com1=1
           com2=0
           self.setData({
             comfirm:com1,
            comfirm2:com2
          })
          wx.request({
            url: 'https://localhost:8443/xiaochengxu/addBill.do',
            data:self.data.comfirm1,
            method:"post",
            header: {'content-type': 'application/x-www-form-urlencoded'},
            success:function(res) {
              console.log('submit success');

          },
          fail:function(res){
              console.log('submit fail');
          },
          complete:function(res){
              console.log('submit complete');
          }
          })
        }
     }
    })

  }
})
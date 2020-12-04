// pages/juben/juben.js
const app = getApp();
Page({
  data: {
    comfirm1:0,
    comfirm2:0,
      'lists':[
        {
          imgurl:'https://img2020.cnblogs.com/blog/2146164/202011/2146164-20201120220248798-2054370804.png',
          storyname:'埃斯顿城堡',
          storyid:1
        },
        {
          imgurl:'https://img2020.cnblogs.com/blog/2146164/202011/2146164-20201120220345598-1996317016.png',
          storyname:'林中秘事',
          storyid:2
        },
        {
          imgurl:'https://img2020.cnblogs.com/blog/2146164/202011/2146164-20201120220738394-1087578705.jpg',
          storyname:'第三个故事',
          storyid:3
        },
        {
          imgurl: 'https://img2020.cnblogs.com/blog/2146164/202011/2146164-20201120220802565-1243761749.jpg',
          storyname:'第四个故事',
          storyid:4
        }
    ]
    
    
  },
  comfirm1:function(e){
    // console.log(e);
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
            url: 'https://api.iminx.cn/user/story/current/change',
            method:"post",
            dataType:"JSON",
            data:{
              "user_id":app.data.user_id,
              "story_id":e.currentTarget.dataset.id
            },
            success:function(res) {
              console.log('submit success');
              console.log(res.data)
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
  // comfirm2:function(e){
  //   var com1=this.data.comfirm1
  //   var com2=this.data.comfirm2
  //   var self=this
  //   wx.showModal({
  //     cancelColor: 'cancelColor',
  //     title:"设为主要剧情",
  //     content:"是否设为主要剧情",
  //     cancelText:"查看剧情",
  //     confirmText:"设置",
  //     success: function (suc) {
  //       if (suc.cancel) {
  //         wx.navigateTo({
  //           url: '../juben2/juben2',
  //         })
  //          //点击取消,默认隐藏弹框
  //       } else {
  //          //点击确定
  //          com1=1
  //          com2=0
  //          self.setData({
  //            comfirm:com1,
  //           comfirm2:com2
  //         })
  //         wx.request({
  //           url: 'https://api.iminx.cn/user/story/current/change',
  //           data:{
  //             user_id:1,
  //             story_id:1
  //           },
  //           method:"post",
  //           dataType:"JOSN",
  //           success:function(res) {
  //             console.log('submit success');
  //         },
  //         fail:function(res){
  //             console.log('submit fail');
  //         },
  //         complete:function(res){
  //             console.log('submit complete');
  //         }
  //         })
  //       }
  //    }
  //   })
  // },
  onLoad:function(options){
    var self="this"
    wx.request({
      url: 'https://api.iminx.cn/user/story',
      method:'POST',
      data:{
        "user_id":app.data.user_id,
      },
      dataType:"JSON",
      success:(res)=>{
        console.log(res.data)
        this.setData({
        lists:JSON.parse(res.data),

        });
        console.log(res.data)
        console.log(this.data.lists)
      }
    })
  },
  onPullDownRefresh: function () {
    this.onLoad();
    wx.stopPullDownRefresh({
      success: (res) => {},
    })
  },
})
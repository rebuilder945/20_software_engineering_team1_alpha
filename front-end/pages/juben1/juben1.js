// pages/juben1/juben1.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    lists:[]
  },
  onLoad: function (options) {
    var that = this;
    /**生成背景星星-开始 */
    var stars = 400;
    var r = 0;
    var stararray = [];
    for (var i = 0; i < stars; i++) {
    var s = 0.2 + Math.random() * 1;
    var curR = r + Math.random() * 300;
    var rotateY = Math.random() * 360;
    var rotateX = Math.random() * -50;
    stararray[i] = { curR: curR, rotateY: rotateY, rotateX: rotateX, s: s };
    }
    // console.log(stararray)
    this.setData({
    stararray: stararray,
    })
    /**生成背景星星-结束 */
    wx.request({
      url: 'https://api.iminx.cn/public/story',
      dataType:"JSON",
      method:"post",
      data:{
        "story_id":1
      },
      success:res =>{
        this.setData({
          lists:res.data
        })
        console.log(this.data.lists)
      }
    })
    }

  
})
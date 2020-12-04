// pages/log/new_log.js
var utils = require('../../utils/util.js');
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    new_log:({}),
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  create_newLog:function(event){
    // console.log(event);
    // console.log(event.detail.value.title)
    var newLog = new Object();
    var key1 = "title";
    var key2 = "content";
    var key3 = "time";
    var value1 = event.detail.value.title;
    var value2 = event.detail.value.content;
    // var value3 = utils.formatTime(new Date()).split(' ')[0];
    var key0 = "user_id";
    var value0 = app.data.user_id;
    newLog[key0] = value0;
    newLog[key1] = value1;
    newLog[key2] = value2;
    // newLog[key3] = value3;
    this.setData({
      new_log:newLog
    });
    console.log(value2)
    // console.log(this.data.new_log);
    wx.serviceMarket.invokeService({
      service: 'wxee446d7507c68b11',
      api: 'msgSecCheck',
      data: {
        "Action": "TextApproval",
        "Text": value0+value1+value2
      },
    }).then(res => {
      console.log(JSON.stringify(res));
      console.log(res.data.Response.EvilTokens);
      if(res.data.Response.EvilTokens != ''){
        wx.showModal({
                title: '提示',
                content: "输入信息违规！请重新输入！",
        })
      }else{
        wx.request({
          url: 'https://api.iminx.cn/user/diary/add',
          data:{
            "user_id":app.data.user_id,
            "title":value1,
            "content":value2
          },
          datatype:"JSON",
          method:"POST",
          success:function(res){
            console.log(res);
          }
        })
      }
    });
    
  },
  transmitNew:function(){
    console.log(1)
  }
})
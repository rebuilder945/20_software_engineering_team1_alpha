// pages/log/edit_log.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    user_id:app.data.user_id,
    old_title:"",
    old_begin_time:"",
    old_end_time:"",
    old_challenge_id:"",
    old_reminder:"",
    old_content:"",
    edit_log:{}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this;
    that.setData({
      user_id:getApp().data.user_id
    })
    console.log(options)
    this.setData({
      old_title:options.title,
      old_begin_time:options.begin_time,
      old_end_time:options.end_time,
      old_challenge_id:options.challenge_id,
      old_reminder:options.reminder,
      old_content:options.content
    })
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
  solve_data:function(event){
    console.log("woshizhendeshabi");
    var editLog = new Object();
    var key0 = "user_id";
    var key1 = "title";
    var key2 = "content";
    var key3 = "end_time";
    var key4 = "begin_time";
    var key5 = "reminder";
    var key6 = "challenge_id";
    var value0 = this.data.user_id;
    if(event.detail.value.title != ""){
      var value1 = event.detail.value.title;
    }else{
      var value1 = this.data.old_title;
    }
    if(event.detail.value.content != ""){
      var value2 = event.detail.value.content;
    }else{
      var value2 = this.data.old_content;
    }
    var value3 = this.data.old_end_time;
    var value4 = this.data.old_begin_time;
    var value5 = this.data.old_reminder;
    var value6 = this.data.old_challenge_id;
    editLog[key0] = value0;
    editLog[key6] = value6;
    editLog[key1] = value1;
    editLog[key2] = value2;
    editLog[key4] = value4;
    editLog[key3] = value3;
    editLog[key5] = value5;
    this.setData({
      edit_log:editLog
    });
    console.log("woshidasb");
    wx.request({
      url: 'https://api.iminx.cn/user/challenges/update',
      data:{
        "user_id":value0,
        "challenge_id":value6,
        "title":value1,
        "content":value2,
        "begin_time":value4,
        "end_time":value3,
        "reminder":value5
      },
      dataType:"JSON",
      method:"POST",
      success:function(res){
        console.log("修改挑战成功！"),
        console.log(res.data)
      }
    })
  },
  confirm_edit:function(){
    console.log(1)
  }

})
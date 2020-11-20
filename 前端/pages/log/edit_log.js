// pages/log/edit_log.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    old_title:"",
    old_time:"",
    old_content:"",
    edit_log:({})
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      old_title:options.title,
      old_time:options.time,
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
    var editLog = new Object();
    var key0 = "user_id";
    var key1 = "title";
    var key2 = "content";
    var key3 = "time";
    var value0 = "user_1";
    var value1 = event.detail.value.title;
    var value2 = event.detail.value.content;
    var value3 = this.data.old_time;
    editLog[key0] = value0;
    editLog[key1] = value1;
    editLog[key2] = value2;
    editLog[key3] = value3;
    this.setData({
      edit_log:editLog
    })
  },
  confirm_edit:function(){
    wx.request({
      url: 'api.iminx.cn',
      data:this.data.edit_log,
      datatype:"JSON",
      method:"POST",
      success:function(res){}
    })
  }

})
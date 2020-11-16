// pages/log/index.js
import { request } from "../../request/index.js";
Page({

  /**
   * 页面的初始数据
   */
  data: {
    myHidden:true,
    main_line:({
      "line_id":1,
      "line_image":"../../pts/story.png",
      "line_url":"../story/index"
    }),
    logList:([
      {
        "user_id":4,
        "log_id":4,
        "line_id":4,
        "time":"2020-11-14",
        "story":{
          "story_id":4,
          "story_title":"当科状元",
          "story_content":"具体内容为、、、、"
        }
      },
      {
        "user_id":3,
        "log_id":3,
        "line_id":3,
        "time":"2020-11-13",
        "story":{
          "story_id":3,
          "story_title":"科举",
          "story_content":"具体内容为、、、、"
        }
      },
      {
        "user_id":2,
        "log_id":2,
        "line_id":2,
        "time":"2020-11-12",
        "story":{
          "story_id":2,
          "story_title":"读书与练武",
          "story_content":"具体内容为、、、、"
        }
      },
      {
        "user_id":1,
        "log_id":1,
        "line_id":1,
        "time":"2020-11-11",
        "story":{
          "story_id":1,
          "story_title":"我出生啦",
          "story_content":"具体内容为、、、、"
        }
      }
    ])
  },
  /*点击事件*/
  btnTap1:function(event){
    wx.showModal({
      title: '剧情',
      content:event.target.dataset.content,
      success (res) {
        if (res.confirm) {
          console.log('用户点击确定')
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.request({
      url: 'api.imix.cn/',
      dataType: "JSON",
      data:{
        "user_id":1,
        "line_id":1
      },
      method:"POST",
      success: (result) => {
        this.setData({
          logList:result.message,
          main_line:result.log
        });
      },
      fail: (res) => {
        console.log("无法获得数据")
      },
      complete: (res) => {},
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

  }


})
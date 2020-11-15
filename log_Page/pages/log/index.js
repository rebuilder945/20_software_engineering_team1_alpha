// pages/log/index.js
import { request } from "../../request/index.js";
Page({

  /**
   * 页面的初始数据
   */
  data: {
    myHidden:true,
    mainStory:({
      "image_url":"",
      "story_url":"",
    }),
    logList:([
      {
        "id":4,
        "log_id":4,
        "user_id":4,
        "story_id":4,
        "time":"2020-11-14",
        "plot":{
          "plot_id":4,
          "plot_title":"当科状元",
          "plot_content":"具体内容为、、、、"
        },
        "year":"",
        "month":"",
        "day":""
      },
      {
        "id":3,
        "log_id":3,
        "user_id":3,
        "story_id":3,
        "time":"2020-11-13",
        "plot":{
          "plot_id":3,
          "plot_title":"科举",
          "plot_content":"具体内容为、、、、"
        },
        "year":"",
        "month":"",
        "day":""
      },
      {
        "id":2,
        "log_id":2,
        "user_id":2,
        "story_id":2,
        "time":"2020-11-12",
        "plot":{
          "plot_id":2,
          "plot_title":"读书与练武",
          "plot_content":"具体内容为、、、、"
        },
        "year":"",
        "month":"",
        "day":""
      },
      {
        "id":1,
        "log_id":1,
        "user_id":1,
        "story_id":1,
        "time":"2020-11-11",
        "plot":{
          "plot_id":1,
          "plot_title":"我出生啦",
          "plot_content":"具体内容为、、、、"
        },
        "year":"",
        "month":"",
        "day":""
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
      method:"GET",
      success: (result) => {
        this.setData({
          logList:result
        });
      },
      fail: (res) => {
        console.log("无法获得日志信息的数据")
      },
      complete: (res) => {},
    }),
    wx.request({
      url: 'api.imix.cn/',
      dataType: "JSON",
      method:"GET",
      success: (result) => {
        this.setData({
          mainStory:result
        });
      },
      fail: (res) => {
        console.log("无法获得主要剧本的数据")
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
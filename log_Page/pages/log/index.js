// pages/log/index.js
import { request } from "../../request/index.js";
Page({

  /**
   * 页面的初始数据
   */
  data: {
    logList:[
      {
        "id":4,
        "log_id":4,
        "user_id":4,
        "story_id":4,
        "time":"2020-11-14",
        "plot":{
          "plot_id":4,
          "plot_content":"当科状元"
        }
      },
      {
        "id":3,
        "log_id":3,
        "user_id":3,
        "story_id":3,
        "time":"2020-11-13",
        "plot":{
          "plot_id":3,
          "plot_content":"科举"
        }
      },
      {
        "id":2,
        "log_id":2,
        "user_id":2,
        "story_id":2,
        "time":"2020-11-12",
        "plot":{
          "plot_id":2,
          "plot_content":"读书与练武"
        }
      },
      {
        "id":1,
        "log_id":1,
        "user_id":1,
        "story_id":1,
        "time":"2020-11-11",
        "plot":{
          "plot_id":1,
          "plot_content":"我出生啦"
        }
      }
    ]
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
    // request({
    //   url:""
    //   .then(result=>{
    //     this.setData({
    //       logList:result.data.message
    //     })
    //   })
    // })
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
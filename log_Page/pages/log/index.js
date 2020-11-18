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
        "diary_id":"4",
        "title":"日记4",
        "time":"2020-4-4",
        "content":"日记内容"
      },
      {
        "diary_id":"3",
        "title":"日记3",
        "time":"2020-3-3",
        "content":"日记内容"
      },
      {
        "diary_id":"2",
        "title":"日记2",
        "time":"2020-2-2",
        "content":"日记内容"
      },
      {
        "diary_id":"1",
        "title":"日记1",
        "time":"2020-1-1",
        "content":"日记内容"
      }
    ])
  },
  /*点击事件*/
  btnTap1:function(event){

    wx.request({
      url: 'http://localhost:8080/user/line/story/find',
      dataType: "JSON",
      method:"POST",
      data: {
        "user_id":"abc1234",
        "line_id":"1",
        "story_id":"1919810"
      },
      success: (result) => {
        console.log(result);
        // this.setData({
        //   logList:result
        // });
      },
      fail: (res) => {
        console.log("无法获得日志信息的数据")
      },
      complete: (res) => {
        console.log("dd");
      },
    }),

    wx.showModal({
      title: '剧情',
      content:event.currentTarget.dataset.content,
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
        "user_id":1
      },
      method:"POST",
      success: (result) => {log

        this.setData({
          logList:result.log,
          main_line:result.main_line
        });
      },
      fail: (res) => {
        console.log("无法获得数据")
      },
      complete: (res) => {},
    })
    // this.draw_line;
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
  // draw_line:function(){
  //   const ctx = wx.createCanvasContext('myCanvas');
  //   ctx.moveTo(10, 10);
  //   ctx.rect(10, 10, 100, 50);
  //   ctx.lineTo(110, 60);
  //   ctx.stroke();
  //   ctx.draw();
  // }


})
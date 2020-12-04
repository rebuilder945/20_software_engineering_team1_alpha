// pages/log/index.js
var utils = require('../../utils/util.js');
import { request } from "../../request/index.js";
import util from "../../utils/util.js";
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */

  data: {
    
    time:"",
    main_line:{},
    // main_line:({
    //   "story_id":1,
    //   "cover_url":"http://m.qpic.cn/psc?/V50RkLYz0YtHf40lah0z3fVlt53AeuCX/ruAMsa53pVQWN7FLK88i5th*wYRa5qye51i2EMBGaRvjjoFq7yZcZRVsrQf66OjrJcNS4BGIZDP1UkRnrPacdc1vF*pP18ytZ2gj7u8zstc!/b&bo=dwGxAAAAAAADB.U!&rf=viewer_4"
    // }),
    logList:{},
    logList:[
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
    ]
  },
  /*点击事件*/
  btnTap1:function(event){
    // console.log(event)
    if(event.target.dataset.content.content!="NULL"){
      wx.showModal({
        title: '剧情',
        content:event.target.dataset.content.content_userdefine+"\n"+event.target.dataset.content.content,
        success: (res) =>{
          if (res.confirm) {
            console.log('用户点击确定')
          } else if (res.cancel) {
            console.log('用户点击取消')
          }
        }
      })
    }else{
      wx.showModal({
        title: '详情',
        content:event.target.dataset.content.content_userdefine,
        success: (res) =>{
          if (res.confirm) {
            console.log('用户点击确定')
          } else if (res.cancel) {
            console.log('用户点击取消')
          }
        }
      })
    }
    
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.request({
      url: 'https://api.iminx.cn/user/diary',
      method:"POST",
      dataType:"JSON",
      data:{
        "user_id":app.data.user_id
      },
      success: (result) => {
        this.setData({
          logList:JSON.parse(result.data).reverse()
        });
        // console.log(result.data)
        // console.log(this.data.logList)
      },
      fail: (res) => {
        console.log("无法获得数据")
      },
      complete: (res) => {},
    });
    wx.request({
      url: 'https://api.iminx.cn/user/story/current',
      method:"POST",
      dataType:"JSON",
      data:{
        "user_id":app.data.user_id
      },
      success:(result)=>{
        var s = JSON.parse(result.data);
        wx.request({
          url: 'https://api.iminx.cn/public/story',
          data:{
            story_id:s.story_id
          },
          dataType:"JSON",
          method:"POST",
          success:(results)=>{
            this.setData({
              main_line:JSON.parse(results.data)
            });
            // console.log(results)
          }
        })
      }
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
    this.onLoad();
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onchange: function () {

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
    this.onLoad();
    wx.stopPullDownRefresh({
      success: (res) => {},
    })
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
  // draw_line:function(){
  //   const ctx = wx.createCanvasContext('myCanvas');
  //   ctx.moveTo(10, 10);
  //   ctx.rect(10, 10, 100, 50);
  //   ctx.lineTo(110, 60);
  //   ctx.stroke();
  //   ctx.draw();
  // },
  // edit_log:function (event) {
  //   // console.log(event)
  //   wx.redirectTo({
  //     url: 'edit_log?'+ '&title=' + event.currentTarget.dataset.item.title + '&time=' + event.currentTarget.dataset.item.time + '&content_userdefine=' + event.currentTarget.dataset.item.content_userdefine + '&diary_id=' + event.currentTarget.dataset.item.diary_id
  //   })
  // }

})
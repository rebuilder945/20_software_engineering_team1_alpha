var util = require('../../utils/util');
Page({
  /**
   * 页面的初始数据
   */
  data: {
    nowType: 0,
    searchState: "noneWxss",
    editState: "noneWxss",
    addState: "noneWxss",
    completeState: "journalEdit",
    Target: [{
        title: "第一个目标",
        content: "第一个目标内容",
        start_time: "2020年3月",
        end_time: "2020年4月",
      },
      {
        title: "第二个目标",
        content: "第二个目标内容",
        start_time: "2020年3月",
        end_time: "2020年4月",
      },
      {
        title: "第三个目标",
        content: "第三个目标内容",
        start_time: "2020年3月",
        end_time: "2020年4月",
      },
      {
        title: "第四个目标",
        content: "第四个目标内容",
        start_time: "2020年3月",
        end_time: "2020年4月",
      },
      {
        title: "第五个目标",
        content: "第五个目标内容",
        start_time: "2020年3月",
        end_time: "2020年4月",
      }
    ],
    newTarget: {
      title: "第五个目标",
      content: "第五个目标内容",
      start_time: "2020年3月",
      end_time: "2020年4月",
    },
    end_date:"2020-11-17",
    end_time:"00:00"
  },
  //控制页面变化
  changeSearch: function (e) {
    var type = this.data.nowType;
    if (type == 0) {
      this.setData({
        searchState: "weui-search-bar",
        nowType: 1,
      })
    } else if (type == 1) {
      this.setData({
        searchState: "noneWxss",
        nowType: 0,
      })
    } else if (type == 2) {
      this.setData({
        addState: "noneWxss",
        searchState: "weui-search-bar",
        nowType: 1,
      })
    } else {
      this.setData({
        editState: "noneWxss",
        completeState: "journalEdit",
        searchState: "weui-search-bar",
        nowType: 1,
      })
    }
  },
  changeEdit: function (e) {
    var type = this.data.nowType;
    if (type == 0) {
      this.setData({
        editState: "journalEdit",
        completeState: "noneWxss",
        nowType: 3,
      })
    } else if (type == 1) {
      this.setData({
        searchState: "noneWxss",
        editState: "journalEdit",
        completeState: "noneWxss",
        nowType: 3,
      })
    } else if (type == 2) {
      this.setData({
        addState: "noneWxss",
        editState: "journalEdit",
        completeState: "noneWxss",
        nowType: 3,
      })
    } else {
      this.setData({
        editState: "noneWxss",
        completeState: "journalEdit",
        nowType: 0,
      })
    }
  },
  changeAdd: function (e) {
    var type = this.data.nowType;
    var text = this.data.addState;
    if (type == 0) {
      this.setData({
        addState: "addBackGround",
        nowType: 2,
      })
    } else if (type == 1) {
      this.setData({
        searchState: "noneWxss",
        addState: "addBackGround",
        nowType: 2,
      })
    } else if (type == 2) {
      this.setData({
        addState: "noneWxss",
        nowType: 0,
      })
    } else {
      this.setData({
        editState: "noneWxss",
        addState: "addBackGround",
        completeState: "journalEdit",
        nowType: 2,
      })
    }
  },

  //获得新增日志数据
  GetTargetValueTitle(e) {
    this.setData({
      'newTarget.title': e.detail.value,
    })
    console.log(this.data.newTarget.title);
  },
  GetDateChange: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      end_date: e.detail.value
    })
  },
  GetTimeChange: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      end_time: e.detail.value
    })
  },
  GetTargetValueContent(e){
    this.setData({
      'newTarget.content': e.detail.value,
    })
  },
  CreateNewTarget:function(e){
    this.setData({
      'newTarget.start_time': util.formatTime(new Date()),
      'newTarget.end_time':[this.data.end_date]+" "+[this.data.end_time],
    })
    console.log(this.data.newTarget.start_time);
    console.log(this.data.newTarget.end_time);
    wx.request({
      url: '/user/target/add ', 
      data:{
        user_id:this.data.user_id,
        Target:this.data.newTarget,
      },
      method: "GET",
      success(res) {
        console.log(res.data)
        this.setData({
          'Target.push':res.data,
        })
      },
      fail(res) {
        console.log("fail")
      },
      complete: function () {
        console.log("ffff")
      }
    })
  },
  onLoad: function (options) {
    wx.request({
      url: '/user/target/history',
      data: this.user_id,
      method: "GET",
      success(res) {
        console.log(res.data)
        var target=this.data.Target;
        target.splice(0,0,this.data.newTarget);
        this.setData({
         Target:target,
        });
      },
      fail(res) {
        console.log("fail")
      },
      complete: function () {
        console.log("ffff")
      }
    })
  }
})
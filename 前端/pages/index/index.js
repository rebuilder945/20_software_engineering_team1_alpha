var util = require('../../utils/util');
Page({
  /**
   * 页面的初始数据
   */
  data: {
    user_id:1,
  
    say1:"请输入挑战目标",
    say2:"......",
    nowType: 0,
    searchState: "noneWxss",
    editState: "noneWxss",
    addState: "noneWxss",
    completeState: "journalEdit",
    Target: [{
        id:0,
        title: "第一个目标",
        content: "第一个目标内容",
        begin_time: "2020年3月ffff",
        end_time: "2020年4fffffff",
        status:false,
      },
      {
        id:1,
        title: "第二个目标",
        content: "第二个目标内容",
        begin_time: "2020年3月",
        end_time: "2020年4月",
        status:false,
      },
      {
        id:2,
        title: "第三个目标",
        content: "第三个目标内容",
        begin_time: "2020年3月",
        end_time: "2020年4月",
        status:false,
      },
      {
        id:3,
        title: "第四个目标",
        content: "第四个目标内容",
        begin_time: "2020年3月",
        end_time: "2020年4月",
        status:false,
      },
      {
        id:4,
        title: "第五个目标",
        content: "第五个目标内容",
        begin_time: "2020-5-173fqwqwf月",
        end_time: "2020年4月",
        status:false,
      }
    ],
    // Target:[],
    newTarget: {
      "user_id":"1",
	    "title":"第三个挑战",
	    "content":"洗袜子",
	    "begin_time":"20150101",
	    "end_time":"20190909",
	    "reminder":"true"
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
      'newTarget.begin_time': util.formatTime(new Date()),
      'newTarget.end_time':[this.data.end_date]+" "+[this.data.end_time],
      'addState':'noneWxss'
    })
    console.log(this.data.newTarget.begin_time);
    console.log(this.data.newTarget.end_time);
    wx.request({
      url: 'http://api.iminx.cn/user/challenges/add', 
      dataType:"JSON",
      data:{
        user_id:this.data.user_id,
        title:this.data.newTarget.title,
        content:this.data.newTarget.content,
        begin_time:this.data.newTarget.begin_time,
        end_time:this.data.newTarget.end_time,
        reminder:this.data.newTarget.reminder,
      },
      method: "POST",
      success: (res)=>{
        // console.log(res.data);
        // var push = JSON.parse(res.data);
        // this.setData({
          // 'Target.push': res.data,
        // })
        this.data.Target.unshift(this.data.newTarget);
        this.setData({
          Target:this.data.Target,
        })
      },
      fail(res) {
        console.log("fail")
      },
      complete: function () {
        console.log("ffff")
      }
    });
 /*   this.data.Target.push(this.data.newTarget);
    this.setData({
      Target:this.data.Target,
    });*/
  },
  onLoad: function (options) {
    wx.request({
      url: "http://api.iminx.cn/user/challenges",
      dataType:"JSON",
      data:{
        "user_id":this.data.user_id,
      },
      method: "POST",
      success: (res)=>{
        var target = JSON.parse(res.data).reverse();
        
        this.setData({Target:target});
      },
      fail(res) {
        console.log("fail")
      },
      complete: function () {
        console.log("ffff")
      }
    });
    // var targettemp= [{"reminder":false,"user_id":1,"end_time":1605813344000,"begin_time":1605813336000,"id":1,"title":"第一个挑战","content":"写亿行代码","status":false},{"reminder":false,"user_id":1,"end_time":0,"begin_time":0,"id":2,"title":"第一个挑战","content":"打亿行代码","status":false},{"reminder":false,"user_id":1,"end_time":0,"begin_time":0,"id":3,"title":"第一个挑战","content":"打亿行代码","status":false},{"reminder":true,"user_id":1,"end_time":20191000,"begin_time":20150000,"id":4,"title":"第三个挑战","content":"洗袜子","status":false},{"reminder":true,"user_id":1,"end_time":20191000,"begin_time":20150000,"id":5,"title":"第三个挑战","content":"洗袜子","status":false},{"reminder":true,"user_id":1,"end_time":20191000,"begin_time":20150000,"id":6,"title":"第三个挑战","content":"洗袜子","status":false},{"reminder":true,"user_id":1,"end_time":20191000,"begin_time":20150000,"id":7,"title":"第三个挑战","content":"洗袜子","status":false}];
    // this.setData({
    //  Target:target,
    // });
    console.log("new_one");
  },
  icon_change1:function(e){
    var num = e.currentTarget.dataset.index;
    console.log(this.data.Target[num]);
    wx.showModal({
      title: this.data.Target[num].title,
      content:this.data.Target[num].content,
      cancelText:"关闭",
      confirmText:"取消完成",
      success: (res) => {
        if (res.confirm) {
          // content.status = true;
          var set = 'Target['+num+'].status';
          // console.log(this.data.Target[content.id])
          this.setData({
            [set]:false
          }),
          wx.request({
            url: 'https://api.iminx.cn/user/challenges/uncomplete',
            dataType:"JSON",
            data:{
              user_id:this.data.user_id,
              challenge_id:this.data.Target[num].id
            },
            method:"POST",
            complete:(res)=>{
              
            }
          });
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
        
      }
    })
    // var set = 'Target['+content.id+'].status';
    // // console.log(set)
    // this.setData({
    //   'set':content.status
    // })
    // console.log(this.data.Target[content.id].status)
  },
  icon_change2:function(e){
    var num = e.currentTarget.dataset.index;
    console.log(this.data.Target[num]);
    wx.showModal({
      title: this.data.Target[num].title,
      content:this.data.Target[num].content,
      cancelText:"关闭",
      confirmText:"完成挑战",
      success: (res) => {
        if (res.confirm) {
          // content.status = true;
          var set = 'Target['+num+'].status';
          // console.log(this.data.Target[content.id])
          this.setData({
            [set]:true
          }),
          wx.request({
            url: 'https://api.iminx.cn/user/challenges/complete',
            dataType:"JSON",
            data:{
              user_id:this.data.user_id,
              challenge_id:this.data.Target[num].id
            },
            method:"POST",
            complete:(res)=>{
              
            }
          });
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
        
      }
    })
    // var set = 'Target['+content.id+'].status';
    // // console.log(set)
    // this.setData({
    //   'set':content.status
    // })
    // console.log(this.data.Target[content.id].status)
  },
  editTarget:function(e){
    console.log(e.currentTarget.dataset.id);
    var index=e.currentTarget.dataset.id;
    wx.navigateTo({
      url: '../log/edit_log?'+ '&title=' + this.data.Target[index].title + '&time=' + this.data.Target[index].end_time + '&content=' + this.data.Target[index].content 
    })
    //console.log(e);
    // var app = getApp();
    // if(e.currentTarget.dataset.isedit=="journalEdit"){
    //   wx.showModal({
    //     title:"提示",
    //     content:"是否删除",
    //     success:(res)=>{
    //       console.log("删除日志")
    //       wx.request({
    //         url: 'http://localhost:8080/user/challenges/delete',
    //         dataType:"JSON",
    //         method:"POST",
    //         data:{
    //           user_id:app.data.user_id,
    //           challenge_id:e.currentTarget.dataset.id
    //         },
    //         success:(res)=>{
    //           console.log("删除成功");
    //           this.onLoad();
    //         },
    //         complete:(res)=>{
    //           console.log("555~")
    //           this.onLoad();
    //         }
    //       })
    //     }
    //   })
    // }
  },


  handleDeleteProduct:function(e)
  {
    this.data.Target.splice(e.currentTarget.dataset.id,1);
    console.log(this.data.Target);
    this.setData({
      Target:this.data.Target,
    })
  }
})
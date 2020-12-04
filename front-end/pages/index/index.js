var util = require('../../utils/util');
const app = getApp();
Page({
  /**
   * 页面的初始数据
   */
  data: {
    xmoveid:-1,
    user_id:app.data.user_id,
    searchValue:"",
    say1:"请输入挑战目标",
    say2:"......",
    nowType: 0,
    nowSearch:false,
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
    ],
    searchTarget:[],
    temp:[],
    // Target:[],
    newTarget: {
      "user_id":app.data.user_id,
	    "title":"一个没有名字的挑战 (ಥ_ಥ)",
	    "content":"你忘了添加挑战的内容哦！",
	    "begin_time":"20150101",
	    "end_time":"20190909",
	    "reminder":"true"
    },
    end_date:util.formatTime(new Date()).split("/")[0]+'-'+util.formatTime(new Date()).split("/")[1]+'-'+util.formatTime(new Date()).split("/")[2].split(" ")[0],
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
    // console.log(this.data.newTarget.title);
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
    // console.log("shabia");
    console.log(this.data.newTarget.begin_time);
    console.log(this.data.newTarget.end_time);
    wx.serviceMarket.invokeService({
      service: 'wxee446d7507c68b11',
      api: 'msgSecCheck',
      data: {
        "Action": "TextApproval",
        "Text": this.data.newTarget.title+this.data.newTarget.content+this.data.newTarget.begin_time+this.data.newTarget.end_time+this.data.newTarget.reminder
        // "Text":"习近平"
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
          url: 'https://api.iminx.cn/user/challenges/add', 
          dataType:"JSON",
          data:{
            user_id:app.data.user_id,
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
            // console.log(res.data);
            var returnTarget=JSON.parse(res.data);
            this.setData({
              'newTarget.id':returnTarget.challenge_id
            })
            var newObj = Object.assign({},this.data.newTarget);
            this.data.Target.unshift(newObj);
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
      }
    });
    
 /*   this.data.Target.push(this.data.newTarget);
    this.setData({
      Target:this.data.Target,
    });*/
  },
  onShow:function(){
    this.onLoad();
    this.setData({
      editState: "noneWxss",
      completeState: "journalEdit",
    })
  },
  onLoad: function (options) {
    // console.log(util.formatTime(new Date()))
    // var a =util.formatTime(new Date()).split("/")[0]+'-'+util.formatTime(new Date()).split("/")[1]+'-'+util.formatTime(new Date()).split("/")[2].split(" ")[0];
    // console.log(a)
    var that=this;
    that.setData({
      user_id:getApp().data.user_id
    })
    console.log("woshisb")
    console.log(this.data.user_id)
    wx.request({
      url: "https://api.iminx.cn/user/challenges",
      dataType:"JSON",
      data:{
        "user_id":app.data.user_id,
      },
      method: "POST",
      success: (res)=>{
        var target = JSON.parse(res.data).reverse();
        for(var i=0;i<target.length;i++)
        {
          target[i].begin_time=util.formatTimeTwo(target[i].begin_time,'Y-M-D h:m');
          target[i].end_time=util.formatTimeTwo(target[i].end_time,'Y-M-D h:m');
        };
        this.setData({Target:target});
      },
      fail(res) {
        console.log("fail")
      },
    });

  },
  icon_change1:function(e){
 /*   var num = e.currentTarget.dataset.index;
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
    })*/
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
            success:(res)=>{
              wx.request({
                url: 'https://api.iminx.cn/user/diary/generate',
                method:"POST",
                dataType:"JSON",
                data:{
                  user_id:app.data.user_id
                },
                success:(res)=>{
                  console.log("日志生成成功");
                  console.log(res);
                  if(res.data=="目前没有后续情节了，考虑切换故事"){
                    wx.showModal({
                      title:"提示",
                      content:"你已经解锁了这个剧本的所有情节，快去探索新的剧本吧！",
                      cancelColor: 'red',
                    })
                  }
                }
              });
            },
            complete:(res)=>{
              
            },
            
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
    console.log(e)
    console.log(e.currentTarget.dataset.po);
    var index=e.currentTarget.dataset.po;
    wx.redirectTo({
      url: 'edit_log?'+ '&title=' + this.data.Target[index].title +'&begin_time=' + this.data.Target[index].begin_time+ '&end_time=' + this.data.Target[index].end_time + '&content=' + this.data.Target[index].content + '&challenge_id=' + this.data.Target[index].id + '&reminder=' + this.data.Target[index].reminder
    })
    //console.log(e);
    // var app = getApp();
    // if(e.currentTarget.dataset.isedit=="journalEdit"){edit_log
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
  setXmove: function (productIndex, xmove) {
    let productList = this.data.Target
    productList[productIndex].xmove = xmove
    this.setData({
     Target: productList
    })
  },
  showDeleteButton: function (e) {
    // console.log(13);
    let productIndex = e.currentTarget.dataset.productindex
    this.setXmove(productIndex, -70)
  },
  hideDeleteButton: function (e) {
    // console.log(12);
    let productIndex = e.currentTarget.dataset.productindex
    this.setXmove(productIndex, 0)
  },

  handleTouchStart(e) {
    // console.log(1);
    var xmoveid=this.data.xmoveid;
    if(xmoveid!=-1)
    {
      this.setXmove(this.data.xmoveid,0);
    }
    this.setData({
      xmoveid:e.currentTarget.dataset.productindex,
    })
    this.startX = e.touches[0].pageX;
  },
  handleTouchEnd(e) {
    // console.log(111);
    if(e.changedTouches[0].pageX < this.startX && e.changedTouches[0].pageX - this.startX <= -40) {
      this.showDeleteButton(e)
    }  else {
      this.hideDeleteButton(e)
    }
  },
  handleDeleteProduct:function(e)
  {
    wx.request({
      url: 'https://api.iminx.cn/user/challenges/delete', 
      dataType:"JSON",
      data:{
        user_id:app.data.user_id,
        challenge_id:this.data.Target[e.currentTarget.dataset.id].id,
      },
      method: "POST",
      success: (res)=>{
        console.log("删除成功")
      },
      fail(res) {
        console.log("fail")
      },
    });
    this.data.Target.splice(e.currentTarget.dataset.id,1);
    this.setData({
      Target:this.data.Target,
    })
  },
  search:function(e)
  {
    var searchText=this.data.searchValue;
    console.log(this.data.searchValue);
    this.setData({
      nowSearch:true
    })
    this.data.searchTarget.splice(0,this.data.searchTarget.length);
    for(var i=0;i<this.data.Target.length;i++)
    {
      if((this.data.Target[i].title.indexOf(searchText))>=0)
      {
        this.data.searchTarget.push(this.data.Target[i])
      }
    }
    this.setData({
      Target:this.data.searchTarget,
    });
  },
  searchReturn:function(e){
    this.setData({
      nowSearch:false
    })
    this.onShow();
  },
  searchInput:function(e){
    this.setData({
      searchValue:e.detail.value
    })
  },
  btp1:function(e){
    console.log(e.currentTarget.dataset.idx),
    console.log(this.data.Target[e.currentTarget.dataset.idx]),
    wx.showModal({
      title: this.data.Target[e.currentTarget.dataset.idx].title,
      content:this.data.Target[e.currentTarget.dataset.idx].content,
      success: (res) =>{
        if (res.confirm) {
          console.log('用户点击确定')
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
  },
  onPullDownRefresh: function () {
    this.onLoad();
    wx.stopPullDownRefresh({
      success: (res) => {},
    })
  },
})
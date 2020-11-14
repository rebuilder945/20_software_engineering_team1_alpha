Page({

  /**
   * 页面的初始数据
   */
  data: {
    nowType: 0,
    date: '2016-09-01',
    searchState: "noneWxss",
    editState: "noneWxss",
    addState: "noneWxss",
    Target: [{
        title: "第一个目标",
        content: "第一个目标内容",
        time: "2020年3月"
      },
      {
        title: "第二个目标",
        content: "第二个目标内容",
        time: "2020年3月"
      },
      {
        title: "第三个目标",
        content: "第三个目标内容",
        time: "2020年3月"
      },
      {
        title: "第四个目标",
        content: "第四个目标内容",
        time: "2020年3月"
      },
      {
        title: "第五个目标",
        content: "第五个目标内容",
        time: "2020年3月"
      }
    ]

  },
  getInputValue(e) {

  },
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
        nowType: 3,
      })
    } else if (type == 1) {
      this.setData({
        searchState:"noneWxss",
        editState: "journalEdit",
        nowType: 3,
      })
    } else if (type == 2) {
      this.setData({
        addState: "noneWxss",
        editState: "journalEdit",
        nowType: 3,
      })
    } else {
      this.setData({
        editState: "noneWxss",
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
        searchState:"noneWxss",
        addState: "addBackGround",
        nowType: 2,
      })
    }else if(type==2){
      this.setData({
        addState:"noneWxss",
        nowType: 0,
      })
    }else{
      this.setData({
        editState:"noneWxss",
        addState: "addBackGround",
        nowType: 2,
      })
    }
  },
bindDateChange: function (e) {
  console.log('picker发送选择改变，携带值为', e.detail.value)
  this.setData({
    date: e.detail.value
  })
},
})
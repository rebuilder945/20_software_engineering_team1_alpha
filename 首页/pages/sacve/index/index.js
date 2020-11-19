Page({

  /**
   * 页面的初始数据
   */
  data: {
    navbarActiveIndex: 0,
    navbarTitle: [
      "搜索",
      "新增",
      "修改"
    ],
    Target: [
      {title:"第一个目标",content:"第一个目标内容",time:"2020年3月"},
      {title:"第二个目标",content:"第二个目标内容",time:"2020年3月"},
      {title:"第三个目标",content:"第三个目标内容",time:"2020年3月"},
      {title:"第四个目标",content:"第四个目标内容",time:"2020年3月"},
      {title:"第五个目标",content:"第五个目标内容",time:"2020年3月"}
    ]

  },

  /**
   * 点击导航栏
   */
  onNavBarTap: function (event) {
    // 获取点击的navbar的index
    let navbarTapIndex = event.currentTarget.dataset.navbarIndex
    // 设置data属性中的navbarActiveIndex为当前点击的navbar
    this.setData({
      navbarActiveIndex: navbarTapIndex
    })
  },

  /**
   *
   */
  onBindAnimationFinish: function ({detail}) {
    // 设置data属性中的navbarActiveIndex为当前点击的navbar
    this.setData({
      navbarActiveIndex: detail.current
    })
  }
})
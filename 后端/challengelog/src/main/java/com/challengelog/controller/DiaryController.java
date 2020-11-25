package com.challengelog.controller;

import com.alibaba.fastjson.JSONObject;
import com.challengelog.mapper.*;
import com.challengelog.pojo.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@Repository
@RequestMapping("/user/diary")
public class DiaryController {
    @Autowired
    DiaryMapper diaryMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    StoryMapper storyMapper;

    @Autowired
    ChallengesMapper challengesMapper;

    @Autowired
    PlotMapper plotMapper;
    //查询用户所有日记
    public JSONObject queryDiaryById(@RequestBody JSONObject jsonParam){
        //根据user_id查询所有日记
        //定义变量及初始化
        JSONObject JsonObject = null;
        List<JSONObject> diaryList = new ArrayList();
        int user_id = jsonParam.getInteger("user_id");
        //查询


        //返回结果
        /*
        if() {

        }
        */
        return JsonObject;
    }


    //添加自定义日记
    @RequestMapping("/add")
    public JSONObject addDiary(@RequestBody JSONObject jsonParam){

        //定义变量及初始化
        JSONObject jsonObject = null;
        int user_id = jsonParam.getInteger("user_id");
        String title = jsonParam.getString("title");
        String content = jsonParam.getString("content");
        //插入


        //返回结果
        /*
        if(){
            jsonObject.put("status",true);
            jsonObject.put("msg","添加自定义日志成功");
            jsonObject.put("diary_id",);
            jsonObject.put("time",);
        }
        else{
            jsonObject.put("status",false);
            jsonObject.put("msg","添加失败");
        }
         */
        return jsonObject;
    }

    //修改日记
    @RequestMapping("/modify")
    public JSONObject updateDiary(@RequestBody JSONObject jsonParam){
        //变量定义及初始化
        int user_id = jsonParam.getInteger("user_id");
        int diary_id = jsonParam.getInteger("diary_id");
        String title = jsonParam.getString("title");
        String content = jsonParam.getString("content");
        JSONObject jsonObject = null;
        //修改



        //返回结果
        /*
        if(){
            jsonObject.put("status",true);
            jsonObject.put("msg","修改日志成功");
        }
        else{
            jsonObject.put("status",false);
            jsonObject.put("msg","修改失败");

        }
        */
        return jsonObject;
    }

    //生成日记
    @RequestMapping("/generate")
    public JSONObject generateDiary(@RequestBody JSONObject jsonParam){
        //变量及初始化
        int user_id = jsonParam.getInteger("user_id");
        JSONObject jsonObject = null;
        //查找用户信息
        User user = userMapper.queryUserById(user_id);

        int current_plot_id = user.getCurrent_plot_id();
        //查找用户今日挑战
        List<Challenges> challenges = challengesMapper.queryChallengesByDate(user_id,new Timestamp(System.currentTimeMillis()));

        //判断今日挑战中是否有未完成的
        int branch = 1;
        for(Challenges i:challenges){
            if(i.getStatus() == false){
                branch = 0;
                break;
            }
        }

        //找到对应情节
        Plot plot = plotMapper.queryNextPlot(current_plot_id,branch);

        //创建新日记
        Diary diary = null;
        diary.setUser_id(user_id);
        diary.setStory_id(user.getCurrent_story_id());
        diary.setContent_plot_id(user.getCurrent_plot_id());
        diary.setTime(new Timestamp(System.currentTimeMillis()));
        diary.setContent_userdifine("剧情："+plot.getContent());

        diary.setId(diaryMapper.insertDiary(diary));
        //返回结果

        if(true){
            jsonObject.put("diary_id",diary.getId());
            jsonObject.put("title","日记"+diary.getId());
            jsonObject.put("time",diary.getTime());
            jsonObject.put("content",diary.getContent_userdifine());
        }
        else{
            jsonObject.put("status",false);
            jsonObject.put("msg","生成失败");
        }

        return null;
    }

}

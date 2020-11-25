package com.challengelog.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.challengelog.mapper.StoryMapper;
import com.challengelog.pojo.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Repository
public class StoryController {
    @Autowired
    StoryMapper storyMapper;

    //查看剧本
    @RequestMapping("/user/story")
    public JSONArray queryStoryByUserId(@RequestBody JSONObject jsonParam){
        //变量定义及初始化
        int user_id = jsonParam.getInteger("user_id");
        JSONObject jsonObject = null;
        List<JSONObject> storyList = new ArrayList();

        //查询
        List<Story> stories = storyMapper.queryStoryByUserId(user_id);
        JSONArray resultArray = JSONArray.parseArray(JSON.toJSONString(stories));
        //返回结果
        /*
        if(){

        }
        */
        return resultArray;
    }

    //查看当前剧本
    @RequestMapping("/user/story/current")
    public JSONObject queryStoryById(@RequestBody JSONObject jsonParam){
        //变量定义及初始化
        int story_id = jsonParam.getInteger("story_id");
        //查询







        //返回结果
        /*
        if(){
            jsonObject.put("status",true);
            jsonObject.put("story_id",);
            jsonObject.put("msg","查看当前剧本成功");
        }
        else{
            jsonObject.put("status",false);
            jsonObject.put("msg","查看当前剧本失败");
        }
        */
        return null;
    }

    //修改（设置）当前剧本
    @RequestMapping("/user/story/current/change")
    public JSONObject changeStory(@RequestBody JSONObject jsonParam){
        //变量定义及初始化
        int user_id = jsonParam.getInteger("user_id");
        int story_id = jsonParam.getInteger("story_id");
        JSONObject jsonObject = null;

        //查询并更新



        //返回结果
        /*
        if(){
            jsonObject.put("status",true);
            jsonObject.put("msg","修改当前剧本成功");
        }
        else{
            jsonObject.put("status",false);
            jsonObject.put("msg","修改当前剧本失败");
        }
        */
        return jsonObject;


    }



}

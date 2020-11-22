package com.challengelog.controller;

import com.alibaba.fastjson.JSONObject;
import com.challengelog.mapper.DiaryMapper;
import com.challengelog.pojo.Diary;
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
import java.util.ArrayList;
import java.util.List;

@RestController
@Repository
@RequestMapping("/user/diary")
public class DiaryController {
    @Autowired
    DiaryMapper diaryMapper;

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

    @RequestMapping("/generate")
    public JSONObject generateDiary(@RequestBody JSONObject jsonParam){
        //变量及初始化
        int user_id = jsonParam.getInteger("user_id");
        JSONObject jsonObject = null;


        //返回结果
        /*
        if(){
            jsonObject.put("diary_id",);
            jsonObject.put("title",);
            jsonObject.put("time",);
            jsonObject.put("content",);
        }
        else{
            jsonObject.put("status",false);
            jsonObject.put("msg","生成失败");
        }
        */
        return jsonObject;
    }

}

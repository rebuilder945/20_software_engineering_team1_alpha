package com.challengelog.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.challengelog.mapper.ChallengesMapper;
import com.challengelog.mapper.PlotMapper;
import com.challengelog.mapper.StoryMapper;
import com.challengelog.mapper.UserMapper;
import com.challengelog.pojo.Challenges;
import com.challengelog.pojo.Story;
import com.challengelog.pojo.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/challenges")
public class ChallengesController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PlotMapper plotMapper;

    @Autowired
    ChallengesMapper challengesMapper;


    @RequestMapping()
    public String getuserallchallenges(@RequestBody JSONObject jsonObject) {

//        System.out.println(jsonObject.getInteger("user_id"));
//        System.out.println(challengesMapper.queryChallengesByUserId(jsonObject.getInteger("user_id")).getClass());
        JSONArray challengeslist =  JSONObject.parseArray(JSONObject.toJSONString(challengesMapper.queryChallengesByUserId(jsonObject.getInteger("user_id"))));
        System.out.println(challengeslist);

        return challengeslist.toJSONString();
    }


    @RequestMapping("/add")
    public String addchallenges(@RequestBody JSONObject jsonObject) {
        int user_id = jsonObject.getInteger("user_id");
        String title = jsonObject.getString("title");
        String content = jsonObject.getString("content");
        Timestamp begin_time = jsonObject.getTimestamp("begin_time");
        Timestamp end_time = jsonObject.getTimestamp("end_time");
        boolean reminder = jsonObject.getBoolean("reminder");
        Challenges challenge = new Challenges(user_id, title, content, begin_time, end_time, false, reminder);
        challengesMapper.insertChallenges(challenge);
        Integer challenge_id = challenge.getId();

        HashMap<String, String> res = new HashMap<>();
        res.put("msg", "新增挑战成功");
        res.put("challenge_id",challenge_id.toString());
        res.put("status", "true");
        System.out.println(res);
        return res.toString();
    }

    @RequestMapping("/update")
    public String updatechallenges(@RequestBody JSONObject jsonObject) {
        int challenge_id = jsonObject.getInteger("challenge_id");
        int user_id = jsonObject.getInteger("user_id");
        String title = jsonObject.getString("title");
        String content = jsonObject.getString("content");
        Timestamp begin_time = jsonObject.getTimestamp("begin_time");
        Timestamp end_time = jsonObject.getTimestamp("end_time");
        boolean reminder = jsonObject.getBoolean("reminder");
        Challenges challenge = new Challenges(challenge_id, user_id, title, content, begin_time, end_time, false, reminder);
        challengesMapper.updateChallenges(challenge);

        HashMap<String, String> res = new HashMap<>();
        res.put("msg", "修改挑战成功");
        res.put("status", "true");
        System.out.println(res);
        return res.toString();
    }

    @RequestMapping("/complete")
    public String completechallenges(@RequestBody JSONObject jsonObject) {
        int user_id = jsonObject.getInteger("user_id");
        int challenge_id = jsonObject.getInteger("challenge_id");
        System.out.println(user_id);
        System.out.println(challenge_id);
        challengesMapper.completeChallenges(user_id, challenge_id);

//        HashMap<String, String> res = new HashMap<>();
        JSONObject res = new JSONObject();
        res.put("msg", "完成挑战成功");
        res.put("status", "true");
        System.out.println(res);
        return res.toJSONString();
    }

    @RequestMapping("/uncomplete")
    public String uncompletechallenges(@RequestBody JSONObject jsonObject) {
        int user_id = jsonObject.getInteger("user_id");
        int challenge_id = jsonObject.getInteger("challenge_id");
        System.out.println(user_id);
        System.out.println(challenge_id);
        challengesMapper.uncompleteChallenges(user_id, challenge_id);

//        HashMap<String, String> res = new HashMap<>();
        JSONObject res = new JSONObject();
        res.put("msg", "取消完成挑战成功");
        res.put("status", "true");
        System.out.println(res);
        return res.toJSONString();
    }

    @RequestMapping("/delete")
    public String deletechallenges(@RequestBody JSONObject jsonObject) {
        int user_id = jsonObject.getInteger("user_id");
        int challenge_id = jsonObject.getInteger("challenge_id");
        System.out.println(user_id);
        System.out.println(challenge_id);
        challengesMapper.deleteChallengesById(challenge_id);

//        HashMap<String, String> res = new HashMap<>();
        JSONObject res = new JSONObject();
        res.put("msg", "删除挑战成功");
        res.put("status", "true");
        System.out.println(res);
        return res.toJSONString();
    }
}
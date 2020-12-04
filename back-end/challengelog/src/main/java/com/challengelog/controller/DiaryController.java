package com.challengelog.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.challengelog.mapper.*;
import com.challengelog.pojo.Challenges;
import com.challengelog.pojo.Diary;
import com.challengelog.pojo.Plot;
import com.challengelog.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

@RestController
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


    @RequestMapping()
    public String queryDiaryByUserId(@RequestBody JSONObject jsonObject) {
        int user_id = jsonObject.getInteger("user_id");
        List<Diary> diaryList = diaryMapper.queryDiaryByUserId(jsonObject.getInteger("user_id"));
//        JSONArray diarylist =  JSONObject.parseArray(JSONObject.toJSONString(diaryMapper.queryDiaryByUserId(jsonObject.getInteger("user_id"))));
        ArrayList<JSONObject> res = new ArrayList<>();
        for (Diary diary:diaryList
             ) {
//            System.out.println(diary);
            JSONObject temp = new JSONObject();
//            HashMap<String, String> temp = new HashMap<>();
//            System.out.println(plotMapper.queryPlotById());
            int content_plot_id = diary.getContent_plot_id();
//            System.out.println(content_plot_id);
            if (content_plot_id == 0) {
                temp.put("content", "NULL");
            }else {
                String content = plotMapper.queryPlotById(diary.getContent_plot_id()).getContent();
                temp.put("content", content);
            }

            if (diary.getContent_userdefine() == null) {
                temp.put("content_userdefine", "Null");
            }else{
                temp.put("content_userdefine", diary.getContent_userdefine());
            }
            temp.put("time",diary.getTime().toString());
            temp.put("title", diary.getTitle());
            temp.put("diary_id",String.valueOf(diary.getId()));

            res.add(temp);
        }

        return res.toString();
    }

    @RequestMapping("/generate")
    public String generatediary(@RequestBody JSONObject jsonObject) {
        int user_id = jsonObject.getInteger("user_id");
        User user = userMapper.queryUserById(user_id);
        int current_plot_id = user.getCurrentPlotId();
        int story_id = user.getCurrentStoryId();
        List<Challenges> challengesList = challengesMapper.queryChallengesByUserId((user_id));

        String content_userdefine = "";
        int flag = 1;
        for (Challenges challenge:challengesList
             ) {
            if (challenge.getStatus() == false) {
                flag = 0;
            }
        }
        System.out.println(flag);
        if (flag == 1) {
            content_userdefine = "由于你今天所有的挑战都完成啦，接下来的剧情像下面这样发展：";
        } else {
            content_userdefine = "由于你今天有的挑战没有完成，所以故事会这样发展：";
        }
//        System.out.println(content_userdefine);
        Plot aplot = null;
        List<Plot> plotList = plotMapper.queryBranchPlot(current_plot_id);
        if (plotList.size() < 1) {
            return "目前没有后续情节了，考虑切换故事";
        }

        for (Plot plot:plotList
             ) {
//            System.out.println(plot.getId());
            if (plot.getBranch() == flag) {
                aplot = plot;
            }
        }
        if (aplot == null) {
            aplot = plotList.get(0);
        }
        String title = aplot.getContent().substring(0, 15);
        Diary diary = new Diary(user_id, story_id, title, content_userdefine, aplot.getId());
        diaryMapper.insertDiary(diary);
        user.setCurrent_plot_id(aplot.getId());
        userMapper.updateUser(user);
        String content = aplot.getContent();
        JSONObject res = new JSONObject();
//        HashMap<String, String> res = new HashMap<>();
        res.put("content", content);
        res.put("time",diary.getTime().toString());
        res.put("title", diary.getTitle());
        res.put("diary_id",String.valueOf(diary.getId()));
        System.out.println(res);
        return res.toJSONString();
    }

    @RequestMapping("/add")
    public String adddiary(@RequestBody JSONObject jsonObject) {
        int user_id = jsonObject.getInteger("user_id");
        String title = jsonObject.getString("title");
        String content = jsonObject.getString("content");
        System.out.println(content);
        Diary diary = new Diary(user_id, title, content);
        diaryMapper.insertDiary1(diary);
        System.out.println(diary);

        Integer diary_id = diary.getId();
        Timestamp timestamp = diary.getTime();
//        HashMap<String, String> res = new HashMap<>();
        JSONObject res = new JSONObject();
        res.put("time", timestamp.toString());
        res.put("diary_id",diary_id.toString());
        res.put("msg", "添加自定义日志成功");
        res.put("status", "true");
        System.out.println(res);
        return res.toJSONString();
    }


    @RequestMapping("/modify")
    public String modifydiary(@RequestBody JSONObject jsonObject) {
        int user_id = jsonObject.getInteger("user_id");
        int diary_id = jsonObject.getInteger("diary_id");
        String title = jsonObject.getString("title");
        String content = jsonObject.getString("content");
        Diary diary = new Diary(diary_id, user_id, title, content);
        diaryMapper.updateDiary(diary);
        System.out.println(diary);

        Timestamp timestamp = diary.getTime();
//        HashMap<String, String> res = new HashMap<>();
        JSONObject res = new JSONObject();
        res.put("msg", "修改日志成功");
        res.put("status", "true");
        System.out.println(res);
        return res.toJSONString();
    }

}

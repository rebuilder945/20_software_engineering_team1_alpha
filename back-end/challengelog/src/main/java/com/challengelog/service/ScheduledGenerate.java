package com.challengelog.service;


import com.alibaba.fastjson.JSONObject;
import com.challengelog.mapper.ChallengesMapper;
import com.challengelog.mapper.DiaryMapper;
import com.challengelog.mapper.PlotMapper;
import com.challengelog.mapper.UserMapper;
import com.challengelog.pojo.Challenges;
import com.challengelog.pojo.Diary;
import com.challengelog.pojo.Plot;
import com.challengelog.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

@Component
@EnableScheduling   // 1.开启定时任务
@EnableAsync        // 2.开启多线程
public class ScheduledGenerate {
    @Autowired
    UserMapper userMapper;

    @Autowired
    ChallengesMapper challengesMapper;

    @Autowired
    PlotMapper plotMapper;

    @Autowired
    DiaryMapper diaryMapper;

    public String generatediary(int user_id) {
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
        if (flag == 1) {
            content_userdefine = "由于你今天所有的挑战都完成啦，接下来的剧情像下面这样发展：";
        } else {
            content_userdefine = "由于你今天有的挑战没有完成，所以故事会这样发展：";
        }
        Plot aplot = null;
        List<Plot> plotList = plotMapper.queryBranchPlot(current_plot_id);
        if (plotList.size() < 1) {
            return "目前没有后续情节了，考虑切换故事";
        }
        for (Plot plot:plotList
        ) {
            System.out.println(plot.getId());
            if (plot.getBranch() == flag) {
                aplot = plot;
            }
        }
        if (aplot == null) {
            aplot = plotList.get(0);
        }
        String title = aplot.getContent().substring(0, 15);
        Diary diary = new Diary(user_id, story_id, title, content_userdefine ,aplot.getId());
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

    @Async
    @Scheduled(cron = "0 0 23 * * ?")
    public void GenerateDiary() throws InterruptedException {
        List<User> userList = userMapper.queryUserList();
        for (User user:userList
             ) {
            generatediary(user.getId());
        }
    }
}
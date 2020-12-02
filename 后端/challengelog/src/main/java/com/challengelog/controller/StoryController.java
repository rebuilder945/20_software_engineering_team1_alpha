package com.challengelog.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.challengelog.mapper.StoryMapper;
import com.challengelog.mapper.UserMapper;
import com.challengelog.mapper.UserstoryMapper;
import com.challengelog.pojo.Story;
import com.challengelog.pojo.User;
import com.challengelog.pojo.Userstory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.SliderUI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user/story")
public class StoryController {

    @Autowired
    StoryMapper storyMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserstoryMapper userstoryMapper;

    @RequestMapping()
    public String getStoryByUserId(@RequestBody JSONObject jsonObject) {
        int user_id = jsonObject.getInteger("user_id");
        User user = userMapper.queryUserById(user_id);
//        int current_story_id = user.getCurrentStoryId();
        ArrayList<Integer> storyIdList = userstoryMapper.queryStoryIdByUserId(user_id);
        JSONArray StroyList = new JSONArray();
//        List<Story> StoryList = new ArrayList<>();
        for (Integer integer:storyIdList
             ) {
            Story storytemp = storyMapper.queryStoryById(integer);
            JSONObject res = new JSONObject();
            res.put("story_id", storytemp.getId());
            res.put("story_name", storytemp.getStory_name());
            res.put("cover_url", storytemp.getCover_url());
            StroyList.add(res);
        }
        return StroyList.toJSONString();
    }


    @RequestMapping("/current")
    public String getCurrentStoryByuserId(@RequestBody JSONObject jsonObject) {
        int user_id = jsonObject.getInteger("user_id");
        User user = userMapper.queryUserById(user_id);
        System.out.println(user);
        Integer story_id = user.getCurrentStoryId();

        JSONObject res = new JSONObject();
//        HashMap<String, String> res = new HashMap<>();
        res.put("msg", "查看当前剧本成功");
        res.put("story_id",story_id.toString());
        res.put("status", "true");
        System.out.println(res);
        return res.toJSONString();
    }

    @RequestMapping("/current/change")
    public String changeCurrentStoryByuserId(@RequestBody JSONObject jsonObject) {
        int user_id = jsonObject.getInteger("user_id");
        int story_id = jsonObject.getInteger("story_id");
        System.out.println(story_id);
        int plot_id = storyMapper.queryBeginPlotIdByStoryId(story_id);
        userMapper.changeCurrentStoryId(user_id, story_id);
        userMapper.changeCurrentPlotId(user_id, plot_id);
//        HashMap<String, String> res = new HashMap<>();
        JSONObject res = new JSONObject();
        res.put("msg", "修改当前剧本成功");
        res.put("status", "true");
        System.out.println(res);
        return res.toJSONString();
    }

}

package com.challengelog.controller;


import com.alibaba.fastjson.JSONObject;
import com.challengelog.mapper.StoryMapper;
import com.challengelog.pojo.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    StoryMapper storyMapper;

    @RequestMapping("story")
    public String getAllStory(@RequestBody JSONObject jsonObject) {
        int story_id = jsonObject.getInteger("story_id");
        Story story = storyMapper.queryStoryById(story_id);

        JSONObject res = new JSONObject();
//        HashMap<String, String> res = new HashMap<>();
        res.put("msg", "查询剧本成功");
        res.put("description", story.getDescription());
        res.put("cover_url", story.getCover_url());
        res.put("story_name",story.getStory_name());
        res.put("status", "true");
        System.out.println(res);
        return res.toJSONString();
//        JSONObject.parseArray(story.toString());
//        return (story.toString());
//        return JSONObject.parseArray(story.toString()).toJSONString();
    }
}

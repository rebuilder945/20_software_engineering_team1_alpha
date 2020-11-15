package com.challengelog.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.challengelog.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/userList")
    public List<Map<String,Object>> userList(){
        //这里mysql查询语句别忘记带分号
        String sql = "select * from user;";
        List<Map<String,Object>> list_maps = jdbcTemplate.queryForList(sql);
        return list_maps;
    }

    @ResponseBody
    @RequestMapping("/register")
    public String register(@RequestBody JSONObject jsonParam) {
        System.out.println(jsonParam.toJSONString());
        int userid = Integer.parseInt(jsonParam.get("userid").toString());
        System.out.println(userid);
        String open_id = "temp";
        User user = new User(userid, open_id, "nick_name", "none", "男", DateFormat.getInstance());
        String sql = "insert into user values(" + user.getId() + "," + user.getOpen_id() + ","
                + user.getNick_name() + "," + user.getAvatar_url() + "," + user.getGender() + ","
                + user.getCreate_time() + ");";
        System.out.println(sql);

////        String sql = "insert into user values(3,'open_id','nick_name', 'avatar_url', '男', current_time);";
//        System.out.println(jdbcTemplate.update(sql));
//        String sql1 = "select * from challenges;";
//        List<Map<String,Object>> list_maps = jdbcTemplate.queryForList(sql1);
////        JSONObject responsesjson = new JSONObject();
////        String temp = "{'status': true}";
////        return JSON.toJSONString(temp);
////        return list_maps.toString();
        return "none";
    }

    @RequestMapping("/login")
    public String userlogin() {
        return "userlogin";
    }

    @RequestMapping("/update")
    public String update() {
        return "update";
    }

    @RequestMapping("/target/add")
    public String targetadd() {
        return "targetadd";
    }

    @RequestMapping("/target/complete")
    public String targetcomplete() {
        return "targetcomplete";
    }

    @RequestMapping("/target/fail")
    public String targetfail() {
        return "targetfail";
    }

    @RequestMapping("/target/history")
    public String targethistory() {
        return "targethistory";
    }

    @RequestMapping("/target/delete")
    public String targetdelete() {
        return "targetdelete";
    }

    @RequestMapping("/target/complete/unlock")
    public String targetcompleteunlock() {
        return "targetcompleteunlock";
    }

    @RequestMapping("/line/story/find")
    public String linestoryfind() {
        return "linestoryfind";
    }

    @RequestMapping("/line/story/add")
    public String linestoryadd() {
        return "linestoryadd";
    }

    @RequestMapping("/line/story/choose")
    public String linestorychoose() {
        return "linestorychoose";
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hellod";
    }
}
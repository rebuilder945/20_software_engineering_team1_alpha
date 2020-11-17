package com.challengelog.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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


    @RequestMapping("/register")
    public String register(@RequestBody JSONObject jsonParam) {
//        System.out.println(jsonParam.toJSONString());
//        int userid = Integer.parseInt(jsonParam.get("userid").toString());
//        System.out.println(userid);
//        String open_id = "temp";
//        User user = new User(userid, open_id, "nick_name", "none", "男", DateFormat.getInstance());
////        System.out.println(user);
//        String sql = "insert into user values(" + "'" + user.getId() + "," + user.getOpen_id() + ","
//                + user.getNick_name() + "," + user.getAvatar_url() + "," + user.getGender() + ","
//                + user.getCreate_time() + ");";
//        System.out.println(sql);
//
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
    public String userlogin(@RequestBody JSONObject jsonParam) {
        System.out.println(jsonParam.toJSONString());
        String user_id = jsonParam.get("user_id").toString();
        System.out.println(user_id);
        String sql = "select * from user where id = " + "'" + user_id + "'" + ";"  ;
        List<Map<String,Object>> list_maps = jdbcTemplate.queryForList(sql);
        JSONObject jsonObject = new JSONObject();
        if (!list_maps.isEmpty()) {
            jsonObject.put("msg", "登录成功");
            jsonObject.put("status",true);
        } else {
            jsonObject.put("msg", "登录失败");
            jsonObject.put("status",false);
        }
        return jsonObject.toJSONString();
    }

    @RequestMapping("/update")
    public String update() {

        return "update";
    }

    @RequestMapping("/target/add")
    public String targetadd(@RequestBody JSONObject jsonParam) {
//        System.out.println(jsonParam);
        String user_id = jsonParam.get("user_id").toString();
        String contant = jsonParam.getJSONObject("target").get("content").toString();
        String title = jsonParam.getJSONObject("target").get("title").toString();
//        System.out.println(jsonParam.getJSONObject("target").get("title"));
//        String sql = "select challenges.id from challenges, user where challenges.user_id = user.id and ;";
//        List<Map<String,Object>> list_maps = jdbcTemplate.queryForList(sql);
        JSONObject jsonObject = new JSONObject();
        if (true) {
            String sql = "insert into challenges values (0," + "'" + user_id + "'" + ", 1, " +"'" +  contant + "'," + "current_time, current_time, 0, 'tags', 0, " + "'" + title + "'" + ");";
            System.out.println(sql);
            jdbcTemplate.update(sql);
            jsonObject.put("msg", "新增目标成功");
            jsonObject.put("status",true);
        } else {
            jsonObject.put("msg", "新增目标失败");
            jsonObject.put("status",false);
        }
        return jsonObject.toJSONString();
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
    public String linestoryfind(@RequestBody JSONObject jsonParam) {

        return jsonParam.toJSONString();
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
package com.challengelog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/userList")
    public List<Map<String,Object>> userList(){
        //宝贝，这里mysql查询语句别忘记带分号
        String sql = "select * from user;";
        List<Map<String,Object>> list_maps = jdbcTemplate.queryForList(sql);
        return list_maps;
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
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
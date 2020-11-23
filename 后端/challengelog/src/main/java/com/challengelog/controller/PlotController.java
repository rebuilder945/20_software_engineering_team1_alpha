package com.challengelog.controller;


import com.alibaba.fastjson.JSONObject;
import com.challengelog.mapper.PlotMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
public class PlotController {

    @Autowired
    PlotMapper plotMapper;

    @RequestMapping("/temp")
    public String temp() {
        return "temp";
    }
}

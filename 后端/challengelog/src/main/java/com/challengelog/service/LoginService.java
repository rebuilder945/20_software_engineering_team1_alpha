package com.challengelog.service;


import com.challengelog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginService {

    @Autowired
    UserMapper userMapper;



}

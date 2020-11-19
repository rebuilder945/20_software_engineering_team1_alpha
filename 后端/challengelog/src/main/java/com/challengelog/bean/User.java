package com.challengelog.bean;

import java.text.DateFormat;
import java.util.Date;

public class User {
    private int id;
    private String open_id;
    private String nick_name;
    private String avatar_url;
    private String gender;
    private DateFormat create_time;

    public User(int id, String open_id, String nick_name, String avatar_url, String gender, DateFormat create_time) {
        this.id = id;
        this.open_id = open_id;
        this.nick_name = nick_name;
        this.avatar_url = avatar_url;
        this.gender = gender;
        this.create_time = create_time;
    }

    public int getId() {
        return id;
    }

    public String getOpen_id() {
        return open_id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getGender() {
        return gender;
    }

    public DateFormat getCreate_time() {
        return create_time;
    }
}

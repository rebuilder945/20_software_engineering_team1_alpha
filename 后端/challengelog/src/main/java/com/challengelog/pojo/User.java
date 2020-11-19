package com.challengelog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id;
    String open_id;
    String nick_name;
    String avatar_url;
    String gender;
    Timestamp create_time;
    int current_story_id;

    public User(String open_id, String nick_name, String avatar_url, String gender, int current_story_id) {
        this.open_id = open_id;
        this.nick_name = nick_name;
        this.avatar_url = avatar_url;
        this.gender = gender;
        this.current_story_id = current_story_id;
    }
}

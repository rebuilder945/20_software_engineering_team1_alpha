package com.challengelog.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diary {

    int id;
    int user_id;
    int story_id;
    Timestamp time;
    int content_plot_id;
    String content_userdefine;

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setStory_id(int story_id) {
        this.story_id = story_id;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public void setContent_plot_id(int content_plot_id) {
        this.content_plot_id = content_plot_id;
    }

    public void setContent_userdefine(String content_userdifine) {
        this.content_userdefine = content_userdifine;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getStory_id() {
        return story_id;
    }

    public Timestamp getTime() {
        return time;
    }

    public int getContent_plot_id() {
        return content_plot_id;
    }

    public String getContent_userdefine() {
        return content_userdefine;
    }
}

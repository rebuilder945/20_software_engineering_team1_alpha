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
    String title;
    int content_plot_id;
    String content_userdefine;

    public Diary(int id, int user_id, String title, String content_userdefine) {
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.content_userdefine = content_userdefine;
    }

    public Diary(int user_id, String title, String content_userdefine) {
        this.user_id = user_id;
        this.title = title;
        this.content_userdefine = content_userdefine;
        this.time = new Timestamp(System.currentTimeMillis());
    }

    public Diary(int user_id, int story_id, String title,String content_userdefine, int content_plot_id) {
        this.user_id = user_id;
        this.story_id = story_id;
        this.title = title;
        this.content_userdefine = content_userdefine;
        this.content_plot_id = content_plot_id;
        this.time = new Timestamp(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public Timestamp getTime() {
        return time;
    }

    public int getContent_plot_id() {
        return content_plot_id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent_userdefine() {
        return content_userdefine;
    }
}

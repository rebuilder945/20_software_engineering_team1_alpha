package com.challengelog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {

    private int id;
    private String open_id;
    private String nick_name;
    private String avatar_url;
    private String gender;
    private Timestamp create_time;
    private int current_story_id;
    private int current_plot_id;

    public User(String open_id, String nick_name, String avatar_url, String gender, int current_story_id) {
        this.open_id = open_id;
        this.nick_name = nick_name;
        this.avatar_url = avatar_url;
        this.gender = gender;
        this.current_story_id = current_story_id;
    }

    public int getCurrentStoryId() {
        return current_story_id;
    }

    public int getCurrentPlotId() {
        return current_plot_id;
    }

    public String getOpen_id() {
        return open_id;
    }

    public int getId() {
        return id;
    }

    public void setCurrent_plot_id(int current_plot_id) {
        this.current_plot_id = current_plot_id;
    }

}

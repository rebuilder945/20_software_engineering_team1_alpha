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
    int current_plot_id;

    public User(String open_id, String nick_name, String avatar_url, String gender, int current_story_id) {
        this.open_id = open_id;
        this.nick_name = nick_name;
        this.avatar_url = avatar_url;
        this.gender = gender;
        this.current_story_id = current_story_id;
    }

    public int getCurrent_plot_id() {
        return current_plot_id;
    }

    public void setCurrent_plot_id(int current_plot_id) {
        this.current_plot_id = current_plot_id;
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

    public Timestamp getCreate_time() {
        return create_time;
    }

    public int getCurrent_story_id() {
        return current_story_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public void setCurrent_story_id(int current_story_id) {
        this.current_story_id = current_story_id;
    }
}

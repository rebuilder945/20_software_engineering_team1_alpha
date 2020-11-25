package com.challengelog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Challenges {

    int id;
    int user_id;
    String title;
    String content;
    Timestamp begin_time;
    Timestamp end_time;
    Boolean status;
    Boolean reminder;

    public Challenges(int user_id, String title, String content, Timestamp begin_time, Timestamp end_time, Boolean status, Boolean reminder) {
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.begin_time = begin_time;
        this.end_time = end_time;
        this.status = status;
        this.reminder = reminder;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getBegin_time() {
        return begin_time;
    }

    public Timestamp getEnd_time() {
        return end_time;
    }

    public Boolean getStatus() {
        return status;
    }

    public Boolean getReminder() {
        return reminder;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setBegin_time(Timestamp begin_time) {
        this.begin_time = begin_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setReminder(Boolean reminder) {
        this.reminder = reminder;
    }
}

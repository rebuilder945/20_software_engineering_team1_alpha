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
}

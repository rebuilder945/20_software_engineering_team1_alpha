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
    String content_userdifine;

}

package com.challengelog.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Story {

    int id;
    String story_name;
    String description;
    String cover_url;
    int plot_begin_node_id;



}

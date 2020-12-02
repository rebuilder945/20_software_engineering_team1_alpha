package com.challengelog.pojo;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Story {

    private int id;
    private String story_name;
    private String description;
    private String cover_url;
    private int plot_begin_node_id;

    public int getId() {
        return id;
    }

    public String getStory_name() {
        return story_name;
    }

    public String getDescription() {
        return description;
    }

    public String getCover_url() {
        return cover_url;
    }

    public int getPlot_begin_node_id() {
        return plot_begin_node_id;
    }
}

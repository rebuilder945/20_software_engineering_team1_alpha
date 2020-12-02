package com.challengelog.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plot {

    int id;
    String plot_name;
    String content;
    int parent_id;
    int branch;

    public int getBranch() {
        return branch;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}

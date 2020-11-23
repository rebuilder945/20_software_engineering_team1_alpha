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

    public int getId() {
        return id;
    }

    public String getPlot_name() {
        return plot_name;
    }

    public String getContent() {
        return content;
    }

    public int getParent_id() {
        return parent_id;
    }

    public int getBranch() {
        return branch;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlot_name(String plot_name) {
        this.plot_name = plot_name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public void setBranch(int branch) {
        this.branch = branch;
    }
}

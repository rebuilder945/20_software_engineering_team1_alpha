package com.challengelog.mapper;

import com.challengelog.pojo.Plot;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PlotMapper {

    Plot queryPlotById(int id);

    List<Plot> queryBranchPlot(int parent_id);


}

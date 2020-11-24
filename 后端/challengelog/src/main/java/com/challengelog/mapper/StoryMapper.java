package com.challengelog.mapper;

import com.challengelog.pojo.Story;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StoryMapper {

    Story queryStoryById(int id);

    List<Story> queryStoryByUserId(int id);

}

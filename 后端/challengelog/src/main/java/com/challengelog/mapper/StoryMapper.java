package com.challengelog.mapper;

import com.challengelog.pojo.Story;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StoryMapper {

    Story queryStoryById(int id);

}

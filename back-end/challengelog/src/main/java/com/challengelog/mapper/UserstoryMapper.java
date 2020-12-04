package com.challengelog.mapper;


import com.challengelog.pojo.Story;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface UserstoryMapper {

    ArrayList<Integer> queryStoryIdByUserId(int user_id);

    int insertUserStory(int user_id, int story_id);

}

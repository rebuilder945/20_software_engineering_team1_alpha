package com.challengelog.mapper;


import com.challengelog.pojo.Diary;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DiaryMapper {

    Diary queryDiaryById(int id);

    List<Diary> queryDiaryByUserId(int user_id);

    int insertDiary(Diary diary);

    int insertDiary1(Diary diary);

    int updateDiary(Diary diary);

    int deleteDiaryById(int id);
}

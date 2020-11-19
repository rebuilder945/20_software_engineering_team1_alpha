package com.challengelog.mapper;


import com.challengelog.pojo.Diary;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DiaryMapper {

    Diary queryDiaryById();

    int insertDiary(Diary diary);

    Diary updateDiary(Diary diary);

    int deleteDiaryById(int id);

}

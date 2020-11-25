package com.challengelog.mapper;


import com.challengelog.pojo.Diary;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.*;

@Mapper
@Repository
public interface DiaryMapper {

    Diary queryDiaryById();

    Diary queryDiaryByUserId(int id);


    int insertDiary(Diary diary);

    Diary updateDiary(Diary diary);



    int deleteDiaryById(int id);

}

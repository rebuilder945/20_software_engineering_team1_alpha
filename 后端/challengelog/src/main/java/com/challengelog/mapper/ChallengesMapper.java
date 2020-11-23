package com.challengelog.mapper;


import com.challengelog.pojo.Challenges;
import com.challengelog.pojo.Diary;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Mapper
@Repository
public interface ChallengesMapper {

    Challenges queryChallengesById(int id);

    int insertChallenges(Challenges challenges);

    int updateChallenges(Challenges challenges);

    List<Challenges> queryChallengesByDate(int user_id, Timestamp today_date);

    int deleteChallengesById(int id);
}

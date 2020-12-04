package com.challengelog.mapper;


import com.challengelog.pojo.Challenges;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ChallengesMapper {

    Challenges queryChallengesById(int id);

    List<Challenges> queryChallengesByUserId(int user_id);

    int insertChallenges(Challenges challenges);

    int updateChallenges(Challenges challenges);

    int deleteChallengesById(int id);

    int completeChallenges(int user_id, int id);

    int uncompleteChallenges(int user_id, int id);
}

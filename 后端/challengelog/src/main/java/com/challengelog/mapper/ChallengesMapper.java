package com.challengelog.mapper;


import com.challengelog.pojo.Challenges;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ChallengesMapper {

    Challenges queryChallengesById(int id);

    int insertChallenges(Challenges challenges);

    int updateChallenges(Challenges challenges);

    int deleteChallengesById(int id);
}

package com.challengelog.mapper;


import com.challengelog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


//表示是stringboot mapper接口
@Mapper
@Repository
public interface UserMapper {

    List<User> queryUserList();

    User queryUserById(int id);

    int insertUser(User user);

    int updateUser(User user);

    int updateUserPlot(int id,int current_plot_id);

    int deleteUserById(int id);

}

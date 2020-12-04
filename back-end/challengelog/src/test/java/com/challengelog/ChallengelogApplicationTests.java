package com.challengelog;

import com.challengelog.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class ChallengelogApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    DataSource dataSource;
    @Test
    void contextLoads() {

        System.out.println(dataSource.getClass());

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            System.out.println(connection);
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}

package MysqlOperation;

import java.sql.*;
import com.alibaba.fastjson.*;
import java.util.*;

public class MysqlInsert {
    /*
    数据库插入
    Place:插入的表以及插入的某列名
    InsertList:传入的表（一列一个list,list内的值均为字符串型）
    example:
    InsertList:
        List[1]:id List[2]:value
        1           2
        2           3
        3           4
    Sqlsite:Sql地址
    格式：INSERT INTO Place VALUES (InsertList[1][i],InsertList[2][i]...InsertList[j][i]);
    插入成功返回true,否则false
    */
    public static boolean MysqlInsert(String Place,List InsertList,String Sqlsite){
        //jdbc驱动名以及数据库url
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://" + Sqlsite + "?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        boolean MysqlStatus = true;

        //数据库用户名、密码
        String USER = "root";
        String PASS = "";
        //数据库连接准备
        Connection conn = null;
        Statement stmt = null;

        String sql = "INSERT INTO " + Place + " VALUES (";
        int i=0,j=0;
        try{
            //注册JDBC驱动
            Class.forName(JDBC_DRIVER);
            //打开链接
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            int listrow = ((List)InsertList.get(0)).size();
            for(j = 0; j < listrow; ++j){
                if(j != 0){
                    sql += ",";
                }
                sql += "?";
            }
            sql += ");";
            System.out.println(sql);
            for(i = 0;i < listrow; ++i){
                PreparedStatement ps = conn.prepareStatement(sql);
                for(j = 0; j < InsertList.size(); ++j){
                    String str = (String)((List)InsertList.get(j)).get(i);
                    System.out.print(str + " ");
                    ps.setString(j+1,str);

                }
                System.out.println();
                int k = ps.executeUpdate();
                ps.close();
            }
            //stmt.close();
            conn.close();
        }
        catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
            MysqlStatus = false;
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
            MysqlStatus = false;
        }finally{
            // 关闭资源
            try{
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }


        return MysqlStatus;
    }


}

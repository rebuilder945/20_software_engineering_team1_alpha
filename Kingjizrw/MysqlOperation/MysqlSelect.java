package MysqlOperation;

import java.sql.*;
import com.alibaba.fastjson.*;
import java.util.*;

public class MysqlSelect {
    /*
        数据库查询
        传入参数Object不能含有除字母和逗号外的字符，包括空格
        Object:要查找的对象
        Table:要查找的表
        Requirement:查找对象的条件
        Sqlsite:Sql地址
        格式：SELECT Object FROM Table [WHERE Requirement]
        返回由json组成的List
     */
    public static List Select(String Object,String Table,String Requirement,String Sqlsite){
        //定义返回的json列表
        List reslist = new ArrayList();


        //jdbc驱动名以及数据库url
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://" + Sqlsite + "?useUnicode=true&characterEncoding=utf-8&useSSL=false";

        //数据库用户名、密码
        String USER = "root";
        String PASS = "";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            //注册JDBC驱动
            Class.forName(JDBC_DRIVER);
            //打开链接
            System.out.print("链接数据库");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT " + Object + " FROM " + Table;
            if (Requirement != ""){
                sql += " WHERE " + Requirement;
            }
            rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                JSONObject object = new JSONObject();
                // 通过字段检索
                List listst = new ArrayList();
                int i = 0, j  = 0;
                String s;
                for(i = 0; i < Object.length(); ++i){
                    if(Object.charAt(i) == ','){
                        listst.add(Object.substring(j,i));
                        j = i+1;
                    }
                }
                listst.add(Object.substring(j,i));
                for(i = 0; i < listst.size(); ++i){
                    s = (String)listst.get(i);
                    object.put((String)listst.get(i),rs.getString(s));
                }
                reslist.add(object);
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
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
        return reslist;
    }

}

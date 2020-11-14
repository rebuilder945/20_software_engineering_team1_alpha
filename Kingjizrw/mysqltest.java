

import java.sql.*;

public class mysqltest {
    //jdbc驱动名以及数据库url
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/vote_system?useUnicode=true&characterEncoding=utf-8&useSSL=false";

    //数据库用户名、密码
    static final String USER = "root";
    static final String PASS = "";
    public static void main(String[] args){
        Connection conn = null;
        Statement stmt = null;
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
            sql = "SELECT user_id, user_vote FROM account";
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                String id  = rs.getString("user_id");
                String vote = rs.getString("user_vote");

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 投票: " + vote);
                System.out.print("\n");
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
        System.out.println("Goodbye!");
    }

}

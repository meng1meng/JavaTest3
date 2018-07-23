package com.hand;

import java.sql.*;

public class App
{
    public static void main( String[] args )
    {
        Connection conn=null;
        //获取连接
        conn= ConnectionFactory.getInstance().makeConnmetion();
        select(conn);
        //conn.commit();
    }

    public static void select(Connection conn) {
        String selectSql = "SELECT country_id,city_id,city FROM city GROUP BY country_id";

        try {
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(selectSql);
            while(rs.next()){
                //打印数据库中的数据
                System.out.print(rs.getInt("country_id")+"  ");
                System.out.print(rs.getInt("city_id")+"  ");
                System.out.print(rs.getString("city")+"  ");
                System.out.println();
            }
            //清理资源
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}





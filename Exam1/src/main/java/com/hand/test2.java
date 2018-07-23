package com.hand;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test2 {
    public static void main() {
        Connection conn = null;

        //获取连接
        conn = ConnectionFactory.getInstance().makeConnmetion();
        select(conn);
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void select(Connection conn) {
        String selectSql = "SELECT film AS(SELECT customer_id FROM rental),rental_date FROM rental ORDER BY rental_date";

//        PreparedStatement ps = null;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectSql);
            while (rs.next()) {
                //打印数据库中的数据
                System.out.print(rs.getInt("film") + "  ");
                System.out.print(rs.getString("rental_date") + "  ");
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
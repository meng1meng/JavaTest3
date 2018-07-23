package com.hand;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static String driver;
    private static String dburl;
    private static String user;
    private static String password;
    //定义自定义的ConnectionFactory类的对象
    public static final ConnectionFactory factory=new ConnectionFactory();
    //保存连接的实例
    private Connection conn=null;
    //构造函数
    ConnectionFactory(){

    }

    //获取ConnectionFactory实例
    public static ConnectionFactory getInstance(){
        //使用单例模式，保证程序运行期间只有一个ConnectionFactory实例存在
        //static代码块只加载一次
        return factory;
    }

    //读取属性文件
    static {//使用静态代码块加载连接属性
        Properties prop=new Properties();
        //ClassLoader()类加载器
        InputStream in=ConnectionFactory.class.getClassLoader()//获取当前类的类加载器
                .getResourceAsStream("jdbc.properties");//使用类加载器的方法读取属性文件中的内容
        try {
            prop.load(in);//从输入流中读取属性列表，即键值对列表
        } catch (IOException e) {
            System.out.println("---------配置错误--------");
        }
        //属性值获取
        driver=prop.getProperty("driver");
        dburl=prop.getProperty("dburl");
        user=prop.getProperty("user");
        password=prop.getProperty("password");

    }


    //定义一个获取数据库连接的方法
    public Connection makeConnmetion(){
        try {
            Class.forName(driver);//驱动
            conn=DriverManager.getConnection(dburl,user,password);//获取连接
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
}

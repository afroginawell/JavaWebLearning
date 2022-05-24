package entity;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
/**
 * @author xie
 * @create 2022-05-20-15:20
 * function : linked database
 * all public methods are static
 */
public class DbUtil {
    private static final String URL = ""; //数据库路径
    private static final String USER = ""; //数据库用户
    private static final String PASSWORD = ""; //数据库密码

    public static Connection getConnection(){
        Connection conn = null;
        // 1.加载驱动程序
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 获得数据库连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}

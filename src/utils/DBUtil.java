package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.cj.callback.UsernameCallback;

import utils.DBUtil;

public class DBUtil {

	private static Statement stmt;
	private static Connection con;

	private static DBUtil utils = null;
	private static PreparedStatement pstmt; // 预编译语句对象

	
	public static Connection getConn() throws IOException, ClassNotFoundException, SQLException {
		try {

			if (con == null) {
				// 加载配置文件
				Properties props = new Properties();
				InputStream in = new FileInputStream("config/mysql.properties");
				props.load(in);
				in.close();

				// 获取配置参数
				String driver = props.getProperty("driver");
				String url = props.getProperty("url");
				String user = props.getProperty("username");
				String password = props.getProperty("password");

				// 加载驱动
				Class.forName(driver);

				// 建立数据库连接
				con = DriverManager.getConnection(url, user, password);
			}
			return con;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return con;
	}

	public static Statement getStatement() {
		if (stmt == null) {
			try {
				if (con == null) {
					con = getConn();
				}
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return stmt;
	}

	public static PreparedStatement getPstmt(String sql) {
		if (pstmt == null) {
			try {
				pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}

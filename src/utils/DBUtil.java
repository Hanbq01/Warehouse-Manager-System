package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.DBUtil;


/**
 * @author 韩炳琪 冯宇 邸腾
 *
 */
public class DBUtil {
	private static final String USER = "root";
	private static final String PWD = "root";
	private static final String URL = "jdbc:mysql://localhost:3306/warehouse?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
	private static Statement stmt;
	private static Connection con;

	private static DBUtil utils = null;
	private static PreparedStatement pstmt; // 预编译语句对象

	private DBUtil() {

	}

	public static Connection getConn() {
		if (con == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(URL, USER, PWD);
			} catch (Exception e) {
				e.printStackTrace();
			}
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

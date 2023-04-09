//package Dao;
//
//import java.sql.PreparedStatement;
//
//import utils.DBUtil;
//
//public class UserDao {
//	 private UserBean user;
//	    private PreparedStatement pstmt2; //预编译语句对象
//	    
//	    public UserBean findUser(String name,String Pwd) {
//	    	String sql = "select * from user where userName = ? AND userPWD = ?;";
//	    	pstmt2 = DBUtil.getPstmt(sql);
//	    	
//			return user;
//	    	
//	    }
//}

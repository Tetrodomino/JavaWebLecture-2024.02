package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import project.entity.User;

public class UserDao {
	public Connection getConnection() {
		Connection conn = null;
		
		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:comp/env/" + "jdbc/bbs"); // 링크에 맞는 DataSource 호출
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public User getUserByUid(String uid) {
		Connection conn = getConnection();
		String sql = "select * from users where uid=? and isDeleted=0";
		
		User u = null;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				u = new User(uid, rs.getString(2), rs.getString(3),
						rs.getString(4), LocalDate.parse(rs.getString(5)), rs.getInt(6));
			}
			pstmt.close();
			rs.close();
			conn.close();
			
			return u;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<User> getUserList(int num, int offset) {
		Connection conn = getConnection();
		String sql = "select * from users where isDeleted=0"
				+ " order by regDate desc, uid limit ? offset ?";
		
		List<User> list = new ArrayList<User>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, offset);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				User u = new User(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getString(4), LocalDate.parse(rs.getString(5)), rs.getInt(6));
				
				list.add(u);
			}
			
			pstmt.close();
			rs.close();
			conn.close();
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void insertUser(User user) {
		Connection conn = getConnection();
		String sql = "insert into users values(?, ?, ?, ?, default, default)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUid());
			pstmt.setString(2, user.getPwd());
			pstmt.setString(3, user.getUname());
			pstmt.setString(4, user.getEmail());
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateUser(User user) {
		Connection conn = getConnection();
		String sql = "update users set pwd=?, uname=?, email=? where uid=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getPwd());
			pstmt.setString(2, user.getUname());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getUid());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser(String uid) {
		Connection conn = getConnection();
		String sql = "update users set isDeleted=1 where uid=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getUserCount() {
		Connection conn = getConnection();
		String sql = "Select count(uid) from users where isDeleted=0";
		
		int count = 0;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				count = rs.getInt(1);
			}
			rs.close(); stmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
}

package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.User;
import util.DBConnectionUtil;
import util.DefineUtil;

public class UserDao {
	private Connection conn;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	public ArrayList<User> getItem() {
		ArrayList<User> list = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM users ORDER BY id DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				User objItem = new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("fullname"));
				list.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUtil.close(rs, st, conn);
		}
		
		return list;
	}
	
	public int NumberOfItem() {
		conn= DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) as count FROM users";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				int count = rs.getInt("count");
				return count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUtil.close(st, conn);
		}
		
		return 0;
	}
	
	public User getItemByUsernameAndPassword(String username, String password) {
		User item = null;
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM users WHERE username = ? && password = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if(rs.next()) {
				item = new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("fullname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		//chỉ cần trả về đối tượng của user
		return item;
		
	}
	
	public int addItem(User item) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql="INSERT INTO users(username, password, fullname) VALUES (?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getUsername());
			pst.setString(2, item.getPassword());
			pst.setString(3, item.getFullname());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
		
		return result;
	}
	
	public boolean hasUser(String username) {
		conn = DBConnectionUtil.getConnection();
		String sql="SELECT * FROM users WHERE username = ?";//được phép thêm vào khi chưa tồn tại
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		
		return false;
	}
	public User getItem(int id) {
		User item = null;
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM users WHERE id =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				item = new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("fullname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		//chỉ cần trả về đối tượng của user
		return item;
		
	}
	public int editItem(User item) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql="UPDATE users SET password = ?, fullname = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, item.getPassword());
			pst.setString(2, item.getFullname());
			pst.setInt(3, item.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
		
		return result;
	}
	public int delItem(int id) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql="DELETE FROM users WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}

	public User checkExit(String accountName) {
		User item = null;
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM users WHERE username = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, accountName);
			rs = pst.executeQuery();
			if(rs.next()) {
				item = new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("fullname"));
				return item;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return null;
	}

	public int getCountItems() {
		conn= DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) as count FROM users";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				int count = rs.getInt("count");
				return count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUtil.close(st, conn);
		}
		
		return 0;
	}

	public ArrayList<User> getItemsPagination(int offset) {
		ArrayList<User> list = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM users ORDER BY id DESC LIMIT ?, ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				User objItem = new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("fullname"));
				list.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		
		return list;
	}
}

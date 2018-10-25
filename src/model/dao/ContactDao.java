package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import model.bean.Contact;
import util.DBConnectionUtil;
import util.DefineUtil;

public class ContactDao {
	private Connection conn;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	public ArrayList<Contact> getItem() {
		ArrayList<Contact> item = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM contacts ORDER BY id DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Contact objItem = new Contact(rs.getInt("id"),rs.getString("name"),rs.getString("email"),rs.getString("website"),rs.getString("message"));
				item.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUtil.close(rs, st, conn);
		}
		
		return item;
	}
	
	public int NumberOfItem() {
		conn= DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) as count FROM contacts";
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
	
	public int delItem(int id) {
		int result = 0;
		conn= DBConnectionUtil.getConnection();
		String sql = "DELETE  FROM contacts WHERE id =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1,id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}
	public int addItem(Contact item) {
		int result = 0;
		conn= DBConnectionUtil.getConnection();
		String sql = "INSERT INTO contacts(name, email, website, message) VALUES (?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getName());
			pst.setString(2, item.getEmail());
			pst.setString(3, item.getWebsite());
			pst.setString(4, item.getMessage());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUtil.close(pst, conn);
		}
		return result;
	}

	public int getCountItems() {
		conn= DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) as count FROM contacts";
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

	public ArrayList<Contact> getItemsPagination(int offset) {
		ArrayList<Contact> item = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM contacts ORDER BY id DESC LIMIT ?, ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				Contact objItem = new Contact(rs.getInt("id"),rs.getString("name"),rs.getString("email"),rs.getString("website"),rs.getString("message"));
				item.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		
		return item;
	}
}

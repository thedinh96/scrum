package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import util.DBConnectionUtil;

public class catDao {
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement pst;
	
	public ArrayList<Category> getItems(){
		ArrayList<Category> listItem = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM categories ORDER BY id DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Category objItem = new Category(rs.getInt("id"),rs.getString("name"));
				listItem.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listItem;
	}

	public int addItem(Category objCat) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO categories(name) VALUES (?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objCat.getCatName());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUtil.close(pst, conn);
		}
		
		return result;
	}

	public int delItem(int catID) {
		int result = 0;
		conn= DBConnectionUtil.getConnection();
		String sql = "DELETE FROM categories WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, catID);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUtil.close(pst, conn);
		}
		
		return result;
	}

	public Category getItem(int catId) {
		Category objItem = null;
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM categories WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, catId);
			rs = pst.executeQuery();
			if(rs.next()) {
				objItem = new Category(rs.getInt("id"),rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUtil.close(pst, conn);
		}
		
		return objItem;
	}

	public int editItem(Category objCat) {
		int result = 0;
		conn= DBConnectionUtil.getConnection();
		String sql = "UPDATE categories SET name = ? WHERE id =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objCat.getCatName());
			pst.setInt(2, objCat.getCatId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionUtil.close(pst, conn);
		}
		
		return 0;
	}
}

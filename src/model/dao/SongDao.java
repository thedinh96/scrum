package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import model.bean.Song;
import util.DBConnectionUtil;
import util.DefineUtil;

public class SongDao {
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement pst;

	public ArrayList<Song> getItems() {
		ArrayList<Song> listItem = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT s.id, s.name AS songName, picture, preview_text, detail_text, date_create,counter,cat_id,c.name AS catName\r\n"
				+ "FROM songs AS s JOIN categories AS c ON s.cat_id = c.id ORDER BY s.id DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Song objItem = new Song(rs.getInt("id"), rs.getInt("counter"), rs.getString("songName"),
						rs.getString("picture"), rs.getString("preview_text"), rs.getString("detail_text"),
						rs.getTimestamp("date_create"), new Category(rs.getInt("cat_id"), rs.getString("catName")));
				listItem.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listItem;
	}

	public ArrayList<Song> getItems1(int n) {
		ArrayList<Song> listItem = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT s.id, s.name AS songName, picture, preview_text, detail_text, date_create,counter,cat_id,c.name AS catName\r\n"
				+ " FROM songs AS s JOIN categories AS c ON s.cat_id = c.id ORDER BY s.id DESC LIMIT ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, n);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song objItem = new Song(rs.getInt("id"), rs.getInt("counter"), rs.getString("songName"),
						rs.getString("picture"), rs.getString("preview_text"), rs.getString("detail_text"),
						rs.getTimestamp("date_create"), new Category(rs.getInt("cat_id"), rs.getString("catName")));
				listItem.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listItem;
	}
	
	public ArrayList<Song> getItems(String search) {
		ArrayList<Song> listItem = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM songs WHERE name LIKE '%?%' || preview_text LIKE '%?%' || detail_text LIKE '%?%' ORDER BY id DESC";
		try { // WHERE songName LIKE '%?%' || catName LIKE '%?%'
			pst = conn.prepareStatement(sql);
			pst.setString(1, search);
			pst.setString(2, search);
			pst.setString(3, search);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song objItem = new Song(rs.getInt("id"), rs.getInt("counter"), rs.getString("name"), rs.getString("picture"),
						rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("date_create"),
						new Category(rs.getInt("cat_id"), null));
				listItem.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listItem;
	}

	public int addItem(Song objSong) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO songs(name,preview_text,detail_text,picture,cat_id) VALUES (?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objSong.getSongName());
			pst.setString(2, objSong.getDescription());
			pst.setString(3, objSong.getDetail());
			pst.setString(4, objSong.getPicture());
			pst.setInt(5, objSong.getObjCat().getCatId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}

		return result;
	}

	public int NumberOfItem() {
		conn= DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) as count FROM songs";
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
	
	public int delItem(int songId) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM songs WHERE id=? ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, songId);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
		return songId;
	}

	public Song getItem(int songId) {
		Song objItem = null;
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM songs WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, songId);
			rs = pst.executeQuery();
			if (rs.next()) {
				objItem = new Song(rs.getInt("id"), rs.getInt("counter"), rs.getString("name"), rs.getString("picture"),
						rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("date_create"),
						new Category(rs.getInt("cat_id"), null));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}

		return objItem;
	}

	public int editItem(Category objCat) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "UPDATE categories SET name = ? WHERE id =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objCat.getCatName());
			pst.setInt(2, objCat.getCatId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}

		return 0;
	}

	public Song getItems(int id) {
		Song item = null;
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT id, name, picture, preview_text, detail_text, date_create,counter,cat_id"
				+ " FROM songs WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				item = new Song(rs.getInt("id"), rs.getInt("counter"), rs.getString("name"), rs.getString("picture"),
						rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("date_create"),
						new Category(rs.getInt("cat_id"), null));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return item;
	}

	public int editItem(Song objSong) {
		int re = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "UPDATE songs SET name = ?, preview_text = ?, detail_text = ?, picture = ?, cat_id = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objSong.getSongName());
			pst.setString(2, objSong.getDescription());
			pst.setString(3, objSong.getDetail());
			pst.setString(4, objSong.getPicture());
			pst.setInt(5, objSong.getObjCat().getCatId());
			pst.setInt(6, objSong.getSongId());
			re = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}
		return re;
	}

	public ArrayList<Song> getItemsById(int cid) {
		ArrayList<Song> listSongById = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM songs WHERE cat_id = ? ORDER BY id DESC";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song objItem = new Song(rs.getInt("id"), rs.getInt("counter"), rs.getString("name"), rs.getString("picture"),
						rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("date_create"),
						new Category(rs.getInt("cat_id"), null));
				listSongById.add(objItem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listSongById;
	}

	public Song getItemsById1(int sid) {
		Song objSong = null;
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM songs WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, sid);
			rs = pst.executeQuery();
			if (rs.next()) {
				objSong = new Song(rs.getInt("id"), rs.getInt("counter"), rs.getString("name"), rs.getString("picture"),
						rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("date_create"),
						new Category(rs.getInt("cat_id"), null));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return objSong;
	}

	public ArrayList<Song> getRelatedItems(Song objSong, int num) {
		ArrayList<Song> listItem = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM songs WHERE cat_id = ? && id != ? ORDER BY id DESC LIMIT ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, objSong.getObjCat().getCatId());
			pst.setInt(2, objSong.getSongId());
			pst.setInt(3, num);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song objItem = new Song(rs.getInt("id"), rs.getInt("counter"), rs.getString("name"), rs.getString("picture"),
						rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("date_create"),
						new Category(rs.getInt("cat_id"), null));
				listItem.add(objItem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listItem;
	}

	public void increaseView(int sid) {
		conn = DBConnectionUtil.getConnection();
		String sql = "UPDATE songs SET counter = counter + 1 WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, sid);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst, conn);
		}

	}

	public int getCountItems() {
		int count=0;
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM songs";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				count = rs.getInt("count");
			}
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return 0;
	}

	public ArrayList<Song> getItemsPagination(int offset) {
		ArrayList<Song> listItem = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT s.id, s.name AS songName, picture, preview_text, detail_text, date_create,counter,cat_id,c.name AS catName\r\n"
				+ " FROM songs AS s JOIN categories AS c ON s.cat_id = c.id ORDER BY s.id DESC LIMIT ?, ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song objItem = new Song(rs.getInt("id"), rs.getInt("counter"), rs.getString("songName"),
						rs.getString("picture"), rs.getString("preview_text"), rs.getString("detail_text"),
						rs.getTimestamp("date_create"), new Category(rs.getInt("cat_id"), rs.getString("catName")));
				listItem.add(objItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listItem;
	}

	public int getCountItems(int cid) {
		int count =0;
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM songs WHERE cat_id = ? ORDER BY id DESC";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			rs = pst.executeQuery();
			while (rs.next()) {
				count=rs.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return count;
	}

	public ArrayList<Song> getItemsPagination(int cid, int offset) {
		ArrayList<Song> listSongById = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM songs WHERE cat_id = ? ORDER BY id DESC LIMIT ?, ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			pst.setInt(2, offset);
			pst.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song objItem = new Song(rs.getInt("id"), rs.getInt("counter"), rs.getString("name"), rs.getString("picture"),
						rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("date_create"),
						new Category(rs.getInt("cat_id"), null));
				listSongById.add(objItem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs, pst, conn);
		}
		return listSongById;
	}

}

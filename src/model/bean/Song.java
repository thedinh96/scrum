package model.bean;

import java.sql.Timestamp;

public class Song {
	private int songId;
	private int couter;
	private String songName;
	private String picture;
	private String description;
	private String detail;
	private Timestamp date_create;
	
	/*private int catId;
	private String catName;*/
	private Category objCat;
	
	public int getSongId() {
		return songId;
	}
	public void setSongId(int songId) {
		this.songId = songId;
	}
	public int getCouter() {
		return couter;
	}
	public void setCouter(int couter) {
		this.couter = couter;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Timestamp getDate_create() {
		return date_create;
	}
	public void setDate_create(Timestamp date_create) {
		this.date_create = date_create;
	}
	public Category getObjCat() {
		return objCat;
	}
	public void setObjCat(Category objCat) {
		this.objCat = objCat;
	}
	public Song() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Song(int songId, int couter, String songName, String description, String detail, Timestamp date_create,
			Category objCat) {
		super();
		this.songId = songId;
		this.couter = couter;
		this.songName = songName;
		this.description = description;
		this.detail = detail;
		this.date_create = date_create;
		this.objCat = objCat;
	}
	public Song(int songId, int couter, String songName, String picture, String description, String detail,
			Timestamp date_create, Category objCat) {
		super();
		this.songId = songId;
		this.couter = couter;
		this.songName = songName;
		this.picture = picture;
		this.description = description;
		this.detail = detail;
		this.date_create = date_create;
		this.objCat = objCat;
	}
}
/*Song
	+Thêm/sửa/xóa(xử lí file)
	+users(video)*/
/*------------------------
chức năng mới:
	tìm kiếm admin, public*/
	
package model.bean;

public class Contact {
	private int ID;
	private String Name;
	private String Email;
	private String Website;
	private String Message;
	public Contact() {
		super();
	}
	public Contact(int iD, String name, String email, String website, String message) {
		super();
		ID = iD;
		Name = name;
		Email = email;
		Website = website;
		Message = message;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getWebsite() {
		return Website;
	}
	public void setWebsite(String website) {
		Website = website;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	
}

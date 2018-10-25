package util;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class MessageUtil {
	public static String getMessage(HttpServletRequest request) {
		String msgText = "";
		if(request.getParameter("msg") != null){
			int msg = Integer.valueOf(request.getParameter("msg"));
			switch(msg){
			case 1: msgText ="Thêm Thành Công"; break;
			case 2: msgText ="Sửa Thành Công"; break;
			case 3: msgText ="Xóa Thành Công"; break;
			case 0: msgText ="Có lỗi trong quá trình thực hiện"; break;
			}
		}
	return msgText;
	}
	
}

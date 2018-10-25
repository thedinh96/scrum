package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.dao.UserDao;
import util.AuthUtil;
import util.StringUtil;

public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminAddUserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		
		HttpSession session = request.getSession();
		User objUser = (User) session.getAttribute("userInfo");
		
		if(!"admin".equals(objUser.getUsername())) {
			response.sendRedirect(request.getContextPath()+"/admin/user/index?err=4");
			return;
		}
		
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rs = request.getRequestDispatcher("/admin/user/add.jsp");
		rs.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		User objUser = (User) session.getAttribute("userInfo");
		
		if(!"admin".equals(objUser.getUsername())) {
			response.sendRedirect(request.getContextPath()+"/admin/user/index?err=4");
			return;
		}
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		UserDao userDao = new UserDao();
		//kiểm tra đã có tên trùng chưa?
		if(userDao.hasUser(username)) {
			RequestDispatcher rs = request.getRequestDispatcher("/admin/user/add.jsp?err=2");
			rs.forward(request, response);
		}
		password = StringUtil.md5(password);
		
		//tạo đối tượng
		
		User item = new User(0, username, password, fullname);
		if(userDao.addItem(item)>0) {
			//thành công
			response.sendRedirect(request.getContextPath()+"/admin/user/index?msg=1");
		} else {
			//lấy lại dữ liệu người nhập vào trong form
			//thất bại
			RequestDispatcher rs = request.getRequestDispatcher("/admin/user/add.jsp?err=1");
			rs.forward(request, response);
		}
	}

}

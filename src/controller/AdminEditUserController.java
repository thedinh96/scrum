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

public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminEditUserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));//lấy ra thông tin của người dùng cần sửa
		}catch(NumberFormatException e){
			//nếu ko hợp lệ sẽ chuyển đến trang danh sách người dùng và thêm 1 lỗi nào đó
			response.sendRedirect(request.getContextPath()+"/admin/user/index?err=2");
			return;
		}
		
		HttpSession session = request.getSession();
		User objUser = (User) session.getAttribute("userInfo");
		UserDao userDao = new UserDao();
		
		if("admin".equals(userDao.getItem(objUser.getId()).getUsername())||id==objUser.getId()) {
			User item = userDao.getItem(id);
				if(item!=null) {
					request.setAttribute("user", item);
					RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp");
					rd.forward(request, response);
				} else {
					response.sendRedirect(request.getContextPath()+"/admin/user/index?err=2");
					return;
				}
		} else {
			response.sendRedirect(request.getContextPath()+"/admin/user/index?err=5");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		}catch(NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/admin/user/edit?err=2");
			return;
		}
		
		HttpSession session = request.getSession();
		User objUser = (User) session.getAttribute("userInfo");
		UserDao userDao = new UserDao();
		
		if("admin".equals(userDao.getItem(objUser.getId()).getUsername())||id==objUser.getId()) {
			//lấy lại dữ liệu cũ
			User item = userDao.getItem(id);
			if(item == null) {
				response.sendRedirect(request.getContextPath()+"/admin/user/edit?err=2");
				return;
				}
			String password = request.getParameter("password");
			
			if("".equals(password)) {
				password = item.getPassword();
			}else {
				password = StringUtil.md5(password);
			}
			String fullname = request.getParameter("fullname");
			//du lieu moi
			item.setFullname(fullname);
			item.setPassword(password);
			
			if(userDao.editItem(item)>0) {
				//thanh cong
				response.sendRedirect(request.getContextPath()+"/admin/user/index?msg=2");
				return;
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp?err=1");
				rd.forward(request, response);
				return;
			}
		} else {
			response.sendRedirect(request.getContextPath()+"/admin/user/index?err=5");
			return;
		}
		
	}

}
